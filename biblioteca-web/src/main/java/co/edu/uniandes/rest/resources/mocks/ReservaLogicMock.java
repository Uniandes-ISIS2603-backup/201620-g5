package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso Ciudades (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import co.edu.uniandes.rest.resources.dtos.PrestamoDTO;
import co.edu.uniandes.rest.resources.dtos.ReservaDTO;
import co.edu.uniandes.rest.resources.dtos.SalaDTO;
import co.edu.uniandes.rest.resources.dtos.UsuarioDTO;
import co.edu.uniandes.rest.resources.dtos.VideoDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import java.text.SimpleDateFormat;

/*
 * CityLogicMock
 * Mock del recurso Ciudades (Mock del servicio REST)
 */
public class ReservaLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(ReservaLogicMock.class.getName());

    // listado de ciudades
    private static ArrayList<ReservaDTO> reservas;
    
    private static RecursoLogicMock recursos;
    private UsuarioLogicMock usuarioMock;
    private BiblioLogicMock biblioMock;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ReservaLogicMock() {

        if (reservas == null) {
            try {
                reservas = new ArrayList<>();
                String fecha1 = "2016-09-25";
                String fecha2 = "2016-09-26";
                String fecha3 = "2016-09-27";
                String fecha4 = "2016-09-28";
                String fecha5 = "2016-09-29";
                String fecha6 = "2016-09-30";
                String fecha7 = "2016-10-01";
                String fecha8 = "2016-10-02";
                String fecha9 = "2016-10-03";
                String fecha10 = "2016-10-04";
                String fecha11 = "2016-10-05";
                String fecha12 = "2016-10-06";
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                usuarioMock = new UsuarioLogicMock();
                UsuarioDTO u1 = usuarioMock.getUsuarios().get(0);
                UsuarioDTO u2 = usuarioMock.getUsuarios().get(1);
                UsuarioDTO u3 = usuarioMock.getUsuarios().get(2);
                
                BiblioDTO b1 = biblioMock.getCities().get(0);
                BiblioDTO b2 = biblioMock.getCities().get(1);
                BiblioDTO b3 = biblioMock.getCities().get(2);
                
                
                reservas.add(new ReservaDTO(1L, u1,b1, true,ReservaDTO.LIBRO, new LibroDTO(1L, 553213113L, "Moby Dick", 10L, true), formater.parse(fecha1), formater.parse(fecha2)));
                reservas.add(new ReservaDTO(2L, u2,b2, true,ReservaDTO.VIDEO, new VideoDTO(1L, "The Revenant", "Alejandro G. Iñarritu",152L, 2015L, "Accion", 2L , "Oso contra hombre",false), formater.parse(fecha3), formater.parse(fecha4)));
                reservas.add(new ReservaDTO(3L, u3,b3, true,ReservaDTO.SALA,  new SalaDTO(1L, 1L, "Sala 1", 6),formater.parse(fecha5),formater.parse(fecha6)));
                reservas.add(new ReservaDTO(4L, u1,b1, true,PrestamoDTO.VIDEO, new VideoDTO(2L, "Mermaids: The Body Found", "Sid Bennet", 27L, 2012L,"Documental", 20L, "Sirenas", true ), formater.parse(fecha7), formater.parse(fecha8)));
            } catch (Exception ex) {
                Logger.getLogger(ReservaLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
    public List<ReservaDTO> getReservas() throws BibliotecaLogicException  {
        if (reservas == null) {
            logger.severe("Error interno: lista de ciudades no existe.");
            throw new BibliotecaLogicException("Error interno: lista de ciudades no existe.");
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
    public ReservaDTO createReserva(ReservaDTO newReserva) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de agregar ciudad " + newReserva);

        // la nueva ciudad tiene id ?
        if (newReserva.getId()!= 0) {
            // busca la ciudad con el id suministrado
            for (ReservaDTO reserva : reservas) {
                // si existe una ciudad con ese id
                if (Objects.equals(reserva.getId(), newReserva.getId())) {
                    logger.severe("Ya existe una reserva con ese id");
                    throw new BibliotecaLogicException("Ya existe una reserva con ese id");
                }
            }

            // la nueva ciudad no tiene id ? 
        } else {

            // genera un id para la ciudad
            logger.info("Generando id paa la nueva ciudad");
           Long newId = 1L;
            for (ReservaDTO reserva : reservas) {
                if (newId <= reserva.getId()) {
                    newId = reserva.getId()+1 ;
                }
            }
            
            newReserva.setId(newId);
            recursos.getRecurso(newReserva.getRecurso().getId()).setEstaPrestado(true);
        }

        // agrega la ciudad
        logger.info("agregando ciudad " + newReserva);
        reservas.add(newReserva);
        return newReserva;
    }

    public ReservaDTO getReserva(Long id) throws BibliotecaLogicException {
        ReservaDTO reservaE = null;
        for (int i = 0; i < reservas.size(); i++) {
           ReservaDTO resActual = reservas.get(i);
            if (resActual.getId() == id) {
                reservaE = resActual;
            } 
        }
        if(reservaE== null)
        {
            logger.severe("No existe una reserva con ese id");
                throw new BibliotecaLogicException("No existe una reserva con ese id");
        }
        return reservaE;
    }

    public ReservaDTO updateReserva(Long id, ReservaDTO reserva) throws BibliotecaLogicException {
        ReservaDTO reservaE = getReserva(id);
        if (reservaE != null) {
            reservaE.setId(reserva.getId());
            reservaE.setIdBiblioteca(reserva.getIdBiblioteca());
            reservaE.setRecurso(reserva.getRecurso());
            reservaE.setIdUsuario(reserva.getIdUsuario());
            reservaE.setEstaA(reserva.isEstaA());
            return reservaE;
        } else {
            logger.severe("No existe una biblioteca con ese id");
            throw new BibliotecaLogicException("No existe una biblioteca con ese id");
        }
    }

    public void deleteReserva(Long id) throws BibliotecaLogicException {
            for (ReservaDTO reserva : reservas) {
                if (id == reserva.getId()) {
                    logger.info("Eliminando reserva con el id especfificado: id = " + reserva.getId());
                    reservas.remove(reserva);
                    return;
                }
            }
        logger.severe("No existe un video con ese id");

        throw new BibliotecaLogicException("No existe una reserva con ese id");
    }

    
    /**
     * R11: Dar reservas activas de un libro
     * @param id id del libro a consultar
     * @return lista de las reservas activas del libro.
     */
    public List<ReservaDTO> getReservasLibro(Long id) {
        ArrayList<ReservaDTO> resLib;
        resLib = new ArrayList<>();
        for (ReservaDTO r : reservas) {
            if (id.equals(r.getRecurso().getId())) {
                resLib.add(r);
            }
        }
        return resLib;
    }
}
