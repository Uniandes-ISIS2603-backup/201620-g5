
package co.edu.uniandes.g5.bibliotecas.api;

import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;

import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.List;

public interface IUsuarioLogic {
   
    public List<UsuarioEntity> getUsuarios();
    public UsuarioEntity getUsuario(Long id);
    public List<UsuarioEntity> getUsuariosByBiblioteca(Long bibliotecaId);
    public UsuarioEntity createUsuario(UsuarioEntity entity,Long idBiblioteca) throws BibliotecaLogicException; 
    public UsuarioEntity updateUsuario(UsuarioEntity entity) throws BibliotecaLogicException;
    public UsuarioEntity deleteUsuario(Long id) throws BibliotecaLogicException;
    

}
