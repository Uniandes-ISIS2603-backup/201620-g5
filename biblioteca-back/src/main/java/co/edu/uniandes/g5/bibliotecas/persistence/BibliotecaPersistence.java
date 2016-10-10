/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

/**
 *
 * @author sf.munera10
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class BibliotecaPersistence {

    private static final Logger LOGGER = Logger.getLogger(BibliotecaPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public BibliotecaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando biblioteca con id={0}", id);
        return em.find(BibliotecaEntity.class, id);
    }

    public BibliotecaEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando biblioteca con name = {0}", name);
        TypedQuery<BibliotecaEntity> q
                = em.createQuery("select u from BibliotecaEntity u where u.name = :name", BibliotecaEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }

    public List<BibliotecaEntity> findAll() {
        LOGGER.info("Consultando todas las bibliotecas");
        Query q = em.createQuery("select u from BibliotecaEntity u");
        return q.getResultList();
    }

    public BibliotecaEntity create(BibliotecaEntity entity) {
        LOGGER.info("Creando una biblioteca nueva");
        em.persist(entity);
        LOGGER.info("Biblioteca creada");
        return entity;
    }

    public BibliotecaEntity update(BibliotecaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando biblioteca con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando biblioteca con id={0}", id);
        BibliotecaEntity entity = em.find(BibliotecaEntity.class, id);
        em.remove(entity);
    }
}
