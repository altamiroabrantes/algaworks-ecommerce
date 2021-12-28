package com.algaworks.ecommerce.mapeamentoavancado;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;

public class HerancaTest extends EntityManagerTest{
	
	@Test
	public void salvarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Carlos Finotti");
				
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertNotNull(clienteVerificacao.getId());
	}
}
