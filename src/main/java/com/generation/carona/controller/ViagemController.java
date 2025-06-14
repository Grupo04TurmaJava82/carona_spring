package com.generation.carona.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.carona.model.Viagem;
import com.generation.carona.repository.ViagemRepository;
import com.generation.carona.service.ViagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViagemController {

    @Autowired
    private ViagemRepository viagemRepository;

    // Listar todas as viagens
    @GetMapping
    public ResponseEntity<List<Viagem>> getAll() {
        return ResponseEntity.ok(viagemRepository.findAll());
    }

    // Buscar viagem por ID
    @GetMapping("/{id}")
    public ResponseEntity<Viagem> getById(@PathVariable Long id) {
        return viagemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar nova viagem
    @PostMapping
    public ResponseEntity<Viagem> post(@Valid @RequestBody Viagem viagem) {
        try {
            Viagem novaViagem = viagemService.cadastrarViagemComTempo(viagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaViagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Atualizar viagem existente
    @PutMapping
    public ResponseEntity<Viagem> put(@Valid @RequestBody Viagem viagem) {
        if (!viagemRepository.existsById(viagem.getId()))
            return ResponseEntity.notFound().build();

        try {
            Viagem viagemAtualizada = viagemService.cadastrarViagemComTempo(viagem);
            return ResponseEntity.ok(viagemAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // Deletar viagem
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!viagemRepository.existsById(id))
            return ResponseEntity.notFound().build();

        viagemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    //Metodos especiais
    @Autowired
    private ViagemService viagemService;
 
    // Buscar viagens por local de partida
    @GetMapping("/partida/{partida}")
    public ResponseEntity<List<Viagem>> getByPartida(@PathVariable String partida) {
        return ResponseEntity.ok(viagemRepository.findByPartidaIgnoreCaseContaining(partida));
    }

    // Buscar viagens por destino
    @GetMapping("/destino/{destino}")
    public ResponseEntity<List<Viagem>> getByDestino(@PathVariable String destino) {
        return ResponseEntity.ok(viagemRepository.findByDestinoIgnoreCaseContaining(destino));
    }

    // Buscar viagens com distância maior que um valor
    @GetMapping("/distancia/maiorque/{valor}")
    public ResponseEntity<List<Viagem>> getByDistanciaMaiorQue(@PathVariable BigDecimal valor) {
        return ResponseEntity.ok(viagemRepository.findByDistanciaGreaterThan(valor));
    }

}
