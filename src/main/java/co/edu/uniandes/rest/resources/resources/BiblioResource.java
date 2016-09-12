/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
import co.edu.uniandes.rest.resources.mocks.BiblioLogicMock;

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
@Path("bibliotecas")
@Produces("application/json")
public class BiblioResource {

    BiblioLogicMock cityLogic = new BiblioLogicMock();

    /**
     * Obtiene el listado de bibliotecas.
     *
     * @return lista de bibliotecas
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    public List<BiblioDTO> getCities() throws BiblioLogicException {
        return cityLogic.getCities();
    }
   
    /**
     * Agrega una biblioteca
     *
     * @param city biblioteca a agregar
     * @return datos de la biblioteca a agregar
     * @throws BiblioLogicException cuando ya existe una biblioteca con el id
     * suministrado
     */
    @POST
    public BiblioDTO createCity(BiblioDTO city) throws BiblioLogicException {
        return cityLogic.createCity(city);
    }

    @GET
    @Path("{id: \\d+}")
    public BiblioDTO getCity(@PathParam("id")int id) throws BiblioLogicException {
        return cityLogic.getCity(id);
    }
    
    @PUT
    @Path("{id: \\d+}")
    public BiblioDTO updateCity(@PathParam("id")int id, BiblioDTO b)throws BiblioLogicException{
        return cityLogic.updateCity(id, b);
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id") int id)throws BiblioLogicException{
        cityLogic.deleteCity(id);
    }
}
