package org.grsstreet.service;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.repository.CarrinhoRepository;
import org.grsstreet.repository.ItemCarrinhoRepository;
import org.grsstreet.repository.ProdutoRepository;

import javax.swing.*;
import java.util.List;

/**
 * Classe de serviço que gerencia as regras de negócio relacionadas ao carrinho de compras.
 */
public class CarrinhoService {

    private CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
    private ItemCarrinhoRepository itemCarrinhoRepository = new ItemCarrinhoRepository();
    private ProdutoRepository produtoRepository = new ProdutoRepository();

    /**
     * Lista os itens do carrinho ativo de um cliente.
     */
    public List<ItemCarrinhoEntity> listarItensCarrinho(ClienteEntity cliente) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);
        if (carrinho == null) return null;
        return carrinho.getItens();
    }

    /**
     * Busca o carrinho ativo de um cliente, já com os itens carregados.
     */
    public CarrinhoEntity buscarCarrinhoAtivoComItens(ClienteEntity cliente) {
        return carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);
    }

    /**
     * Finaliza a compra de um cliente: valida o estoque, atualiza a quantidade,
     * e marca o carrinho como finalizado.
     */
    public boolean finalizarCompra(ClienteEntity cliente) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);

        if (carrinho == null || carrinho.getItens() == null || carrinho.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum carrinho ativo com itens encontrado.");
            return false;
        }

        // Verifica se há estoque suficiente para cada item
        for (ItemCarrinhoEntity item : carrinho.getItens()) {
            ProdutoEntity produto = item.getProduto();
            int novaQuantidade = produto.getQuantidade() - item.getQuantidade();

            if (novaQuantidade < 0) {
                JOptionPane.showMessageDialog(null, "Estoque insuficiente para o produto: " + produto.getNome());
                return false;
            }

            produto.setQuantidade(novaQuantidade);
            produtoRepository.atualizar(produto); // Atualiza o estoque
        }

        // Finaliza o carrinho
        carrinho.setFinalizado(true);
        carrinhoRepository.atualizar(carrinho);

        JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso! Obrigado pela preferência!");
        return true;
    }

    /**
     * Calcula o valor total (sem desconto) de todos os itens do carrinho.
     */
    public double calcularSubtotal(CarrinhoEntity carrinho) {
        return carrinho.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }

    /**
     * Soma todas as quantidades de produtos no carrinho.
     */
    public int calcularQuantidadeTotal(CarrinhoEntity carrinho) {
        return carrinho.getItens().stream()
                .mapToInt(ItemCarrinhoEntity::getQuantidade)
                .sum();
    }

    /**
     * Aplica descontos com base na quantidade total de itens:
     *  - 10% se ≥ 5 itens
     *  - 5% se ≥ 3 itens
     */
    public double calcularDesconto(CarrinhoEntity carrinho) {
        int totalItens = calcularQuantidadeTotal(carrinho);
        double subtotal = calcularSubtotal(carrinho);

        if (totalItens >= 5) {
            return subtotal * 0.10;
        } else if (totalItens >= 3) {
            return subtotal * 0.05;
        }

        return 0.0;
    }

    /**
     * Calcula o total do carrinho (sem aplicar descontos).
     */
    public double calcularTotalCarrinho(CarrinhoEntity carrinho) {
        if (carrinho == null || carrinho.getItens() == null) return 0;
        return carrinho.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }

    /**
     * Remove um produto específico do carrinho ativo de um cliente.
     */
    public void removerDoCarrinho(ClienteEntity cliente, ProdutoEntity produto) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);
        if (carrinho == null) {
            JOptionPane.showMessageDialog(null, "Carrinho ativo não encontrado para o cliente.");
            return;
        }

        List<ItemCarrinhoEntity> itens = carrinho.getItens();
        ItemCarrinhoEntity itemParaRemover = null;

        // Busca o item correspondente ao produto
        for (ItemCarrinhoEntity item : itens) {
            if (item.getProduto().getId().equals(produto.getId())) {
                itemParaRemover = item;
                break;
            }
        }

        if (itemParaRemover == null) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado no carrinho.");
            return;
        }

        try {
            itemCarrinhoRepository.deletar(itemParaRemover);
            itens.remove(itemParaRemover);
            carrinho.setItens(itens); // Atualiza a lista interna
            carrinhoRepository.atualizar(carrinho);
            JOptionPane.showMessageDialog(null, "Produto removido do carrinho com sucesso!");
        } catch (Exception e) {
            // Pode ser útil logar esse erro
        }
    }
}
