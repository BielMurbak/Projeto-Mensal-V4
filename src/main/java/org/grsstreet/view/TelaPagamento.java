package org.grsstreet.view;

import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.service.CarrinhoService;
import org.grsstreet.service.PagamentoService;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class TelaPagamento extends JFrame {

    private ClienteEntity cliente;
    private String tipoEnvio;
    private double valorFrete;
    private double valorBase;

    private JRadioButton rbPix, rbDebito, rbCredito;
    private JComboBox<String> comboParcelas;
    private JLabel lblValorFinal, lblResumo, lblTitulo;
    private ButtonGroup grupoPagamento;
    private JButton btnFinalizar, btnVoltar;

    private PagamentoService pagamentoService;

    public TelaPagamento(ClienteEntity cliente, String tipoEnvio, double valorFrete, double valorBase) {
        this.cliente = cliente;
        this.tipoEnvio = tipoEnvio;
        this.valorFrete = valorFrete;
        this.valorBase = valorBase;

        pagamentoService = new PagamentoService(valorBase, valorFrete);

        setTitle("Pagamento - GR's Street");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ------ TÍTULO SUPERIOR ------
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(new Color(54, 54, 54)); // cinza escuro
        painelTitulo.setPreferredSize(new Dimension(1080, 70));
        painelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        lblTitulo = new JLabel("GR's Street - Pagamento");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        painelTitulo.add(lblTitulo);
        add(painelTitulo, BorderLayout.NORTH);

        // ------ PAINEL CENTRAL ------
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Resumo total
        lblResumo = new JLabel(String.format("Total (carrinho + frete): R$ %.2f", valorBase + valorFrete));
        lblResumo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblResumo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentral.add(lblResumo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Opções de pagamento
        rbPix = new JRadioButton("PIX (5% de desconto)");
        rbDebito = new JRadioButton("Débito (5% de desconto)");
        rbCredito = new JRadioButton("Cartão de Crédito");

        rbPix.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rbDebito.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rbCredito.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        grupoPagamento = new ButtonGroup();
        grupoPagamento.add(rbPix);
        grupoPagamento.add(rbDebito);
        grupoPagamento.add(rbCredito);

        JPanel painelPagamentos = new JPanel();
        painelPagamentos.setLayout(new BoxLayout(painelPagamentos, BoxLayout.Y_AXIS));
        painelPagamentos.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelPagamentos.add(rbPix);
        painelPagamentos.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPagamentos.add(rbDebito);
        painelPagamentos.add(Box.createRigidArea(new Dimension(0, 10)));
        painelPagamentos.add(rbCredito);

        painelCentral.add(painelPagamentos);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        // Combo parcelas
        comboParcelas = new JComboBox<>();
        comboParcelas.setVisible(false);
        comboParcelas.setMaximumSize(new Dimension(200, 30));
        comboParcelas.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentral.add(comboParcelas);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Valor final
        lblValorFinal = new JLabel("Valor final: R$ 0,00");
        lblValorFinal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblValorFinal.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentral.add(lblValorFinal);

        add(painelCentral, BorderLayout.CENTER);

        // ------ RODAPÉ COM BOTOES ------
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        painelBotoes.setPreferredSize(new Dimension(1080, 80));

        btnVoltar = new JButton("Voltar ao Menu");
        btnFinalizar = new JButton("Finalizar Pagamento");

        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 16));

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnFinalizar);

        add(painelBotoes, BorderLayout.SOUTH);

        // Listeners
        rbPix.addActionListener(e -> {
            comboParcelas.setVisible(false);
            atualizarValorFinal(1);
        });

        rbDebito.addActionListener(e -> {
            comboParcelas.setVisible(false);
            atualizarValorFinal(1);
        });

        rbCredito.addActionListener(e -> {
            comboParcelas.setVisible(true);
            preencherParcelas();
            atualizarValorFinal(comboParcelas.getSelectedIndex() + 1);
        });

        comboParcelas.addActionListener(e -> {
            if (rbCredito.isSelected()) {
                atualizarValorFinal(comboParcelas.getSelectedIndex() + 1);
            }
        });

        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaMenuPrincipal().setVisible(true);
        });

        btnFinalizar.addActionListener(e -> {
            CarrinhoService carrinhoService = new CarrinhoService();
            boolean sucesso = carrinhoService.finalizarCompra(cliente);
            if (sucesso) {
                dispose();
                new TelaMenuPrincipal().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao finalizar a compra.");
            }
        });

        // Padrão PIX selecionado
        rbPix.setSelected(true);
        atualizarValorFinal(1);

        setVisible(true);
    }

    private void preencherParcelas() {
        comboParcelas.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            if (i == 1) comboParcelas.addItem("À vista");
            else if (i <= 3) comboParcelas.addItem(i + "x sem juros");
            else comboParcelas.addItem(i + "x com juros");
        }
        comboParcelas.setSelectedIndex(0);
    }

    private void atualizarValorFinal(int parcelas) {
        if (rbPix.isSelected() || rbDebito.isSelected()) {
            double desconto = -0.05; // 5% desconto
            double valorFinal = pagamentoService.calcularValorComDesconto(desconto);
            lblValorFinal.setText(formatarValorFinal(valorFinal));
        } else if (rbCredito.isSelected()) {
            double valorFinal = pagamentoService.calcularValorComJuros(parcelas);
            lblValorFinal.setText(formatarValorFinalComParcelas(valorFinal, parcelas));
        }
    }

    private String formatarValorFinal(double valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "Valor final: R$ " + df.format(valor);
    }

    private String formatarValorFinalComParcelas(double valorFinal, int parcelas) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("Valor final: R$ %s (%dx de R$ %s)",
                df.format(valorFinal),
                parcelas,
                df.format(valorFinal / parcelas));
    }
}
