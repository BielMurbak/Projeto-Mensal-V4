package org.grsstreet.model.carrinho;

import org.grsstreet.model.product.ProdutoEntity;

import javax.persistence.*;

@Entity
@Table(name = "item_carrinho") // Mapeia a entidade para a tabela "item_carrinho"
public class ItemCarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID gerado automaticamente
    private Long id;

    @ManyToOne // Muitos itens para um carrinho
    @JoinColumn(name = "carrinho_id", nullable = false) // FK para carrinho
    private CarrinhoEntity carrinho;

    @ManyToOne // Muitos itens para um produto
    @JoinColumn(name = "produto_id", nullable = false) // FK para produto
    private ProdutoEntity produto;

    @Column(nullable = false)
    private int quantidade; // Quantidade do produto no carrinho

    // Construtor padrão
    public ItemCarrinhoEntity() {}

    // Construtor completo
    public ItemCarrinhoEntity(CarrinhoEntity carrinho, ProdutoEntity produto, int quantidade) {
        this.carrinho = carrinho;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public CarrinhoEntity getCarrinho() { return carrinho; }

    public void setCarrinho(CarrinhoEntity carrinho) { this.carrinho = carrinho; }

    public ProdutoEntity getProduto() { return produto; }

    public void setProduto(ProdutoEntity produto) { this.produto = produto; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
