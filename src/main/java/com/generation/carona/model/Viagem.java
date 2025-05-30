package com.generation.carona.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_viagens")
public class Viagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario idMotorista;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario idPassageiro;
	
	@NotNull(message = "O Atributo idVeiculo é Obrigatório!")
	private Long idVeiculo;
	
	@Column(length = 1000)
	@Size(min = 10, max = 1000, message = "O atributo partida deve ter no mínimo 10 e no máximo 1000 caracteres")
	private String partida;
	
	@Column(length = 1000)
	@Size(min = 10, max = 1000, message = "O atributo destino deve ter no mínimo 10 e no máximo 1000 caracteres")
	private String destino;
	
	@Positive
	@Column(precision = 7, scale = 2)
	private BigDecimal distancia;
	
	@Positive
	@Column(precision = 5, scale = 2)
	private BigDecimal tempoDeViagem;
}
