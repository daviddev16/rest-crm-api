package com.daviddev16.atendimento.specs;

import com.daviddev16.atendimento.Atendimento;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class AtendimentoSpecifications {

    private AtendimentoSpecifications() {}

    public static Specification<Atendimento> filtroPorPeriodo(LocalDate dataInicio, LocalDate dataFinal) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("dataCriacao"), dataInicio, dataFinal);
    }

}
