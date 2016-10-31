/*
 * VideoDTO
 * Objeto de transferencia de datos de Videos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

import co.edu.uniandes.g5.bibliotecas.entities.VideoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de videos.
 */
@XmlRootElement
public class VideoDTO{
    private Long id;
    private String name;
    
    
    private String director;
    private Integer numEjemplares;
    private Integer ejemplaresDisponibles; 
    private boolean online;

    /**
     * Crea un libroDTO vacio.
     */
    public VideoDTO() {
        
    }
    
    public VideoDTO(VideoEntity entity) {
        if(entity!=null){
            this.id = entity.getId();
            this.name = entity.getName();
            this.director = entity.getDirector();
            this.numEjemplares = entity.getNumEjemplares();
            this.ejemplaresDisponibles = entity.getEjemplaresDisponibles();
            this.online = entity.isOnline();
        }
    }

    @Override
    public String toString() {
        return "{ id: " + id + ", titulo : \"" + name + "\" , numEjemplares: " + numEjemplares + " , online: \"" + online + "\" }";
    }

    public VideoEntity toEntity() {
        VideoEntity entity = new VideoEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setDirector(director);
        entity.setNumEjemplares(numEjemplares);
        entity.setEjemplaresDisponibles(ejemplaresDisponibles);
        entity.setOnline(online);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(Integer numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    public Integer getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public void setEjemplaresDisponibles(Integer ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    
    
}
