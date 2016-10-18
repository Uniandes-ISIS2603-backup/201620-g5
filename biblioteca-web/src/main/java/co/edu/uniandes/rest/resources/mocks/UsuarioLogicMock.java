/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.mocks;


import co.edu.uniandes.rest.resources.dtos.UsuarioDTO;
import co.edu.uniandes.rest.resources.exceptions.BibliotecaLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author js.prieto10
 */
public class UsuarioLogicMock {
    
    
    private final static Logger LOGGER = Logger.getLogger(UsuarioLogicMock.class.getName());
	
	// listado de ciudades
    private static ArrayList<UsuarioDTO> usuarios;
    
                
    /**
     * Crea unos usuarios de ejemplo
     */ 
    public UsuarioLogicMock()
    {
        if ( usuarios == null)
        {
            usuarios = new ArrayList<>();
            UsuarioDTO u1 = new UsuarioDTO("Stephen", "Hawking", "elLocoStephe", 1L, "1234", "en mi casa");
            usuarios.add(u1);
            UsuarioDTO u2 = new UsuarioDTO("Uribe", "Alvaro", "elDotorUribe", 2L, "4321", "la casa nariño");
            usuarios.add(u2);
            UsuarioDTO u3 = new UsuarioDTO("Chuck", "Norris", " tuDios", 3L, "9874", "En todo lado");
            usuarios.add(u3);
            
            LOGGER.setLevel(Level.INFO);
            
            // muestra información
            LOGGER.info("Inicializa la lista de usuarios");
            LOGGER.log(Level.INFO, "usuarios{0}", usuarios);
        }
        
    }
    
    /**
     * 
     * @return la lista de usuario
     */
    public List<UsuarioDTO> getUsuarios() throws BibliotecaLogicException {
    	if (usuarios == null) {
    		LOGGER.severe("Error interno: lista de usuarios no existe.");
    		throw new BibliotecaLogicException("Error interno: lista de usuarios no existe.");    		
    	}
    	
    	LOGGER.info("retornando todas las usuarios");
    	return usuarios;
    }
    
    
 
    /**
     * 
     * @param pId
     * @return un usuario dado por parametro
     * @throws BibliotecaLogicException arroja una excepcion si el usuario no existe
     */
    public UsuarioDTO getUsuario(Long pId) throws BibliotecaLogicException
    {
        if (usuarios == null) {
    		LOGGER.severe("Error interno: lista de usuarios no existe.");
    		throw new BibliotecaLogicException("Error interno: lista de usuarios no existe.");
        }
        for (UsuarioDTO usuario: usuarios) {
	        
	            if (Objects.equals(usuario.getId(), pId)){
	            	LOGGER.info("retornando la usuario buscada");
                        return usuario;
	            }
	        }
                LOGGER.severe("No existe un usuario con ese id");
	        throw new BibliotecaLogicException("No existe un usuario con ese id");
    }
    

    /**
     * 
     * @param newUsuario
     * @return crea un usuario con la información que le entra por parametro
     * @throws BibliotecaLogicException arroja una excepcion si el usuario existe
     */
    public UsuarioDTO createUsuario(UsuarioDTO newUsuario) throws BibliotecaLogicException
    {
    LOGGER.log(Level.INFO, "recibiendo solicitud de agregar Usuario{0}", newUsuario);
    	
    
    	if ( newUsuario.getId() != null ) {
            
	        for (UsuarioDTO usuario: usuarios) {
                    
	            if (Objects.equals(usuario.getId(), newUsuario.getId())){
	            	LOGGER.severe("Ya existe una Usuario con ese id");
	                throw new BibliotecaLogicException("Ya existe una Usuario con ese id");
	            }
	        }
	        
    	} else {

    		LOGGER.info("Generando id paa la nueva Usuario");
    		long newId = 1;
	        for (UsuarioDTO usuario : usuarios) {
	            if (newId <= usuario.getId()){
	                newId =  usuario.getId() + 1;
	            }
	        }
	        newUsuario.setId(newId);
    	}
    	
    	LOGGER.log(Level.INFO, "agregando usuario {0}", newUsuario);
        usuarios.add(newUsuario);
        return newUsuario;
    }
    
    /**
     * 
     * @param idUsuario
     * @param newUsuario
     * @return acualiza la informacion de un usuario
     * @throws BibliotecaLogicException arroja una excepcion si el id dado no existe
     */
    public UsuarioDTO updateUsuario(Long idUsuario, UsuarioDTO newUsuario) throws BibliotecaLogicException
    {
        LOGGER.log(Level.INFO, "recibiendo solicitud de actualizar video {0}", idUsuario);
   
   			UsuarioDTO usuario = getUsuario(idUsuario);
	        	// si existe un usuario con ese id
	            if (Objects.equals(usuario.getId(), idUsuario)){
                         // actualiza el usuario
                        LOGGER.log(Level.INFO, "actualizando usuario {0}", idUsuario);
	            	usuario.setNombre(newUsuario.getNombre());
                        usuario.setApellido(newUsuario.getApellido());
                        usuario.setContrasenha(newUsuario.getContrasenha());
                        usuario.setLogin(newUsuario.getLogin());
                        usuario.setDireccion(newUsuario.getDireccion());
                        usuario.setId(newUsuario.getId());
                        
                        return newUsuario;
	        }
	        
                 LOGGER.severe("No existe un Usuario con ese id");
	         throw new BibliotecaLogicException("No existe un Usuario con ese id");
    }
    
    /**
     * 
     * @param pId elimina a un usuario con el id dado 
     * @throws BibliotecaLogicException 
     */
    public void deleteUsuario(Long pId) throws BibliotecaLogicException
    {
    	try {
            usuarios.remove(getUsuario(pId));
        } catch (BibliotecaLogicException ex) {
            Logger.getLogger(UsuarioLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.log(Level.SEVERE, "No fue posible elminiar el usuario con id {0}", pId);
  throw new BibliotecaLogicException("No fue posible elminiar el usuario con id " + pId);
        }
    }
    
}
