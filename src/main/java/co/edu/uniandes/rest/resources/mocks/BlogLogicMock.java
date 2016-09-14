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
            blogs.add(new BlogDTO("nombre", 23424L, "Aqui debe ir la reseña", new Date(242342424), "Juan Sebastian", 34234L, 4324L));
        }
        
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info(
                "Inicializa la lista de blogs");
        logger.info(
                "Blogs: " + blogs);
    }
    
    public List<BlogDTO> getBlogs() throws BibliotecaLogicException {
        if (blogs == null) {
            logger.severe("Error interno: lista de blogs no existe.");
            throw new BibliotecaLogicException("Error interno: lista de blogs no existe.");
        }

        logger.info("retornando todos los blogs");
        return blogs;
    }
    
    public BlogDTO getBlog(Long pId) throws BibliotecaLogicException
     {
         if (blogs == null) {
            logger.severe("Error interno: lista de blogs no existe.");
            throw new BibliotecaLogicException("Error interno: lista de blogs no existe.");
        }
        logger.info("buscando blogs");
        for (BlogDTO blog : blogs) {
            if (blog.getIdBlog().equals(pId)) {
                logger.info("blog encontrado");
                return blog;
            }
        }
        logger.severe("No se encontró el blog");
        throw new BibliotecaLogicException("blog no encontrado");
         
         
     }
    
    public BlogDTO createBlog (BlogDTO pBlog) throws BibliotecaLogicException
    {
        logger.info("recibiendo solicitud de agregar blog " + pBlog);
    	
    	
    	if ( pBlog.getIdBlog() != null ) {
	    	
	        for (BlogDTO blog : blogs) {
	        
	            if (blog.getIdBlog().equals(pBlog.getIdBlog())){
	            	logger.severe("Ya existe un Blog con ese id");
                        throw new BibliotecaLogicException("Ya existe un Blog con ese id");
                    }
	        }
	        
    	} else {

    		logger.info("Generando id para el nuevo Blog");
    		long id = 1;
	        for (BlogDTO blog : blogs) {
	            if (id <= blog.getIdBlog()){
	                id =  blog.getIdBlog() + 1;
	            }
	        }
	        pBlog.setIdBlog(id);
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

