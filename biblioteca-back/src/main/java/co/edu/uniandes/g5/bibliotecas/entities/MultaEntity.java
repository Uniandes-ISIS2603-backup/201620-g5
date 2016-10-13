/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.entities;

/**
 *
 * @author d.patino12
 */
import java.io.Serializable;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class MultaEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity biblioteca;
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity   usuario;
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity   recurso;
   
    
    private double costo;
    
    private Date fecha;

    /**
     * Obtiene el atributo biblioteca.
     *
     * @return atributo biblioteca.
     *
     */
    public co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    /**
     * Establece el valor del atributo biblioteca.
     *
     * @param biblioteca nuevo valor del atributo
     *
     */
    public void setBiblioteca(co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
    }
    /**
     * 
     * @return 
     */

    public UsuarioEntity getUsuario() {
        return usuario;
    }
    /**
     * 
     * @param usuario 
     */

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    /**
     * 
     * @return 
     */

    public RecursoEntity getRecurso() {
        return recurso;
    }
    /**
     * 
     * @param recurso 
     */

    public void setRecurso(RecursoEntity recurso) {
        this.recurso = recurso;
    }
    
    
    /**
     * devuelve el costo de la multa
     * @return costo de la multa
     */


    public double getCosto() {
        return costo;
    }
    /**
     * cambia el costo de la multa por el valor que le entra por parametro
     * @param costo con el que se va a cambiar
     */

    public void setCosto(double costo) {
        this.costo = costo;
    }
    /**
     * devuelve la fecha de la multa
     * @return fecha de la multa
     */

    public Date getFecha() {
        return fecha;
    }
    /**
     * Cambia la fecha de la multa por el valor que le entra por parametro
     * @param fecha con la que se va a cambiar
     */

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}
