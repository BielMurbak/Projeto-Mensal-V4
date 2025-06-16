package org.grsstreet.view.adm.produto;

import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.repository.ProdutoRepository;
import org.grsstreet.service.ProdutosLista;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListarProdutos extends JFrame {

    public ListarProdutos() {
        Color backgroundColor = new Color(30, 30, 30);
        Color buttonColor = new Color(45, 120, 200);
        Color TextColor = Color.WHITE;

        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());

        // Cabeçalho
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Listar Produtos", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

        // Painel com os dados da tabela
        JPanel dadosAdm = new JPanel();
        dadosAdm.setLayout(new BoxLayout(dadosAdm, BoxLayout.Y_AXIS));
        dadosAdm.setBackground(backgroundColor);
        dadosAdm.setMaximumSize(new Dimension(1000, 400));
        dadosAdm.setAlignmentX(Component.CENTER_ALIGNMENT);

        ProdutoRepository produtoRepository = new ProdutoRepository();
        List<ProdutoEntity> produto = produtoRepository.listarTenis();

        String[] colunas = {"Nome", "quantidade", "preco", "tipo", "imagem"};
        String[][] dados = ProdutosLista.construirTabela(produto);

        JTable tabela = new JTable(dados, colunas);
        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(900, 200));
        dadosAdm.add(scrollPane);

        panelAdm.add(dadosAdm);

        // Busca por nome parcial
        JLabel labelNome = new JLabel("Buscar por nome parcial");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        labelNome.setForeground(TextColor);
        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(300, 30));
        campoNome.setMaximumSize(new Dimension(300, 30));
        campoNome.setFont(new Font("Arial", Font.PLAIN, 30));
        campoNome.setBackground(Color.DARK_GRAY);
        campoNome.setForeground(TextColor);
        campoNome.setCaretColor(TextColor);
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnNome = new JButton("Buscar");
        btnNome.setFont(new Font("Arial", Font.BOLD, 20));
        btnNome.setBackground(buttonColor);
        btnNome.setForeground(Color.white);
        btnNome.setFocusPainted(false);
        btnNome.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNome.setPreferredSize(new Dimension(120, 40));
        btnNome.setMaximumSize(new Dimension(120, 40));

        // Painel horizontal para nome e botão
        JPanel buscaNomePanel = new JPanel();
        buscaNomePanel.setLayout(new BoxLayout(buscaNomePanel, BoxLayout.X_AXIS));
        buscaNomePanel.setBackground(backgroundColor);
        buscaNomePanel.setMaximumSize(new Dimension(450, 50));
        buscaNomePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buscaNomePanel.add(campoNome);
        buscaNomePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buscaNomePanel.add(btnNome);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(labelNome);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 5)));
        panelAdm.add(buscaNomePanel);

        btnNome.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            List<ProdutoEntity> produtosFiltrados = produtoRepository.buscarPorParcialNome(nome);
            String[][] novosDados = ProdutosLista.construirTabela(produtosFiltrados);
            tabela.setModel(new javax.swing.table.DefaultTableModel(novosDados, colunas));
        });

        // Busca por valor máximo
        JLabel labelValorMaximo = new JLabel("Buscar por valor máximo");
        labelValorMaximo.setFont(new Font("Arial", Font.PLAIN, 22));
        labelValorMaximo.setForeground(TextColor);
        labelValorMaximo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoValorMaximo = new JTextField();
        campoValorMaximo.setPreferredSize(new Dimension(300, 30));
        campoValorMaximo.setMaximumSize(new Dimension(300, 30));
        campoValorMaximo.setFont(new Font("Arial", Font.PLAIN, 30));
        campoValorMaximo.setBackground(Color.DARK_GRAY);
        campoValorMaximo.setForeground(TextColor);
        campoValorMaximo.setCaretColor(TextColor);
        campoValorMaximo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnValorMaximo = new JButton("Buscar");
        btnValorMaximo.setFont(new Font("Arial", Font.BOLD, 20));
        btnValorMaximo.setBackground(buttonColor);
        btnValorMaximo.setForeground(Color.white);
        btnValorMaximo.setFocusPainted(false);
        btnValorMaximo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnValorMaximo.setPreferredSize(new Dimension(120, 40));
        btnValorMaximo.setMaximumSize(new Dimension(120, 40));

        // Painel horizontal para valor máximo e botão
        JPanel buscaValorPanel = new JPanel();
        buscaValorPanel.setLayout(new BoxLayout(buscaValorPanel, BoxLayout.X_AXIS));
        buscaValorPanel.setBackground(backgroundColor);
        buscaValorPanel.setMaximumSize(new Dimension(450, 50));
        buscaValorPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        buscaValorPanel.add(campoValorMaximo);
        buscaValorPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buscaValorPanel.add(btnValorMaximo);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(labelValorMaximo);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 5)));
        panelAdm.add(buscaValorPanel);

        btnValorMaximo.addActionListener(e -> {
            String valor = campoValorMaximo.getText().trim();
            if (!valor.isEmpty()) {
                try {
                    double valorMaximo = Double.parseDouble(valor);
                    List<ProdutoEntity> produtosFiltrados = produtoRepository.buscarProdutosComPrecoMaximo();
                    String[][] novosDados = ProdutosLista.construirTabela(produtosFiltrados);
                    tabela.setModel(new javax.swing.table.DefaultTableModel(novosDados, colunas));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido para o valor máximo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Se o campo estiver vazio, exibir todos os produtos ou limpar filtro
                List<ProdutoEntity> produtos = produtoRepository.listarTenis();
                String[][] novosDados = ProdutosLista.construirTabela(produtos);
                tabela.setModel(new javax.swing.table.DefaultTableModel(novosDados, colunas));
            }
        });

        // Botão Voltar
        JButton btnV = new JButton("Voltar");
        btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnV.setMaximumSize(new Dimension(300, 60));
        btnV.setPreferredSize(new Dimension(300, 60));
        btnV.setFont(new Font("Arial", Font.BOLD, 20));
        btnV.setForeground(Color.WHITE);
        btnV.setBackground(buttonColor);
        btnV.setFocusPainted(false);
        btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelAdm.add(Box.createRigidArea(new Dimension(0, 100)));
        panelAdm.add(btnV);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 50))); // espaço abaixo do botão

        btnV.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        // Adiciona os painéis à janela
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        sistemaAdm.setVisible(true);
    }

    public static void main(String[] args) {
        new ListarProdutos();
    }
}
