package org.grsstreet.view;

import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.session.Sessao;
import org.grsstreet.service.ProdutoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * TelaProdutos representa a interface gráfica da lista de produtos disponíveis para compra.
 * Permite ao usuário visualizar produtos, adicionar quantidades ao carrinho, navegar ao carrinho e voltar ao menu principal.
 */
public class TelaProdutos extends JFrame {

    private ProdutoService produtoService = new ProdutoService();

    /**
     * Construtor que configura a interface da tela de produtos, listando todos os produtos disponíveis.
     * Configura botões, imagens, preços e quantidade em estoque.
     */
    public TelaProdutos() {

        setTitle("GR's Street - Produtos");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Obtém lista de produtos cadastrados
        List<ProdutoEntity> produtos = produtoService.listarProdutos();

        // Painel principal com BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout());

        // Título no topo da tela
        JLabel titulo = new JLabel("GR's Street", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Painel central que exibirá os produtos em grade 3 colunas
        JPanel painelProdutos = new JPanel();
        painelProdutos.setLayout(new GridLayout(0, 3, 15, 15));
        painelProdutos.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Para cada produto, cria um painel com imagem, nome, preço, estoque e botão para adicionar ao carrinho
        for (ProdutoEntity produto : produtos) {
            JPanel painelProduto = new JPanel(new BorderLayout());
            painelProduto.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            painelProduto.setBackground(Color.WHITE);

            JLabel imagemLabel;
            try {
                ImageIcon icon = new ImageIcon(produto.getImagem());
                Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
                imagemLabel = new JLabel(new ImageIcon(img));
                imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
            } catch (Exception e) {
                imagemLabel = new JLabel("Imagem não encontrada", SwingConstants.CENTER);
            }

            JLabel nomeLabel = new JLabel(produto.getNome(), SwingConstants.CENTER);
            nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nomeLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            JLabel precoLabel = new JLabel(String.format("R$ %.2f", produto.getPreco()), SwingConstants.CENTER);
            precoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            precoLabel.setForeground(new Color(0, 128, 0));
            precoLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            JLabel quantidadeLabel = new JLabel("Estoque: " + produto.getQuantidade(), SwingConstants.CENTER);
            quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            quantidadeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
            btnAdicionar.setMaximumSize(new Dimension(180, 50));
            btnAdicionar.setFocusPainted(false);

            // Listener para o botão adicionar ao carrinho
            btnAdicionar.addActionListener(e -> {
                ClienteEntity clienteLogado = Sessao.getClienteLogado();
                if (clienteLogado == null) {
                    JOptionPane.showMessageDialog(this, "Você precisa estar logado para adicionar ao carrinho.");
                    return;
                }

                String qtdStr = JOptionPane.showInputDialog(this,
                        "Informe a quantidade para o produto: " + produto.getNome(),
                        "Quantidade", JOptionPane.PLAIN_MESSAGE);

                if (qtdStr == null) return; // Usuário cancelou

                int qtd;
                try {
                    qtd = Integer.parseInt(qtdStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantidade inválida.");
                    return;
                }

                if (qtd <= 0) {
                    JOptionPane.showMessageDialog(this, "Quantidade deve ser maior que zero.");
                    return;
                }

                try {
                    produtoService.adicionarAoCarrinho(clienteLogado, produto, qtd);
                    JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho!");
                    this.dispose();
                    new TelaProdutos().setVisible(true); // Atualiza a tela para refletir novo estoque
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            });

            JPanel painelInfo = new JPanel();
            painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
            painelInfo.add(nomeLabel);
            painelInfo.add(precoLabel);
            painelInfo.add(quantidadeLabel);
            painelInfo.add(Box.createVerticalStrut(10));
            painelInfo.add(btnAdicionar);
            painelInfo.setBackground(Color.WHITE);

            painelProduto.add(imagemLabel, BorderLayout.NORTH);
            painelProduto.add(painelInfo, BorderLayout.CENTER);

            painelProdutos.add(painelProduto);
        }

        // Scroll para os produtos, permitindo rolagem vertical
        JScrollPane scrollPane = new JScrollPane(painelProdutos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botões inferiores para navegar ao carrinho ou ao menu principal
        JButton btnIrCarrinho = new JButton("Ir ao Carrinho");
        btnIrCarrinho.setFont(new Font("Arial", Font.BOLD, 18));
        btnIrCarrinho.setPreferredSize(new Dimension(200, 50));
        btnIrCarrinho.setFocusPainted(false);

        JButton btnVoltarMenu = new JButton("Voltar ao Menu");
        btnVoltarMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltarMenu.setPreferredSize(new Dimension(200, 50));
        btnVoltarMenu.setFocusPainted(false);

        // Navega para a tela do carrinho se usuário estiver logado
        btnIrCarrinho.addActionListener(e -> {
            ClienteEntity clienteLogado = Sessao.getClienteLogado();
            if (clienteLogado == null) {
                JOptionPane.showMessageDialog(this, "Você precisa estar logado para acessar o carrinho.");
                return;
            }
            this.dispose();
            new TelaCarrinho(clienteLogado).setVisible(true);
        });

        // Volta ao menu principal
        btnVoltarMenu.addActionListener(e -> {
            this.dispose();
            new TelaMenuPrincipal().setVisible(true);
        });

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelInferior.add(btnIrCarrinho);
        painelInferior.add(btnVoltarMenu);

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
    }
}
