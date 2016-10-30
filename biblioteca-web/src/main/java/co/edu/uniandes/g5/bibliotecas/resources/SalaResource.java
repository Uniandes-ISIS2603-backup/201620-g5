/*
 * SalaResource.java
 * Clase que representa el recurso "/salas"
 * Implementa varios métodos para manipular las salas
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.dtos.SalaDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.VideoDTO;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.text.ParseException;
import java.util.ArrayList;

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
@Path("")
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
    @Path("salas")
    public List<SalaDTO> getAllSalas() throws BibliotecaLogicException {
        return salaLogic.getAllSalas();
    }
    
     @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/salas")
    public ArrayList<SalaDTO> getSalasBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) throws BibliotecaLogicException, ParseException {
        return salaLogic.getSalasBiblioteca(idBiblioteca);
    }

    @Path("salas/{id: \\d+}")
    @GET
    public SalaDTO getSala(@PathParam("id")Long id) throws BibliotecaLogicException {
        return salaLogic.getSala(id);
    }

    @POST
    @Path("bibliotecas/salas")
    public SalaDTO createSala(SalaDTO sala) throws BibliotecaLogicException{
        return salaLogic.createSala(sala);
    }
    

    
    @PUT
    @Path("salas/{id: \\d+}")
    public SalaDTO updateSala(@PathParam("id") Long id, SalaDTO newSala) throws BibliotecaLogicException 
    {
        return salaLogic.updateSala(id, newSala);
    }
    

    @DELETE
    @Path("salas/{id: \\d+}")
    public SalaDTO deleteSala(@PathParam("id") Long id) throws BibliotecaLogicException 
    {
        return salaLogic.deleteSala(id);
    }
    /*
    R13
    */
    @GET
    @Path("salas/ {id: \\d+}")
    public SalaDTO getDisponibilidadSala(@PathParam("id") Long idSala, String fecha){
        return salaLogic.getDisponibilidadSala(idSala, fecha);
    }
}
