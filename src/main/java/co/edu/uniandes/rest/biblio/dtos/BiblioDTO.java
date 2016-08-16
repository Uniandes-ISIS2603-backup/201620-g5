/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.biblio.dtos;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class BiblioDTO {
    private Long id;
    private Long idAdmin;
    private String name;
    private String zona;

    /**
     * Constructor por defecto
     */
    public BiblioDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     */
    public BiblioDTO(Long id, Long idAdmin, String name, String zona) {
		super();
		this.id = id;
                this.idAdmin = idAdmin;
		this.name = name;
                this.zona = zona;
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
     * @return the id
     */
    public Long getIdAdmin() {
        return idAdmin;
    }

    /**
     * @param id the id to set
     */
    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
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
    
    /**
     * @return the name
     */
    public String getZona() {
        return zona;
    }

    /**
     * @param name the name to set
     */
    public void setZona(String zona) {
        this.zona = zona;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idAdmin : \"" + getIdAdmin() + ", nombre : \"" + getName() + ", zona : \"" + getZona() + "\" }" ;  
    }
}
