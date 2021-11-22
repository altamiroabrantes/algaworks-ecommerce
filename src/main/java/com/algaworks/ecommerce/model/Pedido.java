package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	@Column(name= "data_criacao")
	private LocalDateTime dataCriacao;
	
	@Column(name= "data_ultima_atualizacao")
	private LocalDateTime dataUltimaAtualizacao;
	
	@Column(name= "data_conclusao")
	private LocalDateTime dataConclusao;
	
	@OneToOne(mappedBy = "pedido")
	private NotaFiscal notaFiscal;
	
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@OneToOne(mappedBy = "pedido")
	private PagamentoCartao pagamento;
	
	@Embedded
	private EnderecoEntregaPedido enderecoEntrega;
	
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
