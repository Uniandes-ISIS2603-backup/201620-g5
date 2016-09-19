/*
 * SalaDTO
 * Objeto de transferencia de datos de Salas.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.resources.dtos;

/**
 * Objeto de transferencia de datos de Salas.
 * @author sf.munera10
 */
public class SalaDTO extends RecursoDTO {
    private Long idBiblioteca;
    private Integer capacidad;
    
    /**
     * Constructor por defecto
     */
    public SalaDTO() {
    }
    
    public SalaDTO(Long idRecurso, Long idBiblioteca, String nombre, Integer capacidad) {
		super(idRecurso, nombre);
                this.idBiblioteca = idBiblioteca;
                this.capacidad = capacidad;
                
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

   
  
    /**
     * @return the capacidad
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
   
     /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idBiblioteca : \"" + getIdBiblioteca() +  ", nombre : \"" + getName() + ", capacidad : \"" + getCapacidad() + "\" }" ;  
    }
}
