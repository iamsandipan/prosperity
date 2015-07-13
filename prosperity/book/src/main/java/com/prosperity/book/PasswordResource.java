package com.prosperity.book;

import com.data.dao.book.VerificationTokenDAOService;
import com.data.mongo.exception.NopSqlDbException;
import com.prosperity.api.LostPasswordRequest;
import com.prosperity.api.PasswordRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @version 1.0
 * @author: Sandipan
 * @since 13/05/2013
 */
@Path("/v1.0/password")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PasswordResource {

    @Autowired
    private VerificationTokenDAOService verificationTokenService;

    @PermitAll
    @Path("tokens")
    @POST
    public Response sendEmailToken(LostPasswordRequest request) {
        verificationTokenService.sendLostPasswordToken(request.getEmailAddress());
        return Response.ok().build();
    }

    @PermitAll
    @Path("tokens/{token}")
    @POST
    public Response resetPassword(@PathParam("token") String base64EncodedToken, PasswordRequest request)  {
        try {
			verificationTokenService.resetPassword(base64EncodedToken, request.getPassword());
		} catch (NopSqlDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Response.ok().build();
    }
}
