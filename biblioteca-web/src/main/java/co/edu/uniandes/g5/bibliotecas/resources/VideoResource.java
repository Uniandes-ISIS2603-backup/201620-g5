/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IVideoLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDetailDTO;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.edu.uniandes.g5.bibliotecas.dtos.VideoDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.VideoDetailDTO;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso REST correspondiente a "videos".
 *
 * @author s.rojas19
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
public class VideoResource {

    /**
     * Mock del modelo de datos para la clase Video
     */
    @Inject
    private IVideoLogic videoLogic;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;

    private List<VideoDetailDTO> listEntity2DetailDTO(List<VideoEntity> entityList) {
        List<VideoDetailDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDetailDTO(entity));
        }
        return list;
    }

    private List<VideoDTO> listEntity2DTO(List<VideoEntity> entityList) {
        List<VideoDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDTO(entity));
        }
        return list;
    }

    public void existsBiblioteca(Long bibliotecaId) {
        BiblioDetailDTO biblioteca = new BiblioDetailDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsVideo(Long videoId) {
        VideoEntity entity = videoLogic.getVideo(videoId);
        if (entity == null) {
            throw new WebApplicationException("El video no existe", 404);
        }
    }

    /**
     * Obtiene el listado de videos.
     *
     * @return lista de videos
     * @throws BibliotecaLogicException retornada por logica.
     */
    @GET
    @Path("videos")
    public List<VideoDTO> getVideos() {
        List<VideoEntity> videos = videoLogic.getVideos();
        return listEntity2DTO(videos);
    }

    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/videos")
    public List<VideoDTO> getVideosByBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) {
        List<VideoEntity> videos = videoLogic.getVideosByBiblioteca(idBiblioteca);
        return listEntity2DTO(videos);
    }

    /**
     * Obtiene el video con id por parametro
     *
     * @param id id del video a retornar
     * @return Video con id dado por parametro
     */
    @GET
    @Path("videos/{id: \\d+}")
    public VideoDetailDTO getVideo(@PathParam("id") Long id) {
        VideoEntity resultado = videoLogic.getVideo(id);
        if (resultado == null) {
            throw new WebApplicationException(404);
        }
        return new VideoDetailDTO(resultado);
    }

    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/videos/name/{name}")
    public VideoDetailDTO getVideoByName(@PathParam("id") Long id, @PathParam("name") String name) {
        VideoEntity resultado = videoLogic.getVideoByName(name, id);
        if (resultado == null) {
            throw new WebApplicationException(404);
        }
        return new VideoDetailDTO(resultado);
    }

    /**
     * Crea un video
     *
     * @param video video a agregar
     * @return video agregado
     */
    @POST
    @Path("bibliotecas/{idBiblioteca: \\d+}/videos")
    public VideoDTO createVideo(VideoDTO video, @PathParam("idBiblioteca") Long idBiblioteca) {
        try {
            VideoEntity entity = video.toEntity();
            VideoEntity respuesta = videoLogic.createVideo(entity, idBiblioteca);
            return new VideoDetailDTO(respuesta);
        } catch (BibliotecaLogicException e) {
            throw new WebApplicationException(e.getMessage(), 404);
        }
    }

    /**
     * Actualiza un video
     *
     * @param id id del video a actualizar
     * @param video objeto con atributos a cambiar del video
     * @return video actualizado
     */
    @PUT
    @Path("bibliotecas/{idBiblioteca: \\d+}/videos/{id: \\d+}")
    public VideoDetailDTO updateVideo(@PathParam("id") Long id, @PathParam("idBiblioteca") Long idBiblioteca, VideoDTO video) {
        try {
            existsBiblioteca(idBiblioteca);
            existsVideo(id);
            VideoEntity entity = video.toEntity();
            entity.setId(id);
            return new VideoDetailDTO(videoLogic.updateVideo(entity, idBiblioteca));
        } catch (BibliotecaLogicException ex) {
            throw new WebApplicationException(ex.getMessage(), 404);
        }

    }

    /**
     * Elimina un video
     *
     * @param id id del video a eliminar
     */
    @DELETE
    @Path("videos/{id: \\d+}")
    public void deleteVideo(@PathParam("id") Long id) {
        existsVideo(id);
        videoLogic.deleteVideo(id);
    }
}
