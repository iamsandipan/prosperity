package com.porterhead.exception;


/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
public class UserNotFoundException extends BaseWebApplicationException {

    public UserNotFoundException() {
        super(404, "User Not Found", "No User could be found for that Id");
    }
}
