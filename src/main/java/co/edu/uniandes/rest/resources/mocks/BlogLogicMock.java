/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.mocks;

import co.edu.uniandes.rest.resources.dtos.BlogDTO;
import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.ParserException;

/**
 *
 * @author js.prieto10
 */
public class BlogLogicMock {
 
    private final static Logger logger = Logger.getLogger(SalaLogicMock.class.getName());
    
    private static ArrayList<BlogDTO> blogs;
    
    public BlogLogicMock() 
    {
        if (blogs == null)
        {
            try
            {
            String fecha1 = "2016-09-1";
            String fecha2 = "2016-09-31";
            String fecha3 = "2016-11-25";
            String fecha4 = "2016-01-25";
            String fecha5 = "2010-02-25";
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            try
            {
            blogs = new ArrayList<>();
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 1L, "Aqui debe ir la reseña", "Juan Sebastian", formater.parse(fecha1), 1L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 1L, "Aqui debe ir la reseña", "Juan Sebastian", formater.parse(fecha2),2L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 2L, "Aqui debe ir la reseña", "Juan Sebastian", formater.parse(fecha3),3L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", formater.parse(fecha4),4L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", formater.parse(fecha5),5L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", formater.parse(fecha2),6L));
            }
            catch(ParserException e)
            {
                Logger.getLogger(BlogLogicMock.class.getName()).log(Level.SEVERE, null, e);
            }
            }
            catch (Exception e)
            {
                Logger.getLogger(BlogLogicMock.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
       
    } 
    
    public ArrayList<BlogDTO> getBlogsLibro(Long idLibro) throws BibliotecaLogicException
    {
        ArrayList<BlogDTO> a = new ArrayList<>();
        if (blogs == null)
        {
            logger.severe("Error");
            throw new BibliotecaLogicException("Error interno: lista de blogs no existe.");
        }
        
        for (BlogDTO m : blogs) {
            if (idLibro == m.getIdLibro()) {
                a.add(m);
            }
        }
            return a;
    }
    
    public List<BlogDTO> getBlogs() throws BibliotecaLogicException {
        if (blogs == null) {
            logger.severe("Error interno: lista de blogs no existe.");
            throw new BibliotecaLogicException("Error interno: lista de blogs no existe.");
        }

        logger.info("retornando todos los blogs");
        return blogs;
    }
    
    public BlogDTO getBlog(Long id) throws BibliotecaLogicException
     {
         if (blogs == null) {
            logger.severe("Error interno: lista de blogs no existe.");
            throw new BibliotecaLogicException("Error interno: lista de blogs no existe.");
        }
        logger.info("buscando blogs");
        for (BlogDTO b : blogs) {
            if (b.getId() == id) {
                logger.info("blog encontrado");
                return b;
            }
        }
        logger.severe("No se encontró el blog");
        throw new BibliotecaLogicException("blog no encontrado");
         
         
     }
    
    public BlogDTO getBlogLibro(Long idLibro, Long id) throws BibliotecaLogicException {
        /*BlogDTO m = null;
        List<BlogDTO> blogsLibro = getBlogsLibro(idLibro);
        for (int i = 0; i < blogsLibro.size() && m == null; i++) {
            BlogDTO blog = blogsLibro.get(i);
            if (blog.getId() == id) {
                m = blog;
            }
        }
        if (m == null) {
            logger.severe("No existe una blog con ese id");
            throw new BibliotecaLogicException("No existe una blog con ese id");
        }
        return m;*/
        return getBlog(id);
    }
    
    
    
    
    
    public BlogDTO createBlog(BlogDTO newBlog, Long idLibro) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de agregar blog " + newBlog);
        // agrega el blog
        // el nuevo blog tiene id ?
        if (newBlog.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (BlogDTO blog : blogs) {
                // si existe una sala con ese id
                if (blog.getId() == newBlog.getId()) {
                    logger.severe("Ya existe un blog con ese id");
                    throw new BibliotecaLogicException("Ya existe un blog con ese id");
                }
            }

            // el nuevo blog no tiene id ? 
        } else {

            // genera un id para la sala
            logger.info("Generando id para el nuevo blog");
            logger.info("Generando idUsuario para el nuevo blog");
            long newId = 1;
            for (BlogDTO blog : blogs) {
                if (newId <= blog.getId()) {
                    newId = blog.getId() + 1;
                }
            }
            newBlog.setId(newId);
            newBlog.setIdLibro(idLibro);
        }
        logger.info("agregando blog: " + newBlog);
        blogs.add(newBlog);
        return newBlog;
    }
    
    /**
     *
     * @param pId
     * @param pTexto
     * @param pFecha
     * @return
     * @throws Exception
     */
    public BlogDTO updateBlog(Long id, BlogDTO b ) throws BibliotecaLogicException
    {
       BlogDTO blog = getBlog(id);
       if (blog != null)
       {
           blog.setTitulo(b.getTitulo());
           blog.setTexto(b.getTexto());
           blog.setNombreAutor(b.getNombreAutor());
           blog.setNombre(b.getNombre());
           blog.setFecha(b.getFecha());
           return blog;
       }
            logger.severe("No existe un blog con ese id");
            throw new BibliotecaLogicException("No existe un blog con ese id");
       
    }
    
    
    public BlogDTO createBlogLibro(BlogDTO newBlog, long idLibro)  {
        logger.info("recibiendo solicitud de agregar multa " + newBlog);
        // agrega la multa
        logger.info("agregando multa: " + newBlog);
        newBlog.setIdLibro(idLibro);
        blogs.add(newBlog);
        return newBlog;
    }
    
    
    
    
    
    
    public String prueba1()
    {
        return "pfokdfpo";
    }
    
}

