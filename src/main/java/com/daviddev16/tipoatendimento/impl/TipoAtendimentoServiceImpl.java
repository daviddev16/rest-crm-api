package com.daviddev16.tipoatendimento.impl;

import com.daviddev16.tipoatendimento.TipoAtendimentoRepository;
import com.daviddev16.comum.QueryParamPaginacaoSimples;
import com.daviddev16.handler.DataIntegrityViolationExceptionHandler;
import com.daviddev16.core.DataIntegrityViolationProcessor;
import com.daviddev16.core.exception.generic.DataConflictFoundException;
import com.daviddev16.tipoatendimento.TipoAtendimento;
import com.daviddev16.tipoatendimento.TipoAtendimentoService;
import com.daviddev16.tipoatendimento.exception.TipoAtendimentoNaoEncontradoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static java.lang.String.*;
import static org.springframework.data.domain.Sort.*;

@Service
public class TipoAtendimentoServiceImpl implements TipoAtendimentoService,
                                                   DataIntegrityViolationProcessor<TipoAtendimento> {

    public static final String UQ_NOME_TIPO_ATENDIMENTO = "uq_tpatendimento_nmtipopatendimento";

    private final TipoAtendimentoRepository tipoAtendimentoRepository;
    private final DataIntegrityViolationExceptionHandler violationHandler;

    public TipoAtendimentoServiceImpl(TipoAtendimentoRepository tipoAtendimentoRepository,
                                      DataIntegrityViolationExceptionHandler violationHandler)
    {
        this.tipoAtendimentoRepository = tipoAtendimentoRepository;
        this.violationHandler = violationHandler;
    }

    private TipoAtendimento internoObterTipoAtendimentoPorId(Integer tipoAtendimentoId) {
        return tipoAtendimentoRepository
                .findById(tipoAtendimentoId)
                .orElseThrow(()
                        -> new TipoAtendimentoNaoEncontradoException(tipoAtendimentoId));
    }

    @Override
    public TipoAtendimento obterTipoAtendimentoPorId(Integer tipoAtendimentoId) {
        return internoObterTipoAtendimentoPorId(tipoAtendimentoId);
    }

    @Override
    public Page<TipoAtendimento> obterTodosTipoAtendimentoPaginado(QueryParamPaginacaoSimples paginacaoSimples) {

        Direction directionOrdenacao = Direction
                .fromOptionalString(paginacaoSimples.getTipoOrdenacao())
                .orElse(Direction.DESC);

        PageRequest pageRequest = PageRequest.of(
                paginacaoSimples.getNumeroPagina(),
                paginacaoSimples.getNumeroDeRegistros(),
                Sort.by(directionOrdenacao, "nome"));

        return tipoAtendimentoRepository.findAll(pageRequest);

    }

    @Override
    public void excluirTipoAtendimentoPorId(Integer tipoAtendimentoId) {
        final TipoAtendimento tipoAtendimento = obterTipoAtendimentoPorId(tipoAtendimentoId);
        tipoAtendimentoRepository.delete(tipoAtendimento);
    }

    @Override
    public TipoAtendimento incluirTipoAtendimento(TipoAtendimento tipoAtendimento) {
        try {
            return tipoAtendimentoRepository.save(tipoAtendimento);
        } catch (DataIntegrityViolationException diveException)
        {
            violationHandler.handleDataIntegrityViolationException(this, diveException, tipoAtendimento);
        }
        return null;
    }

    @Override
    public void processConstraintViolationEvent(String constraintName, TipoAtendimento tipoAtendimento) {
        if (constraintName.equals(UQ_NOME_TIPO_ATENDIMENTO))
        {
            throw new DataConflictFoundException(format("Não é possível criar um tipo de atendimento com o" +
                    " nome '%s'. Este nome já foi usado anteriormente.", tipoAtendimento.getNome()));
        }
        DataConflictFoundException.throwUnexpectedException();
    }
}
