package com.prosperity.book;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.dao.book.VerificationTokenDAOService;
import com.data.mongo.exception.NopSqlDbException;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
@Path("/v1.0/verify")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class VerificationResource  {

    @Autowired
    protected VerificationTokenDAOService verificationTokenService;

    @PermitAll
    @Path("tokens/{token}")
    @POST
    public Response verifyToken(@PathParam("token") String token) {
        try {
			verificationTokenService.verify(token);
		} catch (NopSqlDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Response.ok().build();
    }

}
