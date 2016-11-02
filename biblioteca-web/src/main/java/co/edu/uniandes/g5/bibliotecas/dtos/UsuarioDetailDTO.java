/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author sf.munera10
 */
@XmlRootElement
public class UsuarioDetailDTO extends UsuarioDTO{
    
    
    
    private List<PrestamoDTO> prestamos = new ArrayList<>();
    
    private List<MultaDTO> multas = new ArrayList<>();
    
    private List<ReservaDTO> reservas = new ArrayList<>();
    
    @PodamExclude
    private BiblioDTO biblioteca;
    
    @PodamExclude
    private List<BlogDTO> blogs = new ArrayList<>();

    public List<BlogDTO> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogDTO> blogs) {
        this.blogs = blogs;
    }

    public BiblioDTO getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BiblioDTO biblioteca) {
        this.biblioteca = biblioteca;
    }

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
        List<BlogEntity> blogsList = entity.getBlogs();
        
        for (PrestamoEntity dept : prestamosList) {
            this.prestamos.add(new PrestamoDTO(dept));
        }
        for (MultaEntity dept : multasList) {
            this.multas.add(new MultaDTO(dept));
        }
        for (ReservaEntity dept : reservasList) {
            this.reservas.add(new ReservaDTO(dept));
        }
        for (BlogEntity dept : blogsList) {
            this.blogs.add(new BlogDTO(dept));
        }
        if(entity.getBiblioteca() != null)
        {
            this.biblioteca = new BiblioDTO(entity.getBiblioteca());
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
         List<BlogDTO> blogs = this.getBlogs();
        for (PrestamoDTO dept : this.getPrestamos()) {         
            entity.getPrestamos().add(dept.toEntity());
        }
        for (MultaDTO dept : this.getMultas()) {         
            entity.getMultas().add(dept.toEntity());
        }
        for (ReservaDTO dept : this.getReservas()) {         
            entity.getReservas().add(dept.toEntity());
        }
        for (BlogDTO dept : this.getBlogs()) {         
            entity.getBlogs().add(dept.toEntity());
        }
        if (getBiblioteca()!= null) {
            entity.setBiblioteca(this.getBiblioteca().toEntity());
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
