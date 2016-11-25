/*
 * MultaDTO
 * Objeto de transferencia de datos de multa.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import java.util.Date;

/**
 * Objeto de transferencia de datos de multas.
 *
 * @author sf.munera10
 */
public class MultaDTO {

    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private double costo;
    private Date fecha;

    /**
     * Constructor por defecto
     */
    public MultaDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     */
    public MultaDTO(MultaEntity entity) {
      
        if(entity!= null)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.costo = entity.getCosto();
            this.fecha = entity.getFecha();
        }
        
    }
    
    public MultaEntity toEntity()
    {
        MultaEntity entity = new MultaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setCosto(this.costo);
        entity.setFecha(this.fecha);
        
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Convierte el objeto a una cadena
     */
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() +  ", costo : \"" + getCosto() + ", fecha : \"" + getFecha() + "\" }" ;  
    }
}
