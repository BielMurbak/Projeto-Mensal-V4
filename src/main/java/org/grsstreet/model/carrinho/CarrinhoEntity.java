package org.grsstreet.model.carrinho;

import org.grsstreet.model.user.ClienteEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho")
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "finalizado", nullable = false)
    private boolean finalizado;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemCarrinhoEntity> itens = new ArrayList<>();

    public CarrinhoEntity() {
        this.dataCriacao = LocalDateTime.now();
        this.finalizado = false;
    }

    public CarrinhoEntity(ClienteEntity cliente) {
        this();
        this.cliente = cliente;
    }

    // Getters e setters

    public Long getId() { return id; }

    public ClienteEntity getCliente() { return cliente; }

    public void setCliente(ClienteEntity cliente) { this.cliente = cliente; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }

    public boolean isFinalizado() { return finalizado; }

    public void setFinalizado(boolean finalizado) { this.finalizado = finalizado; }

    public List<ItemCarrinhoEntity> getItens() { return itens; }

    public void setItens(List<ItemCarrinhoEntity> itens) {
        this.itens = itens;
        for (ItemCarrinhoEntity item : itens) {
            item.setCarrinho(this);
        }
    }

    // MÉTODOS PARA GERENCIAR O RELACIONAMENTO BIDIRECIONAL

    public void adicionarItem(ItemCarrinhoEntity item) {
        itens.add(item);
        item.setCarrinho(this);
    }

    public void removerItem(ItemCarrinhoEntity item) {
        itens.remove(item);
        item.setCarrinho(null);
    }
}