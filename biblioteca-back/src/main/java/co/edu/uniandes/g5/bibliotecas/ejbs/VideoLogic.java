/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IVideoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.BibliotecaPersistence;
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

    @Inject
    private BibliotecaPersistence bibliotecaPersistence;

    @Override
    public List<VideoEntity> getVideos() {
        return persistence.findAll();
    }

    @Override
    public VideoEntity getVideo(Long id) {
        return persistence.find(id);
    }

    @Override
    public VideoEntity createVideo(VideoEntity entity) throws BibliotecaLogicException {
        VideoEntity alreadyExists = getVideoByName(entity.getName(), entity.getBiblioteca().getId());
        BibliotecaEntity bibliotecaExiste = bibliotecaPersistence.find(entity.getBiblioteca().getId());
        if (bibliotecaExiste == null) {
            throw new BibliotecaLogicException("La biblioteca a la que el video pertenece no existe");
        } else if (alreadyExists != null) {
            throw new BibliotecaLogicException("Ya existe un video con el mismo nombre en la misma biblioteca");
        } else if (entity.getNumEjemplares() < entity.getEjemplaresDisponibles()) {
            throw new BibliotecaLogicException("Un video no puede tener mas ejemplares disponibles que su cantidad total");
        } else {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public VideoEntity updateVideo(VideoEntity entity) throws BibliotecaLogicException {
        BibliotecaEntity bibliotecaExiste = bibliotecaPersistence.find(entity.getBiblioteca().getId());
        if (bibliotecaExiste == null) {
            throw new BibliotecaLogicException("La biblioteca a la que el video pertenece no existe");
        } else if (entity.getNumEjemplares() < entity.getEjemplaresDisponibles()) {
            throw new BibliotecaLogicException("Un video no puede tener mas ejemplares disponibles que su cantidad total");
        } else {
            return persistence.update(entity);
        }
    }

    @Override
    public void deleteVideo(Long id
    ) {
        persistence.delete(id);
    }

    @Override
    public List<VideoEntity> getVideos(Long idBiblioteca
    ) {
        return persistence.findAllInBiblioteca(idBiblioteca);
    }

    @Override
    public VideoEntity getVideoByName(String name, Long idBiblioteca
    ) {
        return persistence.findByName(name, idBiblioteca);
    }

}
