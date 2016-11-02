/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IMultaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.MultaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author ce.gonzalez13
 */
public class MultaLogic implements IMultaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MultaLogic.class.getName());
    
    
    @Inject
    private MultaPersistence persistence;

    @Override
    public List<MultaEntity> getMultas() {
        return persistence.getMultas();
    }
    
    @Override
    public List<MultaEntity> getMultasByBiblioteca(Long idBiblioteca) {
        return persistence.getMultasByBiblioteca(idBiblioteca);
    }
    @Override
    public List<MultaEntity> getMultasByUsuario(Long idBiblioteca, Long idUsuario) {
        return persistence.getMultasByUsuario(idBiblioteca,idUsuario);
    }
 
    /**
     *
     * @param idBiblioteca
     * @param idRecurso
     * @return
     */
    @Override
    public List<MultaEntity> getMultasByRecurso(Long idBiblioteca, Long idRecurso) {
        return persistence.getMultasByRecurso(idBiblioteca, idRecurso);
    }

    
    @Override
    public MultaEntity getMulta(Long id) {
        LOGGER.log(Level.INFO, "Consultando multa con id={0}", id);
        try {
            return persistence.getMulta(id);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El multa no existe");
        }
    }

    /**
     * Pre: multa.usuario corresponde a un usuario existente
     * El multa.biblioteca corresponde a una biblioteca existente
     * El multa.recurso corresponde a un recurso existente en la biblioteca actual.
     * multa.tipoRecurso.equals("Libro")||multa.tipoRecurso.equals("Video")||multa.tipoRecurso.equals("Sala")
     * multa.fechaInicial < multa.fechaFinal
     * multa.fechaInicial > Calendar.getInstance() (la fecha inicial es mayor a la fecha actual)
     * multa.medioPago.equals("Tarjeta de credito")||multa.medioPago.equals("Efectivo")||multa.medioPago.equals("Tarjeta de debito")
     * 
     * 
     * @param multa
     * @return MultaEntity
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Override
    public MultaEntity createMulta(MultaEntity multa) throws BibliotecaLogicException {
         
     MultaEntity alreadyExist = getMulta(multa.getId());
        if (alreadyExist != null) 
        {
            throw new BibliotecaLogicException("Ya existe un multa con ese id");
        } 
        if(multa.getCosto() <= 0 )
        {
            throw new BibliotecaLogicException("Costo inválido. El costo no puede ser menor o igual a 0");
        }

        if(multa.getRecurso().getTipoRecurso() == RecursoEntity.LIBRO)
        {
            LibroEntity libro = (LibroEntity) multa.getRecurso();
            if(libro.getEjemplaresDisponibles() <= 0)
            {
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
            }
        }
        else if(multa.getRecurso().getTipoRecurso() == RecursoEntity.VIDEO)
        {
            VideoEntity video = (VideoEntity) multa.getRecurso();
            if(video.getEjemplaresDisponibles() == 0)
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
        }
        
        multa = persistence.create(multa);

        
        return multa;
        
        
        
    }

    

    @Override
    public MultaEntity updateMulta(MultaEntity multa) throws BibliotecaLogicException {
       
         if(multa.getCosto() <= 0 )
        {
            throw new BibliotecaLogicException("Costo inválido");
        }
       if(multa.getRecurso().getTipoRecurso() == RecursoEntity.LIBRO)
        {
            LibroEntity libro = (LibroEntity) multa.getRecurso();
            if(libro.getEjemplaresDisponibles() == 0)
            throw new BibliotecaLogicException("No hay libros disponibles para prestar.");
        }
        else if(multa.getRecurso().getTipoRecurso() == RecursoEntity.VIDEO)
        {
            VideoEntity video = (VideoEntity) multa.getRecurso();
            if(video.getEjemplaresDisponibles() == 0)
            throw new BibliotecaLogicException("No hay videos disponibles para prestar.");
        }
        
        return persistence.update(multa);
        
    }

    /**
     *
     * @param idMulta
     * @return
     * @throws co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     */
    @Override
    public MultaEntity deleteMulta(Long idMulta) throws BibliotecaLogicException{
        MultaEntity multa = persistence.getMulta(idMulta);
        if(multa== null)
        {
            throw new BibliotecaLogicException ("Se esta tratando de remover un multa inexistente");
        }
        persistence.delete(idMulta);
        return multa;
    }
    

    
}
