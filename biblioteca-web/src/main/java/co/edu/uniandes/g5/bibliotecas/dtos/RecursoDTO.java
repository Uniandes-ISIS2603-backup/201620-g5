/*
 * RecursoDTO
 * Objeto de transferencia de datos de Recursos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.RecursoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Objeto de transferencia de datos de Recursos.
 *
 * @author sf.munera10
 */
@XmlRootElement
public class RecursoDTO {

    protected Long id;
    protected String name;

    public static final int VIDEO = 1;
    public static final int LIBRO = 2;
    public static final  int SALA =3;
    
    
    private int tipoRecurso;

    
   
    public int getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(int tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


}