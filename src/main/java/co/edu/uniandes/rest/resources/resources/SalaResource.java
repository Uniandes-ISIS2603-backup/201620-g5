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
    
   
    SalaLogicMock salaLogic = new SalaLogicMock();

    /**
     * Obtiene el listado de salas.
     *
     * @return lista de salas completa
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    public List<SalaDTO> getAllSalas() throws BibliotecaLogicException {
        return salaLogic.getAllSalas();
    }
    
    @Path("{id: \\d+}")
    @GET
    public SalaDTO getSala(@PathParam("id")Long id) throws BibliotecaLogicException {
        return salaLogic.getSala(id);
    }

    @POST
    public SalaDTO createSala(SalaDTO sala) throws BibliotecaLogicException, BibliotecaLogicException {
        return salaLogic.createSala(sala);
    }
    

    
    @PUT
    @Path("{id: \\d+}")
    public SalaDTO updateSala(@PathParam("id") Long id, SalaDTO newSala) throws BibliotecaLogicException 
    {
        return salaLogic.updateSala(id, newSala);
    }
    

    @DELETE
    @Path("{id: \\d+}")
    public SalaDTO deleteSala(@PathParam("id") Long id) throws BibliotecaLogicException 
    {
        return salaLogic.deleteSala(id);
    }
}