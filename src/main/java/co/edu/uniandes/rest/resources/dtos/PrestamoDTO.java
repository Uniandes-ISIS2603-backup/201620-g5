/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.dtos;

import java.util.Date;

/**
 *
 * @author sf.munera10
 */
public class PrestamoDTO {
    
    public static final String LIBRO = "Libro";
    public static final String VIDEO = "Video";
    public static final String SALA = "Sala";

    
    
    private Long id;
    private Long idUsuario;
    private Long idBiblioteca;
    private String tipoRecurso;

    
    private RecursoDTO recurso;
    private double costo;
    private String medioPago;
    private Date fechaInicial;
    private Date fechaFinal;
    private boolean estaActivo;

    /**
     * Constructor por defecto
     */
    public PrestamoDTO() {
    }

    public PrestamoDTO(Long id, Long idUsuario, Long idBiblioteca,String tipoRecurso, RecursoDTO recurso, double costo, String medioPago, Date fechaInicial, Date fechaFinal, boolean estaActivo) {
        super();
        this.id = id;
        this.idUsuario = idUsuario;
        this.idBiblioteca = idBiblioteca;
        this.tipoRecurso = tipoRecurso;
        this.recurso = recurso;
        this.costo = costo;
        this.medioPago = medioPago;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.estaActivo = estaActivo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idBiblioteca
     */
    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    /**
     * @param idBiblioteca the idBiblioteca to set
     */
    public void setIdBiblioteca(Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }
public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }
    /**
     * @return the idRecurso
     */
    public RecursoDTO getRecurso() {
        return recurso;
    }

    /**
     * @param nombreRecurso the idRecurso to set
     */
    public void setRecurso(RecursoDTO recurso) {
        this.recurso = recurso;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the medioPago
     */
    public String getMedioPago() {
        return medioPago;
    }

    /**
     * @param medioPago the medioPago to set
     */
    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the estaActivo
     */
    public boolean isEstaActivo() {
        return estaActivo;
    }

    /**
     * @param estaActivo the estaActivo to set
     */
    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idUsuario : \"" + getIdUsuario()+ ", idBiblioteca : \"" + getIdBiblioteca() + ", idRecurso : \"" + getRecurso() + ", costo : \"" + getCosto() + ", medioPago : \"" + getMedioPago() + ", fechaInicial : \"" + getFechaInicial() + ", fechaFinal : \"" + getFechaFinal()  + ", estaActivo : \"" + isEstaActivo() + "\" }" ;  
    }
}
