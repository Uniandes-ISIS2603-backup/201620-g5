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
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class SalaPersistence {

    private static final Logger LOGGER = Logger.getLogger(SalaPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public SalaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando sala con id={0}", id);
        return em.find(SalaEntity.class, id);
    }

    public List<SalaEntity> findAll() {
        LOGGER.info("Consultando todas las salas");
        Query q = em.createQuery("select u from SalaEntity u");
        return q.getResultList();
    }

    public List<SalaEntity> findAllInBiblioteca(Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Consultando todas las salas de la biblioteca id={0}", bibliotecaId);
        TypedQuery q = em.createQuery("select d from SalaEntity d  where d.biblioteca.id = :bibliotecaId", SalaEntity.class);
        q = q.setParameter("bibliotecaId", bibliotecaId);
        return q.getResultList();
    }

    public SalaEntity create(SalaEntity entity) {
        LOGGER.info("Creando una sala nueva");
        em.persist(entity);
        LOGGER.info("Sala creada");
        return entity;
    }

    public SalaEntity update(SalaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando sala con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el deptarment
     * crrespondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando sala con id={0}", id);
        SalaEntity entity = em.find(SalaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
