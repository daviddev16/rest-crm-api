package com.daviddev16.comum;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueryParamPeriodoSimples {

    @NotNull(message = "A data inicial não foi informada")
    public LocalDate dataInicio;

    @NotNull(message = "A data final não foi informada")
    public LocalDate dataFinal;

}
