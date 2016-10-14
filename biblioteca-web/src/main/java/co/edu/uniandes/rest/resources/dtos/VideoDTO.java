/*
 * VideoDTO
 * Objeto de transferencia de datos de Videos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.resources.dtos;

import java.util.Date;

/**
 * Objeto de transferencia de datos de videos.
 */
public class VideoDTO extends RecursoDTO{
    private String director;

   
    private String genero;
    private Long anio_publicacion;
    private Long numEjemplares;
    private boolean esOnline;
    private String sinopsis;
    private Long duracion;
    
    /**
     * Constructor por defecto
     */
    public VideoDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador del video
     * @param titulo título del video
     * @param director
     * @param duracion
     * @param anio_publicacion
     * @param genero
     * @param numEjemplares
     * @param sinopsis
     * @param esOnline
     */
    public VideoDTO(Long id, String titulo, String director, Long duracion, Long anio_publicacion, String genero,  Long numEjemplares, String sinopsis, boolean esOnline, BiblioDTO biblioteca) {
		super(id, titulo, biblioteca);
                this.director = director;
                this.duracion = duracion;
                this.anio_publicacion = anio_publicacion;
                this.genero = genero;
                this.numEjemplares = numEjemplares;
                this.sinopsis = sinopsis;
                this.esOnline = esOnline;
	}

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }
    
    /**
     * @return the duration in minutes
     */
    public Long getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duration to set in minutes
     */
    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }
    
    /**
     * @return the publication date
     */
    public Long getAnioPublicacion() {
        return anio_publicacion;
    }

    /**
     * @param date the publication date to set
     */
    public void setAnioPublicacion(Long date) {
        this.anio_publicacion = date;
    }
    
    /**
     * @return the genre of the movie
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenero(String genre) {
        this.genero = genre;
    }
    
    /**
     * @return el numero de ejemplares del video
     */
    public Long getNumEjemplares() {
        return numEjemplares;
    }

    /**
     * @param numEjemplares el numero de ejemplares a setear
     */
    public void setNumEjemplares(Long numEjemplares) {
        this.numEjemplares = numEjemplares;
    }
    
    /**
     * @return the sinopsis
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * @param sinopsis the sinopsis to set
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    
     /**
     * @return esOnline
     */
    public Boolean getEsOnline() {
        return esOnline;
    }

     /**
     * @param esOnline true si el libro está disponible online, false de lo contrario
     */
    public void setEsOnline(Boolean esOnline) {
        this.esOnline = esOnline;
    }
    
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", titulo : \"" + getName() + ", director : \"" + getDirector()+", duracion : \"" + getDuracion()+", año de publicacion : \"" + getAnioPublicacion()+", genero : \"" + getGenero() +", Número de ejemplares : \"" + getNumEjemplares()+", Sinopsis : \"" + getSinopsis() +", Es online : \"" + getEsOnline()+ "\" }" ;  
    }
}
