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

public class CarrinhoService {

    private CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
    private ItemCarrinhoRepository itemCarrinhoRepository = new ItemCarrinhoRepository();
    private ProdutoRepository produtoRepository = new ProdutoRepository();

    public List<ItemCarrinhoEntity> listarItensCarrinho(ClienteEntity cliente) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);
        if (carrinho == null) return null;
        return carrinho.getItens();
    }

    public CarrinhoEntity buscarCarrinhoAtivoComItens(ClienteEntity cliente) {
        return carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);
    }

    public boolean finalizarCompra(ClienteEntity cliente) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);

        if (carrinho == null || carrinho.getItens() == null || carrinho.getItens().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum carrinho ativo com itens encontrado.");
            return false;
        }

        // Verifica e atualiza estoque dos produtos
        for (ItemCarrinhoEntity item : carrinho.getItens()) {
            ProdutoEntity produto = item.getProduto();
            int novaQuantidade = produto.getQuantidade() - item.getQuantidade();

            if (novaQuantidade < 0) {
                JOptionPane.showMessageDialog(null, "Estoque insuficiente para o produto: " + produto.getNome());
                return false; // Cancela a finalização
            }

            produto.setQuantidade(novaQuantidade);
            produtoRepository.atualizar(produto);  // Atualiza o produto no banco
        }

        // Marca carrinho como finalizado
        carrinho.setFinalizado(true);
        carrinhoRepository.atualizar(carrinho);

        JOptionPane.showMessageDialog(null, "Compra finalizada com sucesso! Obrigado pela preferência!");

        return true;
    }

    public double calcularSubtotal(CarrinhoEntity carrinho) {
        return carrinho.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }

    public int calcularQuantidadeTotal(CarrinhoEntity carrinho) {
        return carrinho.getItens().stream()
                .mapToInt(ItemCarrinhoEntity::getQuantidade)
                .sum();
    }

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

    public double calcularTotalCarrinho(CarrinhoEntity carrinho) {
        if (carrinho == null || carrinho.getItens() == null) return 0;
        return carrinho.getItens().stream()
                .mapToDouble(item -> item.getProduto().getPreco() * item.getQuantidade())
                .sum();
    }


    public void removerDoCarrinho(ClienteEntity cliente, ProdutoEntity produto) {
        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);
        if (carrinho == null) {
            JOptionPane.showMessageDialog(null, "Carrinho ativo não encontrado para o cliente.");
            return;
        }

        List<ItemCarrinhoEntity> itens = carrinho.getItens();
        ItemCarrinhoEntity itemParaRemover = null;

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
            carrinho.setItens(itens);
            carrinhoRepository.atualizar(carrinho);
            JOptionPane.showMessageDialog(null, "Produto removido do carrinho com sucesso!");
        } catch (Exception e) {
        }
    }
}
