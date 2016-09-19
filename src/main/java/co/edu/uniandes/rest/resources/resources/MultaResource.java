/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.MultaDTO;

import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.MultaLogicMock;

import java.util.List;
import javax.ws.rs.DELETE;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "cities".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "cities". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/cities"
 *
 * @author sf.munera10
 */
@Path("multas")
@Produces("application/json")
public class MultaResource {

    MultaLogicMock multaLogic = new MultaLogicMock();

    /**
     * Obtiene el listado de bibliotecas.
     *
     * @return lista de bibliotecas
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    public List<MultaDTO> getMultas() throws BibliotecaLogicException {
        return multaLogic.getMultas();
    }
   
    @Path("{idUsuario: \\d+}")
    @GET
    public List<MultaDTO> getMultasUsuario(@PathParam("idUsuario")int idUsuario) throws BibliotecaLogicException {
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.getMultasUsuario(idUsuario);
    }
    
    /**
     * Agrega una multa a un usuario
     *
     * @param multa multa a agregar
     * @param idUsuario id del usuario para agregarle una multa
     * @return datos de la multa a agregar
     * suministrado
     */
    @Path("{idUsuario: \\d+}")
    @POST
    public MultaDTO createMulta(MultaDTO multa,@PathParam("idUsuario")int idUsuario){
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.createMulta(multa, idUsuario);
    }
     
    @GET
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public MultaDTO getMultaDeUsuario(@PathParam("id")int id,@PathParam("idUsuario")int idUsuario) throws BibliotecaLogicException {
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.getMultaDeUsuario(id, idUsuario);
    }
    
    @PUT
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public MultaDTO updateMulta(@PathParam("id")int id, MultaDTO m,@PathParam("idUsuario")int idUsuario)throws BibliotecaLogicException{
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.updateMultaDeUsuario(id, m, idUsuario);
    }
    
    @DELETE
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public void deleteMulta(@PathParam("id") int id,@PathParam("idUsuario")int idUsuario )throws BibliotecaLogicException{
        MultaLogicMock multaLogic = new MultaLogicMock();
        multaLogic.deleteMultaDeBiblioteca(id, idUsuario);
    }
}
