package com.daviddev16.parecer;

import com.daviddev16.atendimento.Atendimento;
import com.daviddev16.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "parecer",
        schema = "crm",
        indexes = {
                @Index(name = "idx_parecer_idatendimento", columnList = "idatendimento")}
)
public class Parecer {

    @Id
    @SequenceGenerator(
            name = "parecer_idparecer_seq",
            sequenceName = "parecer_idparecer_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "parecer_idparecer_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idparecer",
            nullable = false,
            updatable = false
    )
    private Long id;


    @Column(
            name = "nmpessoacontato",
            nullable = false
    )
    private String nomePessoaContato;


    @Column(
            name = "dsparecer",
            nullable = false
    )
    private String descricao;


    @Column(
            name = "conteudo",
            columnDefinition = "TEXT",
            nullable = false,
            updatable = false
    )
    private String conteudo;


    @Column(
            name = "dtcricao",
            nullable = false,
            updatable = false
    )
    private LocalDateTime dataCriacao;


    @JoinColumn(
            name = "idusuario",
            foreignKey =
                    @ForeignKey(name = "fk_parecer_idusuario",
                                value = ConstraintMode.NO_CONSTRAINT)
    )
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Usuario usuario;


    @JoinColumn(
            name = "idatendimento",
            foreignKey =
                    @ForeignKey(name = "fk_parecer_idatendimento",
                                value = ConstraintMode.CONSTRAINT)

    )
    @ManyToOne
    private Atendimento atendimento;
}
