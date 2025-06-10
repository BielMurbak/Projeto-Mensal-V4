package org.grsstreet.model.product;

import org.grsstreet.model.enums.TipoProduto;
import javax.persistence.*;

@Entity
@Table (name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProduto tipo;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "quantidade" ,nullable = false)
    private int quantidade;

    @Column(name = "preco" ,nullable = false)
    private double preco;

    public ProdutoEntity(TipoProduto tipo, String nome,int quantidade, double preco) {

        this.tipo = tipo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;

    }

    public ProdutoEntity() {
        // Construtor vazio para o Hibernate
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
