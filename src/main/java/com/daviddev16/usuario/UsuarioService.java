package com.daviddev16.usuario;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {

    List<Usuario> obterTodosUsuarios();

    Usuario obterUsuarioPorId(Integer usuarioId);

    void desativarUsuarioPorId(Integer usuarioId);

    Usuario cadastrarNovoUsuario(Usuario usuario);

}
