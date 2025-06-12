package org.grsstreet.view;

import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.repository.ProdutoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaProdutos extends JFrame {

    public TelaProdutos() {
        setTitle("GRs Street");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ProdutoRepository produtoRepo = new ProdutoRepository();
        List<ProdutoEntity> produtos = produtoRepo.listarTodos();

        // Painel principal com BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Título centralizado no topo
        JLabel titulo = new JLabel("GR's Street", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Painel de produtos (grid)
        JPanel painelProdutos = new JPanel();
        painelProdutos.setLayout(new GridLayout(0, 3, 15, 15));
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

            // Nome, preço e quantidade centralizados
            JLabel nomeLabel = new JLabel(produto.getNome(), SwingConstants.CENTER);
            nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JLabel precoLabel = new JLabel(String.format("R$ %.2f", produto.getPreco()), SwingConstants.CENTER);
            precoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            precoLabel.setForeground(new Color(0, 128, 0));
            precoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            JLabel quantidadeLabel = new JLabel("Quantidade: " + produto.getQuantidade(), SwingConstants.CENTER);
            quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            quantidadeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            // Botão "Adicionar ao Carrinho"
            JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
            btnAdicionar.setMaximumSize(new Dimension(180, 50));
            btnAdicionar.setFocusPainted(false);

            // Painel de informações com layout vertical
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

        JScrollPane scrollPane = new JScrollPane(painelProdutos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botão inferior "Ir ao Carrinho"
        JButton btnIrCarrinho = new JButton("Ir ao Carrinho");
        btnIrCarrinho.setFont(new Font("Arial", Font.BOLD, 18));
        btnIrCarrinho.setPreferredSize(new Dimension(200, 50));
        btnIrCarrinho.setFocusPainted(false);

        // Botão "Voltar ao Menu"
        JButton btnVoltarMenu = new JButton("Voltar ao Menu");
        btnVoltarMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltarMenu.setPreferredSize(new Dimension(200, 50));
        btnVoltarMenu.setFocusPainted(false);

        // Ação: Voltar ao menu principal
        btnVoltarMenu.addActionListener(e -> {
            dispose(); // Fecha esta janela
            new TelaMenuPrincipal().setVisible(true); // Abre o menu principal
        });

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelInferior.add(btnIrCarrinho);
        painelInferior.add(btnVoltarMenu);


        // Adiciona scroll e botão inferior ao painel principal
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaProdutos().setVisible(true));
    }
}
