/*
 * VideoResource.java
 * Clase que representa el recurso "/videos"
 * Implementa varios métodos para manipular los videos
 */
package co.edu.uniandes.rest.resources.resources;
import co.edu.uniandes.rest.resources.dtos.VideoDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import co.edu.uniandes.rest.resources.mocks.VideoLogicMock;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Clase que implementa el recurso REST correspondiente a "videos".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "videos". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/cities"
 *
 * @author Asistente
 */
@Path("videos")
@Produces("application/json")
public class VideoResource
{

    VideoLogicMock videoLogic = new VideoLogicMock();

    /**
     * Obtiene el listado de videos.
     *
     * @return lista de videos
     * @throws BibliotecaLogicException excepción retornada por la lógica
     */
    @GET
    public List<VideoDTO> getVideos() throws BibliotecaLogicException {
        return videoLogic.getVideos();
    }
    
    /**
     * Obtiene el video especificado
     *
     * @param id video a buscar
     * @return el video buscado
     * @throws BibliotecaLogicException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public VideoDTO getVideo(@PathParam("id") Long id) throws BibliotecaLogicException {
        return videoLogic.getVideo(id);
    }


   
    /**
     * Agrega un video
     *
     * @param video video a agregar
     * @return datos del video a agregar
     * @throws BibliotecaLogicException cuando ya existe un video con el id
     * suministrado
     */
    @POST
    public VideoDTO createVideo(VideoDTO video) throws BibliotecaLogicException {
        return videoLogic.createVideo(video);
    }
    
    /**
     * Actualiza un video
     *
     * @param id video a actualizar
     * @param newVideo video actualizado
     * @throws BibliotecaLogicException cuando el id no se encuentra
     * @return datos del video a actualizar
     */
    @PUT
    @Path("{id: \\d+}")
    public VideoDTO updateVideo(@PathParam("id") Long id, VideoDTO newVideo) throws BibliotecaLogicException 
    {
        return videoLogic.updateVideo(id, newVideo);
    }
    
     /**
     * Elimina un video
     *
     * @param id id del video a eliminar
     * @throws BibliotecaLogicException cuando no existe el video que se quiere eliminar
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVideo(@PathParam("id") Long id) throws BibliotecaLogicException 
    {
        videoLogic.deleteVideo(id);
    }
    
    

  
}
