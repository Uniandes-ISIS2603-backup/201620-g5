package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso prestamos (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import co.edu.uniandes.rest.resources.dtos.MultaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.PrestamoDTO;
import co.edu.uniandes.rest.resources.exceptions.BiblioLogicException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * PrestamoLogicMock
 * Mock del recurso prestamos (Mock del servicio REST)
 */
public class PrestamoLogicMock {

    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(PrestamoLogicMock.class.getName());

    // listado de prestamos
    private static ArrayList<PrestamoDTO> prestamos;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public PrestamoLogicMock() {

        if (prestamos == null) {
            try {
                prestamos = new ArrayList<>();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                String fecha1 = "30-Nov-1996";
                String fecha2 = "30-Nov-1997";
                String fecha3 = "30-Nov-1998";
                String fecha4 = "30-Nov-1999";
                String fecha5 = "30-Nov-2000";
                String fecha6 = "30-Nov-2001";
                String fecha7 = "30-Nov-2002";
                String fecha8 = "30-Nov-2003";
                String fecha9 = "30-Nov-2004";
                String fecha10 = "30-Nov-2005";
                String fecha11 = "30-Nov-2006";
                String fecha12 = "30-Nov-2007";
                prestamos.add(new PrestamoDTO(1L, 1L,1L,PrestamoDTO.LIBRO,  1L, 3000, "Efectivo", formatter.parse(fecha1), formatter.parse(fecha2), true));
                prestamos.add(new PrestamoDTO(2L, 2L, 2L,PrestamoDTO.VIDEO, 2L, 3000, "Tarjeta Credito", formatter.parse(fecha3), formatter.parse(fecha4), true));
                prestamos.add(new PrestamoDTO(3L, 3L, 3L,PrestamoDTO.SALA,  3L, 3000, "Efectivo", formatter.parse(fecha5), formatter.parse(fecha6), true));
                prestamos.add(new PrestamoDTO(4L, 1L, 1L,PrestamoDTO.VIDEO,  1L, 3000, "Efectivo", formatter.parse(fecha7), formatter.parse(fecha8), true));
                prestamos.add(new PrestamoDTO(5L, 2L, 2L,PrestamoDTO.SALA,  2L, 3000, "Tarjeta Credito", formatter.parse(fecha9), formatter.parse(fecha10), true));
                prestamos.add(new PrestamoDTO(6L, 3L, 3L,PrestamoDTO.LIBRO, 3L, 3000, "Efectivo", formatter.parse(fecha11), formatter.parse(fecha12), true));
                
                
            } catch (ParseException ex) {
                Logger.getLogger(PrestamoLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de prestamos");
        logger.info("prestamos: " + prestamos);
    }

    /**
     * Obtiene el listado de personas.
     *
     * @return lista de prestamos
     * @throws BiblioLogicException cuando no existe la lista en memoria
     */
    public ArrayList<PrestamoDTO> getPrestamos() throws BiblioLogicException {
        if (prestamos == null) {
            logger.severe("Error interno: lista de prestamos no existe.");
            throw new BiblioLogicException("Error interno: lista de prestamos no existe.");
        }

        logger.info("retornando todas las prestamos");
        return prestamos;
    }
    
    public ArrayList<PrestamoDTO> getPrestamo(Long idPrestamo) throws BiblioLogicException {
        ArrayList<PrestamoDTO> prestamosP = new ArrayList<>();
        if (prestamos == null) {
            logger.severe("Error interno: lista de prestamos no existe.");
            throw new BiblioLogicException("Error interno: lista de prestamos no existe.");
        }
        for (PrestamoDTO m : prestamos) {
            if (idPrestamo == m.getId()) {
                prestamosP.add(m);
            }
        }
        logger.info("retornando todos los prestamos");
        return prestamosP;
    }

    public ArrayList<PrestamoDTO> getPrestamosUsuario(Long idUsuario) throws BiblioLogicException {
        ArrayList<PrestamoDTO> prestamosUsuario = new ArrayList<>();
        if (prestamos == null) {
            logger.severe("Error interno: lista de prestamos no existe.");
            throw new BiblioLogicException("Error interno: lista de prestamos no existe.");
        }
        for (PrestamoDTO m : prestamos) {
            if (idUsuario == m.getIdUsuario()) {
                prestamosUsuario.add(m);
            }
        }
        logger.info("retornando todos los prestamos del usuario con id especificado");
        return prestamosUsuario;
    }

    
    /**
     * Agrega una prestamo a un usuario y a la lista.
     *
     * @param newPrestamo prestamo a adicionar
     * @throws BiblioLogicException cuando ya existe una biblioteca con el id
     * suministrado
     * @return biblioteca agregada
     */
    public PrestamoDTO createPrestamo(PrestamoDTO newPrestamo, Long idUsuario) throws BiblioLogicException {
        logger.info("recibiendo solicitud de agregar prestamo " + newPrestamo);
        // agrega el prestamo
        // el nuevo prestamo tiene id ?
        if (newPrestamo.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (PrestamoDTO prestamo : prestamos) {
                // si existe una sala con ese id
                if (prestamo.getId() == newPrestamo.getId()) {
                    logger.severe("Ya existe un prestamo con ese id");
                    throw new BiblioLogicException("Ya existe un prestamo con ese id");
                }
            }

            // el nuevo prestamo no tiene id ? 
        } else {

            // genera un id para la sala
            logger.info("Generando id para el nuevo prestamo");
            logger.info("Generando idUsuario para el nuevo prestamo");
            long newId = 1;
            for (PrestamoDTO prestamo : prestamos) {
                if (newId <= prestamo.getId()) {
                    newId = prestamo.getId() + 1;
                }
            }
            newPrestamo.setId(newId);
            newPrestamo.setIdUsuario(idUsuario);
        }
        logger.info("agregando prestamo: " + newPrestamo);
        prestamos.add(newPrestamo);
        return newPrestamo;
    }

    public PrestamoDTO getPrestamoDeUsuario(long id, long idUsuario) throws BiblioLogicException {
        PrestamoDTO m = null;
        List<PrestamoDTO> prestamosUsuario = getPrestamosUsuario(idUsuario);
        for (int i = 0; i < prestamosUsuario.size() && m == null; i++) {
            PrestamoDTO prestamo = prestamosUsuario.get(i);
            if (prestamo.getId() == id) {
                m = prestamo;
            }
        }
        if (m == null) {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
        return m;
    }

    public PrestamoDTO updatePrestamoDeUsuario(long id, PrestamoDTO m, long idUsuario) throws BiblioLogicException {
        PrestamoDTO prestamo = getPrestamoDeUsuario(id, idUsuario);
        if (prestamo != null) {
            prestamo.setCosto(m.getCosto());
            prestamo.setFechaInicial(m.getFechaInicial());
            prestamo.setFechaFinal(m.getFechaFinal());
            prestamo.setEstaActivo(m.isEstaActivo());
            prestamo.setMedioPago(m.getMedioPago());
            prestamo.setId(id);
            prestamo.setIdBiblioteca(m.getIdBiblioteca());
            prestamo.setIdRecurso(m.getIdRecurso());
            prestamo.setIdUsuario(idUsuario);
            return prestamo;
        } else {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
    }

    public PrestamoDTO regresarLibro(long idRecurso, long idUsuario, String fechaEntrega) throws BiblioLogicException, ParseException {
        MultaLogicMock multaLogic = new MultaLogicMock();
        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        List<MultaDTO> multas = multaLogic.getMultas();
        PrestamoDTO prestamo = null;
        for (int i = 0; i < prestamos.size() && prestamo == null; i++) {
            PrestamoDTO p = prestamos.get(i);
            logger.info("recibiendo informacion para retornar libro...");
            if (p.getIdRecurso() == idRecurso) {
                prestamo = p;
                MultaDTO multa = new MultaDTO(multas.size()+1L, idUsuario, p.getIdBiblioteca(), idRecurso, 1500, fecha);
                prestamo.setCosto(p.getCosto());
                prestamo.setFechaInicial(p.getFechaInicial());
                prestamo.setFechaFinal(p.getFechaFinal());
                prestamo.setEstaActivo(false);
                prestamo.setMedioPago(p.getMedioPago());
                prestamo.setId(p.getId());
                prestamo.setIdBiblioteca(p.getIdBiblioteca());
                prestamo.setIdRecurso(idRecurso);
                prestamo.setIdUsuario(idUsuario);
                if(prestamo.getFechaFinal().before(formatter.parse(fechaEntrega))){
                    multaLogic.createMulta(multa, idUsuario);
                }
            }
        }
        if (prestamo == null) {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
        return prestamo;
    }

    public void deletePrestamoDeUsuario(long id, long idUsuario) throws BiblioLogicException {
        logger.info("recibiendo solicitud de eliminar prestamo " + id);
        PrestamoDTO prestamo = getPrestamoDeUsuario(id, idUsuario);
        if (prestamo != null) {
            logger.info("Eliminando prestamo con el id especfificado: id = " + prestamo.getId());
            prestamos.remove(prestamo);
        } else {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
    }

    public List<PrestamoDTO> getPrestamosLibro(Long id) {
        ArrayList<PrestamoDTO> presLib;
        presLib = new ArrayList<>();
        for (PrestamoDTO p : prestamos) {
            if (id.equals(p.getIdRecurso()) && p.isEstaActivo()) {
                presLib.add(p);
            }
        }
        return presLib; 
    }

    public ArrayList<PrestamoDTO> getPrestamosBiblioteca(Long idBiblioteca) throws BiblioLogicException {
        ArrayList<PrestamoDTO> prestamosBiblioteca = new ArrayList<>();
        if (prestamos == null) {
            logger.severe("Error interno: lista de prestamos no existe.");
            throw new BiblioLogicException("Error interno: lista de prestamos no existe.");
        }
        for (PrestamoDTO m : prestamos) {
            if (idBiblioteca == m.getIdBiblioteca()) {
                prestamosBiblioteca.add(m);
            }
        }
        logger.info("retornando todos los prestamos del usuario con id especificado");
        return prestamosBiblioteca;
    }


    public PrestamoDTO getPrestamoDeBiblioteca(long id, long idBiblioteca) throws BiblioLogicException {
        PrestamoDTO m = null;
        List<PrestamoDTO> prestamosBiblioteca = getPrestamosBiblioteca(idBiblioteca);
        for (int i = 0; i < prestamosBiblioteca.size() && m == null; i++) {
            PrestamoDTO prestamo = prestamosBiblioteca.get(i);
            if (prestamo.getId() == id) {
                m = prestamo;
            }
        }
        if (m == null) {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
        return m;
    }

     /**
     * Agrega una prestamo a un usuario y a la lista.
     *
     * @param newPrestamo prestamo a adicionar
     * @throws BiblioLogicException cuando ya existe una biblioteca con el id
     * suministrado
     * @return biblioteca agregada
     */
    public PrestamoDTO createPrestamoBiblioteca(PrestamoDTO newPrestamo, Long idBiblioteca) throws BiblioLogicException {
        logger.info("recibiendo solicitud de agregar prestamo " + newPrestamo);
        // agrega el prestamo
        // el nuevo prestamo tiene id ?
        if (newPrestamo.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (PrestamoDTO prestamo : prestamos) {
                // si existe una sala con ese id
                if (prestamo.getId() == newPrestamo.getId()) {
                    logger.severe("Ya existe un prestamo con ese id");
                    throw new BiblioLogicException("Ya existe un prestamo con ese id");
                }
            }

            // el nuevo prestamo no tiene id ? 
        } else {

            // genera un id para la sala
            logger.info("Generando id para el nuevo prestamo");
            logger.info("Generando idUsuario para el nuevo prestamo");
            long newId = 1;
            for (PrestamoDTO prestamo : prestamos) {
                if (newId <= prestamo.getId()) {
                    newId = prestamo.getId() + 1;
                }
            }
            newPrestamo.setId(newId);
            newPrestamo.setIdBiblioteca(idBiblioteca);
        }
        logger.info("agregando prestamo: " + newPrestamo);
        prestamos.add(newPrestamo);
        return newPrestamo;
    }
}
