package com.daviddev16.handler;

import com.daviddev16.core.ApiErrorDetails;
import com.daviddev16.core.exception.generic.DataConflictFoundException;
import com.daviddev16.core.exception.generic.NotFoundException;
import com.daviddev16.core.exception.RuntimeServiceException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * {@link ServiceExceptionHandler} Lida com as exceções lançadas durante a execução da API.
 **/
@RestControllerAdvice
public class ServiceExceptionHandler {

    /**
     * handleRuntimeServiceException é utilizado como método genérico para as as exceções que são
     * lançadas na camada de serviço NÃO imbutir exeções que herdam RuntimeServiceException dentro
     * de handleRuntimeServiceException,a aplicação deve lidar com cada exceção em seu próprio método
     * para facilitar a visualização e não gerar uma cadeia de if's desnecessária.
     **/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeServiceException.class)
    public ApiErrorDetails handleRuntimeServiceException(RuntimeServiceException serviceException)
    {
        return new ApiErrorDetails(serviceException.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiErrorDetails handleGenericNotFoundException(NotFoundException notFoundException)
    {
        return handleRuntimeServiceException(notFoundException);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataConflictFoundException.class)
    public ApiErrorDetails handleDataConflitFoundException(DataConflictFoundException conflitFoundException)
    {
        return handleRuntimeServiceException(conflitFoundException);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorDetails handleMethodNotValidException(MethodArgumentNotValidException notValidException)
    {
        return new ApiErrorDetails(
                notValidException
                        .getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()));
    }

}
