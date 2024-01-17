package com.daviddev16.produto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "produto",
        schema = "crm"
)
public class Produto {

    @Id
    @SequenceGenerator(
            name = "produto_idproduto_seq",
            sequenceName = "produto_idproduto_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "produto_idproduto_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idproduto",
            nullable = false
    )
    private Long id;


    @Column(
            name = "nmproduto",
            nullable = false
    )
    private String nome;


    @Column(
            name = "dsproduto",
            nullable = false
    )
    private String descricao;


    /* DEFINIÇÃO DE PREÇO TERÁ OUTRO FLUXO */

}
