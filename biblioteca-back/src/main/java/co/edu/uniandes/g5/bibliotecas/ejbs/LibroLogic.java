/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
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

    @Override
    public List<LibroEntity> getLibros() {
        return persistence.findAll();
    }

    @Override
    public LibroEntity getLibro(Long id) {
        return persistence.find(id);
    }

    @Override
    public LibroEntity createLibro(LibroEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public LibroEntity updateLibro(LibroEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteLibro(Long id) {
        persistence.delete(id);
    }

    @Override
    public LibroEntity getLibroByISBN(Long isbn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BlogEntity> getBlogsByLibro(Long idLibro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
