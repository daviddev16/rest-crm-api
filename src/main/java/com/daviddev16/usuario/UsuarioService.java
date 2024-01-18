package com.daviddev16.usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> obterTodosUsuarios();

    Usuario obterUsuarioPorId(Integer usuarioId);

    void desativarUsuarioPorId(Integer usuarioId);

    Usuario cadastrarNovoUsuario(Usuario usuario);

}
