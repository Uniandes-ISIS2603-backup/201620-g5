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
import java.util.Date;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class PrestamoEntity extends BaseEntity implements Serializable {

    
    
    public static final String LIBRO = "Libro";
    public static final String VIDEO = "Video";
    public static final String SALA = "Sala";

    
    
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity usuario;

    
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity biblioteca;
    
    private String tipoRecurso;

     @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity  recurso;

     private Double costo;
    private String medioPago;
    
    //@PodamStrategyValue(DateStrategy.class)
    private Date fechaInicial;
    
   // @PodamStrategyValue(DateStrategy.class)
    private Date fechaFinal;
    private boolean estaActivo;

    public PrestamoEntity() {
    }

    
 
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

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public RecursoEntity getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoEntity recurso) {
        this.recurso = recurso;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
        

}
