/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.g5.bibliotecas.ejbs;

import co.edu.uniandes.g5.bibliotecas.api.ISalaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.persistence.SalaPersistence;
import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.entities.BibliotecaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

@Stateless
public class SalaLogic implements ISalaLogic {

    @Inject
    private SalaPersistence persistence;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;

    public List<SalaEntity> getAllSalas() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Sala que pertenecen a una
     * Biblioteca.
     *
     * @param bibliotecaId id de la Biblioteca la cual es padre de las Salas.
     * @return Colección de objetos de SalaEntity.
     *
     */
    @Override
    public List<SalaEntity> getSalas(Long bibliotecaId) {
        return persistence.findAllInBiblioteca(bibliotecaId);
    }

    /**
     * Obtiene los datos de una instancia de Sala a partir de su ID.
     *
     * @param salaId Identificador del Sala a consultar
     * @return Instancia de SalaEntity con los datos del Sala consultado.
     *
     */
    @Override
    public SalaEntity getSala(Long salaId) {
        try {
            return persistence.find(salaId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("La Sala no existe");
        }
    }

    /**
     * Se encarga de crear una Sala en la base de datos.
     *
     * @param entity Objeto de SalaEntity con los datos nuevos
     * @param bibliotecaId id del Biblioteca el cual sera padre de la nueva
     * Sala.
     * @return Objeto de SalaEntity con los datos nuevos y su ID.
     * @throws
     * co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException
     *
     */
    @Override
    public SalaEntity createSala(Long bibliotecaId, SalaEntity entity) throws BibliotecaLogicException {
        SalaEntity existe = getSala(entity.getId());
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(bibliotecaId);
        entity.setBiblioteca(biblioteca);
        if (existe != null) {
            throw new BibliotecaLogicException("ya existe esa sala");
        } 
        else if(entity.getBiblioteca()== null){
            throw new BibliotecaLogicException("No se puede crear la sala porque no existe la biblioteca");
        }
        else { 
            entity = persistence.create(entity);
        }
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Sala.
     *
     * @param entity Instancia de SalaEntity con los nuevos datos.
     * @param bibliotecaId id de la Biblioteca el cual sera padre de la Sala
     * actualizado.
     * @return Instancia de SalaEntity con los datos actualizados.
     *
     */
    @Override
    public SalaEntity updateSala(Long bibliotecaId, SalaEntity entity) {
        BibliotecaEntity biblioteca = bibliotecaLogic.getBiblioteca(bibliotecaId);
        entity.setBiblioteca(biblioteca);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Sala de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param bibliotecaId id del Biblioteca el cual es padre de la Sala.
     *
     */
    @Override
    public void deleteSala(Long id) {
        SalaEntity old = getSala(id);
        persistence.delete(old.getId());
    }
}
