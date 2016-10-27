/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;

/**
 *
 * @author ce.gonzalez13
 */
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class PrestamoPersistence {

    private static final Logger LOGGER = Logger.getLogger(PrestamoPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public PrestamoEntity getPrestamo(Long id) {
        LOGGER.log(Level.INFO, "Consultando prestamo con id={0}", id);
        return em.find(PrestamoEntity.class, id);
    }

    public List<PrestamoEntity> getPrestamosByUsuario(Long usuarioId) {
        LOGGER.log(Level.INFO, "Consultando todos los prestamos con usuario = {0}", usuarioId);
        TypedQuery<PrestamoEntity> q
                = em.createQuery("select u from PrestamoEntity u where u.usuario.id = :usuarioId", PrestamoEntity.class);
        q = q.setParameter("usuarioId", usuarioId); 
        return q.getResultList();
    }
    
     public List<PrestamoEntity> getPrestamosByBiblioteca(Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Consultando todos los prestamos de la id de biblioteca ={0}", bibliotecaId);
        TypedQuery q = em.createQuery("select d from PrestamoEntity d  where d.biblioteca.id = :bibliotecaId", PrestamoEntity.class);
        q = q.setParameter("bibliotecaId", bibliotecaId);
        return q.getResultList();
    }
     
      public List<PrestamoEntity> getPrestamosByRecurso(Long recursoId) {
        LOGGER.log(Level.INFO, "Consultando todos los prestamos del recurso con id={0}", recursoId);
        TypedQuery q = em.createQuery("select d from PrestamoEntity d  where d.recurso.id = :recursoId", PrestamoEntity.class);
        q = q.setParameter("recursoId", recursoId);
        return q.getResultList();
    }
      
       

    public List<PrestamoEntity> getPrestamos() {
        LOGGER.info("Consultando todos los prestamos");
        Query q = em.createQuery("select u from PrestamoEntity u");
        return q.getResultList();
    }

    public PrestamoEntity create(PrestamoEntity entity) {
        LOGGER.info("Creando un prestamo nuevo");
        em.persist(entity);
        LOGGER.info("Prestamo creado");
        return entity;
    }

    public PrestamoEntity update(PrestamoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando prestamo con id={0}", entity.getId());
        return em.merge(entity);
    }

    public PrestamoEntity delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando prestamo con id={0}", id);
        PrestamoEntity entity = em.find(PrestamoEntity.class, id);
        em.remove(entity);
        return entity;
    }
}
