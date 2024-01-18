package com.daviddev16.atendimento.exception;

import com.daviddev16.core.exception.generic.NotFoundException;

import java.util.UUID;

import static java.lang.String.format;

public class AtendimentoNaoEncontradoException extends NotFoundException {

    public AtendimentoNaoEncontradoException(UUID atendimentoId) {
        super( format("Não foi possível localizar um atendimento com id %d.", atendimentoId) );
    }
}
