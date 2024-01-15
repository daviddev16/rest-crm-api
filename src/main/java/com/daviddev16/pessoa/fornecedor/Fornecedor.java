package com.daviddev16.pessoa.fornecedor;

import com.daviddev16.pessoa.Pessoa;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder

@Entity
@DiscriminatorValue("FORNECEDOR")
public class Fornecedor extends Pessoa { }
