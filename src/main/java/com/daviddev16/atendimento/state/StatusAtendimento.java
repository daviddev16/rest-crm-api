package com.daviddev16.atendimento.state;

import com.daviddev16.core.exception.generic.InvalidRequestStateException;

import java.util.Arrays;

import static java.lang.String.format;

public enum StatusAtendimento {

    ABERTO,
    DELEGADO,
    ENCERRADO;

    public static StatusAtendimento converterParaStatusAtendimento(final String nomeStatusAtendimento)
            throws InvalidRequestStateException {
        try {
            return StatusAtendimento.valueOf(nomeStatusAtendimento);
        } catch (Exception e) {
            throw new InvalidRequestStateException(format("Não existe uma prioridade com" +
                    " o nome \"%s\". [Opções: %s]", nomeStatusAtendimento, Arrays.toString(values())));
        }
    }

}
