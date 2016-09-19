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

    //servicios rest de bibliotecas.
    @GET
    @Path("usuarios/{idUsuario: \\d+}/prestamos")
    public ArrayList<PrestamoDTO> getPrestamosUsuario(@PathParam("idUsuario") Long idUsuario) throws BiblioLogicException, ParseException {
        return prestamoLogic.getPrestamosUsuario(idUsuario);
    }

    @GET
    @Path("libros/{idLibro: \\d+}/prestamos")
    public List<PrestamoDTO> getPrestamosLibro(@PathParam("idLibro") Long id){
        return prestamoLogic.getPrestamosLibro(id);
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
    @Path("usuarios/{idUsuario: \\d+}/prestamos/{id: \\d+}")
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
    
    
    //Servicios rest utilizados desde biblioteca.
    
    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/prestamos")
    public ArrayList<PrestamoDTO> getPrestamosBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) throws BiblioLogicException, ParseException {
        return prestamoLogic.getPrestamosBiblioteca(idBiblioteca);
    }
    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/prestamos/{id: \\d+}")
    public PrestamoDTO getPrestamoDeBiblioteca(@PathParam("id") Long id, @PathParam("idBiblioteca") Long idBiblioteca) throws BiblioLogicException, ParseException {
        return prestamoLogic.getPrestamoDeBiblioteca(id, idBiblioteca);
    }
    
    @POST
    @Path("bibliotecas/{idBiblioteca: \\d+}/prestamos")
    public PrestamoDTO createPrestamoBiblioteca(PrestamoDTO prestamo, @PathParam("idBiblioteca") Long idBiblioteca) throws ParseException, BiblioLogicException {
        return prestamoLogic.createPrestamoBiblioteca(prestamo, idBiblioteca);
    }
    
    @DELETE
    @Path("bibliotecas/{idBiblioteca: \\d+}/prestamos/{id: \\d+}")
    public PrestamoDTO deletePrestamoBiblioteca(@PathParam("id") Long id, @PathParam("idBiblioteca") Long idBiblioteca) throws ParseException, BiblioLogicException {
        return prestamoLogic.deletePrestamoDeBiblioteca(id, idBiblioteca);
    }
}
