package com.algaworks.ecommerce.mapeamentoavancado;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class DetalhesColumnTest extends EntityManagerTest {

	@Test
	public void impedirInsercaoDaColunaAtualizacao() {

		Produto produto = new Produto();
		produto.setNome("Teclado para smartphone");
		produto.setDescricao("O mais confortável");
		produto.setPreco(BigDecimal.ONE);
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());

		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificado.getDataCriacao());
		Assert.assertNull(produtoVerificado.getDataUltimaAtualizacao());
	}

	@Test
	public void impedirInsercaoDaColunaCriacao() {

		Produto produto = entityManager.find(Produto.class, 1);
		produto.setPreco(BigDecimal.TEN);
		produto.setDataCriacao(LocalDateTime.now());
		produto.setDataUltimaAtualizacao(LocalDateTime.now());

		entityManager.getTransaction().begin();
		// entityManager.merge(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificado = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
				produtoVerificado.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));
		Assert.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
				produtoVerificado.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
	}
}
