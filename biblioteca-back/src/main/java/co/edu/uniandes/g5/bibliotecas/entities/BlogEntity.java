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
public class BlogEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.LibroEntity libro;
     
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity usuario;

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
     
    
    private String texto;
    
    private String autor;

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
        return autor;
    }

    public void setAutor(String autor) {
        this.autor= autor;
    }

    

}
