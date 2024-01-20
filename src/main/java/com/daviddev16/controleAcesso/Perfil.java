package com.daviddev16.controleAcesso;

import jakarta.persistence.*;

@Entity
@Table(
        name = "perfil",
        schema = "crm"
)
public class Perfil {

    @Id
    @SequenceGenerator(
            name = "perfil_idperfil_seq",
            sequenceName = "perfil_idperfil_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "perfil_idperfil_seq",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "idperfil",
            nullable = false
    )
    private Integer id;

    @Column(
            name = "nmperfil",
            nullable = false
    )
    private String nome;


}
