/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IPrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.PrestamoPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author js.prieto10
 */
public class PrestamoLogic implements IPrestamoLogic {
    
    @Inject
    private PrestamoPersistence persistence;

    @Override
    public List<PrestamoEntity> getPrestamos() {
        return persistence.getPrestamos();
    }

    @Override
    public PrestamoEntity getPrestamo(Long id) {
        return persistence.getPrestamo(id);
    }

    @Override
    public PrestamoEntity createPrestamo(PrestamoEntity entity) {
        return persistence.create(entity);
    }

    @Override
    public PrestamoEntity updatePrestamo(PrestamoEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deletePrestamo(Long id) {
        persistence.delete(id);
    }
    
}
