/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import co.edu.uniandes.rest.resources.dtos.ReservaDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
import co.edu.uniandes.rest.resources.mocks.BiblioLogicMock;
import co.edu.uniandes.rest.resources.mocks.ReservaLogicMock;

import java.util.List;
import javax.ws.rs.Consumes;
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
@Path("reservas")
@Produces("application/json")
public class ReservaResource {

    ReservaLogicMock ReservaLogic = new ReservaLogicMock();

    /**
     * Obtiene el listado de ciudades.
     *
     * @return lista de ciudades
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    public List<ReservaDTO> getReservas() throws BiblioLogicException {
        return ReservaLogic.getReservas();
    }
   
    /**
     * Agrega una ciudad
     *
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws BiblioLogicException cuando ya existe una ciudad con el id
     * suministrado
     */
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws BiblioLogicException {
        return ReservaLogic.createReserva(reserva);
    }

    @GET
    @Path("{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("id")Long id) throws BiblioLogicException {
        return ReservaLogic.getReserva(id);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ReservaDTO updateReserva(@PathParam("id")int id, ReservaDTO reserva)throws BiblioLogicException{
        return ReservaLogic.updateReserva(id, reserva);
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteReserva(@PathParam("id") int id)throws BiblioLogicException{
        ReservaLogic.deleteReserva(id);
    }
}
