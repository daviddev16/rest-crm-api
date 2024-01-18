package com.daviddev16.tipoatendimento.exception;

import com.daviddev16.core.exception.generic.NotFoundException;

import static java.lang.String.format;

public class TipoAtendimentoNaoEncontradoException extends NotFoundException {

    public TipoAtendimentoNaoEncontradoException(Integer tipoAtendimentoId) {
        super( format("Não foi possível localizar um tipo de atendimento com id %d.", tipoAtendimentoId) );
    }
}
