package org.grsstreet.view.adm.produto;

import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.repository.ProdutoRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;
import org.grsstreet.view.adm.cliente.RemoverCliente;

import javax.swing.*;
import java.awt.*;

public class RemoverProduto extends JFrame {

    /**
     * Construtor da janela RemoverProduto.
     * Configura a interface gráfica para permitir a remoção de um produto pelo nome.
     */
    public RemoverProduto() {

        // Definição das cores utilizadas na interface
        Color backgroundColor = new Color(30, 30, 30);
        Color buttonColor = new Color(45, 120, 200);
        Color TextColor = Color.WHITE;
        Color headerColor = new Color(20, 20, 20);

        // Criação da janela principal
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());

        // Cabeçalho da janela
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Remover Produto", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal com layout vertical
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));

        // Label para o campo de texto do nome do produto
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        labelNome.setForeground(Color.white);
        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(labelNome);

        // Campo de texto para inserir o nome do produto a ser removido
        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(300, 60));
        campoNome.setMaximumSize(new Dimension(300, 60));
        campoNome.setFont(new Font("Arial", Font.PLAIN, 20));
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNome.setBackground(Color.WHITE);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));
        panelAdm.add(campoNome);

        // Botão para executar a remoção do produto
        JButton btn = new JButton("Remover");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300, 60));
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(buttonColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(btn);

        // Botão para voltar ao menu principal do administrador
        JButton btnV = new JButton("Voltar");
        btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnV.setMaximumSize(new Dimension(300, 60));
        btnV.setPreferredSize(new Dimension(300, 60));
        btnV.setFont(new Font("Arial", Font.BOLD, 20));
        btnV.setBackground(buttonColor);
        btnV.setForeground(Color.WHITE);
        btnV.setFocusPainted(false);
        btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(btnV);

        // Ação do botão "Voltar" que fecha esta janela e abre o menu principal
        btnV.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        // Rodapé (painel cinza escuro vazio para espaço visual)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(footerPanel);

        // Ação do botão "Remover": remove o produto com o nome fornecido
        btn.addActionListener(e -> {
            String nome = campoNome.getText().trim();

            ProdutoRepository produtoRepository = new ProdutoRepository();
            produtoRepository.deletarNomeProduto(nome); // Chama o método para deletar produto pelo nome

            campoNome.setText(""); // Limpa o campo de texto após remoção
        });

        // Adiciona os painéis à janela principal
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        sistemaAdm.setVisible(true); // Exibe a janela
    }

    /**
     * Método principal para executar a interface RemoverProduto.
     * @param args argumentos da linha de comando (não usados)
     */
    public static void main(String[] args) {
        new RemoverProduto();
    }
}
