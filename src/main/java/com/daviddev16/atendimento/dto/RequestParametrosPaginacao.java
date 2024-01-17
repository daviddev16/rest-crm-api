package com.daviddev16.atendimento.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestParametrosPaginacao {

    @NotNull(message = "O número da página deve ser informado.")
    @Min(value = 0, message = "O número de páginas deve ser de no mínimo 0.")
    private Integer numeroPagina;

    @NotNull(message = "O número de registros deve ser informado.")
    @Min(value = 1, message = "O número de registros deve ser de no mínimo 1.")
    private Integer numeroDeRegistros;

    @Nullable
    private String tipoOrdenacao;

}
