package com.algaworks.ecommerce.conhecendoentitymanager;

import java.math.BigDecimal;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;

public class ContextoDePersistenciaTest extends EntityManagerTest {

	@Test
	public void usarContextoPersistencia() {
		entityManager.getTransaction().begin();

		Produto produto = entityManager.find(Produto.class, 1);//Est� no contexto
		produto.setPreco(new BigDecimal(100.0));

		Produto produto2 = new Produto();
		produto2.setNome("Caneca para caf�");
		produto2.setPreco(new BigDecimal(10.0));
		produto2.setDescricao("Boa caneca para caf�");
		entityManager.persist(produto2); //Est� no contexto

		Produto produto3 = new Produto();
		produto3.setNome("Caneca para ch�");
		produto3.setPreco(new BigDecimal(10.0));
		produto3.setDescricao("Boa caneca para ch�");
		produto3 = entityManager.merge(produto3); //Est� no contexto

		entityManager.flush();

		produto3.setDescricao("Alterar descri��o");

		entityManager.getTransaction().commit();
	}
}
