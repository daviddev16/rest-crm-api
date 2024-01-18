package com.daviddev16.tipoatendimento;

import com.daviddev16.tipoatendimento.TipoAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento, Integer> { }
