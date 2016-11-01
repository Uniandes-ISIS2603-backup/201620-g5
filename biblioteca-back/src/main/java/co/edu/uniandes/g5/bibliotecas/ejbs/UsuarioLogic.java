package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UsuarioLogic implements IUsuarioLogic {

    @Inject
    private UsuarioPersistence persistence;
    

    @Override
    public List<UsuarioEntity> getUsuarios() {
        return persistence.getUsuarios();
    }

    @Override
    public UsuarioEntity getUsuario(Long id) {
        return persistence.getUsuario(id);
    }

    @Override
    public UsuarioEntity createUsuario(UsuarioEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public UsuarioEntity updateUsuario(UsuarioEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteUsuario(Long id) {
        persistence.delete(id);
    }


}
