package org.grsstreet.view;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("GR's Street - Login");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com bordas internas
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        setContentPane(painelPrincipal);

        // Título no topo
        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Painel central
        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 0));

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoEmail = new JTextField();
        campoEmail.setPreferredSize(new Dimension(800, 40));
        campoEmail.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(800, 40));
        campoSenha.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));

        painelCampos.add(lblEmail);
        painelCampos.add(Box.createVerticalStrut(10));
        painelCampos.add(campoEmail);
        painelCampos.add(Box.createVerticalStrut(30));
        painelCampos.add(lblSenha);
        painelCampos.add(Box.createVerticalStrut(10));
        painelCampos.add(campoSenha);

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // Painel inferior com botões
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setPreferredSize(new Dimension(180, 50));
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setPreferredSize(new Dimension(180, 50));
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));

        painelBotoes.add(btnEntrar);
        painelBotoes.add(btnCadastrar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Ação do botão Entrar
        btnEntrar.addActionListener(e -> {
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            if (email.equals("admin@email.com") && senha.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                // Aqui você pode abrir a próxima tela do sistema
            } else {
                JOptionPane.showMessageDialog(this, "Email ou senha incorretos!");
            }
        });
    }

}