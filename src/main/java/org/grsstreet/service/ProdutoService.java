package org.grsstreet.service;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.repository.CarrinhoRepository;
import org.grsstreet.repository.ItemCarrinhoRepository;
import org.grsstreet.repository.ProdutoRepository;

import java.util.List;

public class ProdutoService {

    private ProdutoRepository produtoRepo = new ProdutoRepository();
    private CarrinhoRepository carrinhoRepo = new CarrinhoRepository();
    private ItemCarrinhoRepository itemCarrinhoRepo = new ItemCarrinhoRepository();

    public List<ProdutoEntity> listarProdutos() {
        return produtoRepo.listarTodos();
    }

    public void adicionarAoCarrinho(ClienteEntity cliente, ProdutoEntity produto, int quantidade) {
        // Buscar carrinho ativo com itens
        CarrinhoEntity carrinho = carrinhoRepo.buscarCarrinhoAtivoPorClienteComItens(cliente);

        if (carrinho == null) {
            carrinho = new CarrinhoEntity();
            carrinho.setCliente(cliente);
            carrinho.setFinalizado(false);
            carrinhoRepo.salvar(carrinho);
        }

        // Verifica estoque disponível
        int estoqueDisponivel = produto.getQuantidade();

        // Verifica se já tem o item no carrinho para somar quantidades
        ItemCarrinhoEntity itemExistente = carrinho.getItens().stream()
                .filter(i -> i.getProduto().getId().equals(produto.getId()))
                .findFirst()
                .orElse(null);

        int qtdAtualNoCarrinho = itemExistente != null ? itemExistente.getQuantidade() : 0;
        int qtdTotalDesejada = qtdAtualNoCarrinho + quantidade;

        if (qtdTotalDesejada > estoqueDisponivel) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
        }

        if (itemExistente != null) {
            itemExistente.setQuantidade(qtdTotalDesejada);
            itemCarrinhoRepo.atualizar(itemExistente);
        } else {
            ItemCarrinhoEntity novoItem = new ItemCarrinhoEntity();
            novoItem.setCarrinho(carrinho);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(quantidade);
            itemCarrinhoRepo.salvar(novoItem);
        }

    }
}