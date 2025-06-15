package org.grsstreet.view.adm.cliente;

import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import java.awt.*;

public class RemoverCliente extends JFrame{

    public RemoverCliente () {

        Color backgroundColor = new Color(30, 30, 30);
        Color buttonColor = new Color(45, 120, 200);
        Color TextColor = Color.WHITE;
        Color headerColor = new Color(20, 20, 20);

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
        titleLabel.setForeground(TextColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal branco
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));

        // Label e campo CPF
        JLabel labelCPF = new JLabel("CPF");
        labelCPF.setFont(new Font("Arial", Font.PLAIN, 22));
        labelCPF.setForeground(TextColor);
        labelCPF.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(labelCPF);

        JTextField campoCPF = new JTextField();
        campoCPF.setPreferredSize(new Dimension(300, 60));
        campoCPF.setMaximumSize(new Dimension(300, 60));
        campoCPF.setFont(new Font("Arial", Font.PLAIN, 20));
        campoCPF.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoCPF.setBackground(TextColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));
        panelAdm.add(campoCPF);

        // Botão Remover
        JButton btn = new JButton("Remover");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300,60));
        btn.setPreferredSize(new Dimension (300,60));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(buttonColor);
        btn.setForeground(TextColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(btn);

        JButton btnV = new JButton("Voltar");
        btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnV.setMaximumSize(new Dimension(300,60));
        btnV.setPreferredSize(new Dimension (300,60));
        btnV.setFont(new Font("Arial", Font.BOLD, 20));
        btnV.setBackground(buttonColor);
        btnV.setForeground(TextColor);
        btnV.setFocusPainted(false);
        btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(btnV);

        btnV.addActionListener(e ->{
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
           new  TelaAdmPrincipal ();
            sistemaAdm.dispose();
        });





        // Rodapé cinza escuro
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(footerPanel);

        // Ação do botão
        btn.addActionListener(e -> {
                    String cpf = campoCPF.getText().trim();

                    ClienteRepository clienteRepository = new ClienteRepository();
                    clienteRepository.deletarCpfCliente(cpf);
                    campoCPF.setText("");
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
