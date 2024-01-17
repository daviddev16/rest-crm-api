package com.daviddev16.core;

import java.util.Arrays;
import java.util.List;

public class ApiErrorDetails {

    private final List<String> errors;

    public ApiErrorDetails(final String mensagem) {
        this(Arrays.asList(mensagem));
    }

    public ApiErrorDetails(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
