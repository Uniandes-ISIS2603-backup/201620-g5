/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.rojas19
 */
@XmlRootElement
public class VideoDetailDTO extends VideoDTO {

    @PodamExclude
    private BiblioDTO biblioteca;

    @PodamExclude
    private List<PrestamoDTO> prestamos = new ArrayList<>();

    @PodamExclude
    private List<ReservaDTO> reservas = new ArrayList<>();

    @PodamExclude
    private List<MultaDTO> multas = new ArrayList<>();

    public VideoDetailDTO() {
        super();
    }

    public VideoDetailDTO(VideoEntity entity) {
        super(entity);
        if (entity != null) {
            biblioteca = new BiblioDTO(entity.getBiblioteca());
            for (PrestamoEntity prestamo : entity.getPrestamos())
                prestamos.add(new PrestamoDTO(prestamo));
            for (ReservaEntity reserva : entity.getReservas())
                reservas.add(new ReservaDTO(reserva));
            for (MultaEntity multa : entity.getMultas())
                multas.add(new MultaDTO(multa));
        }
    }

    @Override
    public VideoEntity toEntity() {
        VideoEntity entity = super.toEntity();
        List<PrestamoEntity> p = new ArrayList<>();
        List<ReservaEntity> r = new ArrayList<>();
        List<MultaEntity> m = new ArrayList<>();
        if(biblioteca != null)
            entity.setBiblioteca(biblioteca.toEntity());
        for (PrestamoDTO prestamo : prestamos) {
            p.add(prestamo.toEntity());
        }
        for (ReservaDTO reserva : reservas) {
            r.add(reserva.toEntity());
        }
        for (MultaDTO multa : multas) {
            m.add(multa.toEntity());
        }
        entity.setPrestamos(p);
        entity.setReservas(r);
        entity.setMultas(m);
        
        return entity;
        
    }

    public BiblioDTO getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BiblioDTO biblioteca) {
        this.biblioteca = biblioteca;
    }

    public List<PrestamoDTO> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public List<MultaDTO> getMultas() {
        return multas;
    }

    public void setMultas(List<MultaDTO> multas) {
        this.multas = multas;
    }
}
