package tp.springJersey.dto.provider;

import java.util.NoSuchElementException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import tp.springJersey.dto.ApiError;

/*
 * Cette classe permet de gérer automatiquement des exceptions pas rattrapées
 * de type NoSuchElementException. cette exception prédéfinie de java >=8
 * est remontée quand on appel .get() sur un objet optional "empty" (pas trouvé)
 */


@Provider
public class NoSuchElementExceptionMapper implements ExceptionMapper<NoSuchElementException> {

	@Override
	public Response toResponse(NoSuchElementException exception) {
		return Response.status(Status.NOT_FOUND)
				.entity(new ApiError(exception.getMessage(),Status.NOT_FOUND.getStatusCode()))
				.type("application/json")
				.build();
	}

}
