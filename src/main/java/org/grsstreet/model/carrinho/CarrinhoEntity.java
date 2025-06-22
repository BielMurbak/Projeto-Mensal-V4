package org.grsstreet.model.carrinho;

import org.grsstreet.model.user.ClienteEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinho") // Mapeia a entidade para a tabela "carrinho"
public class CarrinhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID gerado automaticamente
    private Long id;

    @ManyToOne // Muitos carrinhos para um cliente
    @JoinColumn(name = "cliente_id", nullable = false) // FK para cliente
    private ClienteEntity cliente;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao; // Data/hora de criação do carrinho

    @Column(name = "finalizado", nullable = false)
    private boolean finalizado; // Indica se o carrinho foi finalizado

    @OneToMany(
            mappedBy = "carrinho",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ItemCarrinhoEntity> itens = new ArrayList<>(); // Lista de itens do carrinho

    // Construtor padrão
    public CarrinhoEntity() {
        this.dataCriacao = LocalDateTime.now();
        this.finalizado = false;
    }

    // Construtor com cliente
    public CarrinhoEntity(ClienteEntity cliente) {
        this();
        this.cliente = cliente;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public ClienteEntity getCliente() { return cliente; }
    public void setCliente(ClienteEntity cliente) { this.cliente = cliente; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public boolean isFinalizado() { return finalizado; }
    public void setFinalizado(boolean finalizado) { this.finalizado = finalizado; }
    public List<ItemCarrinhoEntity> getItens() { return itens; }

    public void setItens(List<ItemCarrinhoEntity> itens) {
        this.itens = itens;
        // Atualiza a referência de carrinho nos itens
        for (ItemCarrinhoEntity item : itens) {
            item.setCarrinho(this);
        }
    }

    // Adiciona item ao carrinho e seta a referência reversa
    public void adicionarItem(ItemCarrinhoEntity item) {
        itens.add(item);
        item.setCarrinho(this);
    }

    // Remove item do carrinho e limpa a referência reversa
    public void removerItem(ItemCarrinhoEntity item) {
        itens.remove(item);
        item.setCarrinho(null);
    }
}
