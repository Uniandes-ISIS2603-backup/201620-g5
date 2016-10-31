/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de Ciudades.
 *
 * @author sf.munera10
 */
@XmlRootElement
public class BiblioDTO {

    private Long id;
    private String name;

    /**
     * Constructor por defecto
     */
    public BiblioDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     */
    public BiblioDTO(BibliotecaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.name = entity.getName();
        }
    }
    
    /**
     * Convierte un objeto BibliotecaDTO a BibliotecaEntity.
     *
     * @return Nueva objeto BibliotecaEntity.
     * 
     */
    public BibliotecaEntity toEntity() {
        BibliotecaEntity entity = new BibliotecaEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        
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
}
