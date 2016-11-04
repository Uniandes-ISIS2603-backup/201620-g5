/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.g5.bibliotecas.resources;


import co.edu.uniandes.g5.bibliotecas.api.IMultaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.MultaDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.MultaDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;

import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso REST correspondiente a "cities".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "cities". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/cities"
 *
 * @author sf.munera10
 */
@Path("/usuarios/{usuarioId: \\d+}/multas")
@Produces("application/json")
public class MultaResource {

    private static final Logger LOGGER = Logger.getLogger(MultaResource.class.getName());

    @Inject
    private IMultaLogic multaLogic;

    @Inject
    private IUsuarioLogic usuarioLogic;

    @PathParam("usuarioId")
    private Long usuarioId;

    /**
     * Convierte una lista de MultaEntity a una lista de
     * MultaDetailDTO
     *
     * @param entityList Lista de MultaEntity a convertir
     * @return Lista de MultaDetailDTO convertida
     *
     */
    private List<MultaDetailDTO> listEntity2DTO(List<MultaEntity> entityList) {
        List<MultaDetailDTO> list = new ArrayList<>();
        for (MultaEntity entity : entityList) {
            list.add(new MultaDetailDTO(entity));
        }
        return list;
    }

    public void existsUsuario(Long usuarioId) {
        UsuarioDetailDTO usuario = new UsuarioDetailDTO(usuarioLogic.getUsuario(usuarioId));
        if (usuario == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsMulta(Long multaId) {
        MultaDetailDTO multa = new MultaDetailDTO(multaLogic.getMulta(multaId));
        if (multa == null) {
            throw new WebApplicationException("El Departamento no existe", 404);
        }
    }
    /**
     * Obtiene el listado de multas de la biblioteca.
     *
     * @return lista de multas
     * @throws BibliotecaLogicException excepción retornada por la lógica
     */
    @GET
    public List<MultaDetailDTO> getMultasUsuario() throws BibliotecaLogicException {
        existsUsuario(usuarioId);
        List<MultaEntity> multas = multaLogic.getMultasByUsuario(usuarioId);
        return listEntity2DTO(multas);
    }
    
     @GET
    @Path("{multaId: \\d+}")
    public MultaDetailDTO getMulta(@PathParam("multaId") Long multaId) throws BibliotecaLogicException {
       existsUsuario(usuarioId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con usuarioId = {0}", usuarioId);
        MultaEntity entity = multaLogic.getMulta(multaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con id = {0}", entity.getBiblioteca().getId());
        if (entity.getUsuario() != null && !usuarioId.equals(entity.getUsuario().getId())) {
            throw new WebApplicationException(404);
        }

        return new MultaDetailDTO(entity);
    }

   
   
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public MultaDetailDTO createMulta(MultaDetailDTO dto) throws BibliotecaLogicException {
        existsUsuario(usuarioId);
        if (dto.getUsuario() != null && !usuarioId.equals(dto.getUsuario().getId())) {
            throw new WebApplicationException(404);
        }
        return new MultaDetailDTO(multaLogic.createMulta(dto.toEntity()));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{multaId: \\d+}")
    public MultaDetailDTO updateMulta(@PathParam("multaId") Long multaId, MultaDetailDTO dto) throws BibliotecaLogicException {
        existsUsuario(usuarioId);
        existsMulta(multaId);
        MultaEntity entity = dto.toEntity();
        entity.setId(multaId);
        return new MultaDetailDTO(multaLogic.updateMulta(entity));
    }
    
    /**
    public MultaDetailDTO regresarLibro(@PathParam("id") Long id, MultaDTO m) throws BibliotecaLogicException, ParseException {
        return multaLogic.updateMulta1(id,m);
    }
    */
    
    @DELETE
    @Path("{multaId: \\d+}")
    public void deleteMulta(@PathParam("multaId") Long multaId) throws BibliotecaLogicException  {
        existsUsuario(usuarioId);
        existsMulta(multaId);
        multaLogic.deleteMulta(multaId);
    }
    
    /*
    public MultaDTO getMultaDeUsuario(@PathParam("id")int id,@PathParam("idUsuario")int idUsuario) throws BibliotecaLogicException {
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.getMultaDeUsuario(id, idUsuario);
    }
   
    public MultaDTO updateMultaUsuario(@PathParam("id")int id, MultaDTO m,@PathParam("idUsuario")int idUsuario)throws BibliotecaLogicException{
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.updateMultaDeUsuario(id, m, idUsuario);
    }
    
    public void deleteMultaUsuario(@PathParam("id") int id,@PathParam("idUsuario")int idUsuario )throws BibliotecaLogicException{
        MultaLogicMock multaLogic = new MultaLogicMock();
        multaLogic.deleteMultaDeBiblioteca(id, idUsuario);
    }
*/

}
