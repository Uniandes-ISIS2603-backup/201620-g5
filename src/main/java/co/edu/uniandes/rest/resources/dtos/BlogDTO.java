/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.dtos;

import java.util.Date;

/**
 *
 * @author js.prieto10
 */
public class BlogDTO {
    
    private String titulo;
    private String nombre;
    private Long idLibro;
    private String texto;
    private String nombreAutor;
    private Date fecha;
    private Long id;

    
    
    public BlogDTO()
    {}
    
    
    public BlogDTO(String pTitulo, String pNombre, Long pIdLibro, String pTexto, String pNombreAutor, Date pFecha, Long pIdBlog)
    {
        this.titulo= pTitulo;
        this.nombre = pNombre;
        this.idLibro = pIdLibro;
        this.texto = pTexto;
        this.nombreAutor = pNombreAutor;
        this.fecha = pFecha;
        this.id = pIdBlog;
    
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    
    
    
    
}
