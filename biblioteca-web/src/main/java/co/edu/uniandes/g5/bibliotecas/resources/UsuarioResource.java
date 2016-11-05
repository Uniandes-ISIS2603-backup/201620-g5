/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.resources;


import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.UsuarioDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author js.prieto10
 */

@Path("")
@Produces("application/json")
public class UsuarioResource {
    

     @Inject
    private IUsuarioLogic usuarioLogic;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;

    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

    private List<UsuarioDTO> listEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDTO(entity));
        }
        return list;
    }

    public void existsBiblioteca(Long bibliotecaId) {
        BiblioDetailDTO biblioteca = new BiblioDetailDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsUsuario(Long usuarioId) {
        UsuarioDetailDTO usuario = new UsuarioDetailDTO(usuarioLogic.getUsuario(usuarioId));
        if (usuario == null) {
            throw new WebApplicationException("El usuario no existe", 404);
        }
    }

    /**
     * Obtiene el listado de usuarios.
     *
     * @return lista de usuarios
     * @throws BibliotecaLogicException retornada por logica.
     */
    @GET
    @Path("usuarios")
    public List<UsuarioDTO> getUsuarios() {
        List<UsuarioEntity> usuarios = usuarioLogic.getUsuarios();
        return listEntity2DTO(usuarios);
    }

    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/usuarios")
    public List<UsuarioDTO> getUsuariosBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) {
        List<UsuarioEntity> usuarios = usuarioLogic.getUsuariosByBiblioteca(idBiblioteca);
        return listEntity2DTO(usuarios);
    }

    /**
     * Obtiene el usuario con id por parametro
     *
     * @param id id del usuario a retornar
     * @return Usuario con id dado por parametro
     */
    @GET
    @Path("usuarios/{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) {
        UsuarioEntity resultado = usuarioLogic.getUsuario(id);
        if (resultado == null) {
            throw new WebApplicationException(404);
        }
        return new UsuarioDetailDTO(resultado);
    }

    /**
     * Crea un usuario
     *
     * @param usuario usuario a agregar
     * @return libtro agregado
     */
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("bibliotecas/{idBiblioteca: \\d+}/usuarios")
    public UsuarioDTO createUsuario(UsuarioDetailDTO usuario, @PathParam("idBiblioteca")Long idBiblioteca) {
        try {
            UsuarioEntity entity = usuario.toEntity();
            UsuarioEntity respuesta = usuarioLogic.createUsuario(entity, idBiblioteca);
            return new UsuarioDetailDTO(respuesta);
        } catch (BibliotecaLogicException e) {
            throw new WebApplicationException(404);
        }
    }
    

    /**
     * Actualiza un usuario
     *
     * @param id id del usuario a actualizar
     * @param usuario objeto con atributos a cambiar del usuario
     * @return usuario actualizado
     * @throws BibliotecaLogicException Si no es posible actualizar el usuario
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("usuarios/{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario) throws BibliotecaLogicException {
        existsBiblioteca(usuario.getBiblioteca().getId());
        existsUsuario(id);
        UsuarioEntity entity = usuario.toEntity();
        entity.setId(id);
        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(entity));
        
    }

    /**
     * Elimina un usuario
     *
     * @param id id del usuario a eliminar
     */
    @DELETE
    @Path("usuarios/{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws BibliotecaLogicException{
        existsUsuario(id);
        usuarioLogic.deleteUsuario(id);
    }
}
