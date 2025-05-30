package com.generation.carona.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.generation.carona.model.Viagem;

public class ViagemService {

    public BigDecimal calcularTempoDeViagem(Viagem viagem, double velocidadeMediaKmH) {
        if (velocidadeMediaKmH <= 0) {
            throw new IllegalArgumentException("A velocidade média deve ser maior que zero.");
        }
        if (viagem.getDistancia() == null) {
            throw new IllegalArgumentException("Distância da viagem não pode ser nula.");
        }

        double tempo = viagem.getDistancia().doubleValue() / velocidadeMediaKmH;
        return BigDecimal.valueOf(tempo).setScale(2, RoundingMode.HALF_UP);
    }
}
