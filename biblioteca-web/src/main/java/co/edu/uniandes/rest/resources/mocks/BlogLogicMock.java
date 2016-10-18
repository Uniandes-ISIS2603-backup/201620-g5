/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.mocks;

import co.edu.uniandes.rest.resources.dtos.BlogDTO;
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
 
    private final static Logger LOGGER = Logger.getLogger(SalaLogicMock.class.getName());
    
    private static ArrayList<BlogDTO> blogs;
    
    public BlogLogicMock() 
    {
        if (blogs == null)
        {
            try
            {
            blogs = new ArrayList<>();
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 1L, "Aqui debe ir la reseña", "Juan Sebastian", 1L)); 
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 1L, "Aqui debe ir la reseña", "Juan Sebastian", 2L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 2L, "Aqui debe ir la reseña", "Juan Sebastian", 3L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", 4L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", 5L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", 6L));
            }
            catch(ParserException e)
            {
                Logger.getLogger(BlogLogicMock.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        
       
    } 
    
    public List<BlogDTO> getBlogsLibro(Long idLibro) throws BibliotecaLogicException
    {
        ArrayList<BlogDTO> a = new ArrayList<>();
        if (blogs == null)
        {
            LOGGER.severe("Error");
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
            LOGGER.severe("Error interno: lista de blogs no existe.");
            throw new BibliotecaLogicException("Error interno: lista de blogs no existe.");
        }

        LOGGER.info("retornando todos los blogs");
        return blogs;
    }
    
    public BlogDTO getBlog(Long id) throws BibliotecaLogicException
     {
         if (blogs == null) {
            LOGGER.severe("Error interno: lista de blogs no existe.");
            throw new BibliotecaLogicException("Error interno: lista de blogs no existe.");
        }
        LOGGER.info("buscando blogs");
        for (BlogDTO b : blogs) {
            if (b.getId() == id) {
                LOGGER.info("blog encontrado");
                return b;
            }
        }
        LOGGER.severe("No se encontró el blog");
        throw new BibliotecaLogicException("blog no encontrado");
         
         
     }
    
    public BlogDTO getBlogLibro(Long idLibro, Long id) throws BibliotecaLogicException {
        return getBlog(id);
    }
    
    
    
    
    
    public BlogDTO createBlog(BlogDTO newBlog, Long idLibro) throws BibliotecaLogicException {
        LOGGER.info("recibiendo solicitud de agregar blog " + newBlog);
        // agrega el blog
        // el nuevo blog tiene id ?
        if (newBlog.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (BlogDTO blog : blogs) {
                // si existe una sala con ese id
                if (blog.getId() == newBlog.getId()) {
                    LOGGER.severe("Ya existe un blog con ese id");
                    throw new BibliotecaLogicException("Ya existe un blog con ese id");
                }
            }

            // el nuevo blog no tiene id ? 
        } else {

            // genera un id para la sala
            LOGGER.info("Generando id para el nuevo blog");
            LOGGER.info("Generando idUsuario para el nuevo blog");
            long newId = 1;
            for (BlogDTO blog : blogs) {
                if (newId <= blog.getId()) {
                    newId = blog.getId() + 1;
                }
            }
            newBlog.setId(newId);
            newBlog.setIdLibro(idLibro);
        }
        LOGGER.info("agregando blog: " + newBlog);
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
           return blog;
       }
            LOGGER.severe("No existe un blog con ese id");
            throw new BibliotecaLogicException("No existe un blog con ese id");
       
    }
    
    
    public BlogDTO createBlogLibro(BlogDTO newBlog, long idLibro)  {
        LOGGER.info("recibiendo solicitud de agregar multa " + newBlog);
        // agrega la multa
        LOGGER.info("agregando multa: " + newBlog);
        newBlog.setIdLibro(idLibro);
        blogs.add(newBlog);
        return newBlog;
    }
    
}

