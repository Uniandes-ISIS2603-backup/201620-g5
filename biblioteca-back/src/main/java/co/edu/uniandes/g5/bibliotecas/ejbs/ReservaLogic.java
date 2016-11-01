/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IReservaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.ReservaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author ce.gonzalez13
 */
public class ReservaLogic implements IReservaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());
    
    
    @Inject
    private ReservaPersistence persistence;

    @Override
    public List<ReservaEntity> getReservas() {
        return persistence.getReservas();
    }
    
    @Override
    public List<ReservaEntity> getReservasByBiblioteca(Long idBiblioteca) {
        return persistence.getReservasByBiblioteca(idBiblioteca);
    }
    @Override
    public List<ReservaEntity> getReservasByUsuario(Long idBiblioteca, Long idUsuario) {
        return persistence.getReservasByUsuario(idBiblioteca,idUsuario);
    }
 
    /**
     *
     * @param idBiblioteca
     * @param idRecurso
     * @return
     */
    @Override
    public List<ReservaEntity> getReservasByRecurso(Long idBiblioteca, Long idRecurso) {
        return persistence.getReservasByRecurso(idBiblioteca, idRecurso);
    }

    
    @Override
    public ReservaEntity getReserva(Long id) {
        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", id);
        try {
            return persistence.getReserva(id);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El reserva no existe");
        }
    }

    /**
     * Pre: reserva.usuario corresponde a un usuario existente
     * El reserva.biblioteca corresponde a una biblioteca existente
     * El reserva.recurso corresponde a un recurso existente en la biblioteca actual.
     * reserva.tipoRecurso.equals("Libro")||reserva.tipoRecurso.equals("Video")||reserva.tipoRecurso.equals("Sala")
     * reserva.fechaInicial < reserva.fechaFinal
     * reserva.fechaInicial > Calendar.getInstance() (la fecha inicial es mayor a la fecha actual)
     * reserva.medioPago.equals("Tarjeta de credito")||reserva.medioPago.equals("Efectivo")||reserva.medioPago.equals("Tarjeta de debito")
     * 
     * 
     * @param reserva
     * @return ReservaEntity
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Override
    public ReservaEntity createReserva(ReservaEntity reserva) throws BibliotecaLogicException {
         
     ReservaEntity alreadyExist = getReserva(reserva.getId());
        if (alreadyExist != null) 
        {
            throw new BibliotecaLogicException("Ya existe un reserva con ese id");
        } 
        if(reserva.getRecurso().getTipoRecurso() == RecursoEntity.LIBRO)
        {
            LibroEntity libro = (LibroEntity) reserva.getRecurso();
            if(libro.getEjemplaresDisponibles() == 0)
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
        }
        else if(reserva.getRecurso().getTipoRecurso() == RecursoEntity.VIDEO)
        {
            VideoEntity video = (VideoEntity) reserva.getRecurso();
            if(video.getEjemplaresDisponibles() == 0)
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
        }
        reserva = persistence.create(reserva);

        
        return reserva;
        
        
        
    }

    

    @Override
    public ReservaEntity updateReserva(ReservaEntity reserva) throws BibliotecaLogicException {
       
        
        if(reserva.getRecurso().getTipoRecurso() == RecursoEntity.LIBRO)
        {
            LibroEntity libro = (LibroEntity) reserva.getRecurso();
            if(libro.getEjemplaresDisponibles() == 0)
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
        }
        else if(reserva.getRecurso().getTipoRecurso() == RecursoEntity.VIDEO)
        {
            VideoEntity video = (VideoEntity) reserva.getRecurso();
            if(video.getEjemplaresDisponibles() == 0)
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
        }
        List<RecursoEntity> recursos = reserva.getBiblioteca().getRecursos();
        RecursoEntity recurso = null;
        for(int i = 0;i<recursos.size(); i++)
        {
            if(recursos.get(i).equals(reserva.getRecurso()))
            {
                recurso = recursos.get(i);
                break;
            }
        }
        if(recurso == null)
        {
            throw new BibliotecaLogicException("El recurso que se quiere prestar no se encuentra en la biblioteca del préstamo");
        }
        return persistence.update(reserva);
        
    }

    /**
     *
     * @param idReserva
     * @return
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Override
    public ReservaEntity deleteReserva(Long idReserva) throws BibliotecaLogicException{
        ReservaEntity reserva = persistence.getReserva(idReserva);
        if(reserva== null)
        {
            throw new BibliotecaLogicException ("Se esta tratando de remover un reserva inexistente");
        }
        persistence.delete(idReserva);
        return reserva;
    }
    

    
}
