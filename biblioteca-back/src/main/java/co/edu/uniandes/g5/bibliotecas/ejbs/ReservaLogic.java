/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IReservaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.ReservaPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ce.gonzalez13
 */
public class ReservaLogic implements IReservaLogic {
    
    @Inject
    private ReservaPersistence persistence;

    @Override
    public List<ReservaEntity> getReservas() {
        return persistence.getReservas();
    }

    @Override
    public ReservaEntity getReserva(Long id) {
        return persistence.getReserva(id);
    }

    @Override
    public ReservaEntity createReserva(ReservaEntity entity) throws BibliotecaLogicException {
        return persistence.create(entity);
    }

    @Override
    public ReservaEntity updateReserva(ReservaEntity entity) throws BibliotecaLogicException {
        return persistence.update(entity);
    }

    @Override
    public ReservaEntity deleteReserva(Long id) throws BibliotecaLogicException{
        ReservaEntity reserva = persistence.getReserva(id);
        if(reserva== null)
        {
            throw new BibliotecaLogicException ("Se esta tratando de remover una reserva inexistente");
        }
        persistence.delete(id);
        return reserva;
    }
    
}
