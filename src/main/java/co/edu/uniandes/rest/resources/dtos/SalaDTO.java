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
    private int capacidad;
    
    /**
     * Constructor por defecto
     */
    public SalaDTO() {
    }
    
    public SalaDTO(Long idRecurso, Long idBiblioteca, String nombre, int capacidad) {
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
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
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
