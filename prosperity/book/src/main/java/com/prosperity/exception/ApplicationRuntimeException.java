package com.prosperity.exception;

/**
 * @version 1.0
 * @author: Sandipan
 * @since 26/04/2013
 */
public class ApplicationRuntimeException extends BaseWebApplicationException {

    public ApplicationRuntimeException(String applicationMessage) {
        super(500, "Internal System error", applicationMessage);
    }
}
