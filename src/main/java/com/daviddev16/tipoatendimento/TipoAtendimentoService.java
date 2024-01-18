package com.daviddev16.tipoatendimento;

import com.daviddev16.comum.QueryParamPaginacaoSimples;
import org.springframework.data.domain.Page;

public interface TipoAtendimentoService {

    Page<TipoAtendimento> obterTodosTipoAtendimentoPaginado(QueryParamPaginacaoSimples parametrosPaginacao);

    TipoAtendimento obterTipoAtendimentoPorId(Integer tipoAtendimentoId);

    void excluirTipoAtendimentoPorId(Integer tipoAtendimentoId);

    TipoAtendimento incluirTipoAtendimento(TipoAtendimento tipoAtendimento);

}
