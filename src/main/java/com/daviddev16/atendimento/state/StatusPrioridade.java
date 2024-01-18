package com.daviddev16.atendimento.state;

import com.daviddev16.core.exception.generic.InvalidRequestStateException;

import java.util.Arrays;

import static java.lang.String.*;

public enum StatusPrioridade {

    ALTO,
    MEDIO,
    BAIXO,
    NENHUM;

    public static StatusPrioridade converterParaPrioridade(final String nomePrioridade) throws InvalidRequestStateException {
        try {
            return StatusPrioridade.valueOf(nomePrioridade);
        } catch (Exception e) {
            throw new InvalidRequestStateException(format("Não existe uma prioridade com" +
                    " o nome \"%s\". [Opções: %s]", nomePrioridade, Arrays.toString(values())));
        }
    }

}
