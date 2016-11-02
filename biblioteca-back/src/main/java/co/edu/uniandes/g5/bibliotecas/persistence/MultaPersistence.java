/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;


import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
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
 * @author d.patino12
 */
@Stateless
public class MultaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MultaPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;
    
    

    public MultaEntity getMulta(Long id) {
        LOGGER.log(Level.INFO, "Consultando la multa con id={0}", id);
        return em.find(MultaEntity.class, id);
    }

    public List<MultaEntity> getMultasByUsuario(Long biblioteca, Long usuario) {
        LOGGER.log(Level.INFO, "Consultando la multa con el id  de usuario = {0}", usuario );
        TypedQuery<MultaEntity> q
                = em.createQuery("select u from MultaEntity u where u.usuario.id = :idUsuario and d.biblioteca.id = :bibliotecaId", MultaEntity.class);
        q = q.setParameter("idUsuario", usuario); 
        return q.getResultList();
    }
    
    public List<MultaEntity> getMultasByBiblioteca(Long biblioteca) {
        LOGGER.log(Level.INFO, "Consultando la multa con el id  de usuario = {0}", biblioteca );
        TypedQuery<MultaEntity> q
                = em.createQuery("select u from MultaEntity u where u.biblioteca.id = :idBiblioteca", MultaEntity.class);
        q = q.setParameter("idBiblioteca", biblioteca); 
        return q.getResultList();
    }
    
    public List<MultaEntity> getMultasByRecurso(Long biblioteca, Long recurso) {
        LOGGER.log(Level.INFO, "Consultando la multa con el id  de usuario = {0}", recurso);
        TypedQuery<MultaEntity> q
                = em.createQuery("select u from MultaEntity u where u.recurso.id = :idRecurso and d.biblioteca.id = :bibliotecaId", MultaEntity.class);
        q = q.setParameter("idRecurso", recurso); 
        return q.getResultList();
    }

    public List<MultaEntity> getMultas() {
        LOGGER.info("Consultando todas las Multas");
        Query q = em.createQuery("select u from MultaEntity u");
        return q.getResultList();
    }

    public MultaEntity create(MultaEntity entity) {
        LOGGER.info("Creando una Multa nueva");
        em.persist(entity);
        LOGGER.info("Multa creada");
        return entity;
    }

    public MultaEntity update(MultaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando la multa con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando multa con id={0}", id);
        MultaEntity entity = em.find(MultaEntity.class, id);
        em.remove(entity);
    }
}
