package org.grsstreet.view;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.service.CarrinhoService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Tela para exibir o carrinho de compras do cliente.
 * Mostra os itens adicionados, valores totais, descontos,
 * permite remover itens e finalizar a compra.
 */
public class TelaCarrinho extends JFrame {

    private ClienteEntity cliente;
    private CarrinhoService carrinhoService = new CarrinhoService();

    private JPanel painelItens;          // Painel onde os itens do carrinho são exibidos
    private JLabel labelTotal;           // Label que exibe o total da compra com desconto aplicado
    private JLabel labelDesconto;        // Label que exibe o valor do desconto aplicado
    private JButton btnFinalizarCompra;  // Botão para finalizar a compra
    private JButton btnVoltarProdutos;   // Botão para voltar para a tela de produtos

    /**
     * Construtor da tela do carrinho.
     *
     * @param cliente Cliente que possui o carrinho ativo.
     */
    public TelaCarrinho(ClienteEntity cliente) {
        this.cliente = cliente;

        setTitle("Carrinho de Compras - GR's Street");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuração do painel principal com layout BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel para listar os itens do carrinho (com scroll)
        painelItens = new JPanel();
        painelItens.setLayout(new BoxLayout(painelItens, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(painelItens);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Painel inferior que contém valores (total e desconto) e botões
        JPanel painelInferior = new JPanel(new BorderLayout());

        // Painel para exibir total e desconto lado a lado
        JPanel painelValores = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        labelTotal = new JLabel("Total: R$ 0.00");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 18));
        labelDesconto = new JLabel("Desconto: R$ 0.00");
        labelDesconto.setFont(new Font("Arial", Font.BOLD, 16));
        labelDesconto.setForeground(new Color(0, 128, 0)); // cor verde para desconto

        painelValores.add(labelTotal);
        painelValores.add(labelDesconto);

        painelInferior.add(painelValores, BorderLayout.WEST);

        // Painel com botões para voltar à tela de produtos e finalizar compra
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnVoltarProdutos = new JButton("Voltar aos Produtos");
        painelBotoes.add(btnVoltarProdutos);
        painelBotoes.add(btnFinalizarCompra);
        painelInferior.add(painelBotoes, BorderLayout.EAST);

        // Adiciona componentes principais ao painel principal
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);

        // Carrega os itens do carrinho e atualiza a interface
        carregarCarrinho();

        // Ação do botão para voltar à tela de produtos
        btnVoltarProdutos.addActionListener(e -> {
            this.dispose();
            new TelaProdutos().setVisible(true);
        });

        // Ação do botão para finalizar a compra
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

    /**
     * Método que carrega e exibe os itens do carrinho na interface,
     * atualiza os valores de total e desconto, e controla a ativação
     * do botão de finalizar compra.
     */
    private void carregarCarrinho() {
        painelItens.removeAll();

        // Obtém a lista de itens do carrinho do cliente
        List<ItemCarrinhoEntity> itens = carrinhoService.listarItensCarrinho(cliente);

        // Caso o carrinho esteja vazio, exibe mensagem apropriada
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

        // Habilita o botão de finalizar compra
        btnFinalizarCompra.setEnabled(true);

        double total = 0;

        // Para cada item do carrinho, cria um painel com suas informações
        for (ItemCarrinhoEntity item : itens) {
            JPanel painelItem = new JPanel(new BorderLayout(15, 15));
            painelItem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            painelItem.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
            painelItem.setBackground(Color.WHITE);

            // Exibe a imagem do produto com tamanho fixo 130x100
            String caminhoImagem = item.getProduto().getImagem();
            JLabel labelImagem;
            if (caminhoImagem != null && !caminhoImagem.isEmpty()) {
                ImageIcon iconOriginal = new ImageIcon(caminhoImagem);
                Image img = iconOriginal.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(img);
                labelImagem = new JLabel(icon);
            } else {
                // Caso não haja imagem, exibe texto "Sem imagem"
                labelImagem = new JLabel("Sem imagem");
                labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
                labelImagem.setPreferredSize(new Dimension(130, 100));
            }
            labelImagem.setPreferredSize(new Dimension(130, 100));
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);

            painelItem.add(labelImagem, BorderLayout.WEST);

            // Painel com informações do produto (nome, quantidade, preço, subtotal)
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
            painelInfo.add(Box.createVerticalStrut(6)); // espaçamento vertical
            painelInfo.add(qtdLabel);
            painelInfo.add(precoLabel);
            painelInfo.add(subtotalLabel);

            painelItem.add(painelInfo, BorderLayout.CENTER);

            // Botão para remover o item do carrinho
            JButton btnRemover = new JButton("Remover");
            btnRemover.setPreferredSize(new Dimension(120, 40));
            btnRemover.setFocusPainted(false);

            // Confirmação antes de remover e recarrega o carrinho após remoção
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

            // Painel para o botão remover, com alinhamento e tamanho definidos
            JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 50)); // alinhado à esquerda
            painelBotao.setPreferredSize(new Dimension(130, 90));
            painelBotao.setOpaque(false);

            painelBotao.add(btnRemover);

            painelItem.add(painelBotao, BorderLayout.EAST);

            // Adiciona o painel do item e um espaçamento vertical entre itens
            painelItens.add(painelItem);
            painelItens.add(Box.createVerticalStrut(10));

            // Soma o subtotal ao total geral
            total += subtotal;
        }

        // Obtém o carrinho atualizado para cálculo do desconto
        CarrinhoEntity carrinho = carrinhoService.buscarCarrinhoAtivoComItens(cliente);
        double desconto = carrinhoService.calcularDesconto(carrinho);
        double totalComDesconto = total - desconto;

        // Atualiza labels de total e desconto na interface
        labelTotal.setText(String.format("Total: R$ %.2f", totalComDesconto));
        labelDesconto.setText(String.format("Desconto: R$ %.2f", desconto));

        // Atualiza a interface gráfica
        revalidate();
        repaint();
    }
}
