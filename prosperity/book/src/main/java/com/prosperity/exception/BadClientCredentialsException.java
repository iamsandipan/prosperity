package com.prosperity.exception;

import com.prosperity.api.ErrorResponse;

/**
 * Created by iainporter on 06/11/2014.
 */
public class BadClientCredentialsException extends ErrorResponse {

    public BadClientCredentialsException(String errorMessage) {
        super("401", "Client Credentials were incorrect", "Client Credentials were incorrect. Useage: Base64Encode(username:password) ");
    }
}
