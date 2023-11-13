package tp.web.util;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
        	// Get the HTTP Authorization header from the request
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            // Extract the token from the HTTP Authorization header
            String token = authorizationHeader.substring("Bearer".length()).trim();
        	JwtUtil.validateToken(token, JwtUtil.DEFAULT_JWT_SECRET);
        	System.out.println("### valid jwt bearer token");
        } catch (Exception e) {
        	System.out.println("#### invalid bearer token");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
		
	}

}
