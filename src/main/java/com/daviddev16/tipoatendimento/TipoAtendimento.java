package com.daviddev16.tipoatendimento;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "tipoatendimento",
        schema = "crm",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_tipoatendimento_nmtipopatendimento", columnNames = "nmtipopatendimento")}
)
public class TipoAtendimento {

    @Id
    @SequenceGenerator(
            name = "tipoatendimento_idtipoatendimento_seq",
            sequenceName = "tipoatendimento_idtipoatendimento_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "tipoatendimento_idtipoatendimento_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idtipoatendimento",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            name = "nmtipopatendimento",
            nullable = false
    )
    private String nome;

    @Column(
            name = "dstipoatendimento",
            nullable = false
    )
    private String descricao;

}
