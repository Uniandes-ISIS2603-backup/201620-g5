/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.api;

import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import java.util.List;

/**
 * Interfaz de Logic
 * @author s.rojas19
 */
public interface ILibroLogic {
    public List<LibroEntity> getLibros();
    
    public LibroEntity getLibro(Long id);
    
    public LibroEntity getLibroByISBN(Long isbn);
    
    public LibroEntity createLibro(LibroEntity entity); 
    
    public LibroEntity updateLibro(LibroEntity entity);
    
    public void deleteLibro(Long id);
    
    public List<BlogEntity> getBlogsByLibro(Long idLibro);
}
