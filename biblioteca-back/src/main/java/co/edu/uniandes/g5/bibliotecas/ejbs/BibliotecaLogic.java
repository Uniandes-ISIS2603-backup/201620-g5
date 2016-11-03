package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.BibliotecaPersistence;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class BibliotecaLogic implements IBibliotecaLogic {

    @Inject
    private BibliotecaPersistence persistence;
    

    @Override
    public List<BibliotecaEntity> getBibliotecas() {
        return persistence.findAll();
    }

    @Override
    public BibliotecaEntity getBiblioteca(Long id) {
        return persistence.find(id);
    }

    @Override
    public BibliotecaEntity createBiblioteca(BibliotecaEntity entity) throws BibliotecaLogicException{
        
        BibliotecaEntity alreadyExist = getBiblioteca(entity.getId());
        if (alreadyExist != null) 
        {
            throw new BibliotecaLogicException("Ya existe una biblioteca con ese id");
        }
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
