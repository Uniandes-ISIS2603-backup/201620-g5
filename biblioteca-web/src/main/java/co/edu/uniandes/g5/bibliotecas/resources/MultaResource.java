/*
 * CityResource.java
 * Clase que representa el recurso "/cities"
 * Implementa varios métodos para manipular las ciudades
 */
package co.edu.uniandes.g5.bibliotecas.resources;


import co.edu.uniandes.g5.bibliotecas.dtos.MultaDTO;

import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;

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
@Path("")
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
    @Path("multas")
    public List<MultaDTO> getMultas() throws BibliotecaLogicException {
        return multaLogic.getMultas();
    }
   
    @POST
    @Path("usuarios/multas")
    public MultaDTO createMulta(MultaDTO multa) throws BibliotecaLogicException {
        return multaLogic.createMulta(multa);
    }

    @GET
    @Path("multas/{id: \\d+}")
    public MultaDTO getMulta(@PathParam("id")int id) throws BibliotecaLogicException {
        return multaLogic.getMulta(id);
    }
    
    @PUT
    @Path("multas/{id: \\d+}")
    public MultaDTO updateMulta(@PathParam("id")int id, MultaDTO b)throws BibliotecaLogicException{
        return multaLogic.updateMulta(id, b);
    }
    
    @DELETE
    @Path("multas/{id: \\d+}")
    public void deleteMulta(@PathParam("id") int id)throws BibliotecaLogicException{
        multaLogic.deleteMulta(id);
    }
    
    
    @Path("usuarios/{idUsuario: \\d+}/multas")
    @GET
    public List<MultaDTO> getMultasUsuario(@PathParam("idUsuario")int idUsuario) throws BibliotecaLogicException {
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.getMultasUsuario(idUsuario);
    }
  
    @Path("usuarios/{idUsuario: \\d+}/multas")
    @POST
    public MultaDTO createMultaUsuario(MultaDTO multa,@PathParam("idUsuario")int idUsuario){
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.createMultaUsuario(multa, idUsuario);
    }
    
    /*
    @GET
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public MultaDTO getMultaDeUsuario(@PathParam("id")int id,@PathParam("idUsuario")int idUsuario) throws BibliotecaLogicException {
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.getMultaDeUsuario(id, idUsuario);
    }
    
    @PUT
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public MultaDTO updateMultaUsuario(@PathParam("id")int id, MultaDTO m,@PathParam("idUsuario")int idUsuario)throws BibliotecaLogicException{
        MultaLogicMock multaLogic = new MultaLogicMock();
        return multaLogic.updateMultaDeUsuario(id, m, idUsuario);
    }
    
    @DELETE
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public void deleteMultaUsuario(@PathParam("id") int id,@PathParam("idUsuario")int idUsuario )throws BibliotecaLogicException{
        MultaLogicMock multaLogic = new MultaLogicMock();
        multaLogic.deleteMultaDeBiblioteca(id, idUsuario);
    }
*/

}
