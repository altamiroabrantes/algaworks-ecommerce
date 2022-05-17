package com.algaworks.ecommerce.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.algaworks.ecommerce.listener.GenericoListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners({GenericoListener.class})
@Entity
@Table(name = "produto", uniqueConstraints = { @UniqueConstraint(name= "unq_nome", columnNames = {"nome"}) },
						 indexes = { @Index(name = "idx_nome", columnList = "nome")})
public class Produto extends EntidadeBaseInteger {
	
	@Column(name= "data_criacao", updatable = false) 
	private LocalDateTime dataCriacao;
	
	@Column(name= "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;
	
	@Column(name = "nome", length = 100, nullable = false)  //nome varchar 100 not null
	private String nome;
	
	@Column(columnDefinition = "varchar(275) not null default 'descricao'")  //nome varchar 275 not null
	private String descricao;
	
	@Column(precision = 10, scale = 2)   // preco decimal (10,2)
	private BigDecimal preco;
	
	@Lob
	private byte[] foto;
	
	@ManyToMany
	@JoinTable(name = "produto_categoria", 
		joinColumns = @JoinColumn(name = "produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias;
	
	@OneToOne(mappedBy = "produto")
	private Estoque estoque;
	
	@ElementCollection
	@CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"))
	@Column(name = "tag")
	private List<String> tags;
	
	@ElementCollection
	@CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id"))
	private List<Atributo> atributos;
}
