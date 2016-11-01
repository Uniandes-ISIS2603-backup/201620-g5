/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IReservaLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.ReservaDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.ReservaDTO;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.text.ParseException;
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
@Path("/bibliotecas/{bibliotecaId: \\d+}/reservas")
@Produces("application/json")
public class ReservaResource {

   private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());

    @Inject
    private IReservaLogic reservaLogic;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;

    @PathParam("bibliotecaId")
    private Long bibliotecaId;

    /**
     * Convierte una lista de PrestamoEntity a una lista de
     * PrestamoDetailDTO
     *
     * @param entityList Lista de PRestamoEntity a convertir
     * @return Lista de PrestamoDetailDTO convertida
     *
     */
    private List<ReservaDetailDTO> listEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }

    public void existsBiblioteca(Long bibliotecaId) {
        BiblioDetailDTO biblioteca = new BiblioDetailDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsReserva(Long reservaId) {
        ReservaDetailDTO reserva = new ReservaDetailDTO(reservaLogic.getReserva(reservaId));
        if (reserva == null) {
            throw new WebApplicationException("El Departamento no existe", 404);
        }
    }
    /**
     * Obtiene el listado de reservas de la biblioteca.
     *
     * @return lista de reservas
     * @throws BibliotecaLogicException excepción retornada por la lógica
     */
    @GET
    @Path("reservas")
    public List<ReservaDetailDTO> getReservasBiblioteca() throws BibliotecaLogicException, ParseException {
        existsBiblioteca(bibliotecaId);
        List<ReservaEntity> reservas = reservaLogic.getReservasByBiblioteca(bibliotecaId);
        return listEntity2DTO(reservas);
    }
    
     @GET
    @Path("{reservaId: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("reservaId") Long reservaId) throws BibliotecaLogicException, ParseException {
       existsBiblioteca(bibliotecaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con bibliotecaId = {0}", bibliotecaId);
        ReservaEntity entity = reservaLogic.getReserva(reservaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con id = {0}", entity.getBiblioteca().getId());
        if (entity.getBiblioteca() != null && !bibliotecaId.equals(entity.getBiblioteca().getId())) {
            throw new WebApplicationException(404);
        }

        return new ReservaDetailDTO(entity);
    }

     @POST
    public ReservaDetailDTO createReserva(ReservaDetailDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        if (dto.getBiblioteca() != null && !bibliotecaId.equals(dto.getBiblioteca().getId())) {
            throw new WebApplicationException(404);
        }
        return new ReservaDetailDTO(reservaLogic.createReserva(dto.toEntity()));
    }
    
    @PUT
    @Path("{reservaId: \\d+}")
    public ReservaDetailDTO updateReserva(@PathParam("reservaId") Long reservaId, ReservaDetailDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsReserva(reservaId);
        ReservaEntity entity = dto.toEntity();
        entity.setId(reservaId);
        return new ReservaDetailDTO(reservaLogic.updateReserva(entity));
    }
    
  
    
    @DELETE
    @Path("{reservaId: \\d+}")
    public void deleteReserva(@PathParam("reservaId") Long reservaId) throws BibliotecaLogicException  {
        existsBiblioteca(bibliotecaId);
        existsReserva(reservaId);
        reservaLogic.deleteReserva(reservaId);
    }
    
    
}
