/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.ReservaDTO;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
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
@Path("")
@Produces("application/json")
public class ReservaResource {

    ReservaLogicMock reservaLogic = new ReservaLogicMock();

    /**
     * Obtiene el listado de ciudades.
     *
     * @return lista de ciudades
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    @Path("reservas")
    public List<ReservaDTO> getReservas() throws BibliotecaLogicException {
        return reservaLogic.getReservas();
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
    @Path("reservas")
    public ReservaDTO createReserva(ReservaDTO reserva) throws BibliotecaLogicException {
        return reservaLogic.createReserva(reserva);
    }

    @GET
    @Path("reservas/{id: \\d+}")
    public ReservaDTO getReserva(@PathParam("id")Long id) throws BibliotecaLogicException {
        return reservaLogic.getReserva(id);
    }
    
    @PUT
    @Path("reservas/{id: \\d+}")
    public ReservaDTO updateReserva(@PathParam("id")Long id, ReservaDTO reserva)throws BibliotecaLogicException{
        return reservaLogic.updateReserva(id, reserva);
    }
    
    @DELETE
    @Path("reservas/{id: \\d+}")
    public void deleteReserva(@PathParam("id") Long id)throws BibliotecaLogicException{
        reservaLogic.deleteReserva(id);
    }
    
    /*
    R11: Ver reservas activas de un libro.
    */
    @GET
    @Path("libros/{idLibro: \\d+}/reservas")
    public List<ReservaDTO> getReservasLibro(@PathParam("idLibro") Long id){
        return reservaLogic.getReservasLibro(id);
    }

}
