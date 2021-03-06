/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.rojas19
 */
@XmlRootElement
public class LibroDTO extends RecursoDTO {

    private Long id;
    private String name;

    private Long isbn;
    private String autor;
    private Integer numEjemplares;
    private Integer ejemplaresDisponibles;
    private boolean online;
    
    private BiblioDTO biblioteca;

    /**
     * Crea un libroDTO vacio.
     */
    public LibroDTO() {

    }

    public LibroDTO(LibroEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.biblioteca = new BiblioDTO(entity.getBiblioteca());
            this.name = entity.getName();
            this.isbn = entity.getIsbn();
            this.autor = entity.getAutor();
            this.numEjemplares = entity.getNumEjemplares();
            this.ejemplaresDisponibles = entity.getEjemplaresDisponibles();
            this.online = entity.isOnline();
        }
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", isbn: " + isbn + ", titulo : \"" + name + "\" , numEjemplares: " + numEjemplares + " , online: \"" + online + "\" }";
    }

    public LibroEntity toEntity() {
        LibroEntity entity = new LibroEntity();
        if (biblioteca != null) {
            entity.setBiblioteca(biblioteca.toEntity());
        }
        entity.setId(id);
        entity.setName(name);
        entity.setIsbn(isbn);
        entity.setAutor(autor);
        entity.setNumEjemplares(numEjemplares);
        entity.setEjemplaresDisponibles(ejemplaresDisponibles);
        entity.setOnline(online);
        return entity;
    }
    
    public BiblioDTO getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BiblioDTO biblioteca) {
        this.biblioteca = biblioteca;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Integer getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(Integer numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    public Integer getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(Integer ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
