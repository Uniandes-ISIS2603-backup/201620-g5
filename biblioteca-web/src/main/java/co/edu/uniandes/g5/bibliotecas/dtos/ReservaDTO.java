/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.g5.bibliotecas.dtos;

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
    private UsuarioDTO usuario;
    private BiblioDTO biblioteca;
    private boolean estaA;
    private RecursoDTO recurso;
    private String tipoRecurso;
    private Date fechaInicial;
    private Date fechaFinal;

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
    public ReservaDTO(Long id, UsuarioDTO idUsuario, BiblioDTO idBiblioteca, boolean estaA,String tipoRecurso, RecursoDTO idRecurso, Date fechaInicial, Date fechaFinal) {
		super();
		this.id = id;
                this.usuario = idUsuario;
		this.biblioteca = idBiblioteca;
                this.estaA = estaA;
                this.recurso = idRecurso;
                this.tipoRecurso = tipoRecurso;
                this.fechaInicial = fechaInicial;
                this.fechaFinal = fechaFinal;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDTO getIdUsuario() {
        return usuario;
    }

    public void setIdUsuario(UsuarioDTO idUsuario) {
        this.usuario = idUsuario;
    }

    public BiblioDTO getIdBiblioteca() {
        return biblioteca;
    }

    public void setIdBiblioteca(BiblioDTO idBiblioteca) {
        this.biblioteca = idBiblioteca;
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

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    
	
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idUsuario : \"" + getIdUsuario()+ ", idBiblioteca : \"" + getIdBiblioteca()+ ", Esta Activo : \"" + isEstaA()+ ",idRecurso : \"" + getRecurso().getId() + "\" }" ;  
    }
}
