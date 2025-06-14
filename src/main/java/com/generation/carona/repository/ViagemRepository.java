package com.generation.carona.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.carona.model.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    // Buscar por local de partida (ignorando maiúsculas/minúsculas)
    List<Viagem> findByPartidaIgnoreCaseContaining(String partida);

    // Buscar por local de destino
    List<Viagem> findByDestinoIgnoreCaseContaining(String destino);

    // Buscar viagens com distância maior que o valor informado
    List<Viagem> findByDistanciaGreaterThan(BigDecimal distancia);
}