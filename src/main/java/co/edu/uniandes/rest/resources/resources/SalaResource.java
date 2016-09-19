/*
 * SalaResource.java
 * Clase que representa el recurso "/salas"
 * Implementa varios métodos para manipular las salas
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.SalaDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.SalaLogicMock;

import java.util.List;
import javax.ws.rs.DELETE;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "salas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "salas". Al ejecutar la aplicación, el recurso
 * será accesibe a través de la ruta "/api/salas"
 *
 * @author sf.munera10
 */
@Path("salas")
@Produces("application/json")
public class SalaResource {
    
    /**
     * Obtiene el listado de salas.
     *
     * @return lista de salas completa
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    public List<SalaDTO> getAllSalas() throws BibliotecaLogicException {
        SalaLogicMock salaLogic = new SalaLogicMock();
        return salaLogic.getAllSalas();
    }
    
    /**
     * Obtiene el listado de salas.
     *
     * @param idBiblioteca biblioteca a buscar
     * @return lista de salas de una biblioteca.
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @Path("{idBiblioteca: \\d+}")
    @GET
    public List<SalaDTO> getSalasBiblioteca(@PathParam("idBiblioteca")int idBiblioteca) throws BibliotecaLogicException {
        SalaLogicMock salaLogic = new SalaLogicMock();
        return salaLogic.getSalasBiblioteca(idBiblioteca);
    }
   
    /**
     * Agrega una sala a una biblioteca
     *
     * @param sala sala a agregar
     * @param idBiblioteca id de la biblioteca a buscar
     * @return datos de la sala a agregar
     * @throws BiblioLogicException cuando ya existe una sala con el id
     * suministrado
     */
    @Path("{idBiblioteca: \\d+}")
    @POST
    public SalaDTO createSala(SalaDTO sala,@PathParam("idBiblioteca")int idBiblioteca) throws BibliotecaLogicException, BibliotecaLogicException {
        SalaLogicMock salaLogic = new SalaLogicMock();
        return salaLogic.createSala(sala, idBiblioteca);
    }
    
     
    @GET
    @Path("{id: \\d+}/{idBiblioteca: \\d+}")
    public SalaDTO getSalaDeBiblioteca(@PathParam("id")int id,@PathParam("idBiblioteca")int idBiblioteca) throws BibliotecaLogicException {
        SalaLogicMock salaLogic = new SalaLogicMock();
        return salaLogic.getSalaDeBiblioteca(id, idBiblioteca);
    }
    
    @PUT
    @Path("{id: \\d+}/{idBiblioteca: \\d+}")
    public SalaDTO updateSala(@PathParam("id")int id, SalaDTO s,@PathParam("idBiblioteca")int idBiblioteca)throws BibliotecaLogicException{
        SalaLogicMock salaLogic = new SalaLogicMock();
        return salaLogic.updateSalaDeBiblioteca(id, s, idBiblioteca);
    }
    
    @DELETE
    @Path("{id: \\d+}/{idBiblioteca: \\d+}")
    public void deleteSala(@PathParam("id") int id,@PathParam("idBiblioteca")int idBiblioteca )throws BibliotecaLogicException, BibliotecaLogicException{
        SalaLogicMock salaLogic = new SalaLogicMock();
        salaLogic.deleteSalaDeBiblioteca(id, idBiblioteca);
    }
}