
package co.edu.uniandes.g5.bibliotecas.api;

import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import java.util.List;

public interface IUsuarioLogic {
   
    public List<UsuarioEntity> getUsuarios();
    public UsuarioEntity getUsuario(Long id);
    public UsuarioEntity createUsuario(UsuarioEntity entity); 
    public UsuarioEntity updateUsuario(UsuarioEntity entity);
    public void deleteUsuario(Long id);

}
