package co.edu.uniandes.g5.bibliotecas.entities;

import co.edu.uniandes.g5.bibliotecas.entities.BaseEntity;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author sf.munera10
 */
@Entity
public abstract class RecursoEntity extends BaseEntity implements Serializable{
    
    
    
    @PodamExclude
    @ManyToOne
    private BibliotecaEntity biblioteca;
    
    @PodamExclude
    @OneToOne
    private PrestamoEntity prestamo;
    
     @PodamExclude
    @OneToOne
    private ReservaEntity reserva;

    public PrestamoEntity getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(PrestamoEntity prestamo) {
        this.prestamo = prestamo;
    }

    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }
    
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
