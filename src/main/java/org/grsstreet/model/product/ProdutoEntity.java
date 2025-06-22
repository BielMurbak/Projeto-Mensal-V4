package org.grsstreet.model.product;

import org.grsstreet.model.enums.TipoProduto;

import javax.persistence.*;

/**
 * Entidade que representa um produto no sistema de e-commerce.
 * Cada produto possui tipo, nome, quantidade disponível, preço e imagem ilustrativa.
 */
@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do produto (chave primária)

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProduto tipo; // Categoria do produto (TENIS, BONE, etc.)

    @Column(name = "nome", nullable = false)
    private String nome; // Nome do produto

    @Column(name = "quantidade", nullable = false)
    private int quantidade; // Quantidade em estoque

    @Column(name = "preco", nullable = false)
    private double preco; // Preço unitário

    @Column(name = "imagem")
    private String imagem; // Caminho para a imagem do produto

    /**
     * Construtor padrão exigido pelo Hibernate.
     */
    public ProdutoEntity() {}

    /**
     * Construtor completo para criar um produto com todos os campos.
     */
    public ProdutoEntity(TipoProduto tipo, String nome, int quantidade, double preco, String imagem) {
        this.tipo = tipo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.imagem = imagem;
    }

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public TipoProduto getTipo() { return tipo; }

    public void setTipo(TipoProduto tipo) { this.tipo = tipo; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }

    public void setPreco(double preco) { this.preco = preco; }

    public String getImagem() { return imagem; }

    public void setImagem(String imagem) { this.imagem = imagem; }
}
