/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ce.gonzalez13
 */
public class PrestamoDTO {
    
    public static final String VIDEO = "Video";
    public static final String LIBRO = "Libro";
    public static final String SALA = "Sala";

    
    
    private Long id;
    private String tipoRecurso;
    private Double costo;
    private String medioPago;
    private String fechaInicial;
    private String fechaFinal;
    private boolean estaActivo;

    /**
     * Constructor por defecto
     */
    public PrestamoDTO() {
    }
    
    /**
     * Crea un objeto PrestamoDTO a partir de un objeto PrestamoEntity.
     *
     * @param entity Entidad PrestamoEntity desde la cual se va a crear el
     * nuevo objeto.
     * 
     */
    public PrestamoDTO(PrestamoEntity entity) {
        if (entity != null) {
             SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            this.id = entity.getId();
            if(entity.getTipoRecurso().equals(1L))
            {
                this.tipoRecurso = PrestamoDTO.VIDEO;
            }
            else if(entity.getTipoRecurso().equals(2L))
            {
                this.tipoRecurso = PrestamoDTO.LIBRO;
            }
            else if(entity.getTipoRecurso().equals(3L))
            {
                this.tipoRecurso = PrestamoDTO.SALA;
            }
          
            this.costo = entity.getCosto();
            this.medioPago = entity.getMedioPago();
            String fechaIni = format.format(entity.getFechaInicial());
             String fechaFin = format.format(entity.getFechaFinal());           
            this.fechaInicial = fechaIni;
            this.fechaFinal = fechaFin;
            this.estaActivo = entity.isEstaActivo();
        }
    }

    /**
     * Convierte un objeto PrestamoDTO a PrestamoEntity.
     *
     * @return Nueva objeto PrestamoEntity.
     * 
     */
    public PrestamoEntity toEntity(){
                    PrestamoEntity entity = new PrestamoEntity();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            entity.setId(this.id);
            switch (this.tipoRecurso) {
                case PrestamoDTO.VIDEO:
                    entity.setTipoRecurso(1L);
                    break;
                case PrestamoDTO.LIBRO:
                    entity.setTipoRecurso(2L);
                    break;
                case PrestamoDTO.SALA:
                    entity.setTipoRecurso(3L);
                    break;
                default:
                    break;
            }
            entity.setCosto(this.costo);
            entity.setMedioPago(this.medioPago);
            
            Date fechaIni = format.parse(this.fechaInicial);
            Date fechaFin = format.parse(this.fechaFinal);
            entity.setFechaFinal(fechaFin);
            entity.setFechaInicial(fechaIni);
            entity.setEstaActivo(this.estaActivo);
            
        } catch (ParseException ex) {
            Logger.getLogger(PrestamoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;

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

public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }
    
    /**
     * @return the costo
     */
    public Double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(Double costo) {
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
    public String getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public String getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(String fechaFinal) {
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
     * @return 
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", costo : \"" + getCosto() + ", medioPago : \"" + getMedioPago() + ", fechaInicial : \"" + getFechaInicial() + ", fechaFinal : \"" + getFechaFinal()  + ", estaActivo : \"" + isEstaActivo() + "\" }" ;  
    }
}
