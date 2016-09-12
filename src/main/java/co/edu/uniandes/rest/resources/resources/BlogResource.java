/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.BlogDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.BlogLogicMock;
import java.util.Date;
import java.util.List;
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
    
@Path("blogs")
@Produces("application/json")
public class BlogResource
{
    
    BlogLogicMock BlogLogic = new BlogLogicMock();
    
    
    @GET
    public List<BlogDTO> getBlogs() throws BiblioLogicException, BibliotecaLogicException 
    {
        return BlogLogic.getBlogs();
    }
    
    @GET
    @Path("{id: \\d+}")
    public BlogDTO getBlog(@PathParam("id") Long id) throws BibliotecaLogicException 
    {
        return BlogLogic.getBlog(id);
    }
    
    @POST
    public BlogDTO createLibro(BlogDTO blog) throws BibliotecaLogicException {
        return BlogLogic.createBlog(blog);
    }
   
    @PUT
    @Path("{id: \\d+}")
    public BlogDTO updateBlog(@PathParam("id") Long id, String texto, Date fecha) throws BibliotecaLogicException {
        return BlogLogic.updateBlog(id, texto, fecha);
    }
}
