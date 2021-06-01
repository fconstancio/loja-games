package com.gamestore.lojadegames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamestore.lojadegames.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Produto> findByValor(BigDecimal valor);
	
	public List<Produto> findByValorLessThanEqual(BigDecimal valor);
	
	public List<Produto> findByValorGreaterThanEqual(BigDecimal valor);
	
	public List<Produto> findByValorBetween(BigDecimal minimo, BigDecimal maximo);
	
}
