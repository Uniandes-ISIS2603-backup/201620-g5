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
import co.edu.uniandes.rest.resources.dtos.ReservaDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;

/*
 * CityLogicMock
 * Mock del recurso Ciudades (Mock del servicio REST)
 */
public class ReservaLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(ReservaLogicMock.class.getName());

    // listado de ciudades
    private static ArrayList<ReservaDTO> reservas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ReservaLogicMock() {

        if (reservas == null) {
            reservas = new ArrayList<>();
            reservas.add(new ReservaDTO(1, 1,1, true,1));
            reservas.add(new ReservaDTO(2, 2,2, true,2));
            reservas.add(new ReservaDTO(3, 3,3, true,3));
            reservas.add(new ReservaDTO(4, 4,4, true,4));
            
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de reservas");
        logger.info("Reservas" + reservas);
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de ciudades
     * @throws BiblioLogicException cuando no existe la lista en memoria
     */
    public List<ReservaDTO> getReservas() throws BiblioLogicException {
        if (reservas == null) {
            logger.severe("Error interno: lista de ciudades no existe.");
            throw new BiblioLogicException("Error interno: lista de ciudades no existe.");
        }

        logger.info("retornando todas las ciudades");
        return reservas;
    }

    /**
     * Agrega una ciudad a la lista.
     *
     * @param newCity ciudad a adicionar
     * @throws BiblioLogicException cuando ya existe una ciudad con el id
     * suministrado
     * @return ciudad agregada
     */
    public ReservaDTO createReserva(ReservaDTO newReserva) throws BiblioLogicException {
        logger.info("recibiendo solicitud de agregar ciudad " + newReserva);

        // la nueva ciudad tiene id ?
        if (newReserva.getId()!= 0) {
            // busca la ciudad con el id suministrado
            for (ReservaDTO reserva : reservas) {
                // si existe una ciudad con ese id
                if (Objects.equals(reserva.getId(), newReserva.getId())) {
                    logger.severe("Ya existe una reserva con ese id");
                    throw new BiblioLogicException("Ya existe una reserva con ese id");
                }
            }

            // la nueva ciudad no tiene id ? 
        } else {

            // genera un id para la ciudad
            logger.info("Generando id paa la nueva ciudad");
            int newId = 1;
            for (ReservaDTO reserva : reservas) {
                if (newId <= reserva.getId()) {
                    newId = reserva.getId() + 1;
                }
            }
            newReserva.setId(newId);
        }

        // agrega la ciudad
        logger.info("agregando ciudad " + newReserva);
        reservas.add(newReserva);
        return newReserva;
    }

    public ReservaDTO getReserva(long id) throws BiblioLogicException {
        ReservaDTO reservaE = null;
        for (int i = 0; i < reservas.size() && reservas == null; i++) {
           ReservaDTO resActual = reservas.get(i);
            if (resActual.getId() == id) {
                reservaE = resActual;
            } 
        }
        if(reservaE== null)
        {
            logger.severe("No existe una reserva con ese id");
                throw new BiblioLogicException("No existe una reserva con ese id");
        }
        return reservaE;
    }

    public ReservaDTO updateReserva(long id, ReservaDTO reserva) throws BiblioLogicException {
        ReservaDTO reservaE = getReserva(id);
        if (reservaE != null) {
            reservaE.setId(reserva.getId());
            reservaE.setIdBiblioteca(reserva.getIdBiblioteca());
            reservaE.setIdRecurso(reserva.getIdRecurso());
            reservaE.setIdUsuario(reserva.getIdUsuario());
            reservaE.setEstaA(reserva.isEstaA());
            return reservaE;
        } else {
            logger.severe("No existe una biblioteca con ese id");
            throw new BiblioLogicException("No existe una biblioteca con ese id");
        }
    }

    public void deleteReserva(long id) throws BiblioLogicException {
        if (id > 0 && id <= reservas.size()) {
            for (ReservaDTO reserva : reservas) {
                if (id == reserva.getId()) {
                    logger.info("Eliminando ciudad con el id especfificado: id = " + reserva.getId());
                    reservas.remove(reserva);
                }
            }
        } else {
            logger.severe("No existe una reserva con ese id");
        }
        throw new BiblioLogicException("No existe una reserva con ese id");
    }
}