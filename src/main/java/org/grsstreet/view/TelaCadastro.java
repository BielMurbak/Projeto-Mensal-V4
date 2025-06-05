package org.grsstreet.view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {

    public TelaCadastro() {
        setTitle("GR's Street - Cadastro");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal que vai conter TUDO, dentro do scroll
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 300, 40, 300)); // margem

        // Título e subtítulo
        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Cadastrar", JLabel.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 32));
        subtitulo.setForeground(Color.DARK_GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(subtitulo);
        painelPrincipal.add(Box.createVerticalStrut(40));

        // Campos
        painelPrincipal.add(criarCampo("Nome:", new JTextField()));
        painelPrincipal.add(criarCampo("CPF:", new JTextField()));
        painelPrincipal.add(criarCampo("Data de Nascimento:", new JTextField()));
        painelPrincipal.add(criarCampo("CEP:", new JTextField()));
        painelPrincipal.add(criarCampo("Senha:", new JPasswordField()));
        painelPrincipal.add(criarCampo("Repetir Senha:", new JPasswordField()));

        // Botão cadastrar
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCadastrar.setPreferredSize(new Dimension(180, 50));
        btnCadastrar.setMaximumSize(new Dimension(180, 50));

        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(btnCadastrar);

        // Scroll pane envolvendo tudo
        JScrollPane scrollPane = new JScrollPane(painelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setContentPane(scrollPane);
    }

    private JPanel criarCampo(String labelText, JComponent campo) {
        JPanel painelCampo = new JPanel();
        painelCampo.setLayout(new BoxLayout(painelCampo, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        campo.setPreferredSize(new Dimension(400, 35));
        campo.setMaximumSize(new Dimension(Short.MAX_VALUE, 35));
        campo.setFont(new Font("Arial", Font.PLAIN, 18));
        painelCampo.add(label);
        painelCampo.add(Box.createVerticalStrut(5));
        painelCampo.add(campo);
        painelCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCampo.setMaximumSize(new Dimension(600, 80));
        painelCampo.add(Box.createVerticalStrut(15));
        return painelCampo;
    }
}
