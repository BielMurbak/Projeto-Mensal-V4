package org.grsstreet.view.adm.cliente;

import org.grsstreet.repository.ClienteRepository;

import javax.swing.*;
import java.awt.*;

public class RemoverCliente {

    public RemoverCliente () {
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());

        // Cabeçalho
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Remover Cliente", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal branco
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(Color.WHITE);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));

        // Label e campo CPF
        JLabel labelCPF = new JLabel("CPF");
        labelCPF.setFont(new Font("Arial", Font.PLAIN, 22));
        labelCPF.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(labelCPF);

        JTextField campoCPF = new JTextField();
        campoCPF.setPreferredSize(new Dimension(300, 60));
        campoCPF.setMaximumSize(new Dimension(300, 60));
        campoCPF.setFont(new Font("Arial", Font.PLAIN, 20));
        campoCPF.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoCPF.setBackground(Color.WHITE);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));
        panelAdm.add(campoCPF);

        // Botão Remover
        JButton btn = new JButton("Remover");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300,60));
        btn.setPreferredSize(new Dimension (300,60));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(Color.GRAY);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(btn);



        // Rodapé cinza escuro
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(footerPanel);

        // Ação do botão
        btn.addActionListener(e -> {
            String cpf = campoCPF.getText().trim();

            try {
                ClienteRepository clienteRepository = new ClienteRepository();
                clienteRepository.deletarCpfCliente(cpf);
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                campoCPF.setText("");

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao remover cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Adiciona os painéis à janela
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        sistemaAdm.setVisible(true);
    }

    public static void main(String[] args) {
        new RemoverCliente();
    }
}
