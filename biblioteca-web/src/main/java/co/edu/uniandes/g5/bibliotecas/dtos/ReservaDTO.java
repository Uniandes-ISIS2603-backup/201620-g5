/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import java.util.Date;


/**
 * Objeto de transferencia de datos de Ciudades.
 * @author sf.munera10
 */
public class ReservaDTO {
    
    public static final Long LIBRO = 2L;
    public static final Long VIDEO = 1L;
    public static final Long SALA = 3L;
    
    private Long id;
    private boolean estaA;
    private Long tipoRecurso;
    private Date fecha;

    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Constructor por defecto
     */
    public ReservaDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     */
    public ReservaDTO(ReservaEntity entity) {
	if (entity != null) 
        {
            this.id = entity.getId();
            this.tipoRecurso = entity.getTipoRecurso();
            this.fecha = entity.getFecha();
            this.estaA = entity.isEstaActivo();
        }
	}

     /**
     * Convierte un objeto ReservaDTO a ReservaEntity.
     *
     * @return Nueva objeto ReservaEntity.
     * 
     */
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.id);
        entity.setTipoRecurso(this.tipoRecurso);
        entity.setFecha(this.fecha);
        entity.setEstaActivo(this.estaA);
        
        return entity;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstaA() {
        return estaA;
    }

    public void setEstaA(boolean estaA) {
        this.estaA = estaA;
    }

  
    public Long getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(Long tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

   
    
	
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", Esta Activo : \"" + isEstaA()+ ",tipo de recurso : \"" + getTipoRecurso()+ "\" }" ;  
    }
}
