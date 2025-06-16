package org.grsstreet.view;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.service.CarrinhoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCarrinho extends JFrame {

    private ClienteEntity cliente;
    private CarrinhoService carrinhoService = new CarrinhoService();

    private JPanel painelItens;
    private JLabel labelTotal;
    private JLabel labelDesconto; // Label de desconto fora do painelItens, pro lado do total
    private JButton btnFinalizarCompra;
    private JButton btnVoltarProdutos;

    public TelaCarrinho(ClienteEntity cliente) {
        this.cliente = cliente;

        setTitle("Carrinho de Compras - GR's Street");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelItens = new JPanel();
        painelItens.setLayout(new BoxLayout(painelItens, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(painelItens);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel painelInferior = new JPanel(new BorderLayout());

        // Painel para total + desconto juntos
        JPanel painelValores = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        labelTotal = new JLabel("Total: R$ 0.00");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18));
        labelDesconto = new JLabel("Desconto: R$ 0.00");
        labelDesconto.setFont(new Font("Arial", Font.BOLD, 16));
        labelDesconto.setForeground(new Color(0, 128, 0));

        painelValores.add(labelTotal);
        painelValores.add(labelDesconto);

        painelInferior.add(painelValores, BorderLayout.WEST);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnVoltarProdutos = new JButton("Voltar aos Produtos");
        painelBotoes.add(btnVoltarProdutos);
        painelBotoes.add(btnFinalizarCompra);
        painelInferior.add(painelBotoes, BorderLayout.EAST);

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);

        carregarCarrinho();

        btnVoltarProdutos.addActionListener(e -> {
            this.dispose();
            new TelaProdutos().setVisible(true);
        });

        btnFinalizarCompra.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Confirma a finalização da compra?",
                    "Finalizar Compra",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                CarrinhoEntity carrinho = carrinhoService.buscarCarrinhoAtivoComItens(cliente);
                double valorCarrinho = carrinhoService.calcularTotalCarrinho(carrinho);
                double desconto = carrinhoService.calcularDesconto(carrinho);
                double valorComDesconto = valorCarrinho - desconto;

                this.dispose();
                new TelaEnvio(cliente, valorComDesconto).setVisible(true);
            }
        });

        setVisible(true);
    }

    private void carregarCarrinho() {
        painelItens.removeAll();

        List<ItemCarrinhoEntity> itens = carrinhoService.listarItensCarrinho(cliente);

        if (itens == null || itens.isEmpty()) {
            JLabel vazio = new JLabel("Seu carrinho está vazio.");
            vazio.setFont(new Font("Arial", Font.PLAIN, 18));
            vazio.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelItens.add(vazio);
            labelTotal.setText("Total: R$ 0.00");
            labelDesconto.setText("Desconto: R$ 0.00");
            btnFinalizarCompra.setEnabled(false);
            revalidate();
            repaint();
            return;
        }

        btnFinalizarCompra.setEnabled(true);

        double total = 0;

        for (ItemCarrinhoEntity item : itens) {
            JPanel painelItem = new JPanel(new BorderLayout(15, 15));
            painelItem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            painelItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
            painelItem.setBackground(Color.WHITE);

            // Imagem do produto - 130x100
            String caminhoImagem = item.getProduto().getImagem();
            JLabel labelImagem;
            if (caminhoImagem != null && !caminhoImagem.isEmpty()) {
                ImageIcon iconOriginal = new ImageIcon(caminhoImagem);
                Image img = iconOriginal.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                labelImagem = new JLabel(icon);
            } else {
                labelImagem = new JLabel("Sem imagem");
                labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
                labelImagem.setPreferredSize(new Dimension(130, 100));
            }
            labelImagem.setPreferredSize(new Dimension(130, 100));
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);

            painelItem.add(labelImagem, BorderLayout.WEST);

            // Painel info com padding
            JPanel painelInfo = new JPanel();
            painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
            painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            painelInfo.setBackground(Color.WHITE);

            JLabel nomeLabel = new JLabel(item.getProduto().getNome());
            nomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel qtdLabel = new JLabel("Quantidade: " + item.getQuantidade());
            qtdLabel.setFont(new Font("Arial", Font.PLAIN, 15));

            JLabel precoLabel = new JLabel(String.format("Preço unitário: R$ %.2f", item.getProduto().getPreco()));
            precoLabel.setFont(new Font("Arial", Font.PLAIN, 15));

            double subtotal = item.getQuantidade() * item.getProduto().getPreco();
            JLabel subtotalLabel = new JLabel(String.format("Subtotal: R$ %.2f", subtotal));
            subtotalLabel.setFont(new Font("Arial", Font.PLAIN, 15));

            painelInfo.add(nomeLabel);
            painelInfo.add(Box.createVerticalStrut(6));
            painelInfo.add(qtdLabel);
            painelInfo.add(precoLabel);
            painelInfo.add(subtotalLabel);

            painelItem.add(painelInfo, BorderLayout.CENTER);

            JButton btnRemover = new JButton("Remover");
            btnRemover.setPreferredSize(new Dimension(120, 40));
            btnRemover.setFocusPainted(false);

            btnRemover.addActionListener(e -> {
                int resposta = JOptionPane.showConfirmDialog(this,
                        "Deseja remover este item do carrinho?",
                        "Remover Item",
                        JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {
                    carrinhoService.removerDoCarrinho(cliente, item.getProduto());
                    carregarCarrinho();
                }
            });

            JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 50)); // alinhado à esquerda
            painelBotao.setPreferredSize(new Dimension(130, 90));
            painelBotao.setOpaque(false);

            painelBotao.add(btnRemover);

            painelItem.add(painelBotao, BorderLayout.EAST);

            painelItens.add(painelItem);
            painelItens.add(Box.createVerticalStrut(10));

            total += subtotal;
        }

        CarrinhoEntity carrinho = carrinhoService.buscarCarrinhoAtivoComItens(cliente);
        double desconto = carrinhoService.calcularDesconto(carrinho);
        double totalComDesconto = total - desconto;

        labelTotal.setText(String.format("Total: R$ %.2f", totalComDesconto));
        labelDesconto.setText(String.format("Desconto: R$ %.2f", desconto));

        revalidate();
        repaint();
    }
}
