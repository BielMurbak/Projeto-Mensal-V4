package org.grsstreet.view;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.user.ClienteEntity;

import javax.swing.*;
import java.awt.*;

public class TelaEnvio extends JFrame {

    private ClienteEntity cliente;
    private JLabel lblEndereco, lblTitulo;
    private JRadioButton rbRetirada, rbFreteNormal, rbFreteExpresso;
    private JButton btnContinuar, btnVoltar;

    private double valorCarrinhoComDesconto;

    public TelaEnvio(ClienteEntity cliente, double valorCarrinhoComDesconto) {
        this.cliente = cliente;
        this.valorCarrinhoComDesconto = valorCarrinhoComDesconto;

        setTitle("Escolha o Método de Envio - GR's Street");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- TÍTULO SUPERIOR ----------
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(new Color(54, 54, 54)); // cinza escuro
        painelTitulo.setPreferredSize(new Dimension(1080, 70));
        painelTitulo.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        lblTitulo = new JLabel("GR's Street - Escolha o Método de Envio");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        painelTitulo.add(lblTitulo);

        add(painelTitulo, BorderLayout.NORTH);

        // ---------- PAINEL CENTRAL ----------
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Endereço do cliente
        EnderecoEntity enderecoCliente = cliente.getEnderecoEntity();
        String endereco = enderecoCliente.getRua() + ", " +
                enderecoCliente.getBairro() + " - " +
                enderecoCliente.getMunicipio() + " - " +
                enderecoCliente.getEstado() + " | CEP: " +
                enderecoCliente.getCep();

        lblEndereco = new JLabel("<html><b>Endereço de entrega:</b><br>" + endereco + "</html>");
        lblEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEndereco.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblEndereco.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        painelCentral.add(lblEndereco);

        // Opções de envio (radio buttons)
        rbRetirada = new JRadioButton("Retirada na loja - R$ 0,00 (imediato)");
        rbFreteNormal = new JRadioButton("Frete Normal - R$ 15,00 (5 dias úteis)");
        rbFreteExpresso = new JRadioButton("Frete Expresso - R$ 30,00 (2 dias úteis)");
        rbFreteNormal.setSelected(true); // padrão

        rbRetirada.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rbFreteNormal.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rbFreteExpresso.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        ButtonGroup grupoEnvio = new ButtonGroup();
        grupoEnvio.add(rbRetirada);
        grupoEnvio.add(rbFreteNormal);
        grupoEnvio.add(rbFreteExpresso);

        // Painel para radio buttons com espaçamento
        JPanel painelRadios = new JPanel();
        painelRadios.setLayout(new BoxLayout(painelRadios, BoxLayout.Y_AXIS));
        painelRadios.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelRadios.add(rbRetirada);
        painelRadios.add(Box.createRigidArea(new Dimension(0, 10)));
        painelRadios.add(rbFreteNormal);
        painelRadios.add(Box.createRigidArea(new Dimension(0, 10)));
        painelRadios.add(rbFreteExpresso);

        painelCentral.add(painelRadios);

        add(painelCentral, BorderLayout.CENTER);

        // ---------- RODAPÉ COM BOTÕES ----------
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        painelBotoes.setPreferredSize(new Dimension(1080, 80));

        btnVoltar = new JButton("Voltar ao Menu");
        btnContinuar = new JButton("Continuar para Pagamento");

        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnContinuar.setFont(new Font("Segoe UI", Font.BOLD, 16));

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnContinuar);

        add(painelBotoes, BorderLayout.SOUTH);

        // Ações dos botões
        btnContinuar.addActionListener(e -> {
            double valorFrete = 0;
            String tipoEnvio = "";

            if (rbRetirada.isSelected()) {
                tipoEnvio = "Retirada";
                valorFrete = 0;
            } else if (rbFreteNormal.isSelected()) {
                tipoEnvio = "Frete Normal";
                valorFrete = 15;
            } else if (rbFreteExpresso.isSelected()) {
                tipoEnvio = "Frete Expresso";
                valorFrete = 30;
            }

            // Abre tela de pagamento e fecha esta
            dispose();
            new TelaPagamento(cliente, tipoEnvio, valorFrete, valorCarrinhoComDesconto).setVisible(true);
        });

        btnVoltar.addActionListener(e -> {
            dispose();
            new TelaMenuPrincipal().setVisible(true);
        });

        setVisible(true);
    }
}
