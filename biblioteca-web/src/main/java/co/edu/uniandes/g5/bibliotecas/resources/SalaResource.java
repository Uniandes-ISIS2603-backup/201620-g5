/*
 * SalaResource.java
 * Clase que representa el recurso "/salas"
 * Implementa varios métodos para manipular las salas
 */
package co.edu.uniandes.g5.bibliotecas.resources;

import co.edu.uniandes.g5.bibliotecas.api.IBibliotecaLogic;
import co.edu.uniandes.g5.bibliotecas.api.ISalaLogic;
import co.edu.uniandes.g5.bibliotecas.dtos.SalaDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.BiblioDetailDTO;
import co.edu.uniandes.g5.bibliotecas.dtos.SalaDTO;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso REST correspondiente a "salas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "salas". Al ejecutar la aplicación, el recurso
 * será accesibe a través de la ruta "/api/salas"
 *
 * @author sf.munera10
 */
@Path("")
@Produces("application/json")
public class SalaResource {

    private static final Logger LOGGER = Logger.getLogger(PrestamoResource.class.getName());

    @Inject
    private ISalaLogic salaLogic;

    @Inject
    private IBibliotecaLogic bibliotecaLogic;

    private List<SalaDetailDTO> listEntity2DTO(List<SalaEntity> entityList) {
        List<SalaDetailDTO> list = new ArrayList<>();
        for (SalaEntity entity : entityList) {
            list.add(new SalaDetailDTO(entity));
        }
        return list;
    }

    public void existsBiblioteca(Long bibliotecaId) {
        BiblioDetailDTO biblioteca = new BiblioDetailDTO(bibliotecaLogic.getBiblioteca(bibliotecaId));
        if (biblioteca == null) {
            throw new WebApplicationException("La biblioteca no existe", 404);
        }
    }

    public void existsSala(Long salaId) {
        SalaDetailDTO sala = new SalaDetailDTO(salaLogic.getSala(salaId));
        if (sala == null) {
            throw new WebApplicationException("La sala no existe", 404);
        }
    }

    /**
     * Obtiene el listado de salas de una biblioteca.
     *
     * @return lista de salas completa
     * @throws BiblioLogicException excepción retornada por la lógica
     */
    @GET
    @Path("bibliotecas/{idBiblioteca: \\d+}/salas")
    public List<SalaDetailDTO> getSalasBiblioteca(@PathParam("idBiblioteca") Long idBiblioteca) throws BibliotecaLogicException, ParseException {
        existsBiblioteca(idBiblioteca);
        List<SalaEntity> salas = salaLogic.getSalas(idBiblioteca);
        return listEntity2DTO(salas);
    }

    @GET
    @Path("salas/{id: \\d+}")
    public SalaDetailDTO getSala(@PathParam("id") Long id) throws BibliotecaLogicException {
        SalaEntity entity = salaLogic.getSala(id);
        LOGGER.log(Level.INFO, "Consultando sala con id = {0}", entity.getId());
        return new SalaDetailDTO(entity);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("bibliotecas/{idBiblioteca: \\d+}/salas")
    public SalaDetailDTO createSala4Biblioteca(@PathParam("idBiblioteca") Long idBiblioteca, SalaDetailDTO sala) throws BibliotecaLogicException {
        existsBiblioteca(idBiblioteca);
        if (sala.getBiblioteca() != null && !idBiblioteca.equals(sala.getBiblioteca().getId())) {
            throw new WebApplicationException(404);
        }
        return new SalaDetailDTO(salaLogic.createSala(idBiblioteca, sala.toEntity()));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("bibliotecas/{idBiblioteca: \\d+}/salas/{id: \\d+}")
    public SalaDetailDTO updateSala4Biblioteca(@PathParam("idBiblioteca") Long idBiblioteca, @PathParam("id") Long id, SalaDetailDTO sala) throws BibliotecaLogicException {
        existsBiblioteca(idBiblioteca);
        existsSala(id);
        SalaEntity entity = sala.toEntity();
        entity.setId(id);
        return new SalaDetailDTO(salaLogic.updateSala(idBiblioteca, entity));
    }

    @DELETE
    @Path("bibliotecas/salas/{id: \\d+}")
    public void deleteSala(@PathParam("id") Long id) throws BibliotecaLogicException {
        existsSala(id);
        salaLogic.deleteSala(id);
    }

   
}
