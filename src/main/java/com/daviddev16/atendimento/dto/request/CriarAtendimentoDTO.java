package com.daviddev16.atendimento.dto.request;

import com.daviddev16.atendimento.state.StatusPrioridade;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CriarAtendimentoDTO {

    /*
    {
            "idUsuario": 3,
            "idPessoa": 4,
            "idTipoAtendimento": 9,
            "stPrioridade": "BAIXA"
    }*/

    /* ID USUÁRIO DEVERÁ SER RECUPERADO INTERNAMENTE ATRAVÉS DO OBJETO DE AUTENTICAÇÃO FUTURAMENTE */
    /* private Integer idUsuario; */

    private Integer idTipoAtendimento;

    @JsonProperty(value = "stPrioridade")
    private StatusPrioridade statusPrioridade;

}
