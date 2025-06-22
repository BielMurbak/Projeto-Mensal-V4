package org.grsstreet.view;

import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.session.Sessao;

import javax.swing.*;
import java.awt.*;

/**
 * Tela principal do sistema exibida após o login do cliente.
 * Exibe opções principais: Produtos, Carrinho, Suporte e Segurança.
 */
public class TelaMenuPrincipal extends JFrame {

    /**
     * Construtor da TelaMenuPrincipal.
     * Inicializa a interface gráfica da página inicial do cliente.
     */
    public TelaMenuPrincipal() {
        // Configurações básicas da janela
        setTitle("GR's Street - Página Inicial");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout vertical (BoxLayout Y_AXIS)
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));
        setContentPane(painelPrincipal);

        // Título do sistema centralizado e estilizado
        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        painelPrincipal.add(titulo);

        // Saudação personalizada com nome do cliente logado
        ClienteEntity cliente = Sessao.getClienteLogado();
        JLabel saudacao = new JLabel("Bem-vindo, " + cliente.getPessoa().getNome(), JLabel.CENTER);
        saudacao.setFont(new Font("Arial", Font.PLAIN, 26));
        saudacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço vertical
        painelPrincipal.add(saudacao);

        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 60))); // Espaço abaixo do título e saudação

        // Botão Produtos: navega para a tela de produtos
        JButton botaoProdutos = new JButton("Produtos");
        botaoProdutos.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoProdutos.setMaximumSize(new Dimension(500, 70));
        botaoProdutos.setFont(new Font("Arial", Font.BOLD, 24));

        botaoProdutos.addActionListener(e -> {
            dispose(); // Fecha a tela atual
            new TelaProdutos().setVisible(true); // Abre a tela de produtos
        });

        painelPrincipal.add(botaoProdutos);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30))); // Espaço entre botões

        // Botão Carrinho: abre a tela do carrinho, somente se cliente estiver logado
        JButton botaoCarrinho = new JButton("Carrinho");
        botaoCarrinho.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoCarrinho.setMaximumSize(new Dimension(500, 70));
        botaoCarrinho.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(botaoCarrinho);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        botaoCarrinho.addActionListener(e -> {
            ClienteEntity clienteLogado = Sessao.getClienteLogado();
            if (clienteLogado == null) {
                // Alerta caso o usuário não esteja logado
                JOptionPane.showMessageDialog(this, "Você precisa estar logado para acessar o carrinho.");
                return;
            }
            dispose();
            new TelaCarrinho(clienteLogado).setVisible(true); // Abre a tela do carrinho com o cliente logado
        });

        // Botão Suporte ao Cliente: abre a tela de suporte
        JButton botaoSuporte = new JButton("Suporte ao Cliente");
        botaoSuporte.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoSuporte.setMaximumSize(new Dimension(500, 70));
        botaoSuporte.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(botaoSuporte);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 30)));

        botaoSuporte.addActionListener(e -> {
            dispose(); // Fecha a tela atual
            new TelaSuporte().setVisible(true); // Abre a tela de suporte
        });

        // Botão Segurança e Privacidade: abre a tela de segurança
        JButton botaoSeguranca = new JButton("Segurança e Privacidade");
        botaoSeguranca.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoSeguranca.setMaximumSize(new Dimension(500, 70));
        botaoSeguranca.setFont(new Font("Arial", Font.BOLD, 24));
        painelPrincipal.add(botaoSeguranca);

        botaoSeguranca.addActionListener(e -> {
            dispose(); // Fecha a tela atual
            new TelaSeguranca().setVisible(true); // Abre a tela de segurança e privacidade
        });
    }
}
