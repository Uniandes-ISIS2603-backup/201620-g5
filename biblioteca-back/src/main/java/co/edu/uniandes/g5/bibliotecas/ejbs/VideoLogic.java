/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IVideoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.VideoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.rojas19
 */
@Stateless
public class VideoLogic implements IVideoLogic {

    @Inject
    private VideoPersistence persistence;

    @Override
    public List<VideoEntity> getVideos() {
        return persistence.findAll();
    }

    @Override
    public VideoEntity getVideo(Long id) {
        return persistence.find(id);
    }

    @Override
    public VideoEntity createVideo(VideoEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public VideoEntity updateVideo(VideoEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteVideo(Long id) {
        persistence.delete(id);
    }

}
