package com.daviddev16.atendimento.entidade;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "tpatendimento",
        schema = "crm"
)
public class TipoAtendimento {

    @Id
    @SequenceGenerator(
            name = "tpAtendimento_idtpatendimento_seq",
            sequenceName = "tpAtendimento_idtpatendimento_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "tpAtendimento_idtpatendimento_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idtpatendimento",
            nullable = false,
            updatable = false
    )
    private Long idTpAtendimento;

    @Column(
            name = "sgtpatendimento"
    )
    private String siglaTpAtendimento;

}
