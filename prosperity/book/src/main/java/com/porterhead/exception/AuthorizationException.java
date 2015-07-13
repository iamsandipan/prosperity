package com.porterhead.exception;



/**
 * @version 1.0
 * @author: Sandipan
 * @since 25/04/2013
 */
public class AuthorizationException extends BaseWebApplicationException {

    public AuthorizationException(String applicationMessage) {
        super(403, "Not authorized", applicationMessage);
    }

}
