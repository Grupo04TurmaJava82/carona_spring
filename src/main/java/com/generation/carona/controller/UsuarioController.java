package com.generation.carona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.carona.model.Usuario;
import com.generation.carona.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	@GetMapping
	public ResponseEntity <List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
    
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Usuario> getByUsuario(@PathVariable String nome) {
		return usuarioRepository.findByNome(nome)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}

	
	@PutMapping
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		if(!usuarioRepository.existsById(usuario.getId())) 
			return ResponseEntity.badRequest().build();
		
		if (usuarioRepository.existsById(usuario.getId())) {
			return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuario));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}