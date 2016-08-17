/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.mocks.LibroLogicMock;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;

/**
 *
 * @author s.rojas19
 */
@Path("libros")
@Produces("application/json")
public class LibroResource {

    LibroLogicMock libroLogic = new LibroLogicMock();

    @GET
    public List<LibroDTO> getLibros() throws BibliotecaLogicException {
        return libroLogic.getLibros();
    }

    @GET
    @Path("{id: \\d+}")
    public LibroDTO getLibro(@PathParam("id") long id) throws BibliotecaLogicException {
        return libroLogic.getLibro(id);
    }

    @POST
    public LibroDTO createLibro(LibroDTO libro) throws BibliotecaLogicException{
        return libroLogic.createLibro(libro);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public LibroDTO updateLibro(@PathParam("id") long id, LibroDTO libro) throws BibliotecaLogicException{
        return libroLogic.updateLibro(id, libro);
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLibro(@PathParam("id") long id){
        libroLogic.deleteLibro(id);
    }

}
