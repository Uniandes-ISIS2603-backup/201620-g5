package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso multas (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.MultaDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
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
    public List<MultaDTO> getMultas() throws BiblioLogicException {
        if (multas == null) {
            logger.severe("Error interno: lista de multas no existe.");
            throw new BiblioLogicException("Error interno: lista de multas no existe.");
        }

        logger.info("retornando todas las multas");
        return multas;
    }

    public List<MultaDTO> getMultasUsuario(long idUsuario) throws BiblioLogicException {
        ArrayList<MultaDTO> multasUsuario = new ArrayList<>();
        if (multas == null) {
            logger.severe("Error interno: lista de multas no existe.");
            throw new BiblioLogicException("Error interno: lista de multas no existe.");
        }
        for (MultaDTO m : multas) {
            if (Objects.equals(idUsuario, m.getIdUsuario())) {
                multasUsuario.add(m);
            }
        }
        logger.info("retornando todas las multas del usuario con id especificado");
        return multasUsuario;
    }
    
    /**
     * Agrega una multa a un usuario y a la lista.
     *
     * @param newMulta multa a adicionar
     * @throws BiblioLogicException cuando ya existe una biblioteca con el id
     * suministrado
     * @return biblioteca agregada
     */
    public MultaDTO createMulta(MultaDTO newMulta, long idUsuario)  {
        logger.info("recibiendo solicitud de agregar multa " + newMulta);
        // agrega la multa
        logger.info("agregando multa: " + newMulta);
        newMulta.setIdUsuario(idUsuario);
        multas.add(newMulta);
        return newMulta;
    }

    public MultaDTO getMultaDeUsuario(long id, long idUsuario) throws BiblioLogicException {
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
            throw new BiblioLogicException("No existe una multa con ese id");
        }
        return m;
    }

    public MultaDTO updateMultaDeUsuario(long id, MultaDTO m, long idUsuario) throws BiblioLogicException {
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
            throw new BiblioLogicException("No existe una multa con ese id");
        }
    }

    public void deleteMultaDeBiblioteca(long id, long idUsuario) throws BiblioLogicException {
        logger.info("recibiendo solicitud de eliminar multa " + id);
        MultaDTO multa = getMultaDeUsuario(id, idUsuario);
        if (multa != null) {
            logger.info("Eliminando multa con el id especfificado: id = " + multa.getId());
            multas.remove(multa);
        } else {
            logger.severe("No existe una multa con ese id");
            throw new BiblioLogicException("No existe una multa con ese id");
        }
    }
}
