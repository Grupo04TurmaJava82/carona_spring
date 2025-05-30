package com.generation.carona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.carona.model.Viagem;
import org.springframework.stereotype.Repository;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
}