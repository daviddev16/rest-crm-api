package com.daviddev16.atendimento;

import com.daviddev16.atendimento.state.StatusPrioridade;
import com.daviddev16.atendimento.state.StatusAtendimento;
import com.daviddev16.empresa.Empresa;
import com.daviddev16.parecer.Parecer;
import com.daviddev16.pessoa.cliente.Cliente;
import com.daviddev16.produto.Produto;
import com.daviddev16.tipoatendimento.TipoAtendimento;
import com.daviddev16.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;


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
    @UuidGenerator(
            style = UuidGenerator.Style.TIME
    )
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    @Column(
            name = "idatendimento",
            nullable = false,
            updatable = false
    )
    private UUID id;


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


    @Column(
            name = "dtencerramento",
            nullable = false
    )
    private LocalDateTime dataEncerramento;


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
    private StatusPrioridade statusPrioridade;

    @ManyToOne
    @JoinColumn(
            name = "idempresa",
            foreignKey = @ForeignKey(name = "fk_empresa_idempresa",
                                     value = ConstraintMode.CONSTRAINT)
    )
    private Empresa empresa;


}
