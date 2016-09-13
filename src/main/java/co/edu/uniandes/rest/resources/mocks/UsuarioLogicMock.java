/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.resources.mocks;


import co.edu.uniandes.rest.resources.dtos.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

/**
 *
 * @author js.prieto10
 */
public class UsuarioLogicMock {
    
    
    private final static Logger logger = Logger.getLogger(co.edu.uniandes.rest.resources.mocks.UsuarioLogicMock.class.getName());
	
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
            usuarios.add(new UsuarioDTO("Stephen", "Hawking", "elLocoStephe", 1L, "1234", "en mi casa"));
            usuarios.add(new UsuarioDTO("Uribe", "Alvaro", "elDotorUribe", 2L, "4321", "la casa nariño"));
            usuarios.add(new UsuarioDTO("Chuck", "Norris", " tuDios", 3L, "9874", "En todo lado"));
           
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de usuarios");
        logger.info("usuarios" + usuarios);
        }
    }
    
    /**
     * 
     * @return la lista de usuario
     * @throws Exception arroja una excepcion si no existe ningun usuario
     */
    public List<UsuarioDTO> getUsuarios() throws Exception {
    	if (usuarios == null) {
    		logger.severe("Error interno: lista de usuarios no existe.");
    		throw new Exception("Error interno: lista de usuarios no existe.");    		
    	}
    	
    	logger.info("retornando todas las usuarios");
    	return usuarios;
    }
    
    
 
    /**
     * 
     * @param pId
     * @return un usuario dado por parametro
     * @throws Exception arroja una excepcion si el usuario no existe
     */
    public UsuarioDTO getUsuario(Long pId) throws Exception
    {
        if (usuarios == null) {
    		logger.severe("Error interno: lista de usuarios no existe.");
    		throw new Exception("Error interno: lista de usuarios no existe.");
        }
        for (UsuarioDTO usuario: usuarios) {
	        
	            if (usuario.getId() == pId){
	            	logger.info("retornando la usuario buscada");
                        return usuario;
	            }
	        }
                logger.severe("No existe un usuario con ese id");
	        throw new Exception("No existe un usuario con ese id");
    }
    

    /**
     * 
     * @param newUsuario
     * @return crea un usuario con la información que le entra por parametro
     * @throws Exception arroja una excepcion si el usuario existe
     */
    public UsuarioDTO createUsuario(UsuarioDTO newUsuario) throws Exception
    {
    logger.info("recibiendo solicitud de agregar Usuario" + newUsuario);
    	
    
    	if ( newUsuario.getId() != null ) {
            
	        for (UsuarioDTO usuario: usuarios) {
                    
	            if (Objects.equals(usuario.getId(), newUsuario.getId())){
	            	logger.severe("Ya existe una Usuario con ese id");
	                throw new Exception("Ya existe una Usuario con ese id");
	            }
	        }
	        
    	} else {

    		logger.info("Generando id paa la nueva Usuario");
    		long newId = 1;
	        for (UsuarioDTO usuario : usuarios) {
	            if (newId <= usuario.getId()){
	                newId =  usuario.getId() + 1;
	            }
	        }
	        newUsuario.setId(newId);
    	}
    	
    	logger.info("agregando usuario " + newUsuario);
        usuarios.add(newUsuario);
        return newUsuario;
    }
    
    /**
     * 
     * @param idUsuario
     * @param newUsuario
     * @return acualiza la informacion de un usuario
     * @throws Exception arroja una excepcion si el id dado no existe
     */
    public UsuarioDTO updateUsuario(Long idUsuario, UsuarioDTO newUsuario) throws Exception
    {
        logger.info("recibiendo solicitud de actualizar video " + idUsuario);
   
   			UsuarioDTO usuario = getUsuario(idUsuario);
	        	// si existe un usuario con ese id
	            if (Objects.equals(usuario.getId(), idUsuario)){
                         // actualiza el usuario
                        logger.info("actualizando usuario " + idUsuario );
	            	usuario.setNombre(newUsuario.getNombre());
                        usuario.setApellido(newUsuario.getApellido());
                        usuario.setContrasenha(newUsuario.getContrasenha());
                        usuario.setLogin(newUsuario.getLogin());
                        usuario.setDireccion(newUsuario.getDireccion());
                        usuario.setId(newUsuario.getId());
                        
                        return newUsuario;
	        }
	        
                 logger.severe("No existe un Usuario con ese id");
	         throw new Exception("No existe un Usuario con ese id");
    }
    
    /**
     * 
     * @param pId elimina a un usuario con el id dado 
     * @throws Exception 
     */
    public void deleteUsuario(Long pId) throws Exception
    {
    	try {
            usuarios.remove(getUsuario(id));
        } catch (BibliotecaLogicException ex) {
            Logger.getLogger(UsuarioLogicMock.class.getName()).log(Level.SEVERE, null, ex);
            logger.severe("No fue posible elminiar el usuario con id " + id);
  throw new BibliotecaLogicException("No fue posible elminiar el usuario con id " + id);
        }
    }
    
}
