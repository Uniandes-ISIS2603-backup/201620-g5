/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.PrestamoDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.PrestamoLogicMock;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 *
 * @author sf.munera10
 */
@Path("")
@Produces("application/json")
public class PrestamoResource {

    PrestamoLogicMock prestamoLogic = new PrestamoLogicMock();

    /**
     * Obtiene el listado de bibliotecas.
     *
     * @return lista de bibliotecas
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    @Path("prestamos")
    public ArrayList<PrestamoDTO> getPrestamos() throws BiblioLogicException, ParseException {
        return prestamoLogic.getPrestamos();
    }

    
    @GET
    @Path("usuarios/{idUsuario: \\d+}/prestamos")
    public ArrayList<PrestamoDTO> getPrestamosUsuario(@PathParam("idUsuario") Long idUsuario) throws BiblioLogicException, ParseException {
        return prestamoLogic.getPrestamosUsuario(idUsuario);
    }

    /**
     * Agrega un prestamo a un usuario
     *
     * @param prestamo prestamo a agregar
     * @param idUsuario id del usuario para agregarle una multa
     * @return datos de la multa a agregar suministrado
     */
    @POST
    @Path("usuarios/{idUsuario: \\d+}/prestamos")
    public PrestamoDTO createPrestamo(PrestamoDTO prestamo, @PathParam("idUsuario") Long idUsuario) throws ParseException, BiblioLogicException {
        return prestamoLogic.createPrestamo(prestamo, idUsuario);
    }

    @GET
    @Path("prestamos/{id: \\d+}/{idUsuario: \\d+}")
    public PrestamoDTO getPrestamoDeUsuario(@PathParam("id") int id, @PathParam("idUsuario") Long idUsuario) throws BiblioLogicException, ParseException {
        return prestamoLogic.getPrestamoDeUsuario(id, idUsuario);
    }

    @PUT
    @Path("prestamos/{id: \\d+}/{idUsuario: \\d+}")
    public PrestamoDTO updatePrestamo(@PathParam("id") Long id, PrestamoDTO m, @PathParam("idUsuario") Long idUsuario) throws BiblioLogicException, ParseException {
        return prestamoLogic.updatePrestamoDeUsuario(id, m, idUsuario);
    }

    @DELETE
    @Path("prestamos/{id: \\d+}/{idUsuario: \\d+}")
    public void deleteMulta(@PathParam("id") int id, @PathParam("idUsuario") int idUsuario) throws BiblioLogicException, ParseException {
        prestamoLogic.deletePrestamoDeUsuario(id, idUsuario);
    }

    @GET
    @Path("prestamos/{idUsuario: \\d+}/{idRecurso: \\d+}/{fechaEntrega}")
    public PrestamoDTO regresarLibro(@PathParam("idUsuario") int idUsuario,@PathParam("fechaEntrega") String fechaEntrega, @PathParam("idRecurso") int idRecurso) throws ParseException, BiblioLogicException, BibliotecaLogicException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        return prestamoLogic.regresarLibro(idRecurso, idUsuario, fechaEntrega);
    }
    
    /**
     * DEBE IR EN LibroResource, solucionar para acceder a reservas desde ahi.
     * @param id 
     */
    @GET
    @Path("libros/{idLibro: \\d+}/prestamos")
    public List<PrestamoDTO> getReservasLibro(@PathParam("idLibro") Long id){
        return prestamoLogic.getPrestamosLibro(id);
    }
}
