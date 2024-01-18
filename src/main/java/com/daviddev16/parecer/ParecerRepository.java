package com.daviddev16.parecer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParecerRepository extends JpaRepository<Parecer, Integer> { }
