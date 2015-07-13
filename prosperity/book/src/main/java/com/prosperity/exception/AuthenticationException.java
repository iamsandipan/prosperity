package com.prosperity.exception;



public class AuthenticationException extends BaseWebApplicationException {

    public AuthenticationException() {
        super(401, "Authentication Error", "Authentication Error. The username or password were incorrect");
    }
}