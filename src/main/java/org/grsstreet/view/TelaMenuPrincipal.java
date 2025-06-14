package org.grsstreet.view;

import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.session.Sessao;

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

        ClienteEntity cliente = Sessao.getClienteLogado();

        JLabel saudacao = new JLabel("Bem-vindo, " + cliente.getPessoa().getNome(), JLabel.CENTER);
        saudacao.setFont(new Font("Arial", Font.PLAIN, 26));
        saudacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));
        painelPrincipal.add(saudacao);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 60))); // Espaço abaixo do título

        // Botão Produtos
        JButton botaoProdutos = new JButton("Produtos");
        botaoProdutos.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoProdutos.setMaximumSize(new Dimension(500, 70));
        botaoProdutos.setFont(new Font("Arial", Font.BOLD, 24));

        botaoProdutos.addActionListener(e -> {
            dispose(); // Fecha esta tela

            new TelaProdutos().setVisible(true); // Abre a tela de produtos
        });

        painelPrincipal.add(botaoProdutos);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botão Carrinho
        JButton botaoCarrinho = new JButton("Carrinho");
        botaoCarrinho.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCarrinho.setMaximumSize(new Dimension(500, 70));
        botaoCarrinho.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(botaoCarrinho);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botão Suporte
        JButton botaoSuporte = new JButton("Suporte ao Cliente");
        botaoSuporte.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoSuporte.setMaximumSize(new Dimension(500, 70));
        botaoSuporte.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(botaoSuporte);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botão Segurança
        JButton botaoSeguranca = new JButton("Segurança e Privacidade");
        botaoSeguranca.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoSeguranca.setMaximumSize(new Dimension(500, 70));
        botaoSeguranca.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(botaoSeguranca);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaMenuPrincipal().setVisible(true));
    }
}
