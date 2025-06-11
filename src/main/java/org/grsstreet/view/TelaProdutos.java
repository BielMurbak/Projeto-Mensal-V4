package org.grsstreet.view;

import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.repository.ProdutoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaProdutos extends JFrame {

    public TelaProdutos() {
        setTitle("Produtos Disponíveis");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ProdutoRepository produtoRepo = new ProdutoRepository();
        List<ProdutoEntity> produtos = produtoRepo.listarTodos();

        // Painel principal onde vai o grid de produtos
        JPanel painelProdutos = new JPanel();
        painelProdutos.setLayout(new GridLayout(0, 3, 15, 15)); // 3 colunas, espaçamento entre

        // Margem interna no painel principal
        painelProdutos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        for (ProdutoEntity produto : produtos) {
            JPanel painelProduto = new JPanel(new BorderLayout());
            painelProduto.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            painelProduto.setBackground(Color.WHITE);

            // Imagem
            JLabel imagemLabel;
            try {
                ImageIcon icon = new ImageIcon(produto.getImagem());
                Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                imagemLabel = new JLabel(new ImageIcon(img));
                imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
            } catch (Exception e) {
                imagemLabel = new JLabel("Imagem não encontrada", SwingConstants.CENTER);
            }

            // Nome, preço e quantidade
            JLabel nomeLabel = new JLabel(produto.getNome(), SwingConstants.CENTER);
            nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JLabel precoLabel = new JLabel(String.format("R$ %.2f", produto.getPreco()), SwingConstants.CENTER);
            precoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            precoLabel.setForeground(new Color(0, 128, 0)); // verde escuro
            precoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            JLabel quantidadeLabel = new JLabel("Quantidade: " + produto.getQuantidade(), SwingConstants.CENTER);
            quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            quantidadeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            // Botão
            JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
            btnAdicionar.setPreferredSize(new Dimension(180, 30));
            btnAdicionar.setFocusPainted(false);

            // Painel para info e botão, com BoxLayout vertical
            JPanel painelInfo = new JPanel();
            painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
            painelInfo.add(nomeLabel);
            painelInfo.add(precoLabel);
            painelInfo.add(quantidadeLabel);
            painelInfo.add(btnAdicionar);
            painelInfo.setBackground(Color.WHITE);

            painelProduto.add(imagemLabel, BorderLayout.NORTH);
            painelProduto.add(painelInfo, BorderLayout.CENTER);

            painelProdutos.add(painelProduto);
        }

        // JScrollPane para rolar a lista de produtos
        JScrollPane scrollPane = new JScrollPane(painelProdutos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botão fixo "Ir ao Carrinho" na parte inferior
        JButton btnIrCarrinho = new JButton("Ir ao Carrinho");
        btnIrCarrinho.setPreferredSize(new Dimension(200, 40));
        btnIrCarrinho.setFocusPainted(false);

        // Painel inferior para o botão do carrinho
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        painelInferior.add(btnIrCarrinho);

        // Layout da janela: BorderLayout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaProdutos().setVisible(true);
        });
    }
}
