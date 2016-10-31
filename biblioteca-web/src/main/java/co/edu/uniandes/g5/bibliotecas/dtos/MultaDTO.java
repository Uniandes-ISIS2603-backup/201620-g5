/*
 * MultaDTO
 * Objeto de transferencia de datos de multa.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import java.util.Date;

/**
 * Objeto de transferencia de datos de multas.
 *
 * @author sf.munera10
 */
public class MultaDTO {

    private Long id;
    private Long idUsuario;
    private Long idBiblioteca;
    private Long idRecurso;
    private double costo;
    private Date fecha;

    /**
     * Constructor por defecto
     */
    public MultaDTO() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     */
    public MultaDTO(Long id, Long idUsuario, Long idBiblioteca, Long idRecurso, double costo, Date fecha) {
        super();
        this.id = id;
        this.idUsuario = idUsuario;
        this.idBiblioteca = idBiblioteca;
        this.idRecurso = idRecurso;
        this.costo = costo;
        this.fecha = fecha;
    }

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
     * @return the idUsuario
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idBiblioteca
     */
    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    /**
     * @param idBiblioteca the idBiblioteca to set
     */
    public void setIdBiblioteca(Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    /**
     * @return the idRecurso
     */
    public Long getIdRecurso() {
        return idRecurso;
    }

    /**
     * @param idRecurso the idRecurso to set
     */
    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costo;
    }

    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costo = costo;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Convierte el objeto a una cadena
     */
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idUsuario : \"" + getIdUsuario()+ ", idBiblioteca : \"" + getIdBiblioteca() + ", idRecurso : \"" + getIdRecurso() + ", costo : \"" + getCosto() + ", fecha : \"" + getFecha() + "\" }" ;  
    }
}
