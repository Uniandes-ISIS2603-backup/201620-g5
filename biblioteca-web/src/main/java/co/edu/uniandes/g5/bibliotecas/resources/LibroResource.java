/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDetailDTO;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import co.edu.uniandes.g5.bibliotecas.dtos.LibroDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.LibroDetailDTO;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso REST correspondiente a "libros".
 *
 * @author s.rojas19
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
public class LibroResource {

    /**
     * Mock del modelo de datos para la clase Libro
     */
    @Inject
    private ILibroLogic libroLogic;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;

    private List<LibroDetailDTO> listEntity2DetailDTO(List<LibroEntity> entityList) {
        List<LibroDetailDTO> list = new ArrayList<>();
        for (LibroEntity entity : entityList) {
            list.add(new LibroDetailDTO(entity));
        }
        return list;
    }

    private List<LibroDTO> listEntity2DTO(List<LibroEntity> entityList) {
        List<LibroDTO> list = new ArrayList<>();
        for (LibroEntity entity : entityList) {
            list.add(new LibroDTO(entity));
        }
        return list;
    }

    public void existsBiblioteca(Long bibliotecaId) {
        BiblioDetailDTO biblioteca = new BiblioDetailDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsLibro(Long libroId) {
        LibroEntity entity = libroLogic.getLibro(libroId);
        if (entity == null) {
            throw new WebApplicationException("El libro no existe", 404);
        }
    }

    /**
     * Obtiene el listado de libros.
     *
     * @return lista de libros
     * @throws BibliotecaLogicException retornada por logica.
     */
    @GET
    @Path("libros")
    public List<LibroDTO> getLibros() {
        List<LibroEntity> libros = libroLogic.getLibros();
        return listEntity2DTO(libros);
    }

    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/libros")
    public List<LibroDTO> getLibrosByBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) {
        List<LibroEntity> libros = libroLogic.getLibrosByBiblioteca(idBiblioteca);
        return listEntity2DTO(libros);
    }

    /**
     * Obtiene el libro con id por parametro
     *
     * @param id id del libro a retornar
     * @return Libro con id dado por parametro
     */
    @GET
    @Path("libros/{id: \\d+}")
    public LibroDetailDTO getLibro(@PathParam("id") Long id) {
        LibroEntity resultado = libroLogic.getLibro(id);
        if (resultado == null) {
            throw new WebApplicationException(404);
        }
        return new LibroDetailDTO(resultado);
    }

    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/libros/isbn/{isbn: \\d+}")
    public LibroDetailDTO getLibroByISBN(@PathParam("id") Long id, @PathParam("isbn") Long isbn) {
        LibroEntity resultado = libroLogic.getLibroByISBN(isbn, id);
        if (resultado == null) {
            throw new WebApplicationException(404);
        }
        return new LibroDetailDTO(resultado);
    }

    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/libros/name/{name}")
    public LibroDetailDTO getLibroByName(@PathParam("id") Long id, @PathParam("name") String name) {
        LibroEntity resultado = libroLogic.getLibroByName(name, id);
        if (resultado == null) {
            throw new WebApplicationException(404);
        }
        return new LibroDetailDTO(resultado);
    }

    /**
     * Crea un libro
     *
     * @param libro libro a agregar
     * @return libro agregado
     */
    @POST
    @Path("bibliotecas/{idBiblioteca: \\d+}/libros")
    public LibroDetailDTO createLibro(LibroDTO libro, @PathParam("idBiblioteca") Long idBiblioteca) throws BibliotecaLogicException {
            LibroEntity entity = libro.toEntity();
            LibroEntity respuesta = libroLogic.createLibro(entity, idBiblioteca);
            return new LibroDetailDTO(respuesta);
    }

    /**
     * Actualiza un libro
     *
     * @param id id del libro a actualizar
     * @param libro objeto con atributos a cambiar del libro
     * @return libro actualizado
     */
    @PUT
    @Path("bibliotecas/{idBiblioteca: \\d+}/libros/{id: \\d+}")
    public LibroDetailDTO updateLibro(@PathParam("id") Long id, @PathParam("idBiblioteca") Long idBiblioteca, LibroDTO libro) throws BibliotecaLogicException {
            existsBiblioteca(idBiblioteca);
            existsLibro(id);
            LibroEntity entity = libro.toEntity();
            entity.setId(id);
            return new LibroDetailDTO(libroLogic.updateLibro(entity, idBiblioteca));
    }

    /**
     * Elimina un libro
     *
     * @param id id del libro a eliminar
     */
    @DELETE
    @Path("libros/{id: \\d+}")
    public void deleteLibro(@PathParam("id") Long id) {
        existsLibro(id);
        libroLogic.deleteLibro(id);
    }
}
