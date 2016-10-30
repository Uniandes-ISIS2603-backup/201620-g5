/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.IPrestamoLogic;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import co.edu.uniandes.g5.bibliotecas.persistence.PrestamoPersistence;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author ce.gonzalez13
 */
public class PrestamoLogic implements IPrestamoLogic {
    
    
    @Inject
    private PrestamoPersistence persistence;

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
    public PrestamoEntity createPrestamo(PrestamoEntity prestamo) throws BibliotecaLogicException {
         
     PrestamoEntity alreadyExist = getPrestamo(prestamo.getId());
        if (alreadyExist != null) 
        {
            throw new BibliotecaLogicException("Ya existe un prestamo con ese id");
        } 
        if(prestamo.getCosto() <= 0 )
        {
            throw new BibliotecaLogicException("Costo inválido. El costo no puede ser menor o igual a 0");
        }
        if(prestamo.getRecurso().getCantidadDisponible() == 0)
        {
            throw new BibliotecaLogicException("No hay "+prestamo.getTipoRecurso()+"s disponibles para prestar.");
        }
        
        prestamo = persistence.create(prestamo);

        
        return prestamo;
        
        
        
    }

    

    @Override
    public PrestamoEntity updatePrestamo(PrestamoEntity prestamo) throws BibliotecaLogicException {
       
         if(prestamo.getCosto() <= 0 )
        {
            throw new BibliotecaLogicException("Costo inválido");
        }
         if(prestamo.getRecurso().getCantidadDisponible() == 0)
        {
            throw new BibliotecaLogicException("No hay "+prestamo.getTipoRecurso()+"s disponibles para prestar.");
        }
        List<RecursoEntity> recursos = prestamo.getBiblioteca().getRecursos();
        RecursoEntity recurso = null;
        for(int i = 0;i<recursos.size(); i++)
        {
            if(recursos.get(i).equals(prestamo.getRecurso()))
            {
                recurso = recursos.get(i);
                break;
            }
        }
        if(recurso == null)
        {
            throw new BibliotecaLogicException("El recurso que se quiere prestar no se encuentra en la biblioteca del préstamo");
        }
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
