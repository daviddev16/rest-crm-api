package com.daviddev16.atendimento.impl;

import com.daviddev16.atendimento.Atendimento;
import com.daviddev16.atendimento.state.StatusPrioridade;
import com.daviddev16.atendimento.state.StatusAtendimento;
import com.daviddev16.atendimento.exception.AtendimentoNaoEncontradoException;
import com.daviddev16.atendimento.AtendimentoRepository;
import com.daviddev16.atendimento.AtendimentoService;
import com.daviddev16.atendimento.specs.AtendimentoSpecifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AtendimentoServiceImpl implements AtendimentoService {


    private final AtendimentoRepository atendimentoRepository;

    public AtendimentoServiceImpl(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    @Override
    public List<Atendimento> obterTodosAtendimentosPorPeriodo(LocalDate dataInicio, LocalDate dataFinal) {

        final Specification<Atendimento> atendimentosEntrePeriodoSpec =
                AtendimentoSpecifications.filtroPorPeriodo(dataInicio, dataFinal);

        return atendimentoRepository.findAll(atendimentosEntrePeriodoSpec);
    }

    @Override
    public Atendimento obterAtendimentoPorId(UUID atendimentoId) {
        return atendimentoRepository
                .findById(atendimentoId)
                .orElseThrow(() -> new AtendimentoNaoEncontradoException(atendimentoId));
    }

    @Override
    public Atendimento atualizarPrioridadeAtendimento(UUID atendimentoId, StatusPrioridade novaPrioridade) {
        Atendimento atendimentoEncontrado = obterAtendimentoPorId(atendimentoId);
        atendimentoEncontrado.setStatusPrioridade(novaPrioridade);
        return atendimentoRepository.save(atendimentoEncontrado);
    }

    @Override
    public Atendimento atualizarStatusAtendimento(UUID atendimentoId, StatusAtendimento novoStatusAtendimento) {
        Atendimento atendimentoEncontrado = obterAtendimentoPorId(atendimentoId);
        atendimentoEncontrado.setStatusAtendimento(novoStatusAtendimento);
        return atendimentoRepository.save(atendimentoEncontrado);
    }

    @Override
    public Atendimento criarNovoAtendimento(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }
}
