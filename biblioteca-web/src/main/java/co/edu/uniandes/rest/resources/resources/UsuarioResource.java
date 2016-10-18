/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.resources;


import co.edu.uniandes.rest.resources.dtos.UsuarioDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.UsuarioLogicMock;
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
public class UsuarioResource {
    

    UsuarioLogicMock usuarioLogic = new UsuarioLogicMock();

    @GET
    @Path("usuarios")
    public List<UsuarioDTO> getUsuarios() throws BibliotecaLogicException {
        return usuarioLogic.getUsuarios();
    }
    
    @GET
    @Path("usuarios/{id: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("id") Long id) throws BibliotecaLogicException{
        return usuarioLogic.getUsuario(id);
    }


   
    @POST
    @Path("usuarios")
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BibliotecaLogicException {
        return usuarioLogic.createUsuario(usuario);
    }
    

    @PUT
    @Path("usuarios/{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO newUsuario) throws BibliotecaLogicException 
    {
        return usuarioLogic.updateUsuario(id, newUsuario);
    }
    

    @DELETE
    @Path("usuarios/{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws BibliotecaLogicException 
    {
        usuarioLogic.deleteUsuario(id);
    }
}
