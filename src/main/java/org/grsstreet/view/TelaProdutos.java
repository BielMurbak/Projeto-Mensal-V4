package org.grsstreet.view;

import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.repository.ProdutoRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaProdutos extends JFrame {

    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public TelaProdutos(List<ProdutoEntity> produtos) {
        setTitle("Produtos - Tênis Disponíveis");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"Nome", "Quantidade", "Preço"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProdutos = new JTable(modeloTabela);

        for (ProdutoEntity produto : produtos) {
            Object[] linha = {
                    produto.getNome(),
                    produto.getQuantidade(),
                    produto.getPreco()
            };
            modeloTabela.addRow(linha);
        }

        add(new JScrollPane(tabelaProdutos), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        ProdutoRepository produtoRepository = new ProdutoRepository();
        List<ProdutoEntity> tenis = produtoRepository.listarTenis();

        SwingUtilities.invokeLater(() -> {
            TelaProdutos tela = new TelaProdutos(tenis);
            tela.setVisible(true);
        });
    }
}
