package com.daviddev16.core.exception.generic;

import com.daviddev16.core.exception.RuntimeServiceException;

public class DataConflictFoundException extends RuntimeServiceException {

    public DataConflictFoundException(String message) {
        super(message);
    }

}
