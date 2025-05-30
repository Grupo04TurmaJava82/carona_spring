package com.generation.carona.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank(message = "O atributo modelo é obrigatório!")
    @Size(min = 5, max = 100, message = "O atributo modelo deve ter no mínimo 5 e no máximo 100 caracteres.")
    private String modelo;

    @Column(length = 20)
    @NotBlank(message = "O atributo placa é obrigatório!")
    private String placa;

    @Min(5)
    @Max(10)
    private int ano;

    @Column(length = 100)
    @Pattern(regexp = "^(?!\\d+$).+", message = "O texto não pode ser apenas numérico")
    private String cor;

    private BigDecimal velocidadeMedia;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public BigDecimal getVelocidadeMedia() {return velocidadeMedia;}
    public void setVelocidadeMedia(BigDecimal velocidadeMedia) { this.velocidadeMedia = velocidadeMedia;}
}