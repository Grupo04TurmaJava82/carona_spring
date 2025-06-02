package com.generation.carona.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.carona.model.Veiculo;
import com.generation.carona.model.Viagem;
import com.generation.carona.repository.VeiculoRepository;
import com.generation.carona.repository.ViagemRepository;

@Service
public class VeiculoService {
	
    @Autowired
    private ViagemRepository viagemRepository;
    
    @Autowired
	private VeiculoRepository veiculoRepository;
    
    @Autowired
    private ViagemService viagemService;
    
	public Optional<Veiculo> cadastrarVeiculo(Veiculo veiculo){
		
		Optional<Veiculo> veiculoSalvo = Optional.ofNullable(veiculoRepository.save(veiculo));
		if(veiculoSalvo.get() != null)
			atualizarTempoDeViagem(veiculoSalvo);
		return veiculoSalvo;
		
	}
	
	public Optional<Veiculo> atualizarVeiculo(Veiculo veiculo) {

		if(veiculoRepository.findById(veiculo.getId()).isPresent()) {

			Optional<Veiculo> veiculoSalvo = Optional.ofNullable(veiculoRepository.save(veiculo));
			if(veiculoSalvo.get() != null)
				atualizarTempoDeViagem(veiculoSalvo);
			return veiculoSalvo;

		}

		return Optional.empty();
	}
    
    public void atualizarTempoDeViagem(Optional<Veiculo> veiculoSalvo) {
    	List<Viagem> viagens = viagemRepository.findAll();
    	for(Viagem viagem : viagens) {
    		viagemService.cadastrarViagemComTempo(viagem);
    	}
    }
}

