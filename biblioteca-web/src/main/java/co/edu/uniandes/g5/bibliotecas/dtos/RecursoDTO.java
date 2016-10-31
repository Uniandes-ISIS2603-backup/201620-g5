/*
 * RecursoDTO
 * Objeto de transferencia de datos de Recursos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Objeto de transferencia de datos de Recursos.
 *
 * @author sf.munera10
 */
@XmlRootElement
public class RecursoDTO {

    protected Long id;
    protected String name;

    @PodamExclude
    protected BiblioDTO biblioteca;
    
    private List<PrestamoDTO> prestamos = new ArrayList<>();
    
    private List<MultaDTO> multas = new ArrayList<>();
    
    private List<ReservaDTO> reservas = new ArrayList<>();

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

    public BiblioDTO getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BiblioDTO biblioteca) {
        this.biblioteca = biblioteca;
    }

    /**
     * @return the prestamos
     */
    public List<PrestamoDTO> getPrestamos() {
        return prestamos;
    }

    /**
     * @param prestamos the prestamos to set
     */
    public void setPrestamos(List<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }

    /**
     * @return the multas
     */
    public List<MultaDTO> getMultas() {
        return multas;
    }

    /**
     * @param multas the multas to set
     */
    public void setMultas(List<MultaDTO> multas) {
        this.multas = multas;
    }

    /**
     * @return the reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
}
