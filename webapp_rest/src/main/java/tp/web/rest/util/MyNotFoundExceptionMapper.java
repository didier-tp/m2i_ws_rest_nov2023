package tp.web.rest.util;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import tp.web.dto.ApiError;
import tp.web.exception.MyNotFoundException;

@Provider
public class MyNotFoundExceptionMapper implements ExceptionMapper<MyNotFoundException> {

	@Override
	public Response toResponse(MyNotFoundException ex) {
		return Response.status(Status.NOT_FOUND)
                .entity(new ApiError(ex.getMessage(),Status.NOT_FOUND.getStatusCode()))
                .type("application/json")
                .build();
	}
}
