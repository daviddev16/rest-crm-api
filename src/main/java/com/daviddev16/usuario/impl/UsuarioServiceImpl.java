package com.daviddev16.usuario.impl;

import com.daviddev16.handler.DataIntegrityViolationExceptionHandler;
import com.daviddev16.core.DataIntegrityViolationProcessor;
import com.daviddev16.core.exception.generic.DataConflictFoundException;
import com.daviddev16.core.exception.generic.InvalidRequestStateException;
import com.daviddev16.usuario.*;
import com.daviddev16.usuario.exception.UsuarioNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class UsuarioServiceImpl implements UsuarioService,
        DataIntegrityViolationProcessor<Usuario> {

    public static final String UQ_USUARIO_LOGIN = "uq_usuario_login";

    private final UsuarioRepository usuarioRepository;
    private final DataIntegrityViolationExceptionHandler violationHandler;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              DataIntegrityViolationExceptionHandler violationHandler)
    {
        this.usuarioRepository = usuarioRepository;
        this.violationHandler = violationHandler;
    }

    @Override
    public Usuario obterUsuarioPorId(Integer usuarioId) {
        return usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }

    @Override
    public void desativarUsuarioPorId(Integer usuarioId) {

        Usuario usuarioEncontrado = obterUsuarioPorId(usuarioId);

        if (!ehUsuarioAtivo(usuarioEncontrado))
            throw new InvalidRequestStateException("Este usuário já se encontra inativo.");

        usuarioEncontrado.setStatus(StatusUsuario.INATIVO);
        usuarioRepository.save(usuarioEncontrado);

    }

    @Override
    public Usuario cadastrarNovoUsuario(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException diveException) {
            violationHandler.handleDataIntegrityViolationException(this, diveException, usuario);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(username));

        return new UsuarioDetails(usuario);
    }

    @Override
    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    private boolean ehUsuarioAtivo(Usuario usuario) {
        return usuario.getStatus() == StatusUsuario.ATIVO;
    }

    @Override
    public void processConstraintViolationEvent(String constraintName, Usuario usuario) {
        if (constraintName.equals(UQ_USUARIO_LOGIN)) {
            throw new DataConflictFoundException(format("Não é possível criar um usuário com o" +
                    " nome '%s'. Este nome já foi usado anteriormente.", usuario.getNome()));
        }
       DataConflictFoundException.throwUnexpectedException();
    }
}
