package org.grsstreet.view;

import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.service.CarrinhoService;
import org.grsstreet.service.PagamentoService;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Tela para seleção e finalização do pagamento.
 * Permite ao usuário escolher a forma de pagamento (PIX, débito ou crédito),
 * selecionar parcelas para cartão de crédito e exibe o valor final com descontos ou juros.
 */
public class TelaPagamento extends JFrame {

    private ClienteEntity cliente;   // Cliente realizando o pagamento
    private String tipoEnvio;        // Tipo de envio selecionado
    private double valorFrete;       // Valor do frete
    private double valorBase;        // Valor base dos produtos no carrinho

    private JRadioButton rbPix, rbDebito, rbCredito;    // Opções de pagamento
    private JComboBox<String> comboParcelas;            // Seleção de parcelas para cartão de crédito
    private JLabel lblValorFinal, lblResumo, lblTitulo; // Labels para exibir informações
    private ButtonGroup grupoPagamento;                  // Grupo de botões de opção para pagamento
    private JButton btnFinalizar, btnVoltar;             // Botões para finalizar pagamento ou voltar

    private PagamentoService pagamentoService;           // Serviço que calcula valores com desconto e juros

    /**
     * Construtor que inicializa a tela de pagamento com os dados do cliente, envio e valores.
     * @param cliente Cliente que realizará o pagamento
     * @param tipoEnvio Tipo de envio selecionado
     * @param valorFrete Valor do frete
     * @param valorBase Valor total dos produtos no carrinho
     */
    public TelaPagamento(ClienteEntity cliente, String tipoEnvio, double valorFrete, double valorBase) {
        this.cliente = cliente;
        this.tipoEnvio = tipoEnvio;
        this.valorFrete = valorFrete;
        this.valorBase = valorBase;

        // Inicializa o serviço de pagamento com valores base e frete
        pagamentoService = new PagamentoService(valorBase, valorFrete);

        // Configurações básicas da janela
        setTitle("Pagamento - GR's Street");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ------ TÍTULO SUPERIOR ------
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(new Color(54, 54, 54)); // fundo cinza escuro
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

        // Exibe o resumo do valor total (produtos + frete)
        lblResumo = new JLabel(String.format("Total (carrinho + frete): R$ %.2f", valorBase + valorFrete));
        lblResumo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblResumo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentral.add(lblResumo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Opções de pagamento: PIX, débito e crédito
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

        // Combo para selecionar parcelas (visível só para cartão de crédito)
        comboParcelas = new JComboBox<>();
        comboParcelas.setVisible(false);
        comboParcelas.setMaximumSize(new Dimension(200, 30));
        comboParcelas.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentral.add(comboParcelas);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Label que mostra o valor final a pagar
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

        // Listeners para atualizar a interface e valores com base na seleção do usuário

        // PIX selecionado: aplica desconto e oculta combo de parcelas
        rbPix.addActionListener(e -> {
            comboParcelas.setVisible(false);
            atualizarValorFinal(1);
        });

        // Débito selecionado: aplica desconto e oculta combo de parcelas
        rbDebito.addActionListener(e -> {
            comboParcelas.setVisible(false);
            atualizarValorFinal(1);
        });

        // Crédito selecionado: mostra combo para selecionar parcelas e atualiza valor com juros
        rbCredito.addActionListener(e -> {
            comboParcelas.setVisible(true);
            preencherParcelas();
            atualizarValorFinal(comboParcelas.getSelectedIndex() + 1);
        });

        // Atualiza valor quando muda o número de parcelas no combo
        comboParcelas.addActionListener(e -> {
            if (rbCredito.isSelected()) {
                atualizarValorFinal(comboParcelas.getSelectedIndex() + 1);
            }
        });

        // Botão voltar: fecha esta tela e abre menu principal
        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaMenuPrincipal().setVisible(true);
        });

        // Botão finalizar: tenta finalizar compra, mostra mensagem de erro em caso de falha
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

        // Seleciona PIX como padrão ao abrir e atualiza valor final
        rbPix.setSelected(true);
        atualizarValorFinal(1);

        setVisible(true);
    }

    /**
     * Preenche o comboBox de parcelas com opções até 12x.
     * 1x = à vista, 2-3x sem juros, 4-12x com juros.
     */
    private void preencherParcelas() {
        comboParcelas.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            if (i == 1) comboParcelas.addItem("À vista");
            else if (i <= 3) comboParcelas.addItem(i + "x sem juros");
            else comboParcelas.addItem(i + "x com juros");
        }
        comboParcelas.setSelectedIndex(0);
    }

    /**
     * Atualiza o label de valor final exibindo o valor com desconto ou juros,
     * conforme a forma de pagamento e quantidade de parcelas selecionada.
     * @param parcelas número de parcelas (usado apenas para cartão de crédito)
     */
    private void atualizarValorFinal(int parcelas) {
        if (rbPix.isSelected() || rbDebito.isSelected()) {
            double desconto = -0.05; // 5% de desconto
            double valorFinal = pagamentoService.calcularValorComDesconto(desconto);
            lblValorFinal.setText(formatarValorFinal(valorFinal));
        } else if (rbCredito.isSelected()) {
            double valorFinal = pagamentoService.calcularValorComJuros(parcelas);
            lblValorFinal.setText(formatarValorFinalComParcelas(valorFinal, parcelas));
        }
    }

    /**
     * Formata o valor final para exibição simples (sem parcelas).
     * @param valor valor a ser formatado
     * @return string formatada "Valor final: R$ XX,XX"
     */
    private String formatarValorFinal(double valor) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return "Valor final: R$ " + df.format(valor);
    }

    /**
     * Formata o valor final para exibição com parcelas.
     * Exemplo: "Valor final: R$ XXX,XX (3x de R$ XX,XX)"
     * @param valorFinal valor total a pagar
     * @param parcelas número de parcelas
     * @return string formatada com valor e parcelas
     */
    private String formatarValorFinalComParcelas(double valorFinal, int parcelas) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return String.format("Valor final: R$ %s (%dx de R$ %s)",
                df.format(valorFinal),
                parcelas,
                df.format(valorFinal / parcelas));
    }
}
