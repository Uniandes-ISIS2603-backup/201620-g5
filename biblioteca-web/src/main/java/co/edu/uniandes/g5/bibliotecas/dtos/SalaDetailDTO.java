/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author sf.munera10
 */
@XmlRootElement
public class SalaDetailDTO extends SalaDTO{
    
    @PodamExclude
    private BiblioDTO biblioteca;
    
    public SalaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto SalaDetailDTO a partir de un objeto SalaEntity
     * incluyendo los atributos de SalaDTO.
     *
     * @param entity Entidad SalaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public SalaDetailDTO(SalaEntity entity) {
        super(entity);
        if (entity.getBiblioteca() != null) {
            this.biblioteca = new BiblioDTO(entity.getBiblioteca());
        }

    }

    /**
     * Convierte un objeto SalaDetailDTO a SalaEntity incluyendo los
     * atributos de SalaDTO.
     *
     * @return  objeto SalaEntity.
     *
     */
    @Override
    public SalaEntity toEntity() {
        SalaEntity entity = super.toEntity();
        if (this.getBiblioteca() != null) {
            entity.setBiblioteca(this.getBiblioteca().toEntity());
        }
        return entity;
    }

    /**
     * @return the biblioteca
     */
    @Override
    public BiblioDTO getBiblioteca() {
        return biblioteca;
    }

    /**
     * @param biblioteca the biblioteca to set
     */
    @Override
    public void setBiblioteca(BiblioDTO biblioteca) {
        this.biblioteca = biblioteca;
    }
    
}
