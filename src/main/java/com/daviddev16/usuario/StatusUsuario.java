package com.daviddev16.usuario;

import com.daviddev16.core.exception.generic.InvalidRequestStateException;

import java.util.Arrays;

import static java.lang.String.format;

public enum StatusUsuario {

    INATIVO,
    ATIVO;

    public static StatusUsuario converterParaStatusUsuario(final String nomeStatusUsuario)
            throws InvalidRequestStateException {
        try {
            return StatusUsuario.valueOf(nomeStatusUsuario);
        } catch (Exception e) {
            throw new InvalidRequestStateException(format("Não existe um status de usuário com" +
                    " o nome \"%s\". [Opções: %s]", nomeStatusUsuario, Arrays.toString(values())));
        }
    }

}
