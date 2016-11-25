/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private String fecha;

    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

            String fechaI = format.format(entity.getFecha());
            this.id = entity.getId();
            this.tipoRecurso = entity.getTipoRecurso();
            this.fecha =  fechaI;
            
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
        try {
            ReservaEntity entity = new ReservaEntity();
            entity.setId(this.id);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
            entity.setTipoRecurso(this.tipoRecurso);
            entity.setFecha(format.parse(this.fecha));
            entity.setEstaActivo(this.estaA);
            
            return entity;
        } catch (ParseException ex) {
            Logger.getLogger(ReservaDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
