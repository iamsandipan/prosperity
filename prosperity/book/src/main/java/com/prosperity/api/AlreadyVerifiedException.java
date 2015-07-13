package com.prosperity.api;

import com.prosperity.exception.BaseWebApplicationException;

/**
 * @version 1.0
 * @author: Sandipan
 * @since 13/05/2013
 */
public class AlreadyVerifiedException extends BaseWebApplicationException {

    public AlreadyVerifiedException() {
        super(409, "Already verified", "The token has already been verified");
    }
}
