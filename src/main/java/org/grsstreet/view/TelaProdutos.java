package org.grsstreet.view;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.repository.CarrinhoRepository;
import org.grsstreet.repository.ItemCarrinhoRepository;
import org.grsstreet.repository.ProdutoRepository;
import org.grsstreet.session.Sessao;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaProdutos extends JFrame {

    private ProdutoRepository produtoRepo = new ProdutoRepository();
    private CarrinhoRepository carrinhoRepo = new CarrinhoRepository();
    private ItemCarrinhoRepository itemCarrinhoRepo = new ItemCarrinhoRepository();

    public TelaProdutos() {

        setTitle("GR's Street - Produtos");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

            JLabel quantidadeLabel = new JLabel("Estoque: " + produto.getQuantidade(), SwingConstants.CENTER);
            quantidadeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            quantidadeLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

            // Botão "Adicionar ao Carrinho"
            JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
            btnAdicionar.setMaximumSize(new Dimension(180, 50));
            btnAdicionar.setFocusPainted(false);

            btnAdicionar.addActionListener(e -> {
                ClienteEntity clienteLogado = Sessao.getClienteLogado();
                if (clienteLogado == null) {
                    JOptionPane.showMessageDialog(this, "Você precisa estar logado para adicionar ao carrinho.");
                    return;
                }

                String qtdStr = JOptionPane.showInputDialog(this,
                        "Informe a quantidade para o produto: " + produto.getNome(),
                        "Quantidade", JOptionPane.PLAIN_MESSAGE);

                if (qtdStr == null) return; // Cancelou

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

                if (qtd > produto.getQuantidade()) {
                    JOptionPane.showMessageDialog(this, "Quantidade insuficiente em estoque.");
                    return;
                }

                adicionarAoCarrinho(clienteLogado, produto, qtd);
            });

            // Painel de informações com layout vertical
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

        JScrollPane scrollPane = new JScrollPane(painelProdutos);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botões inferiores
        JButton btnIrCarrinho = new JButton("Ir ao Carrinho");
        btnIrCarrinho.setFont(new Font("Arial", Font.BOLD, 18));
        btnIrCarrinho.setPreferredSize(new Dimension(200, 50));
        btnIrCarrinho.setFocusPainted(false);

        JButton btnVoltarMenu = new JButton("Voltar ao Menu");
        btnVoltarMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnVoltarMenu.setPreferredSize(new Dimension(200, 50));
        btnVoltarMenu.setFocusPainted(false);

        btnIrCarrinho.addActionListener(e -> {
            ClienteEntity clienteLogado = Sessao.getClienteLogado();
            if (clienteLogado == null) {
                JOptionPane.showMessageDialog(this, "Você precisa estar logado para acessar o carrinho.");
                return;
            }
            this.dispose();
            new TelaCarrinho(clienteLogado).setVisible(true);
        });

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

    private void adicionarAoCarrinho(ClienteEntity cliente, ProdutoEntity produto, int quantidade) {
        CarrinhoEntity carrinho = carrinhoRepo.buscarCarrinhoAtivoPorCliente(cliente);

        if (carrinho == null) {
            carrinho = new CarrinhoEntity();
            carrinho.setCliente(cliente);
            carrinho.setFinalizado(false);
            carrinhoRepo.salvar(carrinho);
        }

        // Verifica se o produto já está no carrinho para somar quantidades
        ItemCarrinhoEntity itemExistente = null;
        for (ItemCarrinhoEntity item : carrinho.getItens()) {
            if (item.getProduto().getId().equals(produto.getId())) {
                itemExistente = item;
                break;
            }
        }

        if (itemExistente != null) {
            int novaQtd = itemExistente.getQuantidade() + quantidade;
            if (novaQtd > produto.getQuantidade() + itemExistente.getQuantidade()) {
                JOptionPane.showMessageDialog(this, "Quantidade insuficiente em estoque para adicionar mais.");
                return;
            }
            itemExistente.setQuantidade(novaQtd);
            itemCarrinhoRepo.atualizar(itemExistente);
        } else {
            ItemCarrinhoEntity novoItem = new ItemCarrinhoEntity();
            novoItem.setCarrinho(carrinho);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(quantidade);
            itemCarrinhoRepo.salvar(novoItem);
        }

        // Atualiza estoque do produto (considerando a quantidade já no carrinho)
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoRepo.atualizar(produto);

        JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaProdutos().setVisible(true));
    }
}
