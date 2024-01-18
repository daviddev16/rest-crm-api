package com.daviddev16.tipoatendimento;

import com.daviddev16.comum.QueryParamPaginacaoSimples;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/tipoAtendimento")
public class TipoAtendimentoController {

    private final TipoAtendimentoService tipoAtendimentoService;

    public TipoAtendimentoController(TipoAtendimentoService tipoAtendimentoService)
    {
        this.tipoAtendimentoService = tipoAtendimentoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TipoAtendimento> obterTodosTipoAtendimentoPaginado( @Valid QueryParamPaginacaoSimples paginacaoSimples )
    {
        return tipoAtendimentoService.obterTodosTipoAtendimentoPaginado(paginacaoSimples);
    }

    @GetMapping(value = "/{tipoAtendimentoId}")
    @ResponseStatus(HttpStatus.OK)
    public TipoAtendimento obterTipoAtendimentoPorId( @PathVariable("tipoAtendimentoId") Integer tipoAtendimentoId )
    {
        return tipoAtendimentoService.obterTipoAtendimentoPorId(tipoAtendimentoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoAtendimento incluirTipoAtendimento( @RequestBody TipoAtendimento tipoAtendimento )
    {
        return tipoAtendimentoService.incluirTipoAtendimento(tipoAtendimento);
    }

    @DeleteMapping(value = "/{tipoAtendimentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirTipoAtendimentoPorId( @PathVariable("tipoAtendimentoId") Integer tipoAtendimentoId )
    {
        tipoAtendimentoService.excluirTipoAtendimentoPorId(tipoAtendimentoId);
    }

}
