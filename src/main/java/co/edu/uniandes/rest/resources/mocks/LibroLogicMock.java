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
	private final static Logger logger = Logger.getLogger(CityLogicMock.class.getName());
	
	// listado de libros
    private static ArrayList<LibroDTO> libros;
    
    public LibroLogicMock() {
        if(libros == null){
            libros = new ArrayList<>();
            libros.add(new LibroDTO(1L, 553213113L, "Moby Dick", 10, true));
            libros.add(new LibroDTO(2L, 743273567L, "The Great Gatsby", 10, false));
            libros.add(new LibroDTO(3L, 451524934L, "1984", 10, false));
        }
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de videos");
    	logger.info("libros" + libros );
    }

    public List<LibroDTO> getLibros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LibroDTO getLibro(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LibroDTO createLibro(LibroDTO libro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LibroDTO updateLibro(long id, LibroDTO libro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void deleteLibro(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
