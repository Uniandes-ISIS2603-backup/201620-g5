
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.LibroEntity;
import co.edu.uniandes.g5.bibliotecas.entities.MultaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import co.edu.uniandes.g5.bibliotecas.entities.SalaEntity;
import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ce.gonzalez13
 */
@XmlRootElement
public class MultaDetailDTO extends MultaDTO {

    @PodamExclude
    private UsuarioDTO usuario;
    
     @PodamExclude
    private RecursoDTO recurso;
     
     @PodamExclude
     private BiblioDTO biblioteca;

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


 
    /**
     *
     */
    public MultaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto PrestamoDetailDTO a partir de un objeto PrestamoEntity
     * incluyendo los atributos de PrestamoDTO.
     *
     * @param entity Entidad PrestamoEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public MultaDetailDTO(MultaEntity entity) {
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
             if (entity.getRecurso().getTipoRecurso() == RecursoDTO.VIDEO)
            {
                this.recurso = new VideoDTO((VideoEntity)entity.getRecurso());
            }
            else if (entity.getRecurso().getTipoRecurso() == RecursoDTO.LIBRO)
            {
                this.recurso = new LibroDTO((LibroEntity)entity.getRecurso());
            }
            else if (entity.getRecurso().getTipoRecurso() == RecursoDTO.SALA)
            {
                this.recurso = new SalaDTO((SalaEntity)entity.getRecurso());
            }
        }
            
        

    }

    /**
     * Convierte un objeto PrestamoDetailDTO a PrestamoEntity incluyendo los
     * atributos de DepartmentDTO.
     *
     * @return  objeto DepartmentEntity.
     *
     */
    @Override
    public MultaEntity toEntity() {
        MultaEntity entity = super.toEntity();
        if (this.getUsuario()!= null) {
            entity.setUsuario(this.getUsuario().toEntity());
        }
        if (getBiblioteca()!= null) {
            entity.setBiblioteca(this.getBiblioteca().toEntity());
        }
        if (getRecurso() != null)
            {
                if(getRecurso().getTipoRecurso() == RecursoDTO.LIBRO)
                {
                    LibroDTO lib = (LibroDTO) getRecurso();
                    entity.setRecurso(lib.toEntity());
                }
                else if(getRecurso().getTipoRecurso() == RecursoDTO.VIDEO)
                {
                    VideoDTO lib = (VideoDTO) getRecurso();
                    entity.setRecurso(lib.toEntity());
                }
                else if(getRecurso().getTipoRecurso() == RecursoDTO.SALA)
                {
                    SalaDTO lib = (SalaDTO) getRecurso();
                    entity.setRecurso(lib.toEntity());
                }
                
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
