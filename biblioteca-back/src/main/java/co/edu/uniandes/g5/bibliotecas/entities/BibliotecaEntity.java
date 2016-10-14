/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.entities;

/**
 *
 * @author sf.munera10
 */
import java.io.Serializable;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;


@Entity
public class BibliotecaEntity extends BaseEntity implements Serializable {

    
    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaEntity> salas = new ArrayList<>();
      
    @PodamExclude
    @OneToMany(mappedBy = "biblioteca")
    private List<RecursoEntity> recursos = new ArrayList<>();
    
    /**
     * Obtiene la colección de salas.
     * @return colección salas. 
     */
    public List<SalaEntity> getSalas() {
        return salas;
    }

    /**
     * Establece el valor de la colección de salas.
     * @param salas nuevo valor de la colección. 
     */
    public void setSalas(List<SalaEntity> salas) {
        this.salas = salas;
    }
    
    /**
     * Obtiene la colección de recursos.
     *
     * @return colección recursos.
     *
     */
    public List<RecursoEntity> getRecursos() {
        return recursos;
    }

    /**
     * Establece el valor de la colección de recursos.
     *
     * @param recursos nuevo valor de la colección.
     *
     */
    public void setRecursos(List<RecursoEntity> recursos) {
        this.recursos = recursos;
    }

}
