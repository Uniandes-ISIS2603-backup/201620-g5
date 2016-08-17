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

    private Long id;
    private Long isbn;
    private String titulo;
    private long numEjemplares;
    private boolean online;

    /**
     * Crea un libroDTO vacio.
     */
    public LibroDTO() {
    }

    /**
     * Crea in objeto de tipo LibroDTO con los parametros de entrada.
     *
     * @param id numero identificador del libro
     * @param isbn codigo isbn10 del libro
     * @param titulo titulo del libro
     * @param numEjemplares cantidad de ejemplares disponibles
     * @param esOnline define si el libro es disponible digitalmente
     */
    public LibroDTO(Long id, Long isbn, String titulo, Long numEjemplares, boolean esOnline) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.numEjemplares = numEjemplares;
        this.online = esOnline;
    }

    /**
     * Devuelve el id del libro
     *
     * @return id del libro
     */
    public Long getId() {
        return id;
    }

    /**
     * Cambia el id del libro por el que entra por parametro
     *
     * @param id nuevo id del libro
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el codigo isbn del libro
     *
     * @return isbn del libro
     */
    public Long getIsbn() {
        return isbn;
    }

    /**
     * Cambia el codigo isbn del libro por el que entra por parametro
     *
     * @param isbn nuevo codigo isbn del libro
     */
    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    /**
     * Devuelve el titulo del libro
     *
     * @return titulo del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Cambia el titulo del libro por el que entra por parametro
     *
     * @param titulo nuevo titulo del libro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve el numero de ejemplares disponibles del libro.
     *
     * @return numero de ejemplares del libro.
     */
    public Long getNumEjemplares() {
        return numEjemplares;
    }

    /**
     * Cambia el numero de ejemplares del libro por el que entra por parametro
     *
     * @param numEjemplares nuevo numero de ejemplares disponibles.
     */
    public void setNumEjemplares(Long numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    /**
     * Responde si el libro es disponible digitalmente
     * @return true si el libro es disponible digitalmente, false de lo contrario.
     */
    public boolean isOnline() {
        return online;
    }

    /**
     * Cambia el estado de disponibilidad digital del libro
     * @param online true si se dispone del libro digitalmente, false de lo contrario
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", isbn: " + isbn + ", titulo : \"" + titulo + "\" , numEjemplares: " + numEjemplares + " , online: \"" + online + "\" }";
    }

}
