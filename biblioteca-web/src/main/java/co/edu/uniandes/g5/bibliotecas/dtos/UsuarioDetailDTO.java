/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sf.munera10
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO{
    
    
    
    private List<PrestamoDTO> prestamos = new ArrayList<>();
    
    private List<MultaDTO> multas = new ArrayList<>();
    
    private List<ReservaDTO> reservas = new ArrayList<>();

    public UsuarioDetailDTO() {
        super();
    }

    /**
     * Crea un objeto BibliotecaDetailDTO a partir de un objeto BibliotecaEntity
     * incluyendo los atributos de BibliotecaDTO.
     *
     * @param entity Entidad BibliotecaEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);
       
        List<PrestamoEntity> prestamosList = entity.getPrestamos();
        List<MultaEntity> multasList = entity.getMultas();
        List<ReservaEntity> reservasList = entity.getReservas();
        
        
        for (PrestamoEntity dept : prestamosList) {
            this.prestamos.add(new PrestamoDTO(dept));
        }
        for (MultaEntity dept : multasList) {
            this.multas.add(new MultaDTO(dept));
        }
        for (ReservaEntity dept : reservasList) {
            this.reservas.add(new ReservaDTO(dept));
        }
    }

    /**
     * Convierte un objeto UsuarioDetailDTO a UsuarioEntity incluyendo los
     * atributos de UsuarioDTO.
     *
     * @return objeto BibliotecaEntity.
     *
     */
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();
         
         List<PrestamoDTO> prestamos = this.getPrestamos();
         List<MultaDTO> multas = this.getMultas();
         List<ReservaDTO> reservas = this.getReservas();
        for (PrestamoDTO dept : this.getPrestamos()) {         
            entity.getPrestamos().add(dept.toEntity());
        }
        for (MultaDTO dept : this.getMultas()) {         
            entity.getMultas().add(dept.toEntity());
        }
        for (ReservaDTO dept : this.getReservas()) {         
            entity.getReservas().add(dept.toEntity());
        }
        return entity;
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
     * @return the reserva
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reserva the reserva to set
     */
    public void setReservas(List<ReservaDTO> reserva) {
        this.reservas = reserva;
    }
}
