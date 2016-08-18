/*
 * CityDTO
 * Objeto de transferencia de datos de Ciudades.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package co.edu.uniandes.rest.resources.dtos;


/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class ReservaDTO {
    private int id;
    private int idUsuario;
    private int idBiblioteca;
    private boolean estaA;
    private int idRecurso;

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
    public ReservaDTO(int id, int idUsuario, int idBiblioteca, boolean estaA, int idRecurso) {
		super();
		this.id = id;
                this.idUsuario = idUsuario;
		this.idBiblioteca = idBiblioteca;
                this.estaA = estaA;
                this.idRecurso=idRecurso;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdBiblioteca() {
        return idBiblioteca;
    }

    public void setIdBiblioteca(int idBiblioteca) {
        this.idBiblioteca = idBiblioteca;
    }

    public boolean isEstaA() {
        return estaA;
    }

    public void setEstaA(boolean estaA) {
        this.estaA = estaA;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }
	
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", idUsuario : \"" + getIdUsuario()+ ", idBiblioteca : \"" + getIdBiblioteca()+ ", Esta Activo : \"" + isEstaA()+ ",idRecurso : \"" + getIdRecurso()+"\" }" ;  
    }
}
