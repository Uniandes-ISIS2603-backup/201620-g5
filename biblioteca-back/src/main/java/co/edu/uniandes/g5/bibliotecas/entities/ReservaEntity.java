/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.entities;

/**
 *
 * @author ce.gonzalez13
 */
import java.io.Serializable;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class ReservaEntity extends BaseEntity implements Serializable {

    
    
    public static final Long LIBRO = 2L;
    public static final Long VIDEO = 1L;
    public static final Long SALA = 3L;

    
    
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity usuario;

    
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity biblioteca;
    
    private Long tipoRecurso;

     @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity   recurso;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstaA() {
        return estaA;
    }

    public void setEstaA(boolean estaA) {
        this.estaA = estaA;
    }
    private boolean estaA;

    

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Long getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(Long tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public RecursoEntity getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoEntity recurso) {
        this.recurso = recurso;
    }



    public boolean isEstaActivo() {
        return estaA;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaA = estaActivo;
    }
        

}
