package org.grsstreet.service;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.repository.CarrinhoRepository;
import org.grsstreet.repository.ItemCarrinhoRepository;
import org.grsstreet.repository.ProdutoRepository;

import java.util.List;

/**
 * Classe de serviço responsável por gerenciar produtos e a adição deles ao carrinho de compras.
 */
public class ProdutoService {

    private ProdutoRepository produtoRepo = new ProdutoRepository();
    private CarrinhoRepository carrinhoRepo = new CarrinhoRepository();
    private ItemCarrinhoRepository itemCarrinhoRepo = new ItemCarrinhoRepository();

    /**
     * Lista todos os produtos disponíveis no sistema.
     *
     * @return Lista de ProdutoEntity
     */
    public List<ProdutoEntity> listarProdutos() {
        return produtoRepo.listarTodos();
    }

    /**
     * Adiciona um produto ao carrinho do cliente.
     * Se não existir um carrinho ativo, cria um.
     * Se o item já estiver no carrinho, soma as quantidades.
     *
     * @param cliente    Cliente que está adicionando o produto
     * @param produto    Produto a ser adicionado
     * @param quantidade Quantidade desejada
     * @throws IllegalArgumentException se a quantidade total exceder o estoque disponível
     */
    public void adicionarAoCarrinho(ClienteEntity cliente, ProdutoEntity produto, int quantidade) {
        // Buscar carrinho ativo (com os itens)
        CarrinhoEntity carrinho = carrinhoRepo.buscarCarrinhoAtivoPorClienteComItens(cliente);

        // Se não existir carrinho ativo, cria um novo
        if (carrinho == null) {
            carrinho = new CarrinhoEntity();
            carrinho.setCliente(cliente);
            carrinho.setFinalizado(false);
            carrinhoRepo.salvar(carrinho);
        }

        // Verifica quantidade em estoque
        int estoqueDisponivel = produto.getQuantidade();

        // Busca item existente no carrinho para atualizar quantidade (se já existe)
        ItemCarrinhoEntity itemExistente = carrinho.getItens().stream()
                .filter(i -> i.getProduto().getId().equals(produto.getId()))
                .findFirst()
                .orElse(null);

        int qtdAtualNoCarrinho = itemExistente != null ? itemExistente.getQuantidade() : 0;
        int qtdTotalDesejada = qtdAtualNoCarrinho + quantidade;

        // Valida se há estoque suficiente
        if (qtdTotalDesejada > estoqueDisponivel) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
        }

        // Atualiza item existente ou salva novo
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
