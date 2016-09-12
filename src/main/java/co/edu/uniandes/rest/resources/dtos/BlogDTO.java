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
    
    private String nombre;
    
    private Long isbn;
    
    private String texto;
    
    private Date fecha;
    
    private String nombreAutor;
    
    private Long idAutor;
    
    private Long idBlog;
    
    
    public BlogDTO()
    {}
    
    
    public BlogDTO(String pNombre, Long pIsbn, String pTexto, Date pFecha, String pNombreAutor, Long pIdAutor, Long pIdBlog)
    {
        super();
        this.nombre = pNombre;
        this.isbn = pIsbn;
        this.texto = pTexto;
        this.fecha = pFecha;
        this.nombreAutor = pNombreAutor;
        this.idAutor = pIdAutor;
        this.idBlog = pIdBlog;
    
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public Long getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(Long idBlog) {
        this.idBlog = idBlog;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
