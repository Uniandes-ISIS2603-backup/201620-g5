/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.persistence;


import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
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
 * @author s.rojas19
 */
@Stateless
public class UsuarioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public UsuarioEntity getUsuario(Long id) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id={0}", id);
        return em.find(UsuarioEntity.class, id);
    }

    public UsuarioEntity getByName(String name) {
        LOGGER.log(Level.INFO, "Consultando el usuario con name = {0}", name);
        TypedQuery<UsuarioEntity> q
                = em.createQuery("select u from UsuarioEntity u where u.name = :name", UsuarioEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }
    
     public List<UsuarioEntity> getUsuariosByBiblioteca(Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Consultando todos los usuarios de la biblioteca con id ={0}", bibliotecaId);
        TypedQuery q = em.createQuery("select d from UsuarioEntity d  where d.biblioteca.id = :bibliotecaId", UsuarioEntity.class);
        q = q.setParameter("bibliotecaId", bibliotecaId);
        return q.getResultList();
    }


    public List<UsuarioEntity> getUsuarios() {
        LOGGER.info("Consultando todos los usuarios");
        Query q = em.createQuery("select u from UsuarioEntity u");
        return q.getResultList();
    }

    public UsuarioEntity create(UsuarioEntity entity) {
        LOGGER.info("Creando un usuario nuevo");
        em.persist(entity);
        LOGGER.info("Usuario creado");
        return entity;
    }

    public UsuarioEntity update(UsuarioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando el usuario con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando el usuario con id={0}", id);
        UsuarioEntity entity = em.find(UsuarioEntity.class, id);
        em.remove(entity);
    }
    
   

}
