package com.daviddev16.usuario.exception;

import com.daviddev16.core.exception.generic.NotFoundException;

import java.util.UUID;

import static java.lang.String.format;

public class UsuarioNaoEncontradoException extends NotFoundException {

    public UsuarioNaoEncontradoException(Integer usuarioId) {
        super( format("Não foi possível localizar um usuário com id %d.", usuarioId) );
    }
}
