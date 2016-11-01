package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
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

    @Override
    public List<RecursoEntity> listRecursos(Long bibliotecaId) {
        return persistence.find(bibliotecaId).getRecursos();
    }

    @Override
    public RecursoEntity getRecurso(Long bibliotecaId, Long recursoId) {
        List<RecursoEntity> list = persistence.find(bibliotecaId).getRecursos();
        for (int i = 0; i < list.size(); i++) {
            RecursoEntity recursoEntity = list.get(i);
            if(Objects.equals(recursoEntity.getId(), recursoId))
            {
                return recursoEntity;
            } 
        }
        return null;
    }

    @Override
    public RecursoEntity addRecurso(Long bibliotecaId, Long recursoId) {
        BibliotecaEntity bibliotecaEntity = persistence.find(bibliotecaId);
        RecursoEntity recursoEntity = getRecurso(bibliotecaId, recursoId);
        recursoEntity.setBiblioteca(bibliotecaEntity);
        return recursoEntity;
    }

    @Override
    public List<RecursoEntity> replaceRecursos(Long bibliotecaId, List<RecursoEntity> list) {
        BibliotecaEntity bibliotecaEntity = persistence.find(bibliotecaId);
        bibliotecaEntity.setRecursos(list);
        return bibliotecaEntity.getRecursos();
    }

    @Override
    public void removeRecurso(Long bibliotecaId, Long recursoId) {
        RecursoEntity entity = getRecurso(bibliotecaId, recursoId);
        entity.setBiblioteca(null);
    }
}
