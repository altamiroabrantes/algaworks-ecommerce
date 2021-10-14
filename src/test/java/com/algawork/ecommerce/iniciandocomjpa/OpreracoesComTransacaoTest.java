package com.algawork.ecommerce.iniciandocomjpa;

import org.junit.Test;

import com.algawork.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class OpreracoesComTransacaoTest extends EntityManagerTest {

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
