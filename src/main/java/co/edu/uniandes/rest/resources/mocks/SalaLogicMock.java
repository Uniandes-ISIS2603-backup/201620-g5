package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso Salas (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import co.edu.uniandes.rest.resources.dtos.RecursoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.SalaDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;

/*
 * SalaLogicMock
 * Mock del recurso Salas (Mock del servicio REST)
 */
public class SalaLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(SalaLogicMock.class.getName());

    // listado de salas
    private static ArrayList<SalaDTO> salas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public SalaLogicMock() throws BiblioLogicException {
        if (salas == null) {
            salas = new ArrayList<>();
            salas.add(new SalaDTO(1L,1L, 1L, false, "Sala 1", 6));
            salas.add(new SalaDTO(2L,2L, 1L, false, "Sala 2", 6));

            salas.add(new SalaDTO(3L,1L, 2L, false, "Sala 1", 6));
            salas.add(new SalaDTO(4L,2L, 2L, false, "Sala 2", 6));

            salas.add(new SalaDTO(5L,1L, 3L, false, "Sala 1", 6));
            salas.add(new SalaDTO(6L,2L, 3L, false, "Sala 2", 6));

            salas.add(new SalaDTO(7L,1L, 4L, false, "Sala 1", 6));
            salas.add(new SalaDTO(8L,2L, 4L, false, "Sala 2", 6));

            salas.add(new SalaDTO(9L,1L, 5L, false, "Sala 1", 6));
            salas.add(new SalaDTO(10L,2L, 5L, false, "Sala 2", 6));

            salas.add(new SalaDTO(11L,1L, 6L, false, "Sala 1", 6));
            salas.add(new SalaDTO(12L,2L, 6L, false, "Sala 2", 6));

            salas.add(new SalaDTO(13L,1L, 7L, false, "Sala 1", 6));
            salas.add(new SalaDTO(14L,2L, 7L, false, "Sala 2", 6));
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info(
                "Inicializa la lista de salas");
        logger.info(
                "Salas: " + salas);
    }

    /**
     * Obtiene el listado de salas.
     *
     * @return lista de salas
     * @throws BiblioLogicException cuando no existe la lista en memoria
     */
    public List<SalaDTO> getAllSalas() throws BiblioLogicException {
        if (salas == null) {
            logger.severe("Error interno: lista de salas no existe.");
            throw new BiblioLogicException("Error interno: lista de salas no existe.");
        }

        logger.info("retornando todas las salas");
        return salas;
    }

    /**
     * Obtiene el listado de salas.
     *
     * @param idBiblioteca biblioteca a buscar
     * @throws BiblioLogicException cuando ya existe una biblioteca con el id
     * suministrado
     * @return lista de salas de la biblioteca buscada
     */
    public List<SalaDTO> getSalasBiblioteca(long idBiblioteca) throws BiblioLogicException {
        ArrayList<SalaDTO> salasBiblioteca = new ArrayList<>();
        if (salas == null) {
            logger.severe("Error interno: lista de salas no existe.");
            throw new BiblioLogicException("Error interno: lista de salas no existe.");
        }
        for (SalaDTO s : salas) {
            if (Objects.equals(idBiblioteca, s.getIdBiblioteca())) {
                salasBiblioteca.add(s);
            }
        }
        logger.info("retornando todas las salas de la biblioteca con id especificado");
        return salasBiblioteca;
    }

    /**
     * Agrega una sala a una biblioteca y a la lista de salas.
     *
     * @param newSala sala a adicionar
     * @param idBiblioteca id de la biblioteca para crear la sala
     * @throws BiblioLogicException cuando ya existe una sala con el id
     * suministrado
     * @return sala agregada
     */
    public SalaDTO createSala(SalaDTO newSala, long idBiblioteca) throws BiblioLogicException, BibliotecaLogicException {
        logger.info("recibiendo solicitud de agregar sala " + newSala);
        // la nueva sala tiene id ?
        if (newSala.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (SalaDTO sala : salas) {
                // si existe una sala con ese id
                if (Objects.equals(sala.getId(), newSala.getId())) {
                    logger.severe("Ya existe una sala con ese id");
                    throw new BiblioLogicException("Ya existe una sala con ese id");
                }
            }

            // la nueva sala no tiene id ? 
        }
        if(newSala.getIdRecurso() != null)
        {
            for (SalaDTO sala : salas) {
                // si existe una sala con ese id
                if (Objects.equals(sala.getIdRecurso(), newSala.getIdRecurso())) {
                    logger.severe("Ya existe un recurso con ese id");
                    throw new BiblioLogicException("Ya existe un recurso con ese id");
                }
            }
        }
        else {

            // genera un id para la sala
            logger.info("Generando id para la nueva sala");
            logger.info("Generando idBiblioteca para la nueva sala");
            logger.info("Generando idRecurso para la nueva sala");
            long newId = 1;
            long newId2 = 1;
            for (SalaDTO sala : salas) {
                if (newId <= sala.getId()) {
                    newId = sala.getId() + 1;
                }
                if(newId2 <= sala.getIdRecurso())
                {
                    newId2 = sala.getIdRecurso()+1;
                }
            }
            newSala.setId(newId);
            newSala.setIdBiblioteca(idBiblioteca);
            newSala.setIdRecurso(newId2);
        }

        // agrega la sala 
        logger.info("agregando sala: " + newSala);
        RecursoLogicMock recursoLogic = new RecursoLogicMock();
        List<RecursoDTO> recursos = recursoLogic.getRecursos();
        salas.add(newSala);
        RecursoDTO r = new RecursoDTO(newSala.getIdRecurso(), newSala.getNombre());
        recursos.add(r);
        return newSala;
    }

    public SalaDTO getSalaDeBiblioteca(long id, long idBiblioteca) throws BiblioLogicException {
        SalaDTO s = null;
        List<SalaDTO> salasBiblioteca = getSalasBiblioteca(idBiblioteca);
        for (int i = 0; i < salasBiblioteca.size() && s == null; i++) {
            SalaDTO sala = salasBiblioteca.get(i);
            if (sala.getId() == id) {
                s = sala;
            }
        }
        if (s == null) {
            logger.severe("No existe una sala con ese id");
            throw new BiblioLogicException("No existe una sala con ese id");
        }
        return s;
    }

    public SalaDTO updateSalaDeBiblioteca(long id, SalaDTO s, long idBiblioteca) throws BiblioLogicException {
        SalaDTO sala = getSalaDeBiblioteca(id, idBiblioteca);
        if (sala != null) {
            sala.setEstaOcupada(s.isEstaOcupada());
            sala.setNombre(s.getNombre());
            sala.setCapacidad(s.getCapacidad());
            return sala;
        } else {
            logger.severe("No existe una sala con ese id");
            throw new BiblioLogicException("No existe una sala con ese id");
        }
    }

    public void deleteSalaDeBiblioteca(long id, long idBiblioteca) throws BiblioLogicException, BibliotecaLogicException {
        logger.info("recibiendo solicitud de eliminar sala " + id);
        SalaDTO sala = getSalaDeBiblioteca(id, idBiblioteca);
        if (sala != null) {
            logger.info("Eliminando sala con el id especfificado: id = " + sala.getId());
            RecursoLogicMock recursoLogic = new RecursoLogicMock();
            List<RecursoDTO> recursos = recursoLogic.getRecursos();
            RecursoDTO r = recursoLogic.getRecurso(sala.getIdRecurso());
            salas.remove(sala);
            recursos.remove(r);
        } else {
            logger.severe("No existe una sala con ese id");
            throw new BiblioLogicException("No existe una sala con ese id");
        }
    }
}
