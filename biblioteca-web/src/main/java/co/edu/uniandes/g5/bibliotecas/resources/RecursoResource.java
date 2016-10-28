/*
 * RecursoResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.dtos.RecursoDTO;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
@Path("recursos")
@Produces("application/json")
public class RecursoResource {

    RecursoLogicMock recursoLogic = new RecursoLogicMock();

    /**
     * Obtiene el listado de ciudades.
     *
     * @return lista de ciudades
     * @throws BibliotecaLogicException excepción retornada por la lógica
     */
    @GET
    public List<RecursoDTO> getRecursos() throws BibliotecaLogicException {
        return recursoLogic.getRecursos();
    }

    /**
     * Agrega una ciudad
     *
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws BibliotecaLogicException cuando ya existe una ciudad con el id
     * suministrado
     */
    @POST
    public RecursoDTO createRecurso(RecursoDTO city) throws BibliotecaLogicException {
        return recursoLogic.createRecurso(city);
    }
}
