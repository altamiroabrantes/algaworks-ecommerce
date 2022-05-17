package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class })
@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	@Column(name= "data_criacao", updatable = false) 
	private LocalDateTime dataCriacao;
	
	@Column(name= "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;
	
	@Column(name= "data_conclusao")
	private LocalDateTime dataConclusao;
	
	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscal;
	
	@Column(precision = 19, scale = 2, nullable = false)
	private BigDecimal total;
	
	@Column(length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;
	
	@Embedded
	private EnderecoEntregaPedido enderecoEntrega;
	
	public boolean isPago() {
		return StatusPedido.PAGO.equals(status);
	}
	
	//@PrePersist
	//@PreUpdate
	public void calcularTotal() {
		if(itens != null) {
			total = itens.stream().map(ItemPedido::getPrecoProduto)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
	}
	
	@PrePersist
	public void aoPersistir() {
		dataCriacao = LocalDateTime.now();
		calcularTotal();
	}
	
	@PreUpdate
	public void aoAtualizar() {
		dataUltimaAtualizacao = LocalDateTime.now();
		calcularTotal();
	}
	
}
