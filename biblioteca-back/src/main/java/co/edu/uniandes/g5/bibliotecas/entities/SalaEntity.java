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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class SalaEntity extends RecursoEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity biblioteca;
    
    private boolean estaOcupada;
    
    private int capacidad;

    /**
     * Obtiene el atributo biblioteca.
     *
     * @return atributo biblioteca.
     *
     */
    public co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    /**
     * Establece el valor del atributo biblioteca.
     *
     * @param biblioteca nuevo valor del atributo
     *
     */
    public void setBiblioteca(co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
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
}
