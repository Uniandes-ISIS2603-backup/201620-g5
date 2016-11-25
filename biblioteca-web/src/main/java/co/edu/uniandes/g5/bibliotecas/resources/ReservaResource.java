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
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.LibroDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.ReservaDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.ReservaDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.RecursoDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.SalaDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.UsuarioDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.VideoDTO;
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
@Path("")
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

    /**
     * Convierte una lista de ReservaEntity a una lista de ReservaDetailDTO
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
        BiblioDTO biblioteca = new BiblioDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsUsuario(Long usuarioId) {
        UsuarioDTO usuario = new UsuarioDTO(usuarioLogic.getUsuario(usuarioId));
        if (usuario == null) {
            throw new WebApplicationException("El usuario no existe", 404);
        }
    }

    public void existsRecurso(Long recursoId, String tipoRecurso) {
        if (tipoRecurso.equals(RecursoDTO.LIBRO)) {
            LibroDTO libro = new LibroDTO(libroLogic.getLibro(recursoId));
            if (libro == null) {
                throw new WebApplicationException("El libro no existe", 404);
            }

        } else if (tipoRecurso.equals(RecursoDTO.VIDEO)) {
            VideoDTO video = new VideoDTO(videoLogic.getVideo(recursoId));
            if (video == null) {
                throw new WebApplicationException("El video no existe", 404);
            }

        } else if (tipoRecurso.equals(RecursoDTO.SALA)) {
            SalaDTO sala = new SalaDTO(salaLogic.getSala(recursoId));
            if (sala == null) {
                throw new WebApplicationException("La sala no existe", 404);
            }

        }
    }

    public void existsReserva(Long reservaId) {
        ReservaDTO reserva = new ReservaDTO(reservaLogic.getReserva(reservaId));
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
    public List<ReservaDetailDTO> getReservas() throws BibliotecaLogicException {
        List<ReservaEntity> reservas = reservaLogic.getReservas();
        return listEntity2DTO(reservas);
    }

    @GET
    @Path("bibliotecas/{bibliotecaId: \\d+}/tipoRecurso/{tipoRecurso}/recurso/{recursoId: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservasByRecurso(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("recursoId") Long recursoId, @PathParam("tipoRecurso") String tipoRecurso) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsRecurso(recursoId, tipoRecurso);
        List<ReservaEntity> reservas = reservaLogic.getReservasByRecurso(bibliotecaId, recursoId);
        return listEntity2DTO(reservas);
    }
    
    @GET
    @Path("bibliotecas/{bibliotecaId: \\d+}/libros/{recursoId: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservasByLibro(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("recursoId") Long recursoId) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        List<ReservaEntity> reservas = reservaLogic.getReservasByRecurso(bibliotecaId, recursoId);
        return listEntity2DTO(reservas);
    }
    
     @GET
    @Path("bibliotecas/{bibliotecaId: \\d+}/videos/{recursoId: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservasByVideo(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("recursoId") Long recursoId) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        List<ReservaEntity> reservas = reservaLogic.getReservasByRecurso(bibliotecaId, recursoId);
        return listEntity2DTO(reservas);
    }

    @GET
    @Path("bibliotecas/{bibliotecaId: \\d+}/usuario/{idUsuario: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservasByUsuario(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("idUsuario") Long usuarioId) throws BibliotecaLogicException, ParseException {
        existsBiblioteca(bibliotecaId);
        existsUsuario(usuarioId);
        List<ReservaEntity> reservas = reservaLogic.getReservasByUsuario(bibliotecaId, usuarioId);
        return listEntity2DTO(reservas);
    }

    @GET
    @Path("bibliotecas/{bibliotecaId: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservasBiblioteca(@PathParam("bibliotecaId") Long bibliotecaId) throws BibliotecaLogicException, ParseException {
        existsBiblioteca(bibliotecaId);
        List<ReservaEntity> reservas = reservaLogic.getReservasByBiblioteca(bibliotecaId);
        return listEntity2DTO(reservas);
    }

    @GET
    @Path("/bibliotecas/{bibliotecaId: \\d+}/reservas/{reservaId: \\d+}")
    public ReservaDTO getReserva(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("reservaId") Long reservaId) throws BibliotecaLogicException, ParseException {
        existsBiblioteca(bibliotecaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con bibliotecaId = {0}", bibliotecaId);
        ReservaEntity entity = reservaLogic.getReserva(reservaId);
        LOGGER.log(Level.INFO, "Consultando biblioteca con id = {0}", entity.getBiblioteca().getId());
        if (entity.getBiblioteca() != null && !bibliotecaId.equals(entity.getBiblioteca().getId())) {
            throw new WebApplicationException(404);
        }

        return new ReservaDTO(entity);
    }

    /**
     * R12
     *
     * @param id id del libro a consultar
     * @return lista de reservas activos del libro
     *
     * @GET
     * @Path("libros/{idLibro: \\d+}/reservas") public List<ReservaDetailDTO>
     * getReservasLibro(@PathParam("idLibro") Long id){ return
     * reservaLogic.getReservasLibro(id); }
     *
     * /**
     * Agrega un reserva a un usuario
     *
     * @param reserva reserva a agregar
     * @param idUsuario id del usuario para agregarle una multa
     * @return datos de la multa a agregar suministrado
     *
     *
     * @POST
     * @Path("usuarios/{idUsuario: \\d+}/reservas") public ReservaDetailDTO
     * createReserva(ReservaDTO reserva, @PathParam("idUsuario") Long idUsuario)
     * throws ParseException, BibliotecaLogicException { return
     * reservaLogic.createReserva(reserva, idUsuario); }
     *
     * @GET
     * @Path("usuarios/{idUsuario: \\d+}/reservas/{id: \\d+}") public
     * ReservaDetailDTO getReservaDeUsuario(@PathParam("id") int id,
     * @PathParam("idUsuario") Long idUsuario) throws BibliotecaLogicException,
     * ParseException { return reservaLogic.getReservaDeUsuario(id, idUsuario);
     * }
     * @PUT
     * @Path("reservas/{id: \\d+}/{idUsuario: \\d+}") public ReservaDetailDTO
     * updateReserva(@PathParam("id") Long id, ReservaDTO m,
     * @PathParam("idUsuario") Long idUsuario) throws BibliotecaLogicException,
     * ParseException { return reservaLogic.updateReservaDeUsuario(id, m,
     * idUsuario); }
     *
     *
     * @DELETE
     * @Path("reservas/{id: \\d+}/{idUsuario: \\d+}") public void
     * deleteMulta(@PathParam("id") int id, @PathParam("idUsuario") int
     * idUsuario) throws BibliotecaLogicException, ParseException {
     * reservaLogic.deleteReservaDeUsuario(id, idUsuario); }
     *
     */
    @POST
    @Path("/bibliotecas/{bibliotecaId: \\d+}/tipoRecurso/{tipoRecurso}/recurso/{recursoId: \\d+}/usuario/{idUsuario: \\d+}/reservas")
    @Consumes(MediaType.APPLICATION_JSON)
    public ReservaDetailDTO createReserva(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("tipoRecurso") String tipoRecurso, @PathParam("recursoId") Long recursoId, @PathParam("idUsuario") Long idUsuario, ReservaDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsUsuario(idUsuario);

        existsRecurso(recursoId, tipoRecurso);
        Long tipoRecursoo;
        if (tipoRecurso.equals("Libro")) {
            tipoRecursoo = 2L;

        } else if (tipoRecurso.equals("Video")) {
            tipoRecursoo = 1L;
        } else if (tipoRecurso.equals("Sala")) {
            tipoRecursoo = 3L;
        } else {
            throw new WebApplicationException("El tipo de recurso tiene que ser 'Video', 'Libro' o 'Sala'", 404);
        }

        return new ReservaDetailDTO(reservaLogic.createReserva(dto.toEntity(), bibliotecaId, tipoRecursoo, recursoId, idUsuario));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/bibliotecas/{bibliotecaId: \\d+}/tipoRecurso/{tipoRecurso}/recurso/{recursoId: \\d+}/usuario/{idUsuario: \\d+}/reservas/{reservaId: \\d+}")
    public ReservaDetailDTO updateReserva(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("reservaId") Long reservaId, @PathParam("tipoRecurso") String tipoRecurso, @PathParam("recursoId") Long recursoId, @PathParam("idUsuario") Long idUsuario, ReservaDetailDTO dto) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsReserva(reservaId);
        existsUsuario(idUsuario);
        existsRecurso(recursoId, tipoRecurso);
        ReservaEntity entity = dto.toEntity();
        entity.setId(reservaId);
        Long tipoRecursoo;
        if (tipoRecurso.equals("Libro")) {
            tipoRecursoo = 2L;

        } else if (tipoRecurso.equals("Video")) {
            tipoRecursoo = 1L;
        } else if (tipoRecurso.equals("Sala")) {
            tipoRecursoo = 3L;
        } else {
            throw new WebApplicationException("El tipo de recurso tiene que ser 'Video', 'Libro' o 'Sala'", 404);
        }

        return new ReservaDetailDTO(reservaLogic.updateReserva(entity, bibliotecaId, tipoRecursoo, recursoId, idUsuario));
    }

    /**
     * @PUT @Path("reservas/{id: \\d+}/usuarios/idUsuario/fecha") public
     * ReservaDetailDTO regresarLibro(@PathParam("id") Long id, ReservaDTO m)
     * throws BibliotecaLogicException, ParseException { return
     * reservaLogic.updateReserva1(id,m); }
     */
    @DELETE
    @Path("/bibliotecas/{bibliotecaId: \\d+}/reservas/{reservaId: \\d+}")
    public void deleteReserva(@PathParam("bibliotecaId") Long bibliotecaId, @PathParam("reservaId") Long reservaId) throws BibliotecaLogicException {
        existsBiblioteca(bibliotecaId);
        existsReserva(reservaId);
        reservaLogic.deleteReserva(reservaId);
    }
}
