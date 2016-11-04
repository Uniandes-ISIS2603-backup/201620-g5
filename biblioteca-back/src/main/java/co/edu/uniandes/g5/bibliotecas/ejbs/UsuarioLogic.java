package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UsuarioLogic implements IUsuarioLogic {

    @Inject
    private UsuarioPersistence persistence;
    @Inject
    private IBibliotecaLogic biblioLogic;
    

    @Override
    public List<UsuarioEntity> getUsuarios() {
        return persistence.getUsuarios();
    }

    @Override
    public UsuarioEntity getUsuario(Long id) {
        return persistence.getUsuario(id);
    }
    
    @Override
    public List<UsuarioEntity> getUsuariosByBiblioteca(Long bibliotecaId)
    {
        return persistence.getUsuariosByBiblioteca(bibliotecaId);
    }
    

    @Override
    public UsuarioEntity createUsuario(UsuarioEntity entity, Long BibliotecaId) throws BibliotecaLogicException {
        UsuarioEntity alreadyExist = getUsuario(entity.getId());
        if (alreadyExist != null) 
        {
            throw new BibliotecaLogicException("Ya existe un usuario con ese id");
        } 
        if(entity.getClave().length() < 5 )
        {
            throw new BibliotecaLogicException("Contraseña inválida. Debe contener más de 4 caracteres");
        }
        if(entity.getClave().equals(entity.getLogin()) )
        {
            throw new BibliotecaLogicException("Contraseña inválida. No puede ser igual al login");
        }
        if(entity.getClave().equals("")||entity.getLogin().equals(""))
        {
            throw new BibliotecaLogicException("Ni el login ni la contraseña deben ser vacíos");
        }
        if(entity.getName().matches("[0-9]+") ||entity.getApellido().matches("[0-9]+")||entity.getName().length()< 2 || entity.getApellido().length() < 2)
        {
            throw new BibliotecaLogicException("Nombre y/o Apellido inválido(s). No deben contener números ni ser de menor longitud que 2");
        }
       
        
        UsuarioEntity usuario = persistence.create(entity);
        BibliotecaEntity biblioteca=biblioLogic.getBiblioteca(BibliotecaId);
        usuario.setBiblioteca(biblioteca);

        
        return usuario;
        
        
        
    }

    @Override
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BibliotecaLogicException{
        if(entity.getClave().length() < 5 )
        {
            throw new BibliotecaLogicException("Contraseña inválida. Debe contener más de 4 caracteres");
        }
        if(entity.getClave().equals(entity.getLogin()) )
        {
            throw new BibliotecaLogicException("Contraseña inválida. No puede ser igual al login");
        }
        if(entity.getClave().equals("")||entity.getLogin().equals(""))
        {
            throw new BibliotecaLogicException("Ni el login ni la contraseña deben ser vacíos");
        }
        if(entity.getName().matches("[0-9]+") ||entity.getApellido().matches("[0-9]+")||entity.getName().length()< 2 || entity.getApellido().length() < 2)
        {
            throw new BibliotecaLogicException("Nombre y/o Apellido inválido(s). No deben contener números ni ser de menor longitud que 2");
        }
        
        UsuarioEntity usuario = persistence.update(entity);

        
        return usuario;
    }

    @Override
    public UsuarioEntity deleteUsuario(Long id) throws BibliotecaLogicException{
        UsuarioEntity usuario = persistence.getUsuario(id);
        if(usuario== null)
        {
            throw new BibliotecaLogicException ("Se esta tratando de remover un usuario inexistente");
        }
        persistence.delete(id);
        return usuario;
    }


}
