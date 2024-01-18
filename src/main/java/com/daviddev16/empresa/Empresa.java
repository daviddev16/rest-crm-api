package com.daviddev16.empresa;

import com.daviddev16.atendimento.Atendimento;
import com.daviddev16.usuario.Usuario;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "empresa",
        schema = "crm",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_empresa_nmempresa", columnNames = "nmempresa")
        }
)
public class Empresa {

    @Id
    @SequenceGenerator(
            name = "empresa_idempresa_seq",
            sequenceName = "empresa_idempresa_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "empresa_idempresa_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idempresa",
            nullable = false,
            updatable = false
    )
    private Integer id;


    @Column(
            name = "nmempresa",
            nullable = false
    )
    private String nomePrincipal;


    @Column(
            name = "nmfantasia",
            nullable = false
    )
    private String nomeFantasia;


    @Column(
            name = "cpfCnpj",
            nullable = false,
            length = 14
    )
    private String cpfCnpj;

    @ManyToOne
    @JoinColumn(
            name = "idusuarioresponsavel",
            foreignKey = @ForeignKey(name = "fk_empresa_idusuarioresponsavel",
                                     value = ConstraintMode.NO_CONSTRAINT)
    )
    private Usuario usuarioResponsavel;


    @OneToMany(mappedBy = "empresa")
    private List<Atendimento> atendimentos;

}
