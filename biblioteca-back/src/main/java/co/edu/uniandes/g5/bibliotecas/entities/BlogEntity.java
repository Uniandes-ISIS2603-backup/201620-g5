/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.prieto10
 */
@Entity
public class BlogEntity extends RecursoEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.LibroEntity libro;
     
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    private String texto;
    private String nombreAutor;

    
    public LibroEntity getLibro()
    {
        return libro;
    }
    
    public void setLibro(LibroEntity libro)
    {
        this.libro = libro;
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
