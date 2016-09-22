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
import co.edu.uniandes.rest.resources.dtos.ReservaDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import java.text.ParseException;
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

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ReservaLogicMock() {
       

        if (reservas == null) {
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
            reservas = new ArrayList<>();
            try {
                reservas.add(new ReservaDTO(1L, 1L,1L,ReservaDTO.LIBRO,"caperucita",formater.parse(fecha1),true));
                reservas.add(new ReservaDTO(2L, 2L,2L, ReservaDTO.SALA, "Sala1", formater.parse(fecha2),true));
                reservas.add(new ReservaDTO(3L, 3L,3L, ReservaDTO.VIDEO, "El video", formater.parse(fecha3), true));
                reservas.add(new ReservaDTO(4L, 4L,4L, ReservaDTO.LIBRO,"libro",formater.parse(fecha4), true));
            } catch (ParseException ex) {
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

    public ReservaDTO updateReserva(long id, ReservaDTO reserva) throws BibliotecaLogicException {
        ReservaDTO reservaE = getReserva(id);
        if (reservaE != null) {
            reservaE.setId(reserva.getId());
            reservaE.setIdBiblioteca(reserva.getIdBiblioteca());
            
            reservaE.setIdUsuario(reserva.getIdUsuario());
            reservaE.setEstaA(reserva.isEstaA());
            return reservaE;
        } else {
            logger.severe("No existe una biblioteca con ese id");
            throw new BibliotecaLogicException("No existe una biblioteca con ese id");
        }
    }

    public void deleteReserva(long id) throws BibliotecaLogicException {
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

    public List<ReservaDTO> getReservasLibro(Long id) {
        ArrayList<ReservaDTO> resLib;
        resLib = new ArrayList<>();
        for (ReservaDTO r : reservas) {
            if (id.equals(r.getIdRecurso())) {
                resLib.add(r);
            }
        }
        return resLib;
    }

    public List<ReservaDTO> getReservasBiblioteca(Long id) {
        ArrayList<ReservaDTO> resBiblioteca;
        resBiblioteca = new ArrayList<>();
        for (ReservaDTO r : reservas) {
            if (id.equals(r.getIdBiblioteca())) {
                resBiblioteca.add(r);
            }
        }
        return resBiblioteca;
    }

    public ReservaDTO getReservaDeBiblioteca(Long id, Long idBiblioteca) throws BibliotecaLogicException {
        ReservaDTO respuesta=null;
        List<ReservaDTO> reservasBiblio= getReservasBiblioteca(idBiblioteca);
        for(ReservaDTO r:reservasBiblio){
            if(r.getId()==id){
                respuesta=r;
            }
        }
        if( respuesta==null){
            throw new BibliotecaLogicException("La reserva no existe");
        }
        return respuesta;
    }

    public ReservaDTO createReservaBiblioteca(ReservaDTO newReserva, Long idBiblioteca) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de agregar reserva " + newReserva);
        // agrega el reserva
        // el nuevo reserva tiene id ?
        if (newReserva.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (ReservaDTO reserva : reservas) {
                // si existe una sala con ese id
                if (reserva.getId() == newReserva.getId()) {
                    logger.severe("Ya existe un reserva con ese id");
                    throw new BibliotecaLogicException("Ya existe un reserva con ese id");
                }
            }

            // el nuevo reserva no tiene id ? 
        } else {

            // genera un id para la sala
            logger.info("Generando id para el nuevo reserva");
            logger.info("Generando idUsuario para el nuevo reserva");
            long newId = 1;
            for (ReservaDTO reserva : reservas) {
                if (newId <= reserva.getId()) {
                    newId = reserva.getId() + 1;
                }
            }
            newReserva.setId(newId);
            newReserva.setIdBiblioteca(idBiblioteca);
            newReserva.getRecurso().setEstaPrestado(true);
    }
        return newReserva;
    }

    public ReservaDTO updateReservaDeBiblioteca(Long id, ReservaDTO m, Long idBiblioteca) throws BibliotecaLogicException {
        
        ReservaDTO reserva=getReservaDeBiblioteca(id, idBiblioteca);
        reserva.actualizar(m, idBiblioteca);
        
                return reserva;
    }

    public ReservaDTO deleteReservaDeBiblioteca(Long id, Long idBiblioteca) throws BibliotecaLogicException {
        ReservaDTO reserva=getReservaDeBiblioteca(id, idBiblioteca);
        reservas.remove(reserva);
        return reserva;
        
    }
}
