package org.grsstreet.view;

import org.grsstreet.model.carrinho.CarrinhoEntity;
import org.grsstreet.model.carrinho.ItemCarrinhoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.repository.CarrinhoRepository;
import org.grsstreet.repository.ItemCarrinhoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCarrinho extends JFrame {

    private ClienteEntity cliente;
    private CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
    private ItemCarrinhoRepository itemCarrinhoRepository = new ItemCarrinhoRepository();

    private JTextArea areaCarrinho;
    private JButton btnFinalizarCompra;
    private JButton btnVoltarProdutos;

    public TelaCarrinho(ClienteEntity cliente) {
        this.cliente = cliente;

        setTitle("Carrinho de Compras - GR's Street");
        setSize(1080, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Área texto para mostrar itens
        areaCarrinho = new JTextArea();
        areaCarrinho.setEditable(false);
        areaCarrinho.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(areaCarrinho);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botões
        btnFinalizarCompra = new JButton("Finalizar Compra");
        btnVoltarProdutos = new JButton("Voltar aos Produtos");

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.add(btnFinalizarCompra);
        painelBotoes.add(btnVoltarProdutos);

        // Layout principal
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        carregarCarrinho();

        // Ações dos botões
        btnVoltarProdutos.addActionListener(e -> {
            this.dispose();
            new TelaProdutos().setVisible(true);
        });

        btnFinalizarCompra.addActionListener(e -> {
            finalizarCompra();
        });

        setVisible(true);
    }

    private void carregarCarrinho() {
        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorClienteComItens(cliente);

        if (carrinho == null || carrinho.getItens() == null || carrinho.getItens().isEmpty()) {
            areaCarrinho.setText("Seu carrinho está vazio.");
            btnFinalizarCompra.setEnabled(false);
            return;
        }

        btnFinalizarCompra.setEnabled(true);

        List<ItemCarrinhoEntity> itens = carrinho.getItens();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-40s %-12s %-15s %-15s\n", "Produto", "Qtd", "Preço Unitário", "Subtotal"));
        sb.append("-----------------------------------------------------------------\n");

        double total = 0;
        for (ItemCarrinhoEntity item : itens) {
            String nome = item.getProduto().getNome();
            int qtd = item.getQuantidade();
            double preco = item.getProduto().getPreco();
            double subtotal = preco * qtd;
            total += subtotal;

            sb.append(String.format("%-40s %-12d R$ %-13.2f R$ %-13.2f\n", nome, qtd, preco, subtotal));
        }

        sb.append("\nTotal: R$ " + String.format("%.2f", total));

        areaCarrinho.setText(sb.toString());
    }

    private void finalizarCompra() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Confirma a finalização da compra?",
                "Finalizar Compra",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        CarrinhoEntity carrinho = carrinhoRepository.buscarCarrinhoAtivoPorCliente(cliente);

        if (carrinho == null) {
            JOptionPane.showMessageDialog(this, "Nenhum carrinho ativo encontrado.");
            return;
        }

        carrinho.setFinalizado(true);
        carrinhoRepository.atualizar(carrinho);

        JOptionPane.showMessageDialog(this, "Compra finalizada com sucesso!");

        this.dispose();
        new TelaProdutos().setVisible(true);
    }
}
