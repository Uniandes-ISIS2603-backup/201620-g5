package co.edu.uniandes.g5.bibliotecas.entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author sf.munera10
 */
@Entity
public abstract class RecursoEntity extends BaseEntity implements Serializable{
    
    public static final int VIDEO = 1;
    public static final int LIBRO = 2;
    public static final int SALA = 3;
    
    @PodamExclude
    @OneToMany(mappedBy = "recurso")
    private List<PrestamoEntity> prestamos = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "recurso")
    private List<MultaEntity> multas = new ArrayList<>();
    
    @PodamExclude
    @OneToMany(mappedBy = "recurso")
    private List<ReservaEntity> reservas = new ArrayList<>();
    
    
    @PodamExclude
    @ManyToOne
    private co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity biblioteca;
   
    private int tipoRecurso;

    public int getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(int tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
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

    /**
     * @return the prestamos
     */
    public List<PrestamoEntity> getPrestamos() {
        return prestamos;
    }

    /**
     * @param prestamos the prestamos to set
     */
    public void setPrestamos(List<PrestamoEntity> prestamos) {
        this.prestamos = prestamos;
    }

    /**
     * @return the multas
     */
    public List<MultaEntity> getMultas() {
        return multas;
    }

    /**
     * @param multas the multas to set
     */
    public void setMultas(List<MultaEntity> multas) {
        this.multas = multas;
    }

    /**
     * @return the reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
    
}