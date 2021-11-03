package com.algaworks.ecommerce.relacionamentos;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Pedido;

public class EagerELazyTest extends EntityManagerTest{
	
	@Test
	public void verificarRelacionamento() {
		Pedido pedido = entityManager.find(Pedido.class, 1);
		pedido.getItens();
	}
}
