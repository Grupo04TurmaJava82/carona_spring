package com.generation.carona.controller;

import com.generation.carona.model.Veiculo;
import com.generation.carona.repository.VeiculoRepository;
import com.generation.carona.service.VeiculoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
@CrossOrigin (origins = "*", allowedHeaders = "*")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired VeiculoService veiculoService;

	@GetMapping
	public ResponseEntity<List<Veiculo>> getAll(){
		List<Veiculo> veiculos = veiculoRepository.findAll();
		if (veiculos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(veiculos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> getById(@PathVariable Long id){
		return veiculoRepository.findById(id)
			.map(ResponseEntity::ok)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado" ));
	}

	@GetMapping("/placa/{placa}")
	public ResponseEntity<List<Veiculo>> getByPlaca(@PathVariable String placa){
		List<Veiculo> veiculos = veiculoRepository.findByPlacaContainingIgnoreCase(placa);
		if (veiculos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(veiculos);
	}	

	@PostMapping
	public ResponseEntity<Veiculo> postVeiculo(@Valid @RequestBody Veiculo veiculo){
		return veiculoService.cadastrarVeiculo(veiculo)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping
	public ResponseEntity<Veiculo> putVeiculo(@Valid @RequestBody Veiculo veiculo) {
		return veiculoService.atualizarVeiculo(veiculo)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteVeiculo(@PathVariable Long id) {
		
		return veiculoRepository.findById(id)
				.map(resposta -> {
					veiculoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}