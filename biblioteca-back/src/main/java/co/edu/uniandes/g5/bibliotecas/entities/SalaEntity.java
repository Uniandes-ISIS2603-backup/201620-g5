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

@Entity
public class SalaEntity extends RecursoEntity implements Serializable {

  

    private boolean estaOcupada;

    private int capacidad;

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
