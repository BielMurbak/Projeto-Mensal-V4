package org.grsstreet.view;

import javax.swing.*;
import java.awt.*;

public class TelaMenuPrincipal extends JFrame {

    public TelaMenuPrincipal() {
        setTitle("GR's Street - Página Inicial");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        setContentPane(painelPrincipal);

        // Título
        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        painelPrincipal.add(titulo);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 60))); // Espaço abaixo do título

        // Criando botões
        String[] textos = {
                "Produtos",
                "Carrinho",
                "Suporte ao Cliente",
                "Segurança e Privacidade"
        };

        for (String texto : textos) {
            JButton botao = new JButton(texto);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(500, 70));
            botao.setFont(new Font("Arial", Font.BOLD, 24));
            painelPrincipal.add(botao);
            painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30))); // Espaço entre botões
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaMenuPrincipal().setVisible(true);
        });
    }
}
