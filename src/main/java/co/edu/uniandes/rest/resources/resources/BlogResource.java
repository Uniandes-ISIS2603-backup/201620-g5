/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.BlogDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.BlogLogicMock;
import co.edu.uniandes.rest.resources.mocks.PrestamoLogicMock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author js.prieto10
 */
    
@Path("")
@Produces("application/json")
public class BlogResource
{
    
    BlogLogicMock BlogLogic = new BlogLogicMock();
    
    
    
    @GET
    @Path("blogs")
    public List<BlogDTO> getBlogs() throws BibliotecaLogicException 
    {
        return BlogLogic.getBlogs();
    }
    
    @GET
    @Path("blogs/{id: \\d+}/")
    public BlogDTO getBlog(@PathParam("id") Long id) throws BibliotecaLogicException 
    {
        return BlogLogic.getBlog(id);
    }
    @GET
    @Path("libros/{libro: \\d+}/blogs/{id: \\d+}")
    public BlogDTO getBlogLibro(@PathParam("idLibro") Long idLibro, @PathParam("id") Long id) throws BibliotecaLogicException
    {
        return BlogLogic.getBlogLibro(idLibro, id);
    }
    
    
    @GET
    @Path("libros/{idLibro: \\d+}/blogs")
    public List<BlogDTO> getBlogsLibro(@PathParam("idLibro") Long idLibro) throws BibliotecaLogicException
    {
        return BlogLogic.getBlogsLibro(idLibro);
    }
    @POST
    @Path("libros/{idLibro: \\d+}/blogs")
    public BlogDTO createLibro(BlogDTO blog, @PathParam("idLibro") Long libro) throws BibliotecaLogicException {
            return BlogLogic.createBlog(blog, libro);
    }
    @PUT
    @Path("libros/{idLibro: \\d+}/blogs/{id: \\d+}")
    public BlogDTO updateBlog(@PathParam("id") Long id, BlogDTO blog) throws BibliotecaLogicException {
        return BlogLogic.updateBlog(id, blog);
    }
    
}
