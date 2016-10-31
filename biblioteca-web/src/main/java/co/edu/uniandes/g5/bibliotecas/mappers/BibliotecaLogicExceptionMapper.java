package co.edu.uniandes.g5.bibliotecas.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.edu.uniandes.g5.bibliotecas.exceptions.BibliotecaLogicException;

/**
 * Convertidor de Excepciones CityLogicException a mensajes REST.
 */
@Provider
public class BibliotecaLogicExceptionMapper implements ExceptionMapper<BibliotecaLogicException> {

	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */
	@Override
	public Response toResponse(BibliotecaLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}
	
}
