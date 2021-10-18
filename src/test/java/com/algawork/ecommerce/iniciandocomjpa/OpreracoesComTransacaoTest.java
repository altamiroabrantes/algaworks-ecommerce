package com.algawork.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algawork.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OpreracoesComTransacaoTest extends EntityManagerTest {

	@Test
	public void atualizarObjeto() {
		Produto produto = new Produto();

		produto.setId(1);
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conhe�a o novo Kindle");
		produto.setPreco(new BigDecimal(599));

		entityManager.getTransaction().begin();
		entityManager.merge(produto); // faz o find automaticamente e depois executa o update.
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
		Assert.assertEquals("Kindle Paperwhite", produtoVerificacao.getNome());

	}

	@Test
	public void removerObjeto() {
		Produto produto = entityManager.find(Produto.class, 3);

		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();

		// entityManager.clear(); N�o � necess�rio na asser��o para opera��o de remo��o.

		Produto produtoVerificacao = entityManager.find(Produto.class, 3);
		Assert.assertNull(produtoVerificacao);

	}

	@Test
	public void inserirOPrimeiroObjeto() {
		Produto produto = new Produto();

		produto.setId(2);
		produto.setNome("C�mera Canon");
		produto.setDescricao("A melhor defini��o para suas fotos.");
		produto.setPreco(new BigDecimal(5000));

		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}

	@Test
	public void abrirEFecharATransacao() {

//		Produto produto = new Produto();

		entityManager.getTransaction().begin();

//		entityManager.persist(produto);
//		entityManager.merge(produto);
//		entityManager.remove(produto);

		entityManager.getTransaction().commit();
	}
}
