package com.daviddev16.core;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
public class DataIntegrityViolationExceptionHandler {

    public <E> void handleDataIntegrityViolationException(
            DataIntegrityViolationProcessor<E> violationProcessor,
            DataIntegrityViolationException exception,
            final E referencedEntity)
    {
        Throwable causa = exception.getCause();
        /*
         * Se é uma violação na integridade de dados e a causa não é uma violação
         * de constraint deverá lançar a exceção novamente por não ser uma causa
         * mapeada.
         **/
        if (!(causa instanceof ConstraintViolationException))
            throw exception;

        String nomeConstraint = ((ConstraintViolationException)causa).getConstraintName();
        violationProcessor.processConstraintViolationEvent(nomeConstraint, referencedEntity);
    }

}
