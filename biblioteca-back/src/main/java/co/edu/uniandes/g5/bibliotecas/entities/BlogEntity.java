/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author js.prieto10
 */
@Entity
public class BlogEntity extends RecursoEntity implements Serializable{
    
    private Long idLibro;
    private String texto;
    private String nombreAutor;
    
   @OneToOne(mappedBy = "libro")
    private LibroEntity libro = new LibroEntity(); 
    
    public LibroEntity getLibro()
    {
        return libro;
    }
    
    public void setLibro(LibroEntity libro)
    {
        this.libro = libro;
    }

   
   

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    

}
