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
    private Integer capacidad;
    
    /**
     * Constructor por defecto
     */
    public SalaDTO() {
    }
    
    public SalaDTO(Long idRecurso, BiblioDTO biblioteca, String nombre, Integer capacidad) {
		super(idRecurso, nombre, biblioteca);
                this.capacidad = capacidad;
                

   
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
    	return "{ id : " + getId() + ", idBiblioteca : \"" + getBiblioteca().getId()+  ", nombre : \"" + getName() + ", capacidad : \"" + getCapacidad() + "\" }" ;  
    }
}
