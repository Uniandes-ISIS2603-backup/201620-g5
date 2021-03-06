/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.api;

import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.List;

/**
 * Interfaz de Logic
 * @author s.rojas19
 */
public interface ILibroLogic {
    public List<LibroEntity> getLibros();
    
    public List<LibroEntity> getLibrosByBiblioteca(Long idBiblioteca);
    
    public LibroEntity getLibro(Long id);
    
    public LibroEntity getLibroByISBN(Long isbn, Long idBiblioteca);
    
    public LibroEntity getLibroByName(String name, Long idBiblioteca);
    
    
    public LibroEntity createLibro(LibroEntity entity, Long idBiblioteca) throws BibliotecaLogicException; 
    
    public LibroEntity updateLibro(LibroEntity entity, Long idBiblioteca) throws BibliotecaLogicException;
    
    public void deleteLibro(Long id);
     
}
