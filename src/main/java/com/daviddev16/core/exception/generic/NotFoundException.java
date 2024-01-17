package com.daviddev16.core.exception.generic;

import com.daviddev16.core.exception.RuntimeServiceException;

public abstract class NotFoundException extends RuntimeServiceException {

    public NotFoundException(String message) {
        super(message);
    }

}
