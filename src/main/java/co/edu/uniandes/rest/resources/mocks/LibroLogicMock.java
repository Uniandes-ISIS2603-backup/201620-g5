/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.mocks;

import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;

/**
 *
 * @author s.rojas19
 */
public class LibroLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(RecursoLogicMock.class.getName());

    // listado de libros
    private static ArrayList<LibroDTO> libros;

    /**
     * Constructor. Inicia un mock de Libros, con 3 ejemplares.
     */
    public LibroLogicMock() {
        if (libros == null) {
            libros = new ArrayList<>();
            libros.add(new LibroDTO(1L, 553213113L, "Moby Dick", 10L, true));
            libros.add(new LibroDTO(2L, 743273567L, "The Great Gatsby", 10L, false));
            libros.add(new LibroDTO(3L, 451524934L, "1984", 10L, false));
        }
        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de videos");
        logger.info("libros" + libros);
    }

    /**
     * Devuelve la lista de libros en la estructura de  datos.
     * @return Lista de libros
     * @throws BibliotecaLogicException Si no existe la lista de libros.
     */
    public ArrayList<LibroDTO> getLibros() throws BibliotecaLogicException {
        if (libros == null) {
            logger.severe("Error interno: lista de libros no existe.");
            throw new BibliotecaLogicException("Error interno: lista de libros no existe.");
        }

        logger.info("retornando todas las ciudades");
        return libros;
    }

    /**
     * Devuelve el libro que tenga el id dado por parametro
     * @param id id del libro solicitado
     * @return libro con id dado por parametro
     * @throws BibliotecaLogicException Si lista de libros no existe, o si no se encuentra el libro.
     */
    public LibroDTO getLibro(Long id) throws BibliotecaLogicException {
        if (libros == null) {
            logger.severe("Error interno: lista de libros no existe.");
            throw new BibliotecaLogicException("Error interno: lista de libros no existe.");
        }
        logger.info("buscando libro");
        for (LibroDTO l : libros) {
            if (l.getId().equals(id)) {
                logger.info("libro encontrado");
                return l;
            }
        }
        logger.severe("No se encontró el libro");
        throw new BibliotecaLogicException("Libro no encontrado");
    }

    /**
     * Agrega un libro a la estructura de datos
     * @param libro Libro a agregar al registro
     * @return libro que entra por parametro
     * @throws BibliotecaLogicException Si ya existe un libro con ese id
     */
    public LibroDTO createLibro(LibroDTO libro) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de agregar libro " + libro);
    	
    	// el nuevo video tiene id ?
    	if ( libro.getId() != null ) {
	    	// busca el video con el id suministrado
	        for (LibroDTO l : libros) {
	        	// si existe un video con ese id
	            if (l.getId().equals(libro.getId())){
	            	logger.severe("Ya existe un libro con ese id");
                        throw new BibliotecaLogicException("Ya existe un libro con ese id");
                    }
	        }
	        
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para el nuevo libro");
    		long id = 1;
	        for (LibroDTO l : libros) {
	            if (id <= l.getId()){
	                id =  l.getId() + 1;
	            }
	        }
	        libro.setId(id);
    	}
    	
        logger.info("agregando libro" + libro);
        libros.add(libro);
        return libro;
    }

    /**
     * Actualiza un libro en el registro con los parametros deseados
     * @param id id del libro a actualizar
     * @param libro Objeto LibroDTO con parametros a cambiar.
     * @return Libro actualizado.
     * @throws BibliotecaLogicException Si el libro no se encuentra.
     */
    public LibroDTO updateLibro(Long id, LibroDTO libro) throws BibliotecaLogicException {
        try {
            LibroDTO l = getLibro(id);
            if (libro.getIsbn() != null)
                l.setIsbn(libro.getIsbn());
            if (libro.getNumEjemplares() != null)
                l.setNumEjemplares(libro.getNumEjemplares());
            if (libro.getName() != null)
                l.setName(libro.getName());
            logger.info("Libro actualizado");
            return l;
        } catch (BibliotecaLogicException ex) {
            logger.severe("No se econtro el libro con id " + id);
            throw new BibliotecaLogicException("No se encontro el libro con id" + id);
        }
    }

    /**
     * Borra un libro en el registro.
     * @param id id del libro a borrar
     * @throws BibliotecaLogicException Si no es posible borrar el libro.
     */
    public void deleteLibro(Long id) throws BibliotecaLogicException {
        try {
            libros.remove(getLibro(id));
        } catch (BibliotecaLogicException ex) {
            Logger.getLogger(LibroLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            logger.severe("No fue posible elminiar el libro con id " + id);
	throw new BibliotecaLogicException("No fue posible elminiar el libro con id " + id);
        }
    }

}
