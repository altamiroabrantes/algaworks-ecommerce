package com.algawork.ecommerce.iniciandocomjpa;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.algawork.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OpreracoesComTransacaoTest extends EntityManagerTest {

	@Test
	public void mostarDiferencaPersistMerge() {
		Produto produtoPersist = new Produto();

		produtoPersist.setId(5);
		produtoPersist.setNome("Smartphone One Plus");
		produtoPersist.setDescricao("O processador mais r�pido.");
		produtoPersist.setPreco(new BigDecimal(2000));

		entityManager.getTransaction().begin();
		//Roda somente o insert
		entityManager.persist(produtoPersist); // faz o find, n�o encontra o objeto e realiza o insert
		//Roda o update
		produtoPersist.setNome("Smartphone Two Plus");
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacaoPersist = entityManager.find(Produto.class, produtoPersist.getId());
		Assert.assertNotNull(produtoVerificacaoPersist);
		
		
		
		Produto produtoMerge = new Produto();

		produtoMerge.setId(6);
		produtoMerge.setNome("Notebook Dell");
		produtoMerge.setDescricao("O melhor da categoria.");
		produtoMerge.setPreco(new BigDecimal(2000));

		entityManager.getTransaction().begin();
		//Rodou insert
		produtoMerge = entityManager.merge(produtoMerge); 
		//Rodou update
		produtoMerge.setNome("Notebook Dell 2");
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacaoMerge = entityManager.find(Produto.class, produtoMerge.getId());
		Assert.assertNotNull(produtoVerificacaoMerge);
		
	}

	
	@Test
	public void inserirObjetoComMerge() {
		Produto produto = new Produto();

		produto.setId(4);
		produto.setNome("Microfone Rode Videmic");
		produto.setDescricao("A melhor qualidade de som.");
		produto.setPreco(new BigDecimal(1000));

		entityManager.getTransaction().begin();
		entityManager.merge(produto); // faz o find, n�o encontra o objeto e realiza o insert
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertNotNull(produtoVerificacao);
	}

	@Test
	public void atualizarObjetoGerenciado() {
		Produto produto = entityManager.find(Produto.class, 1);

		entityManager.getTransaction().begin();
		produto.setNome("Kindle Paperwhite 2� Gera��o");
		entityManager.getTransaction().commit();

		entityManager.clear();

		Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
		Assert.assertEquals("Kindle Paperwhite 2� Gera��o", produtoVerificacao.getNome());

	}

	@Test
	public void atualizarObjeto() {
		Produto produto = new Produto();

		produto.setId(1);
		produto.setNome("Kindle Paperwhite");
		produto.setDescricao("Conhe�a o novo Kindle");
		produto.setPreco(new BigDecimal(599));

		entityManager.getTransaction().begin();
		entityManager.merge(produto); // faz o select automaticamente e depois executa o update.
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
