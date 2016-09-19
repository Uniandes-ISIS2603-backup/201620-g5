package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso prestamos (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import co.edu.uniandes.rest.resources.dtos.LibroDTO;
import co.edu.uniandes.rest.resources.dtos.MultaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.PrestamoDTO;
import co.edu.uniandes.rest.resources.dtos.SalaDTO;
import co.edu.uniandes.rest.resources.dtos.UsuarioDTO;
import co.edu.uniandes.rest.resources.dtos.VideoDTO;
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

    
    private UsuarioLogicMock usuarioMock;
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public PrestamoLogicMock() {

        if (prestamos == null) {
            try {
                prestamos = new ArrayList<>();
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

                try {
                    UsuarioDTO u1 = usuarioMock.getUsuarios().get(0);
                    UsuarioDTO u2 = usuarioMock.getUsuarios().get(1);
                    UsuarioDTO u3 = usuarioMock.getUsuarios().get(2);
                
                
                prestamos.add(new PrestamoDTO(1L, u1,1L,PrestamoDTO.LIBRO, new LibroDTO(1L, 553213113L, "Moby Dick", 10L, true), 3000D, "Efectivo", formater.parse(fecha1), formater.parse(fecha2), true));
                prestamos.add(new PrestamoDTO(2L, u2, 2L,PrestamoDTO.VIDEO, new VideoDTO(1L, "The Revenant", "Alejandro G. Iñarritu",152L, 2015L, "Accion", 2L , "Oso contra hombre",false), 3000D, "Tarjeta Credito", formater.parse(fecha3), formater.parse(fecha4), true));
                prestamos.add(new PrestamoDTO(3L, u3, 3L,PrestamoDTO.SALA,  new SalaDTO(1L, 1L, "Sala 1", 6), 3000D, "Efectivo",formater.parse(fecha5),formater.parse(fecha6), true));
                prestamos.add(new PrestamoDTO(4L, u1, 1L,PrestamoDTO.VIDEO,  new VideoDTO(2L, "Mermaids: The Body Found", "Sid Bennet", 27L, 2012L,"Documental", 20L, "Sirenas", true ), 3000D, "Efectivo", formater.parse(fecha7), formater.parse(fecha8), true));
                prestamos.add(new PrestamoDTO(5L, u2, 2L,PrestamoDTO.SALA,  new SalaDTO(2L, 2L, "Sala 2", 6), 3000D, "Tarjeta Credito", formater.parse(fecha9), formater.parse(fecha10), true));
                prestamos.add(new PrestamoDTO(6L, u3, 3L,PrestamoDTO.LIBRO, new LibroDTO(2L, 743273567L, "The Great Gatsby", 10L, false), 3000D, "Efectivo", formater.parse(fecha11), formater.parse(fecha12), true));
                
            } catch (ParseException ex) {
                Logger.getLogger(PrestamoLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            }
            } catch (Exception ex) {
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
            if (idUsuario == m.getUsuario().getId()) {
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
                newPrestamo.setUsuario(getUsuario(idUsuario));
           
        }
        logger.info("agregando prestamo: " + newPrestamo);
        prestamos.add(newPrestamo);
        return newPrestamo;
    }

    public UsuarioDTO getUsuario( Long idUsuario)
    {
        try {
            return usuarioMock.getUsuario(idUsuario);
        } catch (Exception ex) {
            Logger.getLogger(PrestamoLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

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

    public PrestamoDTO updatePrestamoDeUsuario(long id, PrestamoDTO m, Long idUsuario) throws BiblioLogicException {
        PrestamoDTO prestamo = getPrestamoDeUsuario(id, idUsuario);
        if (prestamo != null) {
            prestamo.setCosto(m.getCosto());
            prestamo.setFechaInicial(m.getFechaInicial());
            prestamo.setFechaFinal(m.getFechaFinal());
            prestamo.setEstaActivo(m.isEstaActivo());
            prestamo.setMedioPago(m.getMedioPago());
            prestamo.setId(id);
            prestamo.setIdBiblioteca(m.getIdBiblioteca());
            prestamo.setRecurso(m.getRecurso());
            
            prestamo.setUsuario(getUsuario(idUsuario));
            return prestamo;
        } else {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
    }

    public PrestamoDTO regresarLibro(Long idRecurso, long idUsuario, String fechaEntrega) throws BiblioLogicException, ParseException {
        MultaLogicMock multaLogic = new MultaLogicMock();
        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        List<MultaDTO> multas = multaLogic.getMultas();
        PrestamoDTO prestamo = null;
        for (int i = 0; i < prestamos.size() && prestamo == null; i++) {
            PrestamoDTO p = prestamos.get(i);
            logger.info("recibiendo informacion para retornar libro...");
            if (p.getRecurso().getId() == idRecurso) {
                prestamo = p;
                MultaDTO multa = new MultaDTO(multas.size()+1L, idUsuario, p.getIdBiblioteca(), idRecurso, 1500, fecha);
                
                if(p.getFechaFinal().before(formatter.parse(fechaEntrega))){
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
            if (id.equals(p.getRecurso().getId()) && p.isEstaActivo() && p.getTipoRecurso().equals(PrestamoDTO.LIBRO)) {
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
            newPrestamo.getRecurso().setEstaPrestado(true);
        }
        logger.info("agregando prestamo: " + newPrestamo);
        prestamos.add(newPrestamo);
        return newPrestamo;
    }
    
     public PrestamoDTO deletePrestamoDeBiblioteca(long id, long idBiblioteca) throws BiblioLogicException {
        logger.info("recibiendo solicitud de eliminar prestamo " + id);
        PrestamoDTO prestamo = getPrestamoDeBiblioteca(id, idBiblioteca);
        if (prestamo != null) {
            logger.info("Eliminando prestamo con el id especfificado: id = " + prestamo.getId());
            prestamos.remove(prestamo);
            return prestamo;
        } else {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
    }
     
     public PrestamoDTO updatePrestamoDeBiblioteca(long id, PrestamoDTO m, Long idBiblioteca) throws BiblioLogicException {
        PrestamoDTO prestamo = getPrestamoDeBiblioteca(id, idBiblioteca);
        if (prestamo != null) {
            prestamo.setCosto(m.getCosto());
            prestamo.setFechaInicial(m.getFechaInicial());
            prestamo.setFechaFinal(m.getFechaFinal());
            prestamo.setEstaActivo(m.isEstaActivo());
            prestamo.setMedioPago(m.getMedioPago());
            prestamo.setId(id);
            prestamo.setUsuario(m.getUsuario());
            prestamo.setRecurso(m.getRecurso());
            
            prestamo.setIdBiblioteca(idBiblioteca);
            return prestamo;
        } else {
            logger.severe("No existe una prestamo con ese id");
            throw new BiblioLogicException("No existe una prestamo con ese id");
        }
    }

}
