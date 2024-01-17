package com.daviddev16.atendimento.repository;

import com.daviddev16.atendimento.entidade.TipoAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento, Integer> { }
