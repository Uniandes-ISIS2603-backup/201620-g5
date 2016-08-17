/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.dtos;

/**
 *
 * @author s.rojas19
 */
public class LibroDTO {
    private long id;
    private long isbn;
    private String titulo;
    private long numEjemplares;
    private boolean online;

    public LibroDTO() {
    }

    public LibroDTO(long id, long isbn, String titulo, long numEjemplares, boolean esOnline) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.numEjemplares = numEjemplares;
        this.online = esOnline;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public long getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(int numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", isbn: " + isbn + ", titulo : \"" + titulo + "\" , numEjemplares: " + numEjemplares + " , online: \"" + online + "\" }";
    }
    
    
    
}
