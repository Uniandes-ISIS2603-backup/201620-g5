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


@Entity
public class BibliotecaEntity extends BaseEntity implements Serializable {

    
    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalaEntity> salas = new ArrayList<>();
        
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
}
