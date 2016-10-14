package co.edu.uniandes.g5.bibliotecas.entities;

import co.edu.uniandes.g5.bibliotecas.entities.BaseEntity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author sf.munera10
 */
public abstract class RecursoEntity extends BaseEntity implements Serializable{
    
    
    private boolean estaReservado;

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
    private boolean estaPrestado;
    @PodamExclude
    @ManyToOne
    private BibliotecaEntity biblioteca;
    
    /**
     * Obtiene el atributo biblioteca.
     *
     * @return atributo biblioteca.
     *
     */
    public BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    /**
     * Establece el valor del atributo biblioteca.
     *
     * @param biblioteca nuevo valor del atributo
     *
     */
    public void setBiblioteca(BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
    }
}
