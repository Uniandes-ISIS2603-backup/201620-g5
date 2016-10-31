/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.api.IBlogLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.BlogDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.BlogDetailDTO;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author js.prieto10
 */
    
@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BlogResource
{
    
    @Inject
    private IBlogLogic blogLogic;
    
    
    private List<BlogDetailDTO> listEntity2DTO(List<BlogEntity> entityList) {
        List<BlogDetailDTO> list = new ArrayList<>();
        for (BlogEntity entity : entityList) {
            list.add(new BlogDetailDTO(entity));
        }
        return list;
    } 
    
    
    
    @GET
    public List<BlogDetailDTO> getBlogs() throws BibliotecaLogicException 
    {
        return listEntity2DTO(blogLogic.getBlogs());
    }
    
    @GET
    @Path("blogs/{id: \\d+}/")
    public BlogDetailDTO getBlog(@PathParam("id") Long id) throws BibliotecaLogicException 
    {
        return new BlogDetailDTO(blogLogic.getBlog(id));
    }
    @GET
    @Path("libros/{idlibro: \\d+}/blogs/{id: \\d+}")
    public BlogDetailDTO getBlogLibro(@PathParam("id") Long id) throws BibliotecaLogicException
    {
        return new BlogDetailDTO(blogLogic.getBlog(id));
    }
    
    
    @GET
    @Path("libros/{idLibro: \\d+}/blogs")
    public List<BlogDetailDTO> getBlogsLibro(@PathParam("idLibro") Long idLibro) throws BibliotecaLogicException
    {
        return listEntity2DTO(blogLogic.getBlogsLibro(idLibro));
    }
    @POST
    @Path("libros/{idLibro: \\d+}/blogs")
    public BlogDetailDTO createLibro(BlogDetailDTO blog) throws BibliotecaLogicException {
            return new BlogDetailDTO(blogLogic.createBlog(blog.toEntity()));
    }
    @PUT
    @Path("libros/{idLibro: \\d+}/blogs/{id: \\d+}")
    public BlogDTO updateBlog(@PathParam("idLibro") Long id, BlogDTO blog) throws BibliotecaLogicException {
        BlogEntity entity = blog.toEntity();
        entity.setId(id);
        return new BlogDetailDTO(blogLogic.updateBlog(entity));
    }
    
}
