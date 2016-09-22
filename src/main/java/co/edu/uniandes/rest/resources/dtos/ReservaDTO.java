/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.resources.dtos;

import java.util.Date;


/**
 * Objeto de transferencia de datos de Ciudades.
 * @author sf.munera10
 */
public class ReservaDTO {
    
    public static final String LIBRO = "Libro";
    public static final String VIDEO = "Video";
    public static final String SALA = "Sala";
    
    private Long id;
    private Long idUsuario;
    private Long idBiblioteca;
    private boolean estaA;
    private RecursoDTO recurso;
    private String nombreRecurso;
    private String tipoRecurso;
    private Date fechaInicial;
    private Long idRecurso;

    /**
     * Constructor por defecto
     */
    public ReservaDTO() {
	}

    /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
     */
    public ReservaDTO(Long id, Long idUsuario, Long idBiblioteca, String tipoRecurso, String nombreRecurso, Date fechaInicio, boolean estaA) {
		super();
		this.id = id;
                this.idUsuario = idUsuario;
		this.idBiblioteca = idBiblioteca;
                this.estaA = estaA;
                this.nombreRecurso=nombreRecurso;
                this.tipoRecurso=tipoRecurso;
                this.fechaInicial=fechaInicio;
                
                
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(Long idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public boolean isEstaA() {
        return estaA;
    }

    public void setEstaA(boolean estaA) {
        this.estaA = estaA;
    }

    public RecursoDTO getRecurso() {
        return recurso;
    }

    public void setRecurso(RecursoDTO recurso) {
        this.recurso = recurso;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Date getFechaInicio() {
        return fechaInicial;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicial = fechaInicio;
    }

    public Long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }
    
    

    
	
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idUsuario : \"" + getIdUsuario()+ ", idBiblioteca : \"" + getIdBiblioteca()+ ", Esta Activo : \"" + isEstaA()+ ",idRecurso : \"" + getIdRecurso()+"\" }" ;  
    }

    public void actualizar(ReservaDTO m, Long idBiblioteca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
