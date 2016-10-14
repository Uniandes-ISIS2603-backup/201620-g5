package co.edu.uniandes.rest.resources.mocks;

/**
 * Mock del recurso Salas (Mock del servicio REST)
 *
 * @author sf.munera10
 */
import co.edu.uniandes.rest.resources.dtos.BiblioDTO;
import co.edu.uniandes.rest.resources.dtos.RecursoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;


import java.util.logging.Logger;

import co.edu.uniandes.rest.resources.dtos.SalaDTO;
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

    private BiblioLogicMock biblioMock;
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public SalaLogicMock() {
        if (salas == null) {
            try {
                salas = new ArrayList<>();
                biblioMock = new BiblioLogicMock();
                BiblioDTO b1 = biblioMock.getCities().get(0);
                BiblioDTO b2 = biblioMock.getCities().get(1);
                BiblioDTO b3 = biblioMock.getCities().get(2);
                salas.add(new SalaDTO(1L, b1, "Sala 1", 6));
                salas.add(new SalaDTO(2L, b3,"Sala 2", 6));
                
                salas.add(new SalaDTO(3L, b1,"Sala 3", 6));
                salas.add(new SalaDTO(4L, b2,  "Sala 4", 6));
                
                salas.add(new SalaDTO(5L, b1,  "Sala 5", 6));
                salas.add(new SalaDTO(6L, b2, "Sala 6", 6));
                
                salas.add(new SalaDTO(7L, b3, "Sala 7", 6));
                salas.add(new SalaDTO(8L, b2, "Sala 8", 6));
                
                salas.add(new SalaDTO(9L, b1,  "Sala 9", 6));
                salas.add(new SalaDTO(10L, b2,  "Sala 10", 6));
                
                salas.add(new SalaDTO(11L, b1, "Sala 11", 6));
                salas.add(new SalaDTO(12L, b2, "Sala 12", 6));
                
                salas.add(new SalaDTO(13L, b1, "Sala 13", 6));
                salas.add(new SalaDTO(14L, b2, "Sala 14", 6));
            } catch (BibliotecaLogicException ex) {
                Logger.getLogger(SalaLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            }
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
    public ArrayList<SalaDTO> getAllSalas() throws BibliotecaLogicException {
        if (salas == null) {
            logger.severe("Error interno: lista de salas no existe.");
            throw new BibliotecaLogicException("Error interno: lista de salas no existe.");
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
    public List<SalaDTO> getSalasBiblioteca(long idBiblioteca) throws BibliotecaLogicException {
        ArrayList<SalaDTO> salasBiblioteca = new ArrayList<>();
        if (salas == null) {
            logger.severe("Error interno: lista de salas no existe.");
            throw new BibliotecaLogicException("Error interno: lista de salas no existe.");
        }
        for (SalaDTO s : salas) {
            if (Objects.equals(idBiblioteca, s.getBiblioteca().getId())) {
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
    public SalaDTO createSalaBiblioteca(SalaDTO newSala, long idBiblioteca) throws BibliotecaLogicException {
        BiblioLogicMock biblioLogic = new BiblioLogicMock();
        BiblioDTO biblioteca = biblioLogic.getCity(idBiblioteca);
        logger.info("recibiendo solicitud de agregar sala " + newSala);
        BiblioDTO biblio = biblioMock.getCity(idBiblioteca);
        // la nueva sala tiene id ?
        if (newSala.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (SalaDTO sala : salas) {
                // si existe una sala con ese id
                if (Objects.equals(sala.getId(), newSala.getId())) {
                    logger.severe("Ya existe una sala con ese id");
                    throw new BibliotecaLogicException("Ya existe una sala con ese id");
                }
            }

            // la nueva sala no tiene id ? 
        }
        if (newSala.getId() != null) {
            for (SalaDTO sala : salas) {
                // si existe una sala con ese id
                if (Objects.equals(sala.getId(), newSala.getId())) {
                    logger.severe("Ya existe un recurso con ese id");
                    throw new BibliotecaLogicException("Ya existe un recurso con ese id");
                }
            }
        }
        if (biblioteca == null) {
            logger.severe("La biblioteca a la que se le quiere crear sala no existe");
            throw new BibliotecaLogicException("La biblioteca a la que se le quiere crear sala no existe");
        } else {

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
                if (newId2 <= sala.getId()) {
                    newId2 = sala.getId() + 1;
                }
            }
            newSala.setId(newId);
            newSala.setBiblioteca(biblio);
            newSala.setId(newId2);
        }

        // agrega la sala 
        RecursoLogicMock recursoLogic = new RecursoLogicMock();
        List<RecursoDTO> recursos = recursoLogic.getRecursos();
        logger.info("agregando sala: " + newSala);
        salas.add(newSala);
        RecursoDTO r = new RecursoDTO(newSala.getId(), newSala.getName(),biblio );
        recursos.add(r);
        return newSala;
    }
    
    public SalaDTO createSala(SalaDTO newSala) throws BibliotecaLogicException {
        logger.info("recibiendo solicitud de agregar sala " + newSala);
        
        // la nueva sala tiene id ?
        if (newSala.getId() != null) {
            // busca la sala con el id suministrado segun el id de Biblioteca
            for (SalaDTO sala : salas) {
                // si existe una sala con ese id
                if (Objects.equals(sala.getId(), newSala.getId())) {
                    logger.severe("Ya existe una sala con ese id");
                    throw new BibliotecaLogicException("Ya existe una sala con ese id");
                }
            }

            // la nueva sala no tiene id ? 
        }
       else
        {
        
            // genera un id para la sala
            logger.info("Generando id para la nueva sala");
            logger.info("Generando idBiblioteca para la nueva sala");
            logger.info("Generando idRecurso para la nueva sala");
            long newId = 1L;
            for (SalaDTO sala : salas) {
                if (newId <= sala.getId()) {
                    newId = sala.getId() + 1;
                }
            }
            newSala.setId(newId);
            newSala.setEstaPrestado(false);
            newSala.setEstaReservado(false);
        }

        // agrega la sala 
        salas.add(newSala);
        return newSala;
    }


    public SalaDTO getSalaDeBiblioteca(long id, long idBiblioteca) throws BibliotecaLogicException {
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
            throw new BibliotecaLogicException("No existe una sala con ese id");
        }
        return s;
    }
    
    public SalaDTO getSala(Long id) throws BibliotecaLogicException {
        SalaDTO s = null;
        List<SalaDTO> salasBiblioteca = getAllSalas();
        for (int i = 0; i < salasBiblioteca.size() && s == null; i++) {
            SalaDTO sala = salasBiblioteca.get(i);
            if (sala.getId() == id) {
                s = sala;
            }
        }
        if (s == null) {
            logger.severe("No existe una sala con ese id");
            throw new BibliotecaLogicException("No existe una sala con ese id");
        }
        return s;
    }
    
    
    public SalaDTO updateSalaDeBiblioteca(long id, SalaDTO s, long idBiblioteca) throws BibliotecaLogicException {
        SalaDTO sala = getSalaDeBiblioteca(id, idBiblioteca);
        if (sala != null) {
            sala.setName(s.getName());
            sala.setCapacidad(s.getCapacidad());
            return sala;
        } else {
            logger.severe("No existe una sala con ese id");
            throw new BibliotecaLogicException("No existe una sala con ese id");
        }
    }
    
     public SalaDTO updateSala(Long idSala, SalaDTO newSala) throws BibliotecaLogicException
    {
    	logger.info("recibiendo solicitud de actualizar video " + idSala);
    	
	    	// busca el video con el id suministrado
	        for (SalaDTO sala : salas) 
                {
	        	// si existe un video con ese id
	            if (Objects.equals(sala.getId(), idSala)){
                         // actualiza el video
                        logger.info("actualizando video " + idSala );
	            	sala.setName(newSala.getName());
                        sala.setId(newSala.getId());
                        sala.setCapacidad(newSala.getCapacidad());
                        sala.setBiblioteca(newSala.getBiblioteca());
                        
                        return newSala;
	            }
	        }
	        
               
                 logger.severe("No existe un video con ese id");
	         throw new BibliotecaLogicException("No existe un video con ese id");

    }
    		

    public void deleteSalaDeBiblioteca(long id, long idBiblioteca) throws BibliotecaLogicException, BibliotecaLogicException {
        logger.info("recibiendo solicitud de eliminar sala " + id);
        SalaDTO sala = getSalaDeBiblioteca(id, idBiblioteca);
        if (sala != null) {
            logger.info("Eliminando sala con el id especfificado: id = " + sala.getId());
            RecursoLogicMock recursoLogic = new RecursoLogicMock();
            List<RecursoDTO> recursos = recursoLogic.getRecursos();
            RecursoDTO r = recursoLogic.getRecurso(sala.getId());
            salas.remove(sala);
            recursos.remove(r);
        } else {
            logger.severe("No existe una sala con ese id");
            throw new BibliotecaLogicException("No existe una sala con ese id");
        }
    }
    
     public SalaDTO deleteSala(Long id) throws BibliotecaLogicException 
    {
    	logger.info("recibiendo solicitud de eliminar sala " + id);
    	
    	// el video tiene id ?
       
	    	// busca el video con el id suministrado
	        for (SalaDTO sala : salas) 
                {
	        	// si existe un video con ese id
	            if (Objects.equals(sala.getId(), id))
                    {
                         // elimina la ciudad
                        logger.info("eliminando video " + id);
                        salas.remove(sala);
                        return sala;
	            }
	        }
               
        logger.severe("No existe un video con ese id");
	throw new BibliotecaLogicException("No existe un video con ese id");
  
                    
	        
	
    	 
       
    }

     /**
      * R13
      * @param idBiblioteca biblioteca a la que pertenece la sala
      * @param fecha fecha que se desea consultar
      * @return la sala buscada si esta disponible, null de lo contrario.
      */
    public SalaDTO getDisponibilidadSala(Long idSala, String fecha) {
        ReservaLogicMock r = new ReservaLogicMock();
        PrestamoLogicMock p = new PrestamoLogicMock();
        return null;
        
    }
   
}
