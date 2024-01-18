package com.daviddev16.atendimento;

import com.daviddev16.atendimento.state.StatusPrioridade;
import com.daviddev16.atendimento.state.StatusAtendimento;
import com.daviddev16.comum.QueryParamPeriodoSimples;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/api/atendimento")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Atendimento> obterTodosAtendimentosPorPeriodo( QueryParamPeriodoSimples periodoSimples )
    {
        return atendimentoService.obterTodosAtendimentosPorPeriodo(
                periodoSimples.getDataInicio(), periodoSimples.getDataFinal());
    }

    @GetMapping("/{atendimentoId}")
    @ResponseStatus(HttpStatus.OK)
    public Atendimento obterAtendimentoPorId( @PathVariable("atendimentoId") UUID atendimentoId )
    {
        return atendimentoService.obterAtendimentoPorId(atendimentoId);
    }

    @PatchMapping("/{atendimentoId}/{novaPrioridade}")
    @ResponseStatus(HttpStatus.OK)
    public Atendimento atualizarPrioridadeAtendimento( @PathVariable("atendimentoId") UUID atendimentoId,
                                                       @PathVariable("novaPrioridade") String nomePrioridade)
    {
        final StatusPrioridade novaPrioridade = StatusPrioridade.converterParaPrioridade(nomePrioridade);
        return atendimentoService.atualizarPrioridadeAtendimento(atendimentoId, novaPrioridade);
    }

    public Atendimento atualizarStatusAtendimento(UUID atendimentoId, StatusAtendimento novoStatusAtendimento)
    {
        return atendimentoService.atualizarStatusAtendimento(atendimentoId, novoStatusAtendimento);
    }

    public Atendimento criarNovoAtendimento(Atendimento atendimento)
    {
        return atendimentoService.criarNovoAtendimento(atendimento);
    }
}
