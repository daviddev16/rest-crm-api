package com.daviddev16.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> obterTodosUsuarios()
    {
        return usuarioService.obterTodosUsuarios();
    }

    @GetMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario obterUsuarioPorId( @PathVariable("usuarioId") Integer usuarioId )
    {
        return usuarioService.obterUsuarioPorId(usuarioId);
    }

    @PatchMapping("/desativar/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarUsuarioPorId( @PathVariable("usuarioId") Integer usuarioId )
    {
        usuarioService.desativarUsuarioPorId(usuarioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrarNovoUsuario(Usuario usuario)
    {
        return usuarioService.cadastrarNovoUsuario(usuario);
    }


}
