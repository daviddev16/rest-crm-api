package com.daviddev16.pessoa;

import com.daviddev16.pessoa.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(
        name = "pessoa",
        schema = "crm"
)
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@DiscriminatorColumn(
        name = "tppessoa",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class Pessoa {

    @Id
    @SequenceGenerator(
            name = "pessoa_idpessoa_seq",
            sequenceName = "pessoa_idpessoa_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "pessoa_idpessoa_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idpessoa",
            nullable = false
    )
    private Long id;


    @Column(
            name = "nmpessoa",
            nullable = false,
            length = 40
    )
    private String nome;


    @Column(
            name = "dsendereco",
            nullable = false
    )
    private String endereco;


    @Column(
            name = "cep",
            nullable = false
    )
    private String cep;


    @Column(
            name = "dsbairro",
            nullable = false
    )
    private String bairro;

    @Column(
            name = "dscidade",
            nullable = false
    )
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "tppessoa",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private TipoPessoa tipoPessoa;

}
