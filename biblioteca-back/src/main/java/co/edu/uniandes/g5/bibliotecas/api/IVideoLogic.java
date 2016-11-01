/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.api;

import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.List;

/**
 *
 * @author s.rojas19
 */
public interface IVideoLogic {
     public List<VideoEntity> getVideos();
    
    public List<VideoEntity> getVideosByBiblioteca(Long idBiblioteca);
    
    public VideoEntity getVideo(Long id);
        
    public VideoEntity getVideoByName(String name, Long idBiblioteca);
    
    public VideoEntity createVideo(VideoEntity entity) throws BibliotecaLogicException; 
    
    public VideoEntity updateVideo(VideoEntity entity) throws BibliotecaLogicException;
    
    public void deleteVideo(Long id);
     
}
