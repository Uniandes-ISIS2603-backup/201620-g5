
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.ReservaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ce.gonzalez13
 */
@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO {

    @PodamExclude
    private UsuarioDTO usuario;
    
     @PodamExclude
    private RecursoDTO recurso;

    public RecursoDTO getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoDTO recurso) {
        this.recurso = recurso;
    }

    public BiblioDTO getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BiblioDTO biblioteca) {
        this.biblioteca = biblioteca;
    }
     
      @PodamExclude
    private BiblioDTO biblioteca;

 
    /**
     *
     */
    public ReservaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto ReservaDetailDTO a partir de un objeto ReservaEntity
     * incluyendo los atributos de ReservaDTO.
     *
     * @param entity Entidad ReservaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public ReservaDetailDTO(ReservaEntity entity) {
        super(entity);
        if (entity.getUsuario() != null)
        {
            this.usuario = new UsuarioDTO(entity.getUsuario());
        }
        if(entity.getBiblioteca() != null)
        {
            this.biblioteca = new BiblioDTO(entity.getBiblioteca());
        }
        if(entity.getRecurso() != null)
        {
            if (entity.getTipoRecurso().equals(entity.VIDEO))
            {
                this.recurso = new VideoDTO(entity.getRecurso());
            }
            else if (entity.getTipoRecurso().equals(entity.LIBRO))
            {
                this.recurso = new LibroDTO(entity.getRecurso());
            }
            else if (entity.getTipoRecurso().equals(entity.SALA))
            {
                this.recurso = new SalaDTO(entity.getRecurso());
            }
        }
            
        

    }

    /**
     * Convierte un objeto ReservaDetailDTO a ReservaEntity incluyendo los
     * atributos de DepartmentDTO.
     *
     * @return  objeto DepartmentEntity.
     *
     */
    @Override
    public ReservaEntity toEntity() {
        ReservaEntity entity = super.toEntity();
        if (this.getUsuario()!= null) {
            entity.setUsuario(this.getUsuario().toEntity());
        }
        if (getBiblioteca()!= null) {
            entity.setBiblioteca(this.getBiblioteca().toEntity());
        }
        if (getRecurso() != null)
            {
                entity.setRecurso(getRecurso().toEntity);
            }
        return entity;
    }

    /**
     * Obtiene el atributo usuario.
     *
     * @return atributo usuario.
     *
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    /**
     * Establece el valor del atributo usuario.
     *
     * @param usuario nuevo valor del atributo
     *
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }


}
