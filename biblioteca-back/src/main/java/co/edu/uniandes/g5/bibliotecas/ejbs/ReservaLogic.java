/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.api.IReservaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ISalaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.api.IVideoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.ReservaPersistence;
import java.util.List;
import java.util.Objects;
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
    
    @Inject
    private IUsuarioLogic usuarioLogic;
    
    @Inject
    private ILibroLogic libroLogic;
    
    @Inject
    private IVideoLogic videoLogic;
    
    @Inject
    private ISalaLogic salaLogic;
    
    @Inject
    private IBibliotecaLogic bibliotecaLogic;

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
     * reserva.fecha es mayor a la fecha actual.
     * reserva.tipoRecurso.equals("Libro")||reserva.tipoRecurso.equals("Video")||reserva.tipoRecurso.equals("Sala")
     * 
     * 
     * @param reserva
     * @return ReservaEntity
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Override
    public ReservaEntity createReserva(ReservaEntity reserva,Long idBiblioteca, Long tipoRecurso, Long idRecurso, Long idUsuario) throws BibliotecaLogicException {
         
        if(reserva.getId() != null)
        {
     ReservaEntity alreadyExist = getReserva(reserva.getId());
        if (alreadyExist != null) 
        {
            throw new BibliotecaLogicException("Ya existe un reserva con ese id");
        } 
        }
        
        if(reserva.getFecha() == null)
        {
             throw new BibliotecaLogicException("Fecha no valida");
        }
        
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        reserva.setUsuario(usuario);
        if(reserva.getUsuario().getMultas().size() > 0)
        {
            throw new BibliotecaLogicException("El usuario tiene multas, no es posible hacer la reserva hasta que se paguen las multas.");
        }

       if(Objects.equals(tipoRecurso, RecursoEntity.LIBRO))
        {
            LibroEntity libro = libroLogic.getLibro(idRecurso);
            if(libro.getEjemplaresDisponibles() <= 0)
            {
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
            }
            reserva.setTipoRecurso(RecursoEntity.LIBRO);
            reserva.setRecurso(libro);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.VIDEO))
        {
            VideoEntity video = videoLogic.getVideo(idRecurso);
            if(video.getEjemplaresDisponibles() == 0)
            {
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
            }
            reserva.setTipoRecurso(RecursoEntity.VIDEO);
            reserva.setRecurso(video);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.SALA))
        {
            SalaEntity sala = salaLogic.getSala(idRecurso);
            reserva.setTipoRecurso(RecursoEntity.SALA);
            reserva.setRecurso(sala);
        }
        

        reserva.setBiblioteca(bibliotecaLogic.getBiblioteca(idBiblioteca));
        
        reserva = persistence.create(reserva);
        return reserva;
        
        
        
    }

    

    @Override
    public ReservaEntity updateReserva(ReservaEntity reserva,Long idBiblioteca, Long tipoRecurso, Long idRecurso, Long idUsuario) throws BibliotecaLogicException {
       
        ReservaEntity oldReserva = getReserva(reserva.getId());
        
       if(Objects.equals(tipoRecurso, RecursoEntity.LIBRO))
        {
            LibroEntity libro = libroLogic.getLibro(idRecurso);
            if(libro.getEjemplaresDisponibles() <= 0)
            {
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
            }
            reserva.setTipoRecurso(RecursoEntity.LIBRO);
            reserva.setRecurso(libro);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.VIDEO))
        {
            VideoEntity video = videoLogic.getVideo(idRecurso);
            if(video.getEjemplaresDisponibles() == 0)
            {
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
            }
            reserva.setTipoRecurso(RecursoEntity.VIDEO);
            reserva.setRecurso(video);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.SALA))
        {
            SalaEntity sala = salaLogic.getSala(idRecurso);
            reserva.setTipoRecurso(RecursoEntity.SALA);
            reserva.setRecurso(sala);
        }
        if(reserva.getFecha()== null)
        {
            reserva.setFecha(oldReserva.getFecha());
        }
       
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        reserva.setUsuario(usuario);
        
        reserva.setBiblioteca(bibliotecaLogic.getBiblioteca(idBiblioteca));
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
