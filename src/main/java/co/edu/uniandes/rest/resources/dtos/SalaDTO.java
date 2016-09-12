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
public class SalaDTO {
    private Long idRecurso;
    private Long id;
    private Long idBiblioteca;
    private boolean estaOcupada;
    private String nombre;
    private int capacidad;
    
    /**
     * Constructor por defecto
     */
    public SalaDTO() {
    }
    
    public SalaDTO(Long idRecurso,Long id, Long idBiblioteca, boolean estaOcupada, String nombre, int capacidad) {
		super();
                this.idRecurso = idRecurso;
		this.id = id;
                this.idBiblioteca = idBiblioteca;
		this.estaOcupada = estaOcupada;
                this.nombre = nombre;
                this.capacidad = capacidad;
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
     * @return the estaOcupada
     */
    public boolean isEstaOcupada() {
        return estaOcupada;
    }

    /**
     * @param estaOcupada the estaOcupada to set
     */
    public void setEstaOcupada(boolean estaOcupada) {
        this.estaOcupada = estaOcupada;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the idRecurso
     */
    public Long getIdRecurso() {
        return idRecurso;
    }

    /**
     * @param idRecurso the idRecurso to set
     */
    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }
    
     /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idRecurso : \"" + getIdRecurso() + ", idBiblioteca : \"" + getIdBiblioteca() + ", estaOcupada : \"" + isEstaOcupada() + ", nombre : \"" + getNombre() + ", capacidad : \"" + getCapacidad() + "\" }" ;  
    }
}
