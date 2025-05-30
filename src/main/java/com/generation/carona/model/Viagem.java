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

	@JsonIgnoreProperties("viagem")
	private Usuario idMotorista;
	
	@ManyToOne
	@JsonIgnoreProperties("viagem")
	private Usuario idPassageiro;
	
	@ManyToOne
	@JsonIgnoreProperties("viagem")
	private Veiculo idVeiculo;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getIdMotorista() {
		return idMotorista;
	}

	public void setIdMotorista(Usuario idMotorista) {
		this.idMotorista = idMotorista;
	}

	public Usuario getIdPassageiro() {
		return idPassageiro;
	}

	public void setIdPassageiro(Usuario idPassageiro) {
		this.idPassageiro = idPassageiro;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public BigDecimal getTempoDeViagem() {
		return tempoDeViagem;
	}

	public void setTempoDeViagem(BigDecimal tempoDeViagem) {
		this.tempoDeViagem = tempoDeViagem;
	}
	
}
