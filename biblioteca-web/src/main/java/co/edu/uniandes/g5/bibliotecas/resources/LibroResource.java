/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.edu.uniandes.g5.bibliotecas.dtos.LibroDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.VideoDTO;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Clase que implementa el recurso REST correspondiente a "libros".
 *
 * @author s.rojas19
 */
@Path("")
@Produces("application/json")
public class LibroResource {

    /**
     * Mock del modelo de datos para la clase Libro
     */
    LibroLogicMock libroLogic = new LibroLogicMock();

    /**
     * Obtiene el listado de libros.
     *
     * @return lista de libros
     * @throws BibliotecaLogicException retornada por logica.
     */
    @GET
    @Path("libros")
    public List<LibroDTO> getLibros() throws BibliotecaLogicException {
        return libroLogic.getLibros();
    }

     @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/libros")
    public ArrayList<LibroDTO> getLibrosBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) throws BibliotecaLogicException, ParseException {
        return libroLogic.getLibrosBiblioteca(idBiblioteca);
    }

    /**
     * Obtiene el libro con id por parametro
     *
     * @param id id del libro a retornar
     * @return Libro con id dado por parametro
     * @throws BibliotecaLogicException Si no se encuentra el libro.
     */
    @GET
    @Path("libros/{id: \\d+}")
    public LibroDTO getLibro(@PathParam("id") Long id) throws BibliotecaLogicException {
        return libroLogic.getLibro(id);
    }

    /**
     * Crea un libro
     *
     * @param libro libro a agregar
     * @return libtro agregado
     * @throws BibliotecaLogicException Si no es posible adicionar el libro
     */
    @POST
    @Path("libros")
    public LibroDTO createLibro(LibroDTO libro) throws BibliotecaLogicException {
        return libroLogic.createLibro(libro);
    }

    /**
     * Actualiza un libro
     *
     * @param id id del libro a actualizar
     * @param libro objeto con atributos a cambiar del libro
     * @return libro actualizado
     * @throws BibliotecaLogicException Si no es posible actualizar el libro
     */
    @PUT
    @Path("libros/{id: \\d+}")
    public LibroDTO updateLibro(@PathParam("id") Long id, LibroDTO libro) throws BibliotecaLogicException {
        return libroLogic.updateLibro(id, libro);
    }

    /**
     * Elimina un libro
     *
     * @param id id del libro a eliminar
     * @throws BibliotecaLogicException Si no es posible eliminar el libro.
     */
    @DELETE
    @Path("libros/{id: \\d+}")
    public void deleteLibro(@PathParam("id") Long id) throws BibliotecaLogicException {
        libroLogic.deleteLibro(id);
    }
}
