/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.api;

import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import java.util.List;

/**
 *
 * @author s.rojas19
 */
public interface IVideoLogic {
     public List<VideoEntity> getVideos();
    public VideoEntity getVideo(Long id);
    public VideoEntity createVideo(VideoEntity entity); 
    public VideoEntity updateVideo(VideoEntity entity);
    public void deleteVideo(Long id);
}
