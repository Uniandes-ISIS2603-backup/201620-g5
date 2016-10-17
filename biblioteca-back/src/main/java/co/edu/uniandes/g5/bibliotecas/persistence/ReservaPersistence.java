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
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ReservaPersistence {

    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public ReservaEntity getReserva(Long id) {
        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", id);
        return em.find(ReservaEntity.class, id);
    }

    public List<ReservaEntity> getReservasByUsuario(Long usuarioId) {
        LOGGER.log(Level.INFO, "Consultando todas las reservas con usuario = {0}", usuarioId);
        TypedQuery<ReservaEntity> q
                = em.createQuery("select u from ReservaEntity u where u.usuario.id = :usuarioId", ReservaEntity.class);
        q = q.setParameter("usuarioId", usuarioId); 
        return q.getResultList();
    }
    
     public List<ReservaEntity> getReservasByBiblioteca(Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Consultando todas las reservas de la id de biblioteca ={0}", bibliotecaId);
        TypedQuery q = em.createQuery("select d from ReservaEntity d  where d.biblioteca.id = :bibliotecaId", ReservaEntity.class);
        q = q.setParameter("bibliotecaId", bibliotecaId);
        return q.getResultList();
    }
     
      public List<ReservaEntity> getReservasByRecurso(Long recursoId) {
        LOGGER.log(Level.INFO, "Consultando todas las reservas del recurso con id={0}", recursoId);
        TypedQuery q = em.createQuery("select d from ReservaEntity d  where d.recurso.id = :recursoId", PrestamoEntity.class);
        q = q.setParameter("recursoId", recursoId);
        return q.getResultList();
    }
      
      

    public List<ReservaEntity> getReservas() {
        LOGGER.info("Consultando todas las reservas");
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();
    }

    public ReservaEntity create(ReservaEntity entity) {
        LOGGER.info("Creando una reserva nueva");
        em.persist(entity);
        LOGGER.info("Prestamo creado");
        return entity;
    }

    public ReservaEntity update(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando reserva con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando prestamo con id={0}", id);
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        em.remove(entity);
    }
}
