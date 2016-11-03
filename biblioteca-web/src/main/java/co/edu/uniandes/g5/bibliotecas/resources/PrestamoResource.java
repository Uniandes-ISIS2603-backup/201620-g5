/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IPrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.PrestamoDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.PrestamoDetailDTO;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sf.munera10
 */
@Path("/bibliotecas/{bibliotecaId: \\d+}/prestamos")
@Produces("application/json")
public class PrestamoResource {

 private static final Logger LOGGER = Logger.getLogger(PrestamoResource.class.getName());

    @Inject
    private IPrestamoLogic prestamoLogic;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;

    @PathParam("bibliotecaId")
    private Long bibliotecaId;

    /**
     * Convierte una lista de PrestamoEntity a una lista de
     * PrestamoDetailDTO
     *
     * @param entityList Lista de PrestamoEntity a convertir
     * @return Lista de PrestamoDetailDTO convertida
     *
     */
    private List<PrestamoDetailDTO> listEntity2DTO(List<PrestamoEntity> entityList) {
        List<PrestamoDetailDTO> list = new ArrayList<>();
        for (PrestamoEntity entity : entityList) {
            list.add(new PrestamoDetailDTO(entity));
        }
        return list;
    }

    public void existsBiblioteca(Long bibliotecaId) {
        BiblioDetailDTO biblioteca = new BiblioDetailDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsPrestamo(Long prestamoId) {
        PrestamoDetailDTO prestamo = new PrestamoDetailDTO(prestamoLogic.getPrestamo(prestamoId));
        if (prestamo == null) {
            throw new WebApplicationException("El Departamento no existe", 404);
        }
    }
    /**
     * Obtiene el listado de prestamos de la biblioteca.
     *
     * @return lista de prestamos
     * @throws BibliotecaLogicException excepción retornada por la lógica
     */
    @GET
    @Path("")
    public List<PrestamoDetailDTO> getPrestamosBiblioteca() throws BibliotecaLogicException, ParseException {
        existsBiblioteca(bibliotecaId);
        List<PrestamoEntity> prestamos = prestamoLogic.getPrestamosByBiblioteca(bibliotecaId);
        return listEntity2DTO(prestamos);
    }
    
     @GET
    @Path("{prestamoId: \\d+}")
    public PrestamoDetailDTO getPrestamo(@PathParam("prestamoId") Long prestamoId) throws BibliotecaLogicException, ParseException {
       existsBiblioteca(bibliotecaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con bibliotecaId = {0}", bibliotecaId);
        PrestamoEntity entity = prestamoLogic.getPrestamo(prestamoId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con id = {0}", entity.getBiblioteca().getId());
        if (entity.getBiblioteca() != null && !bibliotecaId.equals(entity.getBiblioteca().getId())) {
            throw new WebApplicationException(404);
        }

        return new PrestamoDetailDTO(entity);
    }

   
    /**
     * R12
     * @param id id del libro a consultar
     * @return lista de prestamos activos del libro
     
    @GET
    @Path("libros/{idLibro: \\d+}/prestamos")
    public List<PrestamoDetailDTO> getPrestamosLibro(@PathParam("idLibro") Long id){
        return prestamoLogic.getPrestamosLibro(id);
    }
    
    /**
     * Agrega un prestamo a un usuario
     *
     * @param prestamo prestamo a agregar
     * @param idUsuario id del usuario para agregarle una multa
     * @return datos de la multa a agregar suministrado
     

    @POST
    @Path("usuarios/{idUsuario: \\d+}/prestamos")
    public PrestamoDetailDTO createPrestamo(PrestamoDTO prestamo, @PathParam("idUsuario") Long idUsuario) throws ParseException, BibliotecaLogicException {
        return prestamoLogic.createPrestamo(prestamo, idUsuario);
    }

    @GET
    @Path("usuarios/{idUsuario: \\d+}/prestamos/{id: \\d+}")
    public PrestamoDetailDTO getPrestamoDeUsuario(@PathParam("id") int id, @PathParam("idUsuario") Long idUsuario) throws BibliotecaLogicException, ParseException {
        return prestamoLogic.getPrestamoDeUsuario(id, idUsuario);
    }
    @PUT
    @Path("prestamos/{id: \\d+}/{idUsuario: \\d+}")
    public PrestamoDetailDTO updatePrestamo(@PathParam("id") Long id, PrestamoDTO m, @PathParam("idUsuario") Long idUsuario) throws BibliotecaLogicException, ParseException {
        return prestamoLogic.updatePrestamoDeUsuario(id, m, idUsuario);
    }
    

    @DELETE
    @Path("prestamos/{id: \\d+}/{idUsuario: \\d+}")
    public void deleteMulta(@PathParam("id") int id, @PathParam("idUsuario") int idUsuario) throws BibliotecaLogicException, ParseException {
        prestamoLogic.deletePrestamoDeUsuario(id, idUsuario);
    }
    
    */
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public PrestamoDetailDTO createPrestamo(PrestamoDetailDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        if (dto.getBiblioteca() != null && !bibliotecaId.equals(dto.getBiblioteca().getId())) {
            throw new WebApplicationException(404);
        }
        return new PrestamoDetailDTO(prestamoLogic.createPrestamo(dto.toEntity()));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{prestamoId: \\d+}")
    public PrestamoDetailDTO updatePrestamo(@PathParam("prestamoId") Long prestamoId, PrestamoDetailDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsPrestamo(prestamoId);
        PrestamoEntity entity = dto.toEntity();
        entity.setId(prestamoId);
        return new PrestamoDetailDTO(prestamoLogic.updatePrestamo(entity));
    }
    
    /**
    @PUT
    @Path("prestamos/{id: \\d+}/usuarios/idUsuario/fecha")
    public PrestamoDetailDTO regresarLibro(@PathParam("id") Long id, PrestamoDTO m) throws BibliotecaLogicException, ParseException {
        return prestamoLogic.updatePrestamo1(id,m);
    }
    */
    
    @DELETE
    @Path("{prestamoId: \\d+}")
    public void deletePrestamo(@PathParam("prestamoId") Long prestamoId) throws BibliotecaLogicException  {
        existsBiblioteca(bibliotecaId);
        existsPrestamo(prestamoId);
        prestamoLogic.deletePrestamo(prestamoId);
    }
}

