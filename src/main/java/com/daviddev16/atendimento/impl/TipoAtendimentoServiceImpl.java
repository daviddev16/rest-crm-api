package com.daviddev16.atendimento.impl;

import com.daviddev16.atendimento.dto.RequestParametrosPaginacao;
import com.daviddev16.atendimento.entidade.TipoAtendimento;
import com.daviddev16.atendimento.exception.TipoAtendimentoNaoEncontradoException;
import com.daviddev16.atendimento.repository.TipoAtendimentoRepository;
import com.daviddev16.atendimento.service.TipoAtendimentoService;
import com.daviddev16.core.DataIntegrityViolationExceptionHandler;
import com.daviddev16.core.DataIntegrityViolationProcessor;
import com.daviddev16.core.exception.generic.DataConflictFoundException;
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

    private final TipoAtendimentoRepository tipoAtendimentoRepository;
    private final DataIntegrityViolationExceptionHandler violationHandler;

    public TipoAtendimentoServiceImpl(TipoAtendimentoRepository tipoAtendimentoRepository,
                                      DataIntegrityViolationExceptionHandler violationHandler)
    {
        this.tipoAtendimentoRepository = tipoAtendimentoRepository;
        this.violationHandler = violationHandler;
    }

    /**
     * Para localização de tipo de atendimento por Id genérico, onde será usado o findById.
     */
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
    public Page<TipoAtendimento> obterTodosTipoAtendimentoPaginado(RequestParametrosPaginacao parametrosPaginacao)
    {
        Direction directionOrdenacao = Direction
                .fromOptionalString(parametrosPaginacao.getTipoOrdenacao())
                .orElse(Direction.DESC);

        PageRequest pageRequest = PageRequest.of(
                parametrosPaginacao.getNumeroPagina(),
                parametrosPaginacao.getNumeroDeRegistros(),
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
    public void processConstraintViolationEvent(String constraintName, TipoAtendimento tipoAtendimento)
    {
        if (constraintName.equals("uq_tpatendimento_nmtipopatendimento"))
        {
            throw new DataConflictFoundException(format("Não é possível criar um tipo de atendimento com o" +
                    " nome '%s'. Este nome já foi usado anteriormente.", tipoAtendimento.getNome()));
        }
    }
}
