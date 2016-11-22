/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
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
 * @author js.prieto10
 */
@Stateless
public class BlogPersistence {
 
    private static final Logger LOGGER = Logger.getLogger(BlogPersistence.class.getName());
    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;
    
    
    public BlogEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando blog con id={0}", id);
        return em.find(BlogEntity.class, id);
    }

    public List<BlogEntity> findAll() {
        LOGGER.info("Consultando todos los blogs");
        Query q = em.createQuery("select u from BlogEntity u");
        return q.getResultList();
    }
    public BlogEntity create(BlogEntity entity) {
        LOGGER.info("Creando un blog nuevo");
        em.persist(entity);
        LOGGER.info("Blog creado");
        return entity;
    }

    public BlogEntity update(BlogEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando blog con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando blog con id={0}", id);
        BlogEntity entity = em.find(BlogEntity.class, id);
        em.remove(entity);
    }
    
    public List<BlogEntity> getBlogsLibros(Long libroId) {
        TypedQuery q = em.createQuery("select u from BlogEntity u where u.libro.id = :libroId", BlogEntity.class);
        q = q.setParameter("libroId", libroId);
        return q.getResultList();
    }
    public List<BlogEntity> getBlogsUsuario(Long usuarioId) {
        TypedQuery q = em.createQuery("select u from BlogEntity u where u.usuario.id = :usuarioId", BlogEntity.class);
        q = q.setParameter("libroId", usuarioId);
        return q.getResultList();
    }
    
    public boolean usuarioPrestoLibro(Long idUsuario, Long idLibro)
    {
        TypedQuery q = em.createQuery("select u from PrestamoEntity u where u.libro.id = :libroId and u.usuario.id = :usuarioId", PrestamoEntity.class);
        q = q.setParameter("libroId", idLibro);
        q = q.setParameter("usuarioId", idUsuario);
        List<PrestamoEntity> lista = q.getResultList();
        if (lista == null)
        return false;
        
        return false;
    }
    
    
    
}
