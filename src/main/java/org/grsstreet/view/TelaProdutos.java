package org.grsstreet.view;

import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.repository.ProdutoRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaProdutos extends JFrame {

    public TelaProdutos() {
        setTitle("GR's Street - Produtos");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//        ProdutoRepository produtoRepository = new ProdutoRepository();
//        List<Produto> produtos = produtoRepository.buscarTodos();
//
//        String[] colunas = {"ID", "Nome", "Preço", "Descrição"};
//        DefaultTableModel model = new DefaultTableModel(colunas, 0);
//
//        for (Produto p : produtos) {
//            model.addRow(new Object[]{ p.getId(), p.getTipoProduto(), p.getNome(), p.getQuantidade(), p.getPreco() } );
//        }
//
//        JTable tabela = new JTable(model);
//        JScrollPane scroll = new JScrollPane(tabela);
//
//        add(scroll, BorderLayout.CENTER);
    }
}
