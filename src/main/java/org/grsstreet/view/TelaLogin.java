package org.grsstreet.view;

import org.grsstreet.repository.AdministradorRepository;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("GR's Street - Login");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        setContentPane(painelPrincipal);

        // Título e subtítulo no topo
        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Entrar", JLabel.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 32));
        subtitulo.setForeground(Color.DARK_GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new BoxLayout(painelTitulo, BoxLayout.Y_AXIS));
        painelTitulo.add(titulo);
        painelTitulo.add(Box.createVerticalStrut(30));
        painelTitulo.add(subtitulo);
        painelTitulo.add(Box.createVerticalStrut(50));

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Painel central com os campos Usuario e Senha
        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(800, 40));
        campoUsuario.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 18));

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(800, 40));
        campoSenha.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));

        painelCampos.add(lblUsuario);
        painelCampos.add(Box.createVerticalStrut(10));
        painelCampos.add(campoUsuario);
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
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            AdministradorRepository administradorRepository = new AdministradorRepository();
            boolean isAdm = administradorRepository.buscarPorSenha(senha);

            if (isAdm) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                new TelaAdm();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
            }
        });

        // Ação do botão Cadastrar
        btnCadastrar.addActionListener(e -> {
            dispose();
            new TelaCadastro().setVisible(true);
        });
    }
}
