package com.generation.carona.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.carona.model.Veiculo;
import com.generation.carona.model.Viagem;
import com.generation.carona.repository.VeiculoRepository;
import com.generation.carona.repository.ViagemRepository;

@Service
public class ViagemService {
	
    @Autowired
    private ViagemRepository viagemRepository;
    
    @Autowired
	private VeiculoRepository veiculoRepository;
	
    public BigDecimal calcularTempoDeViagem(Viagem viagem) {
        Long veiculoId = viagem.getVeiculo().getId();
        Optional<Veiculo> veiculo = veiculoRepository.findById(veiculoId);
        if (veiculo.get() == null || veiculo.get().getVelocidadeMedia() == null ||
        		veiculo.get().getVelocidadeMedia().doubleValue() <= 0) {
            throw new IllegalArgumentException("A velocidade média do veículo deve ser maior que zero.");
        }

        if (viagem.getDistancia() == null) {
            throw new IllegalArgumentException("Distância da viagem não pode ser nula.");
        }

        double tempo = viagem.getDistancia().doubleValue() / veiculo.get().getVelocidadeMedia().doubleValue();
        return BigDecimal.valueOf(tempo).setScale(2, RoundingMode.HALF_UP);
    }
    
    public Viagem cadastrarViagemComTempo(Viagem viagem) {
        BigDecimal tempoEstimado = calcularTempoDeViagem(viagem);
        viagem.setTempoDeViagem(tempoEstimado);
        return viagemRepository.save(viagem);
    }
}
