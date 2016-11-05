/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ILibroLogic;
import co.edu.uniandes.g5.bibliotecas.api.IPrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.api.ISalaLogic;
import co.edu.uniandes.g5.bibliotecas.api.IUsuarioLogic;
import co.edu.uniandes.g5.bibliotecas.api.IVideoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.UsuarioEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.PrestamoPersistence;
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
public class PrestamoLogic implements IPrestamoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(PrestamoLogic.class.getName());
    
    
    @Inject
    private PrestamoPersistence persistence;
    
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
    public List<PrestamoEntity> getPrestamos() {
        return persistence.getPrestamos();
    }
    
    @Override
    public List<PrestamoEntity> getPrestamosByBiblioteca(Long idBiblioteca) {
        return persistence.getPrestamosByBiblioteca(idBiblioteca);
    }
    @Override
    public List<PrestamoEntity> getPrestamosByUsuario(Long idBiblioteca, Long idUsuario) {
        return persistence.getPrestamosByUsuario(idBiblioteca,idUsuario);
    }
 
    /**
     *
     * @param idBiblioteca
     * @param idRecurso
     * @return
     */
    @Override
    public List<PrestamoEntity> getPrestamosByRecurso(Long idBiblioteca, Long idRecurso) {
        return persistence.getPrestamosByRecurso(idBiblioteca, idRecurso);
    }

    
    @Override
    public PrestamoEntity getPrestamo(Long id) {
        LOGGER.log(Level.INFO, "Consultando prestamo con id={0}", id);
        try {
            return persistence.getPrestamo(id);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El prestamo no existe");
        }
    }

    /**
     * Pre: prestamo.usuario corresponde a un usuario existente
     * El prestamo.biblioteca corresponde a una biblioteca existente
     * El prestamo.recurso corresponde a un recurso existente en la biblioteca actual.
     * prestamo.tipoRecurso.equals("Libro")||prestamo.tipoRecurso.equals("Video")||prestamo.tipoRecurso.equals("Sala")
     * prestamo.fechaInicial < prestamo.fechaFinal
     * prestamo.fechaInicial > Calendar.getInstance() (la fecha inicial es mayor a la fecha actual)
     * prestamo.medioPago.equals("Tarjeta de credito")||prestamo.medioPago.equals("Efectivo")||prestamo.medioPago.equals("Tarjeta de debito")
     * 
     * 
     * @param prestamo
     * @return PrestamoEntity
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Override
    public PrestamoEntity createPrestamo(PrestamoEntity prestamo,Long idBiblioteca, Long tipoRecurso, Long idRecurso, Long idUsuario) throws BibliotecaLogicException {
         
     PrestamoEntity alreadyExist = getPrestamo(prestamo.getId());
      UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
         prestamo.setUsuario(usuario);
        if (alreadyExist != null) 
        {
            throw new BibliotecaLogicException("Ya existe un prestamo con ese id");
        } 
        if(prestamo.getCosto() <= 0 )
        {
            throw new BibliotecaLogicException("Costo inválido. El costo no puede ser menor o igual a 0");
        }
        
        if(usuario.getMultas().size() > 0)
        {
            throw new BibliotecaLogicException("El usuario tiene multas, no es posible hacer el préstamo hasta que se paguen las multas.");
        }

        if(Objects.equals(tipoRecurso, RecursoEntity.LIBRO))
        {
            LibroEntity libro = libroLogic.getLibro(idRecurso);
            if(libro.getEjemplaresDisponibles() <= 0)
            {
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
            }
            prestamo.setTipoRecurso(RecursoEntity.LIBRO);
            prestamo.setRecurso(libro);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.VIDEO))
        {
            VideoEntity video = videoLogic.getVideo(idRecurso);
            if(video.getEjemplaresDisponibles() == 0)
            {
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
            }
            prestamo.setTipoRecurso(RecursoEntity.VIDEO);
            prestamo.setRecurso(video);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.SALA))
        {
            SalaEntity sala = salaLogic.getSala(idRecurso);
            prestamo.setTipoRecurso(RecursoEntity.SALA);
            prestamo.setRecurso(sala);
        }
       

        prestamo.setBiblioteca(bibliotecaLogic.getBiblioteca(idBiblioteca));
        
        prestamo = persistence.create(prestamo);
        return prestamo;
        
        
        
    }

    

    @Override
    public PrestamoEntity updatePrestamo(PrestamoEntity prestamo,Long idBiblioteca, Long tipoRecurso, Long idRecurso, Long idUsuario) throws BibliotecaLogicException {
       
        PrestamoEntity oldPrestamo = getPrestamo(prestamo.getId());
        if(prestamo.getCosto() == null)
        {
            prestamo.setCosto(oldPrestamo.getCosto());
        }
        else if(prestamo.getCosto() <= 0 )
        {
            throw new BibliotecaLogicException("Costo inválido");
        }
       if(Objects.equals(tipoRecurso, RecursoEntity.LIBRO))
        {
            LibroEntity libro = libroLogic.getLibro(idRecurso);
            if(libro.getEjemplaresDisponibles() <= 0)
            {
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
            }
            prestamo.setTipoRecurso(RecursoEntity.LIBRO);
            prestamo.setRecurso(libro);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.VIDEO))
        {
            VideoEntity video = videoLogic.getVideo(idRecurso);
            if(video.getEjemplaresDisponibles() == 0)
            {
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
            }
            prestamo.setTipoRecurso(RecursoEntity.VIDEO);
            prestamo.setRecurso(video);
        }
        else if(Objects.equals(tipoRecurso, RecursoEntity.SALA))
        {
            SalaEntity sala = salaLogic.getSala(idRecurso);
            prestamo.setTipoRecurso(RecursoEntity.SALA);
            prestamo.setRecurso(sala);
        }
       
        if(prestamo.getFechaInicial() == null)
        {
            prestamo.setFechaInicial(oldPrestamo.getFechaInicial());
        }
        if(prestamo.getFechaFinal() == null)
        {
            prestamo.setFechaFinal(oldPrestamo.getFechaFinal());
        }
        if(prestamo.getMedioPago() == null)
        {
            prestamo.setMedioPago(oldPrestamo.getMedioPago());
        }
        UsuarioEntity usuario = usuarioLogic.getUsuario(idUsuario);
        prestamo.setUsuario(usuario);
        
        prestamo.setBiblioteca(bibliotecaLogic.getBiblioteca(idBiblioteca));
        return persistence.update(prestamo);
        
    }

    /**
     *
     * @param idPrestamo
     * @return
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Override
    public PrestamoEntity deletePrestamo(Long idPrestamo) throws BibliotecaLogicException{
        PrestamoEntity prestamo = persistence.getPrestamo(idPrestamo);
        if(prestamo== null)
        {
            throw new BibliotecaLogicException ("Se esta tratando de remover un prestamo inexistente");
        }
        persistence.delete(idPrestamo);
        return prestamo;
    }
    

    
}
