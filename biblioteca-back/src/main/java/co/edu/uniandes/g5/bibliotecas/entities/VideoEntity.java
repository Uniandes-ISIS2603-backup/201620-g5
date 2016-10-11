/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author s.rojas19
 */
@Entity
public class VideoEntity extends RecursoEntity implements Serializable{
    
    private String director;
    
    private boolean online;
    
    private Integer numEjemplares;
    
    private Integer EjemplaresDisponibles;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Integer getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(Integer numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    public Integer getEjemplaresDisponibles() {
        return EjemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(Integer EjemplaresDisponibles) {
        this.EjemplaresDisponibles = EjemplaresDisponibles;
    }
    
    
}
