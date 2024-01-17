package com.daviddev16.core;

import org.springframework.dao.DataIntegrityViolationException;

/**
 * {@link DataIntegrityViolationProcessor<T>} Faz o processamento de um evento onde
 * ocorre a exceção {@link DataIntegrityViolationException}. Geralmente ocorre durante
 * operações DDL onde é feito a inserção/alteração de dados que possuem constraints que
 * são violadas.
 *
 * @see DataIntegrityViolationExceptionHandler
 *
 */
public interface DataIntegrityViolationProcessor<T> {

    void processConstraintViolationEvent(String constraintName, T entity);

}
