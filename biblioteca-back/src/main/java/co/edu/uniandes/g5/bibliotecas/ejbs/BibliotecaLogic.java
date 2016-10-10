
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.BibliotecaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;


@Stateless
public class BibliotecaLogic implements IBibliotecaLogic {

    @Inject private BibliotecaPersistence persistence;

    @Override
    public List<BibliotecaEntity> getBibliotecas() {
        return persistence.findAll();
    }

    @Override
    public BibliotecaEntity getBiblioteca(Long id) {
        return persistence.find(id);
    }

    @Override
    public BibliotecaEntity createBiblioteca(BibliotecaEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public BibliotecaEntity updateBiblioteca(BibliotecaEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteBiblioteca(Long id) {
        persistence.delete(id);
    }
}
