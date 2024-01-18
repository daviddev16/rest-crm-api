package com.daviddev16.parecer.impl;

import com.daviddev16.atendimento.Atendimento;
import com.daviddev16.atendimento.AtendimentoService;
import com.daviddev16.parecer.Parecer;
import com.daviddev16.parecer.ParecerRepository;
import com.daviddev16.parecer.ParecerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ParecerServiceImpl implements ParecerService {

    private final ParecerRepository parecerRepository;
    private final AtendimentoService atendimentoService;

    public ParecerServiceImpl(AtendimentoService atendimentoService, ParecerRepository parecerRepository) {
        this.atendimentoService = atendimentoService;
        this.parecerRepository = parecerRepository;
    }

    @Override
    @Transactional
    public Parecer registrarParecerEmAtendimento(UUID atendimentoId, Parecer parecer) {

        Atendimento atendimento = atendimentoService.obterAtendimentoPorId(atendimentoId);

        Parecer novoParecer = Parecer.builder()
                .atendimento(atendimento)
                .dataCriacao(LocalDateTime.now())
                .build();

        return parecerRepository.save(novoParecer);
    }
}
