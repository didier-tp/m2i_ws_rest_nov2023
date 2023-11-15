package tp.web.rest.util;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import tp.web.dto.ApiError;
import tp.web.exception.MyConflictException;

@Provider
public class MyConflictExceptionMapper implements ExceptionMapper<MyConflictException> {

	@Override
	public Response toResponse(MyConflictException ex) {
		return Response.status(Status.CONFLICT)
                .entity(new ApiError(ex.getMessage(),Status.CONFLICT.getStatusCode()))
                .type("application/json")
                .build();
	}
}
