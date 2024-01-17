package com.daviddev16.atendimento.service;

import com.daviddev16.atendimento.dto.RequestParametrosPaginacao;
import com.daviddev16.atendimento.entidade.TipoAtendimento;
import org.springframework.data.domain.Page;

public interface TipoAtendimentoService {

    Page<TipoAtendimento> obterTodosTipoAtendimentoPaginado(RequestParametrosPaginacao parametrosPaginacao);

    TipoAtendimento obterTipoAtendimentoPorId(Integer tipoAtendimentoId);

    void excluirTipoAtendimentoPorId(Integer tipoAtendimentoId);

    TipoAtendimento incluirTipoAtendimento(TipoAtendimento tipoAtendimento);

}
