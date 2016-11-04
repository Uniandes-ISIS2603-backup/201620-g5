/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.api.IReservaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ISalaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.api.IVideoLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.LibroDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.ReservaDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.ReservaDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.RecursoDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.SalaDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.VideoDetailDTO;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
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
@Path("/bibliotecas/{bibliotecaId: \\d+}")
@Produces("application/json")
public class ReservaResource {

 private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());

    @Inject
    private IReservaLogic reservaLogic;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;
    
    @Inject
    private IUsuarioLogic usuarioLogic;
    
    @Inject
    private ILibroLogic libroLogic;
    
    @Inject
    private IVideoLogic videoLogic;
    
    @Inject 
    private ISalaLogic salaLogic;

    @PathParam("bibliotecaId")
    private Long bibliotecaId;

    /**
     * Convierte una lista de ReservaEntity a una lista de
     * ReservaDetailDTO
     *
     * @param entityList Lista de ReservaEntity a convertir
     * @return Lista de ReservaDetailDTO convertida
     *
     */
    private List<ReservaDetailDTO> listEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }

    public void existsBiblioteca(Long bibliotecaId) {
        BiblioDetailDTO biblioteca = new BiblioDetailDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }
    
    public void existsUsuario(Long usuarioId) {
        UsuarioDetailDTO usuario = new UsuarioDetailDTO(usuarioLogic.getUsuario(usuarioId));
        if (usuario == null) {
            throw new WebApplicationException("El usuario no existe", 404);
        }
    }
    
    public void existsRecurso(Long recursoId, String tipoRecurso)
    {
        if(tipoRecurso.equals(RecursoDTO.LIBRO))
        {
            LibroDetailDTO libro = new LibroDetailDTO(libroLogic.getLibro(recursoId));
            if(libro == null)
            {
            throw new WebApplicationException("El libro no existe", 404);
            }
            
              
        }
        else if(tipoRecurso.equals(RecursoDTO.VIDEO))
        {
            VideoDetailDTO video = new VideoDetailDTO(videoLogic.getVideo(recursoId));
            if(video == null)
            {
            throw new WebApplicationException("El video no existe", 404);
            }
            
              
        }
        else if(tipoRecurso.equals(RecursoDTO.SALA))
        {
            SalaDetailDTO sala = new SalaDetailDTO(salaLogic.getSala(recursoId));
            if(sala == null)
            {
            throw new WebApplicationException("La sala no existe", 404);
            }
            
              
        }
    }

    public void existsReserva(Long reservaId) {
        ReservaDetailDTO reserva = new ReservaDetailDTO(reservaLogic.getReserva(reservaId));
        if (reserva == null) {
            throw new WebApplicationException("El Departamento no existe", 404);
        }
    }

    
        /**
     * Obtiene el listado de reservas de la biblioteca.
     *
     * @return lista de reservas
     * @throws BibliotecaLogicException excepción retornada por la lógica
     */
    @GET
    @Path("reservas")
    public List<ReservaDetailDTO> getReservasBiblioteca() throws BibliotecaLogicException, ParseException {
        existsBiblioteca(bibliotecaId);
        List<ReservaEntity> reservas = reservaLogic.getReservasByBiblioteca(bibliotecaId);
        return listEntity2DTO(reservas);
    }
    
     @GET
    @Path("reservas/{reservaId: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("reservaId") Long reservaId) throws BibliotecaLogicException, ParseException {
       existsBiblioteca(bibliotecaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con bibliotecaId = {0}", bibliotecaId);
        ReservaEntity entity = reservaLogic.getReserva(reservaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con id = {0}", entity.getBiblioteca().getId());
        if (entity.getBiblioteca() != null && !bibliotecaId.equals(entity.getBiblioteca().getId())) {
            throw new WebApplicationException(404);
        }

        return new ReservaDetailDTO(entity);
    }

    
    @POST
    @Path("recurso/{tipoRecurso}/{recursoId: \\d+}/usuario/{idUsuario: \\d+}/reservas")
    @Consumes(MediaType.APPLICATION_JSON)
    public ReservaDetailDTO createReserva(@PathParam("tipoRecurso") String tipoRecurso, @PathParam("recursoId") Long recursoId,@PathParam("idUsuario") Long idUsuario, ReservaDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsUsuario(idUsuario);
        
        existsRecurso(recursoId, tipoRecurso);
        Long tipoRecursoo;
        if (tipoRecurso.equals("Libro")) {
            tipoRecursoo = 2L;
            
        }
        else if(tipoRecurso.equals("Video"))
        {
            tipoRecursoo = 1L;
        }
        else if(tipoRecurso.equals("Sala"))
        {
            tipoRecursoo = 3L;
        }
        else
        {
            throw new WebApplicationException("El tipo de recurso tiene que ser 'Video', 'Libro' o 'Sala'", 404);
        }
        
        return new ReservaDetailDTO(reservaLogic.createReserva(dto.toEntity(),bibliotecaId, tipoRecursoo, recursoId, idUsuario));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("recurso/{tipoRecurso}/{recursoId: \\d+}/usuario/{idUsuario: \\d+}/reservas{reservaId: \\d+}")
    public ReservaDetailDTO updateReserva(@PathParam("reservaId") Long reservaId,@PathParam("tipoRecurso") String tipoRecurso, @PathParam("recursoId") Long recursoId,@PathParam("idUsuario") Long idUsuario, ReservaDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsReserva(reservaId);
        existsUsuario(idUsuario);
        existsRecurso(recursoId, tipoRecurso);
        ReservaEntity entity = dto.toEntity();
        entity.setId(reservaId);
        Long tipoRecursoo;
        if (tipoRecurso.equals("Libro")) {
            tipoRecursoo = 2L;
            
        }
        else if(tipoRecurso.equals("Video"))
        {
            tipoRecursoo = 1L;
        }
        else if(tipoRecurso.equals("Sala"))
        {
            tipoRecursoo = 3L;
        }
        else
        {
            throw new WebApplicationException("El tipo de recurso tiene que ser 'Video', 'Libro' o 'Sala'", 404);
        }
        
        return new ReservaDetailDTO(reservaLogic.updateReserva(entity, bibliotecaId,tipoRecursoo, recursoId, idUsuario ));
    }
    
    /**
    @PUT
    @Path("reservas/{id: \\d+}/usuarios/idUsuario/fecha")
    public ReservaDetailDTO regresarLibro(@PathParam("id") Long id, ReservaDTO m) throws BibliotecaLogicException, ParseException {
        return reservaLogic.updateReserva1(id,m);
    }
    */
    
    @DELETE
    @Path("reservas/{reservaId: \\d+}")
    public void deleteReserva(@PathParam("reservaId") Long reservaId) throws BibliotecaLogicException  {
        existsBiblioteca(bibliotecaId);
        existsReserva(reservaId);
        reservaLogic.deleteReserva(reservaId);
    }
}

