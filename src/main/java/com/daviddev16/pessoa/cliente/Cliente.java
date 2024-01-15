package com.daviddev16.pessoa.cliente;

import com.daviddev16.pessoa.Pessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Pessoa { }
