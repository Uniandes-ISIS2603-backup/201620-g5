/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.BlogEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author js.prieto10
 */
@XmlRootElement
public class BlogDTO {
    
    private String nombre;
    private String texto;
    private String autor;
    private Long id;

    public BlogDTO()
    {}
    
    public BlogDTO(BlogEntity entity)
    {
        this.nombre = entity.getName();
        this.texto = entity.getTexto();
        this.autor = entity.getNombreAutor();
        this.id = entity.getId();    
    }
    
    public BlogEntity toEntity()
    {
        BlogEntity entity = new BlogEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);
        entity.setNombreAutor(this.autor);
        entity.setTexto(this.texto);
        return entity;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreAutor() {
        return autor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.autor = nombreAutor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
