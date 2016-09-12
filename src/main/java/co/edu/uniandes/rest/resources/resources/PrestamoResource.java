/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.resources;

import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import co.edu.uniandes.rest.resources.dtos.MultaDTO;
import co.edu.uniandes.rest.resources.dtos.PrestamoDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.LibroLogicMock;
import co.edu.uniandes.rest.resources.mocks.MultaLogicMock;
import co.edu.uniandes.rest.resources.mocks.PrestamoLogicMock;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
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
@Path("prestamos")
@Produces("application/json")
public class PrestamoResource {

    /**
     * Obtiene el listado de bibliotecas.
     *
     * @return lista de bibliotecas
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    public List<PrestamoDTO> getPrestamos() throws BiblioLogicException, ParseException {
        PrestamoLogicMock PrestamoLogic = new PrestamoLogicMock();
        return PrestamoLogic.getPrestamos();
    }

    @Path("{idUsuario: \\d+}")
    @GET
    public List<PrestamoDTO> getPrestamosUsuario(@PathParam("idUsuario") int idUsuario) throws BiblioLogicException, ParseException {
        PrestamoLogicMock PrestamoLogic = new PrestamoLogicMock();
        return PrestamoLogic.getPrestamosUsuario(idUsuario);
    }

    /**
     * Agrega un prestamo a un usuario
     *
     * @param prestamo prestamo a agregar
     * @param idUsuario id del usuario para agregarle una multa
     * @return datos de la multa a agregar suministrado
     */
    @Path("{idUsuario: \\d+}")
    @POST
    public PrestamoDTO createPrestamo(PrestamoDTO prestamo, @PathParam("idUsuario") int idUsuario) throws ParseException, BiblioLogicException {
        PrestamoLogicMock PrestamoLogic = new PrestamoLogicMock();
        return PrestamoLogic.createPrestamo(prestamo, idUsuario);
    }

    @GET
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public PrestamoDTO getPrestamoDeUsuario(@PathParam("id") int id, @PathParam("idUsuario") int idUsuario) throws BiblioLogicException, ParseException {
        PrestamoLogicMock PrestamoLogic = new PrestamoLogicMock();
        return PrestamoLogic.getPrestamoDeUsuario(id, idUsuario);
    }

    @PUT
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public PrestamoDTO updatePrestamo(@PathParam("id") int id, PrestamoDTO m, @PathParam("idUsuario") int idUsuario) throws BiblioLogicException, ParseException {
        PrestamoLogicMock PrestamoLogic = new PrestamoLogicMock();
        return PrestamoLogic.updatePrestamoDeUsuario(id, m, idUsuario);
    }

    @DELETE
    @Path("{id: \\d+}/{idUsuario: \\d+}")
    public void deleteMulta(@PathParam("id") int id, @PathParam("idUsuario") int idUsuario) throws BiblioLogicException, ParseException {
        PrestamoLogicMock PrestamoLogic = new PrestamoLogicMock();
        PrestamoLogic.deletePrestamoDeUsuario(id, idUsuario);
    }

    @GET
    @Path("{idUsuario: \\d+}/{idRecurso: \\d+}/{fechaEntrega}")
    public PrestamoDTO regresarLibro(@PathParam("idUsuario") int idUsuario,@PathParam("fechaEntrega") String fechaEntrega, @PathParam("idRecurso") int idRecurso) throws ParseException, BiblioLogicException, BibliotecaLogicException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        PrestamoLogicMock prestamoLogic = new PrestamoLogicMock();
        return prestamoLogic.regresarLibro(idRecurso, idUsuario, fechaEntrega);
    }
}
