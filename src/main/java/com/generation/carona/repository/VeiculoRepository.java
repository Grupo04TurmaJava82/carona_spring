package com.generation.carona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.carona.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	
	public List<Veiculo> findByPlacaContainingIgnoreCase(String placa);

}
