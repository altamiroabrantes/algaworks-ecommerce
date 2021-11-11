package com.algaworks.ecommerce.conhecendoentitymanager;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Categoria;

public class EstadosECiclosDeVidaTest extends EntityManagerTest {
	
	@Test
	public void analisarEstados() {
		
		Categoria categoriaNovo = new Categoria(); // Transient
		Categoria categoriaGerenciadaMerge = entityManager.merge(categoriaNovo); // Manager
		
		Categoria categoriaGerenciada = entityManager.find(Categoria.class, 1); // Manager
		
		entityManager.remove(categoriaGerenciada); // Removed
		
		entityManager.persist(categoriaGerenciada); // Manager
		
		entityManager.detach(categoriaGerenciada); // Detached
	}

}
