package com.daviddev16.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioDetails extends User {

    public UsuarioDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UsuarioDetails(Usuario usuario) {
        this(usuario.getLogin(), usuario.getSenha(), null);
    }

}

