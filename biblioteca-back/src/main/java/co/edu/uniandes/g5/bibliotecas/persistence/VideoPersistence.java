/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.rojas19
 */
@Stateless
public class VideoPersistence {
    private static final Logger LOGGER = Logger.getLogger(LibroPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public VideoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando video con id={0}", id);
        return em.find(VideoEntity.class, id);
    }

    public VideoEntity findByName(String name, Long idBiblioteca) {
        LOGGER.log(Level.INFO, "Consultando video con name = {0}", name);
        TypedQuery q
                = em.createQuery("select u from VideoEntity u where u.name = :name and u.biblioteca.id = :idBiblioteca", VideoEntity.class);
        q = q.setParameter("name", name);
        q = q.setParameter("idBiblioteca", idBiblioteca);
        List<VideoEntity> similarName = q.getResultList();
        if (similarName.isEmpty()) {
            return null;
        } else {
            return similarName.get(0);
        }
    }

    public List<VideoEntity> findAll() {
        LOGGER.info("Consultando todos los videos");
        Query q = em.createQuery("select u from VideoEntity u");
        return q.getResultList();
    }

    public List<VideoEntity> findAllInBiblioteca(Long idBiblioteca){
        LOGGER.log(Level.INFO, "Consultando todos los videos de la biblioteca id={0}", idBiblioteca);
        TypedQuery q = em.createQuery("select u from VideoEntity u where u.biblioteca.id= :idBiblioteca",VideoEntity.class);
        q = q.setParameter("idBiblioteca", idBiblioteca);
        return q.getResultList();
        
    }
    
    public VideoEntity create(VideoEntity entity) {
        LOGGER.info("Creando un video nuevo");
        em.persist(entity);
        LOGGER.info("Video creado");
        return entity;
    }

    public VideoEntity update(VideoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando video con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando video con id={0}", id);
        VideoEntity entity = em.find(VideoEntity.class, id);
        em.remove(entity);
    }
    
}
