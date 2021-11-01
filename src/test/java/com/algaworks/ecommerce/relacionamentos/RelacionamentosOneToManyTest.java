package com.algaworks.ecommerce.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.StatusPedido;

public class RelacionamentosOneToManyTest extends EntityManagerTest{
	
	@Test
	public void verificarRelacionamentoClientePedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setCliente(cliente);
		pedido.setTotal(BigDecimal.TEN);
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
		Assert.assertFalse(clienteVerificacao.getPedidos().isEmpty());
		
	}
	
	@Test
	public void verificarRelacionamentoPedidoItemPedido() {
		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);
		
		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setCliente(cliente);
		pedido.setTotal(BigDecimal.TEN);
		
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setPrecoProduto(produto.getPreco());
		itemPedido.setQuantidade(1);
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();
		
		entityManager.clear();
		
		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertFalse(pedidoVerificacao.getItensPedido().isEmpty());
		
		
	}
}
