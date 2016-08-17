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
public class VideoDTO {
    private Long id;
    private String title;
    private String director;

   
    private String genero;
    private Long año_publicacion;
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
     * @param title título del video
     * @param director
     * @param duracion
     * @param año_publicacion
     * @param genero
     * @param numEjemplares
     * @param sinopsis
     * @param esOnline
     */
    public VideoDTO(Long id, String title, String director, Long duracion, Long año_publicacion, String genero,  Long numEjemplares, String sinopsis, String esOnline) {
		super();
		this.id = id;
		this.title = title;
                this.director = director;
                this.duracion = duracion;
                this.año_publicacion = año_publicacion;
                this.genero = genero;
                this.numEjemplares = numEjemplares;
                this.sinopsis = sinopsis;
                if(esOnline.equalsIgnoreCase("Si") || esOnline.equalsIgnoreCase("Sí"))
                {
                    this.esOnline = true;
                }
                else if (esOnline.equalsIgnoreCase("No"))
                {
                    this.esOnline = false;
                }
                else
                {
                    this.esOnline = false;
                }
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
    public void setDuration(Long duracion) {
        this.duracion = duracion;
    }
    
    /**
     * @return the publication date
     */
    public Long getAñoPublicacion() {
        return año_publicacion;
    }

    /**
     * @param date the publication date to set
     */
    public void setAñoPublicacion(Long date) {
        this.año_publicacion = date;
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
    public void setSinopsis(String title) {
        this.title = title;
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
    	return "{ id : " + getId() + ", title : \"" + getTitle() + ", director : \"" + getDirector()+", duracion : \"" + getDuracion()+", año de publicacion : \"" + getAñoPublicacion()+", genero : \"" + getGenero() +", Número de ejemplares : \"" + getNumEjemplares()+", Sinopsis : \"" + getSinopsis() +", Es online : \"" + getEsOnline()+ "\" }" ;  
    }
}
