package com.daviddev16.atendimento.entidade;

import com.daviddev16.atendimento.enums.Prioridade;
import com.daviddev16.atendimento.enums.StatusAtendimento;
import com.daviddev16.pessoa.cliente.Cliente;
import com.daviddev16.produto.Produto;
import com.daviddev16.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "atendimento",
        schema = "crm",
        indexes = {
                @Index(name = "idx_atendimento_idusuario", columnList = "idusuario"),
                @Index(name = "idx_atendimento_idcliente", columnList = "idcliente")
        }
)
public class Atendimento {

    @Id
    @SequenceGenerator(
            name = "atendimento_idatendimento_seq",
            sequenceName = "atendimento_idatendimento_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "atendimento_idatendimento_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idatendimento",
            nullable = false,
            updatable = false
    )
    private Long id;


    @Column(
            name = "dsatendimento",
            nullable = false,
            length = 100
    )
    private String descricao;


    @JoinColumn(
            name = "idusuario",
            foreignKey =
                    @ForeignKey(name = "fk_atendimento_idusuario",
                                value = ConstraintMode.NO_CONSTRAINT)
    )
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Usuario usuario;


    @OneToMany(
            mappedBy = "atendimento",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private Set<Parecer> parecers;

    @JoinColumn(
            name = "idproduto",
            foreignKey =
                @ForeignKey(name = "fk_atendimento_idproduto",
                            value = ConstraintMode.NO_CONSTRAINT)
    )
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Produto produto;


    @JoinColumn(
            name = "idcliente",
            foreignKey =
                @ForeignKey(name = "fk_atendimento_idcliente",
                            value = ConstraintMode.NO_CONSTRAINT)
    )
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private Cliente cliente;


    @JoinColumn(
            name = "idtpatendimento",
            foreignKey =
                    @ForeignKey(name = "fk_atendimento_idtpatendimento",
                                value = ConstraintMode.NO_CONSTRAINT)
    )
    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    private TipoAtendimento tipoAtendimento;


    @Column(
            name = "dtcriacao",
            nullable = false
    )
    private LocalDateTime dataCriacao;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "statendimento",
            nullable = false
    )
    private StatusAtendimento statusAtendimento;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "stprioridade",
            nullable = false
    )
    private Prioridade statusPrioridade;


}
