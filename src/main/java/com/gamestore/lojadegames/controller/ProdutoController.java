package com.gamestore.lojadegames.controller;

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

import com.gamestore.lojadegames.model.Produto;
import com.gamestore.lojadegames.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getAllByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/valor/{valor}")
	public ResponseEntity<List<Produto>> getAllByValor (@PathVariable BigDecimal valor) {
		return ResponseEntity.ok(repository.findByValor(valor));
	}
	
	@GetMapping("/valor/<{valor}")
	public ResponseEntity<List<Produto>> getAllByValorLessEqual (@PathVariable BigDecimal valor) {
		return ResponseEntity.ok(repository.findByValorLessThanEqual(valor));
	}
	
	@GetMapping("/valor/>{valor}")
	public ResponseEntity<List<Produto>> getAllByValorGreaterEqual(@PathVariable BigDecimal valor) {
		return ResponseEntity.ok(repository.findByValorGreaterThanEqual(valor));
	}
	
	
	@GetMapping("/valor/{minimo}<{maximo}")
	public ResponseEntity<List<Produto>> getAllByValorBetween(@PathVariable BigDecimal minimo, @PathVariable BigDecimal maximo) {
		return ResponseEntity.ok(repository.findByValorBetween(minimo, maximo));
	}

	@PostMapping
	public ResponseEntity<Produto> post(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produto) {
		return ResponseEntity.ok(repository.save(produto));
	}

	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
}
