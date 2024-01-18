package com.daviddev16.core.exception.generic;

import com.daviddev16.core.exception.RuntimeServiceException;

public class InvalidRequestStateException extends RuntimeServiceException {

    public InvalidRequestStateException(String message) {
        super(message);
    }
}
