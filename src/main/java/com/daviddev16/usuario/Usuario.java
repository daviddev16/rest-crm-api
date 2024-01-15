package com.daviddev16.usuario;

import com.daviddev16.atendimento.entidade.Atendimento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(
        name = "usuario",
        schema = "crm",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_usuario_login", columnNames = "login")}
)
public class Usuario {

    @Id
    @SequenceGenerator(
            name = "usuario_idusuario_seq",
            sequenceName = "usuario_idusuario_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "usuario_idusuario_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idusuario",
            nullable = false,
            updatable = false
    )
    private Long idUsuario;


    @Column(
            name = "login",
            nullable = false,
            length = 40
    )
    private String login;


    @Column(
            name = "nmusuario",
            nullable = false
    )
    private String nome;


    @Column(
            name = "email",
            nullable = false
    )
    private String email;


    @Column(
            name = "senha",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String senha;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "stusuario",
            nullable = false
    )
    private StatusUsuario statusUsuario;


    @Column(
            name = "dtcadastro",
            nullable = false
    )
    private LocalDateTime dataCadastro;


    @OneToMany(mappedBy = "usuario")
    private List<Atendimento> atendimentos;

}
