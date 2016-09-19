/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.mocks;

import co.edu.uniandes.rest.resources.dtos.BlogDTO;
import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            blogs = new ArrayList<>();
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 1L, "Aqui debe ir la reseña", "Juan Sebastian", 1L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 1L, "Aqui debe ir la reseña", "Juan Sebastian", 2L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 2L, "Aqui debe ir la reseña", "Juan Sebastian", 3L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", 4L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", 5L));
            blogs.add(new BlogDTO("Titulo libro", "nombreReseña", 3L, "Aqui debe ir la reseña", "Juan Sebastian", 6L));
        }
        
       
    }
    
    
    public String prueba1()
    {
        return "Probando";
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
    
    public BlogDTO createBlog (BlogDTO pBlog) throws BibliotecaLogicException
    {
        logger.info("recibiendo solicitud de agregar blog " + pBlog);
    	
    	
    	if ( pBlog.getId() != null ) {
	    	
	        for (BlogDTO blog : blogs) {
	        
	            if (blog.getId().equals(pBlog.getId())){
	            	logger.severe("Ya existe un Blog con ese id");
                        throw new BibliotecaLogicException("Ya existe un Blog con ese id");
                    }
	        }
	        
    	} else {

    		logger.info("Generando id para el nuevo Blog");
    		long id = 1;
	        for (BlogDTO blog : blogs) {
	            if (id <= blog.getId()){
	                id =  blog.getId() + 1;
	            }
	        }
	        pBlog.setId(id);
    	}
    	
        logger.info("agregando Blog" + pBlog);
        blogs.add(pBlog);
        return pBlog;
    }
    
    /**
     *
     * @param pId
     * @param pTexto
     * @param pFecha
     * @return
     * @throws Exception
     */
    public BlogDTO updateBlog(Long pId, String pTexto) throws BibliotecaLogicException
    {
    
    try
    {
        BlogDTO blog = getBlog(pId);
        blog.setTexto(pTexto);
        logger.info("Libro actualizado");
        return blog;
        } 
    catch(Exception e) 
        {
            logger.severe("No existe una sala con ese id");
            throw new BibliotecaLogicException("No existe una sala con ese id");
        }
    } 
    
}

