package com.daviddev16.atendimento;

import com.daviddev16.atendimento.state.StatusPrioridade;
import com.daviddev16.atendimento.state.StatusAtendimento;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AtendimentoService {

    List<Atendimento> obterTodosAtendimentosPorPeriodo(LocalDate dataInicio, LocalDate dataFinal);

    Atendimento obterAtendimentoPorId(UUID atendimentoId);

    Atendimento atualizarPrioridadeAtendimento(UUID atendimentoId, StatusPrioridade novaPrioridade);

    Atendimento atualizarStatusAtendimento(UUID atendimentoId, StatusAtendimento novoStatusAtendimento);

    Atendimento criarNovoAtendimento(Atendimento atendimento);

}
