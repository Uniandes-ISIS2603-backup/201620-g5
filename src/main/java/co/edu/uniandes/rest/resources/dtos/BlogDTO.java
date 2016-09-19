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
    private Long idBlog;

    
    
    public BlogDTO()
    {}
    
    
    public BlogDTO(String pTitulo, String pNombre, Long pIdLibro, String pTexto, String pNombreAutor, Long pIdBlog)
    {
        super();
        this.titulo= pTitulo;
        this.nombre = pNombre;
        this.idLibro = pIdLibro;
        this.texto = pTexto;
        this.nombreAutor = pNombreAutor;
        this.idBlog = pIdBlog;
    
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

    public Long getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(Long idBlog) {
        this.idBlog = idBlog;
    }
    
    public String getTitulo()
    {
        return titulo;
    }
    

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
