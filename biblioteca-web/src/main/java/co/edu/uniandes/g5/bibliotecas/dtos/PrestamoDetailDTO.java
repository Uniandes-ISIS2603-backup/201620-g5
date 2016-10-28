
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.PrestamoEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ce.gonzalez13
 */
@XmlRootElement
public class PrestamoDetailDTO extends PrestamoDTO {

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
    public PrestamoDetailDTO() {
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
    public PrestamoDetailDTO(PrestamoEntity entity) {
        super(entity);
        if (entity.getUsuario() != null) {
            this.usuario = new UsuarioDTO(entity.getUsuario());
            this.biblioteca = new BiblioDTO(entity.getBiblioteca());
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
     * Convierte un objeto PrestamoDetailDTO a PrestamoEntity incluyendo los
     * atributos de DepartmentDTO.
     *
     * @return  objeto DepartmentEntity.
     *
     */
    @Override
    public PrestamoEntity toEntity() {
        PrestamoEntity entity = super.toEntity();
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
