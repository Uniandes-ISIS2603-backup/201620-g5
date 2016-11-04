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
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sf.munera10
 */
@XmlRootElement
public class BiblioDetailDTO extends BiblioDTO{
    
    private List<RecursoDTO> recursos = new ArrayList<>();
    
    private List<PrestamoDTO> prestamos = new ArrayList<>();
    
    private List<MultaDTO> multas = new ArrayList<>();
    
    private List<ReservaDTO> reservas = new ArrayList<>();

    public BiblioDetailDTO() {
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
    public BiblioDetailDTO(BibliotecaEntity entity) {
        super(entity);
        List<RecursoEntity> recursosList = entity.getRecursos();
        List<PrestamoEntity> prestamosList = entity.getPrestamos();
        List<MultaEntity> multasList = entity.getMultas();
        List<ReservaEntity> reservasList = entity.getReservas();

        for (RecursoEntity dept : recursosList) {
            
            if(dept.getTipoRecurso() == RecursoDTO.LIBRO)
            {
                LibroDTO libro = new LibroDTO((LibroEntity) dept);
                this.recursos.add(libro);
            }
            else if(dept.getTipoRecurso() == RecursoDTO.VIDEO)
            {
                VideoDTO video = new VideoDTO((VideoEntity) dept);
                this.recursos.add(video);
            }
            if(dept.getTipoRecurso() == RecursoDTO.SALA)
            {
                SalaDTO sala = new SalaDTO((SalaEntity) dept);
                this.recursos.add(sala);
            }
        }
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
     * Convierte un objeto BibliotecaDetailDTO a BibliotecaEntity incluyendo los
     * atributos de BibliotecaDTO.
     *
     * @return objeto BibliotecaEntity.
     *
     */
    @Override
    public BibliotecaEntity toEntity() {
        BibliotecaEntity entity = super.toEntity();
         List<RecursoDTO> recursos = this.getRecursos();
         List<PrestamoDTO> prestamos = this.getPrestamos();
         List<MultaDTO> multas = this.getMultas();
         List<ReservaDTO> reservas = this.getReservas();
        for (RecursoDTO dept : this.getRecursos()) {
             if(dept.getTipoRecurso() == RecursoDTO.LIBRO)
                {
                    LibroDTO lib = (LibroDTO) dept;
                    entity.getRecursos().add(lib.toEntity());
                }
                else if(dept.getTipoRecurso() == RecursoDTO.VIDEO)
                {
                    VideoDTO vid = (VideoDTO) dept;
                    entity.getRecursos().add(vid.toEntity());
                }
                else if(dept.getTipoRecurso() == RecursoDTO.SALA)
                {
                    SalaDTO sal = (SalaDTO) dept;
                    entity.getRecursos().add(sal.toEntity());
                }
        }
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
     * @return the recursos
     */
    public List<RecursoDTO> getRecursos() {
        return recursos;
    }

    /**
     * @param recursos the recursos to set
     */
    public void setRecursos(List<RecursoDTO> recursos) {
        this.recursos = recursos;
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
