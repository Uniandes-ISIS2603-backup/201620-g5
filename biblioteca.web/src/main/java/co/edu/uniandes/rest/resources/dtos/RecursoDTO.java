/*
 * RecursoDTO
 * Objeto de transferencia de datos de Recursos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.resources.dtos;

/**
 * Objeto de transferencia de datos de Recursos.
 * @author sf.munera10
 */
public class RecursoDTO {
    protected Long id;
    protected String name;
    protected boolean estaReservado;
    protected boolean estaPrestado;

    /**
     * Constructor por defecto
     */
    public RecursoDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la recurso
     * @param name nombre de la recurso
     */
    public RecursoDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
                estaPrestado=false;
                estaReservado=false;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public boolean isEstaReservado() {
        return estaReservado;
    }

    public void setEstaReservado(boolean estaReservado) {
        this.estaReservado = estaReservado;
    }

    public boolean isEstaPrestado() {
        return estaPrestado;
    }

    public void setEstaPrestado(boolean estaPrestado) {
        this.estaPrestado = estaPrestado;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return getName() ;  
    }
}