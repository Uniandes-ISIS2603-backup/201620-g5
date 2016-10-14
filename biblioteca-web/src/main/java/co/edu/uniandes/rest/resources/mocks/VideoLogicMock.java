package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso Videos (Mock del servicio REST)
 * author: ce.gonzalez13
 */
import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;



import co.edu.uniandes.rest.resources.dtos.VideoDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;

/*
 * VideoLogicMock
 * Mock del recurso Videos (Mock del servicio REST)
 */

public class VideoLogicMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(VideoLogicMock.class.getName());
	
	// listado de videos
    private static ArrayList<VideoDTO> videos;
    
    private BiblioLogicMock biblioMock;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public VideoLogicMock() {

    	if (videos == null) {
                try {
                    videos = new ArrayList<>();
                    biblioMock = new BiblioLogicMock();
                    BiblioDTO b1 = biblioMock.getCities().get(0);
                    BiblioDTO b2 = biblioMock.getCities().get(1);
                    BiblioDTO b3 = biblioMock.getCities().get(2);
                    videos.add(new VideoDTO(1L, "The Revenant", "Alejandro G. Iñarritu",152L, 2015L, "Accion", 2L , "Oso contra hombre",false, b1));
                    videos.add(new VideoDTO(2L, "Mermaids: The Body Found", "Sid Bennet", 27L, 2012L,"Documental", 20L, "Sirenas", true , b2));
                    videos.add(new VideoDTO(3L, "The Wolf of Wall Street","Martin Scorsese",180L, 2013L , "Pelicula Biográfica", 5L, "Dinero y locura", false,b3  ));
                } catch (BibliotecaLogicException ex) {
                    Logger.getLogger(VideoLogicMock.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de videos");
    	logger.info("videos" + videos );
    }    
    
	/**
	 * Obtiene el listado de videos. 
	 * @return lista de videos
	 * @throws BibliotecaLogicException cuando no existe la lista en memoria  
	 */    
    public ArrayList<VideoDTO> getVideos() throws BibliotecaLogicException {
    	if (videos == null) {
    		logger.severe("Error interno: lista de videos no existe.");
    		throw new BibliotecaLogicException("Error interno: lista de videos no existe.");    		
    	}
    	
    	logger.info("retornando todas las ciudades");
    	return videos;
    }
    
    public VideoDTO getVideo(Long idVideo) throws BibliotecaLogicException
    {
        if (videos == null) {
    		logger.severe("Error interno: lista de videos no existe.");
    		throw new BibliotecaLogicException("Error interno: lista de videos no existe.");    		
    	}
    	// busca la ciudad con el id suministrado
	        for (VideoDTO video : videos) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(video.getId(), idVideo)){
	            	logger.info("retornando la ciudad buscada");
                        return video;
	            }
	        }
                logger.severe("No existe un video con ese id");
	        throw new BibliotecaLogicException("No existe un video con ese id");
    	
    }

 

    /**
     * Agrega un video a la lista.
     * @param newVideo video a adicionar
     * @throws BibliotecaLogicException cuando ya existe un video con el id suministrado
     * @return video agregado
     */
    public VideoDTO createVideo(VideoDTO newVideo) throws BibliotecaLogicException {
    	logger.info("recibiendo solicitud de agregar video " + newVideo);
    	
    	// el nuevo video tiene id ?
    	if ( newVideo.getId() != null ) {
	    	// busca el video con el id suministrado
	        for (VideoDTO video : videos) {
	        	// si existe un video con ese id
	            if (Objects.equals(video.getId(), newVideo.getId())){
	            	logger.severe("Ya existe un video con ese id");
	                throw new BibliotecaLogicException("Ya existe un video con ese id");
	            }
	        }
	        
	    // el nuevo video no tiene id ? 
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para el nuevo video");
    		long newId = 1;
	        for (VideoDTO video : videos) {
	            if (newId <= video.getId()){
	                newId =  video.getId() + 1;
	            }
	        }
	        newVideo.setId(newId);
    	}
    	
        // agrega el video
    	logger.info("agregando video " + newVideo);
        videos.add(newVideo);
        return newVideo;
    }

    /**
     * Actualiza un video de la lista.
     * @param newVideo video a actualizar
     * @throws BibliotecaLogicException cuando no existe un video con el id suministrado
     * @return video actualizado
     */
    public VideoDTO updateVideo(Long idVideo, VideoDTO newVideo) throws BibliotecaLogicException
    {
    	logger.info("recibiendo solicitud de actualizar video " + idVideo);
    	
	    	// busca el video con el id suministrado
	        for (VideoDTO video : videos) 
                {
	        	// si existe un video con ese id
	            if (Objects.equals(video.getId(), idVideo)){
                         // actualiza el video
                        logger.info("actualizando video " + idVideo );
	            	video.setName(newVideo.getName());
                        video.setId(newVideo.getId());
                        video.setAnioPublicacion(newVideo.getAnioPublicacion());
                        video.setDirector(newVideo.getDirector());
                        video.setDuracion(newVideo.getDuracion());
                        video.setGenero(newVideo.getGenero());
                        video.setNumEjemplares(newVideo.getNumEjemplares());
                        video.setSinopsis(newVideo.getSinopsis());
                        video.setEsOnline(newVideo.getEsOnline());
                        return newVideo;
	            }
	        }
	        
               
                 logger.severe("No existe un video con ese id");
	         throw new BibliotecaLogicException("No existe un video con ese id");

    }
    		
      	
       
    
    
    /**
     * Elimina un video de la lista.
     * @param id video a eliminar
     * @throws BibliotecaLogicException cuando no existe un video con el id suministrado
     */
    public void deleteVideo(Long id) throws BibliotecaLogicException 
    {
    	logger.info("recibiendo solicitud de eliminar video " + id);
    	
    	// el video tiene id ?
       
	    	// busca el video con el id suministrado
	        for (VideoDTO video : videos) 
                {
	        	// si existe un video con ese id
	            if (Objects.equals(video.getId(), id))
                    {
                         // elimina la ciudad
                        logger.info("eliminando video " + id);
                        videos.remove(video);
                        return;
	            }
	        }
               
        logger.severe("No existe un video con ese id");
	throw new BibliotecaLogicException("No existe un video con ese id");
  
                    
	        
	
    	 
       
    }
   
}
