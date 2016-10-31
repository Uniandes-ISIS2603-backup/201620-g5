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
public class LibroPersistence {

    private static final Logger LOGGER = Logger.getLogger(LibroPersistence.class.getName());

    @PersistenceContext(unitName = "G5PU")
    protected EntityManager em;

    public LibroEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando libro con id={0}", id);
        return em.find(LibroEntity.class, id);
    }

    public LibroEntity findByName(String name, Long idBiblioteca) {
        LOGGER.log(Level.INFO, "Consultando libro con name = {0}", name);
        TypedQuery<LibroEntity> q
                = em.createQuery("select u from LibroEntity u where u.name = :name and u.biblioteca.id = :idBiblioteca", LibroEntity.class);
        q = q.setParameter("name", name);
        q = q.setParameter("idBiblioteca", idBiblioteca);
        return q.getSingleResult();
    }

    public LibroEntity findByISBN(Integer isbn, Long idBiblioteca) {
        LOGGER.log(Level.INFO, "Consultando libro con isbn = {0}", isbn);
        TypedQuery<LibroEntity> q = em.createQuery("select u from LibroEntity u where u.isbn = :isbn and u.biblioteca.id = :idBiblioteca", LibroEntity.class);
        q = q.setParameter("isbn", isbn);
        q = q.setParameter("idBiblioteca", idBiblioteca);
        return q.getSingleResult();
    }

    public List<LibroEntity> findAll() {
        LOGGER.info("Consultando todos los libros");
        Query q = em.createQuery("select u from LibroEntity u");
        return q.getResultList();
    }

    public List<LibroEntity> findAllInBiblioteca(Long idBiblioteca) {
        LOGGER.log(Level.INFO, "Consultando todos los libros de la biblioteca id={0}", idBiblioteca);
        TypedQuery q = em.createQuery("select u from LibroEntity u where u.biblioteca.id= :idBiblioteca", LibroEntity.class);
        q = q.setParameter("idBiblioteca", idBiblioteca);
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
