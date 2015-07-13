package com.prosperity.book;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.data.book.constants.Role;
import com.data.dao.book.UserDAOService;
import com.data.dao.book.VerificationTokenDAOService;
import com.data.mongo.exception.NopSqlDbException;
import com.data.mongo.model.ApiUser;
import com.data.mongo.model.User;
import com.prosperity.api.UpdateUserRequest;

@Path("/v1.0/users")
@Component
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class UserResource extends BaseResource {


    private UserDAOService userService;
    private DefaultTokenServices tokenServices;
    private PasswordEncoder passwordEncoder;
    private ClientDetailsService clientDetailsService;

    public UserResource(){}

    @Autowired
    public UserResource(final UserDAOService userService, 
                        final PasswordEncoder passwordEncoder, 
                        ClientDetailsService clientDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.clientDetailsService = clientDetailsService;
    }

    /*@PermitAll
    @POST
    public Response signupUser(final CreateUserRequest request, @Context SecurityContext sc, @Context UriInfo uriInfo) {
        ApiUser user = userService.createUser(request);
        CreateUserResponse createUserResponse = new CreateUserResponse(user, createTokenForNewUser(
                user.getId(), request.getPassword().getPassword(), sc.getUserPrincipal().getName()));
        verificationTokenService.sendEmailRegistrationToken(createUserResponse.getApiUser().getId());
        URI location = uriInfo.getAbsolutePathBuilder().path(createUserResponse.getApiUser().getId()).build();
        return Response.created(location).entity(createUserResponse).build();
    }
*/

    @RolesAllowed({"ROLE_USER"})
    @Path("{id}")
    @GET
    public ApiUser getUser(final @PathParam("id") String userId, final @Context SecurityContext securityContext) throws NopSqlDbException {
        User requestingUser = ensureUserIsAuthorized(securityContext, userId);
        return userService.getUser(requestingUser.getId());
    }

    private OAuth2AccessToken createTokenForNewUser(String userId, String password, String clientId) {
        String hashedPassword = passwordEncoder.encode(password);
        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
                userId,
                hashedPassword, Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.toString())));
        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
        OAuth2Request oAuth2Request = createOAuth2Request(null, clientId,
                Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.toString())),
                true, authenticatedClient.getScope(), null, null, null, null);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
        return tokenServices.createAccessToken(oAuth2Authentication);
    }

    @RolesAllowed({"ROLE_USER"})
    @Path("{userId}")
    @PUT
    public Response updateUser(@Context SecurityContext sc, @PathParam("userId") String userId, UpdateUserRequest request) {
        User requestingUser = ensureUserIsAuthorized(sc, userId);

        boolean sendVerificationToken = StringUtils.hasLength(request.getEmailAddress()) &&
                !request.getEmailAddress().equals(requestingUser.getEmailAddress());
        try {
        	User user = new User();
        	user.setFirstName(request.getFirstName());
        	user.setLastName(request.getLastName());
        	user.setEmailAddress(request.getEmailAddress());
			userService.saveUser(user);
		} catch (NopSqlDbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return Response.ok().build();
        

        
    }

    private OAuth2Request createOAuth2Request(Map<String, String> requestParameters, String clientId,
                                              Collection<? extends GrantedAuthority> authorities, boolean approved, Collection<String> scope,
                                              Set<String> resourceIds, String redirectUri, Set<String> responseTypes,
                                              Map<String, Serializable> extensionProperties) {
        return new OAuth2Request(requestParameters, clientId, authorities, approved, scope == null ? null
                : new LinkedHashSet<String>(scope), resourceIds, redirectUri, responseTypes, extensionProperties);
    }

}