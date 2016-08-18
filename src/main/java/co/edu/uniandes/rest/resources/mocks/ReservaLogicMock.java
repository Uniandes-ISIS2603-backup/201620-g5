package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso Ciudades (Mock del servicio REST)
 *
 * @author Asistente
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;

/*
 * CityLogicMock
 * Mock del recurso Ciudades (Mock del servicio REST)
 */
public class BiblioLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(BiblioLogicMock.class.getName());

    // listado de ciudades
    private static ArrayList<BiblioDTO> cities;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public BiblioLogicMock() {

        if (cities == null) {
            cities = new ArrayList<>();
            cities.add(new BiblioDTO(1L, 1L, "Biblioteca 1", "Norte"));
            cities.add(new BiblioDTO(2L, 1L, "Biblioteca 2", "Sur"));
            cities.add(new BiblioDTO(3L, 1L, "Biblioteca 3", "Occidente"));
            cities.add(new BiblioDTO(4L, 1L, "Biblioteca 4", "Oriente"));
            cities.add(new BiblioDTO(5L, 1L, "Biblioteca 5", "Norte"));
            cities.add(new BiblioDTO(6L, 1L, "Biblioteca 6", "Sur"));
            cities.add(new BiblioDTO(7L, 1L, "Biblioteca 7", "Oriente"));
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de ciudades");
        logger.info("ciudades" + cities);
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de ciudades
     * @throws BiblioLogicException cuando no existe la lista en memoria
     */
    public List<BiblioDTO> getCities() throws BiblioLogicException {
        if (cities == null) {
            logger.severe("Error interno: lista de ciudades no existe.");
            throw new BiblioLogicException("Error interno: lista de ciudades no existe.");
        }

        logger.info("retornando todas las ciudades");
        return cities;
    }

    /**
     * Agrega una ciudad a la lista.
     *
     * @param newCity ciudad a adicionar
     * @throws BiblioLogicException cuando ya existe una ciudad con el id
     * suministrado
     * @return ciudad agregada
     */
    public BiblioDTO createCity(BiblioDTO newCity) throws BiblioLogicException {
        logger.info("recibiendo solicitud de agregar ciudad " + newCity);

        // la nueva ciudad tiene id ?
        if (newCity.getId() != null) {
            // busca la ciudad con el id suministrado
            for (BiblioDTO city : cities) {
                // si existe una ciudad con ese id
                if (Objects.equals(city.getId(), newCity.getId())) {
                    logger.severe("Ya existe una ciudad con ese id");
                    throw new BiblioLogicException("Ya existe una ciudad con ese id");
                }
            }

            // la nueva ciudad no tiene id ? 
        } else {

            // genera un id para la ciudad
            logger.info("Generando id paa la nueva ciudad");
            long newId = 1;
            for (BiblioDTO city : cities) {
                if (newId <= city.getId()) {
                    newId = city.getId() + 1;
                }
            }
            newCity.setId(newId);
        }

        // agrega la ciudad
        logger.info("agregando ciudad " + newCity);
        cities.add(newCity);
        return newCity;
    }

    public BiblioDTO getCity(long id) throws BiblioLogicException {
        BiblioDTO b = null;
        for (int i = 0; i < cities.size() && b == null; i++) {
            BiblioDTO biblio = cities.get(i);
            if (biblio.getId() == id) {
                b = biblio;
            } 
        }
        if(b== null)
        {
            logger.severe("No existe una biblioteca con ese id");
                throw new BiblioLogicException("No existe una biblioteca con ese id");
        }
        return b;
    }

    public BiblioDTO updateCity(long id, BiblioDTO b) throws BiblioLogicException {
        BiblioDTO c = getCity(id);
        if (c != null) {
            c.setIdAdmin(b.getIdAdmin());
            c.setName(b.getName());
            c.setZona(b.getZona());
            return c;
        } else {
            logger.severe("No existe una biblioteca con ese id");
            throw new BiblioLogicException("No existe una biblioteca con ese id");
        }
    }

    public void deleteCity(long id) throws BiblioLogicException {
        if (id > 0 && id <= cities.size()) {
            for (BiblioDTO b : cities) {
                if (id == b.getId()) {
                    logger.info("Eliminando ciudad con el id especfificado: id = " + b.getId());
                    cities.remove(b);
                }
            }
        } else {
            logger.severe("No existe una ciudad con ese id");
        }
        throw new BiblioLogicException("No existe una ciudad con ese id");
    }
}
