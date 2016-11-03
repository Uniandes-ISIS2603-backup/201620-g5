/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.BibliotecaPersistence;
import co.edu.uniandes.g5.bibliotecas.persistence.LibroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.rojas19
 */
@Stateless
public class LibroLogic implements ILibroLogic {

    @Inject
    private LibroPersistence persistence;

    @Inject
    BibliotecaPersistence bibliotecaPersistence;

    @Override
    public List<LibroEntity> getLibros() {
        return persistence.findAll();
    }

    @Override
    public LibroEntity getLibro(Long id) {
        return persistence.find(id);
    }

    @Override
    public LibroEntity createLibro(LibroEntity entity) throws BibliotecaLogicException {
        LibroEntity alreadyExists = persistence.findByISBN(entity.getIsbn(), entity.getBiblioteca().getId());
        BibliotecaEntity bibliotecaExiste = bibliotecaPersistence.find(entity.getBiblioteca().getId());
        if (bibliotecaExiste == null) {
            throw new BibliotecaLogicException("La biblioteca a la que el libro pertenece no existe");
        } else if (alreadyExists != null) {
            throw new BibliotecaLogicException("Ya existe un libro con el mismo isbn en la misma biblioteca");
        } else if (entity.getEjemplaresDisponibles() < 0 || entity.getNumEjemplares() < 0) {
            throw new BibliotecaLogicException("Un libro no puede tener un numero negativo de ejemplares");
        } else if (entity.getNumEjemplares() < entity.getEjemplaresDisponibles()) {
            throw new BibliotecaLogicException("Un libro no puede tener mas ejemplares disponibles que su cantidad total");
        } else if (entity.getIsbn() > 9790000000000L || entity.getIsbn() < 9780000000000L) {
            throw new BibliotecaLogicException("ISBN invalido");
        } else {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public LibroEntity updateLibro(LibroEntity entity) throws BibliotecaLogicException {
        LibroEntity alreadyExists = persistence.findByISBN(entity.getIsbn(), entity.getBiblioteca().getId());
        BibliotecaEntity bibliotecaExiste = bibliotecaPersistence.find(entity.getBiblioteca().getId());
        if (bibliotecaExiste == null) {
            throw new BibliotecaLogicException("La biblioteca a la que el libro pertenece no existe");
        } else if (alreadyExists != null && alreadyExists.getId() != entity.getId()) {
            throw new BibliotecaLogicException("Ya existe un libro con el nuevo isbn en la biblioteca");
        } else if (entity.getEjemplaresDisponibles() < 0 || entity.getNumEjemplares() < 0) {
            throw new BibliotecaLogicException("Un libro no puede tener un numero negativo de ejemplares");
        } else if (entity.getNumEjemplares() < entity.getEjemplaresDisponibles()) {
            throw new BibliotecaLogicException("Un libro no puede tener mas ejemplares disponibles que su cantidad total");
        } else if (entity.getIsbn() > 9790000000000L || entity.getIsbn() < 9780000000000L) {
            throw new BibliotecaLogicException("ISBN invalido");
        } else {
            return persistence.update(entity);
        }
    }

    @Override
    public void deleteLibro(Long id) {
        persistence.delete(id);
    }

    @Override
    public LibroEntity getLibroByISBN(Long isbn, Long idBiblioteca) {
        return persistence.findByISBN(isbn, idBiblioteca);
    }

    @Override
    public LibroEntity getLibroByName(String name, Long idBiblioteca) {
        return persistence.findByName(name, idBiblioteca);
    }

    @Override
    public List<LibroEntity> getLibrosByBiblioteca(Long idBiblioteca) {
        return persistence.findAllInBiblioteca(idBiblioteca);
    }

}
