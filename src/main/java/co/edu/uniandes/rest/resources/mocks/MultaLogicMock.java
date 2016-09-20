package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso multas (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.MultaDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * MultaLogicMock
 * Mock del recurso multas (Mock del servicio REST)
 */
public class MultaLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(MultaLogicMock.class.getName());

    // listado de multas
    private static ArrayList<MultaDTO> multas;
    
    

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public MultaLogicMock() {
        Date fecha = new Date();
        if (multas == null) {
            multas = new ArrayList<>();
            multas.add(new MultaDTO(1L, 1L, 1L, 1L, 1500, fecha));
            multas.add(new MultaDTO(2L, 2L, 2L, 2L, 1500, fecha));
            multas.add(new MultaDTO(3L, 3L, 3L, 3L, 1500, fecha));
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de multas");
        logger.info("multas: " + multas);
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de multas
     * @throws BiblioLogicException cuando no existe la lista en memoria
     */
    public List<MultaDTO> getMultas() throws BibliotecaLogicException {
        if (multas == null) {
            logger.severe("Error interno: lista de multas no existe.");
            throw new BibliotecaLogicException("Error interno: lista de multas no existe.");
        }

        logger.info("retornando todas las multas");
        return multas;
    }

    public List<MultaDTO> getMultasUsuario(long idUsuario) throws BibliotecaLogicException {
        ArrayList<MultaDTO> multasUsuario = new ArrayList<>();
        if (multas == null) {
            logger.severe("Error interno: lista de multas no existe.");
            throw new BibliotecaLogicException("Error interno: lista de multas no existe.");
        }
        for (MultaDTO m : multas) {
            if (Objects.equals(idUsuario, m.getIdUsuario())) {
                multasUsuario.add(m);
            }
        }
        logger.info("retornando todas las multas del usuario con id especificado");
        return multasUsuario;
    }
    
    public MultaDTO createMulta(MultaDTO newMulta) throws BibliotecaLogicException
    {
        logger.info("recibiendo solicitud de agregar multa " + newMulta);

        // la nueva multa tiene id ?
        if (newMulta.getId() != null) {
            // busca la multa con el id suministrado
            for (MultaDTO m : multas) {
                // si existe una multa con ese id
                if (Objects.equals(m.getId(), newMulta.getId())) {
                    logger.severe("Ya existe una multa con ese id");
                    throw new BibliotecaLogicException("Ya existe una multa con ese id");
                }
            }

            // la nueva multa no tiene id ? 
        } else {

            // genera un id para la multa
            logger.info("Generando id para la nueva multa");
            long newId = 1;
            for (MultaDTO multa : multas) {
                if (newId <= multa.getId()) {
                    newId = multa.getId() + 1;
                }
            }
            newMulta.setId(newId);
        }

        // agrega la biblioteca
        logger.info("agregando multa: " + newMulta);
        multas.add(newMulta);
        return newMulta;
    }
    
    public MultaDTO getMulta(long id) throws BibliotecaLogicException {
        MultaDTO m = null;
        for (int i = 0; i < multas.size() && m == null; i++) {
            MultaDTO multa = multas.get(i);
            if (multa.getId() == id) {
                m = multa;
            }
        }
        if (m == null) {
            logger.severe("No existe una multa con ese id");
            throw new BibliotecaLogicException("No existe una multa con ese id");
        }
        return m;
    }

    public MultaDTO updateMulta(long id, MultaDTO m) throws BibliotecaLogicException {
        MultaDTO mu = getMulta(id);
        if (mu != null) {
            mu.setCosto(m.getCosto());
            mu.setFecha(m.getFecha());
            mu.setIdBiblioteca(m.getIdBiblioteca());
            mu.setIdRecurso(m.getIdRecurso());
            mu.setIdUsuario(m.getIdUsuario());
            return mu;
        } else {
            logger.severe("No existe una multa con ese id");
            throw new BibliotecaLogicException("No existe una multa con ese id");
        }
    }

    public void deleteMulta(long id) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de eliminar multa " + id);
        MultaDTO b = null;
        for (int i = 0; i < multas.size() && b == null; i++) {
            MultaDTO m = multas.get(i);
            if (id == m.getId()) {
                b = m;
                logger.info("Eliminando multa con el id especfificado: id = " + b.getId());
                multas.remove(b);
            }
        }
        if (b == null) {
            logger.severe("No existe una multa con ese id");
            throw new BibliotecaLogicException("No existe una multa con ese id");
        }
    }
    
    /**
     * Agrega una multa a un usuario y a la lista.
     *
     * @param newMulta multa a adicionar
     * @throws BiblioLogicException cuando ya existe una biblioteca con el id
     * suministrado
     * @return biblioteca agregada
     */
    public MultaDTO createMultaUsuario(MultaDTO newMulta, long idUsuario)  {
        logger.info("recibiendo solicitud de agregar multa " + newMulta);
        // agrega la multa
        logger.info("agregando multa: " + newMulta);
        newMulta.setIdUsuario(idUsuario);
        multas.add(newMulta);
        return newMulta;
    }

    public MultaDTO getMultaDeUsuario(long id, long idUsuario) throws BibliotecaLogicException {
        MultaDTO m = null;
        List<MultaDTO> multasUsuario = getMultasUsuario(idUsuario);
        for (int i = 0; i < multasUsuario.size() && m == null; i++) {
            MultaDTO multa = multasUsuario.get(i);
            if (multa.getId() == id) {
                m = multa;
            }
        }
        if (m == null) {
            logger.severe("No existe una multa con ese id");
            throw new BibliotecaLogicException("No existe una multa con ese id");
        }
        return m;
    }

    public MultaDTO updateMultaDeUsuario(long id, MultaDTO m, long idUsuario) throws BibliotecaLogicException {
        MultaDTO multa = getMultaDeUsuario(id, idUsuario);
        if (multa != null) {
            multa.setCosto(m.getCosto());
            multa.setFecha(m.getFecha());
            multa.setId(id);
            multa.setIdBiblioteca(m.getIdBiblioteca());
            multa.setIdRecurso(m.getIdRecurso());
            multa.setIdUsuario(idUsuario);
            return multa;
        } else {
            logger.severe("No existe una multa con ese id");
            throw new BibliotecaLogicException("No existe una multa con ese id");
        }
    }

    public void deleteMultaDeBiblioteca(long id, long idUsuario) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de eliminar multa " + id);
        MultaDTO multa = getMultaDeUsuario(id, idUsuario);
        if (multa != null) {
            logger.info("Eliminando multa con el id especfificado: id = " + multa.getId());
            multas.remove(multa);
        } else {
            logger.severe("No existe una multa con ese id");
            throw new BibliotecaLogicException("No existe una multa con ese id");
        }
    }
}
