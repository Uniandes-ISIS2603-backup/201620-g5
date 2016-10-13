/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
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
public class MultaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MultaPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public LibroEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando libro con id={0}", id);
        return em.find(LibroEntity.class, id);
    }

    public LibroEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando libro con name = {0}", name);
        TypedQuery<LibroEntity> q
                = em.createQuery("select u from LibroEntity u where u.name = :name", LibroEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }

    public List<LibroEntity> findAll() {
        LOGGER.info("Consultando todos los libros");
        Query q = em.createQuery("select u from LibroEntity u");
        return q.getResultList();
    }

    public LibroEntity create(LibroEntity entity) {
        LOGGER.info("Creando un libro nuevo");
        em.persist(entity);
        LOGGER.info("Libro creado");
        return entity;
    }

    public LibroEntity update(LibroEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando libro con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando libro con id={0}", id);
        LibroEntity entity = em.find(LibroEntity.class, id);
        em.remove(entity);
    }
}
