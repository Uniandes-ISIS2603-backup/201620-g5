package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso Recursos (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.RecursoDTO;
import co.edu.uniandes.rest.resources.dtos.SalaDTO;
import co.edu.uniandes.rest.resources.dtos.VideoDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;

/*
 * RecursoLogicMock
 * Mock del recurso Recursos (Mock del servicio REST)
 */
public class RecursoLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(RecursoLogicMock.class.getName());

    // listado de Recursos
    private static ArrayList<RecursoDTO> recursos;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public RecursoLogicMock() {

        try {
            LibroLogicMock libros = new LibroLogicMock();
            ArrayList<LibroDTO> listaLibros = libros.getLibros();
            VideoLogicMock videos = new VideoLogicMock();
            ArrayList<VideoDTO> listaVideos = videos.getVideos();
            SalaLogicMock salas = new SalaLogicMock();
            ArrayList<SalaDTO> listaSalas = salas.getAllSalas();
            if (recursos == null) {
                recursos = new ArrayList<>();
                for(int i = 0; i < listaLibros.size(); i++)
                {
                    recursos.add(listaLibros.get(i));
                }
                for(int i = 0; i < listaVideos.size(); i++)
                {
                    recursos.add(listaVideos.get(i));
                }
                for(int i = 0; i < listaSalas.size(); i++)
                {
                    recursos.add(listaSalas.get(i));
                }
                
            }
            
            // indica que se muestren todos los mensajes
            logger.setLevel(Level.INFO);
            
            // muestra información
            logger.info("Inicializa la lista de Recursos");
            logger.info("Recursos" + recursos);
        } catch (BibliotecaLogicException ex) {
            Logger.getLogger(RecursoLogicMock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de Recursos
     * @throws BibliotecaLogicException cuando no existe la lista en memoria
     */
    public List<RecursoDTO> getRecursos() throws BibliotecaLogicException {
        if (recursos == null) {
            logger.severe("Error interno: lista de Recursos no existe.");
            throw new BibliotecaLogicException("Error interno: lista de Recursos no existe.");
        }

        logger.info("retornando todas las Recursos");
        return recursos;
    }

    /**
     * Agrega una ciudad a la lista.
     *
     * @param newCity ciudad a adicionar
     * @throws BibliotecaLogicException cuando ya existe una ciudad con el id
     * suministrado
     * @return ciudad agregada
     */
    public RecursoDTO createRecurso(RecursoDTO newCity) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de agregar recurso " + newCity);
        // la nueva ciudad tiene id ?
        if (newCity.getId() != null) {
            // busca la ciudad con el id suministrado
            for (RecursoDTO city : recursos) {
                // si existe una ciudad con ese id
                if (Objects.equals(city.getId(), newCity.getId())) {
                    logger.severe("Ya existe una ciudad con ese id");
                    throw new BibliotecaLogicException("Ya existe una ciudad con ese id");
                }
            }

            // la nueva ciudad no tiene id ? 
        } else {

            // genera un id para la ciudad
            logger.info("Generando id paa la nueva ciudad");
            long newId = 1;
            for (RecursoDTO city : recursos) {
                if (newId <= city.getId()) {
                    newId = city.getId() + 1;
                }
            }
            newCity.setId(newId);
        }

        // agrega el recurso
            logger.info("agregando recurso " + newCity);
            recursos.add(newCity);
        
        return newCity;
    }
    
     public RecursoDTO getRecurso(long id) throws BibliotecaLogicException {
        RecursoDTO b = null;
        for (int i = 0; i < recursos.size() && b == null; i++) {
            RecursoDTO r = recursos.get(i);
            if (r.getId() == id) {
                b = r;
            } 
        }
        if(b== null)
        {
            logger.severe("No existe un recurso con ese id");
                throw new BibliotecaLogicException("No existe un recurso con ese id");
        }
        return b;
    }

}
