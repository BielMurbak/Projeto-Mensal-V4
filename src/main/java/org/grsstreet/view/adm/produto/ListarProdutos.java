package org.grsstreet.view.adm.produto;

import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.repository.ProdutoRepository;
import org.grsstreet.service.ProdutosLista;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Classe responsável pela interface gráfica para listar produtos no sistema administrativo.
 * Permite visualizar, buscar por nome parcial ou por preço máximo, e voltar ao menu principal.
 */
public class ListarProdutos extends JFrame {

    /**
     * Construtor que inicializa a interface com tabela de produtos,
     * campos de busca e botão de navegação para voltar ao menu principal.
     */
    public ListarProdutos() {
        // Definição de cores usadas na interface
        Color backgroundColor = new Color(30, 30, 30);
        Color buttonColor = new Color(45, 120, 200);
        Color TextColor = Color.WHITE;

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

        JLabel titleLabel = new JLabel("Listar Produtos", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal que contém todos os componentes da tela
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

        // Painel que contém a tabela de produtos
        JPanel dadosAdm = new JPanel();
        dadosAdm.setLayout(new BoxLayout(dadosAdm, BoxLayout.Y_AXIS));
        dadosAdm.setBackground(backgroundColor);
        dadosAdm.setMaximumSize(new Dimension(1000, 400));
        dadosAdm.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Instancia o repositório para buscar os produtos
        ProdutoRepository produtoRepository = new ProdutoRepository();
        List<ProdutoEntity> produto = produtoRepository.listarTenis();

        // Definição das colunas da tabela
        String[] colunas = {"Nome", "quantidade", "preco", "tipo", "imagem"};
        // Dados da tabela construídos a partir da lista de produtos
        String[][] dados = ProdutosLista.construirTabela(produto);

        // Criação da tabela e seu painel de rolagem
        JTable tabela = new JTable(dados, colunas);
        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(900, 200));
        dadosAdm.add(scrollPane);

        panelAdm.add(dadosAdm);

        // Consulta o total de produtos para exibir na interface
        ProdutoRepository produtoRepository2 = new ProdutoRepository();
        long totalProdutos = produtoRepository2.contarProdutos();

        JLabel labelTotal = new JLabel("Total de produtos: " + totalProdutos);
        labelTotal.setForeground(Color.WHITE);
        labelTotal.setFont(new Font("Arial", Font.BOLD, 14));
        labelTotal.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 20)));
        panelAdm.add(labelTotal);

        // Configuração da busca por nome parcial
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

        // Painel horizontal para o campo e botão de busca por nome
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

        // Ação do botão de busca por nome parcial
        btnNome.addActionListener(e -> {
            String nome = campoNome.getText().trim();
            List<ProdutoEntity> produtosFiltrados = produtoRepository.buscarPorParcialNome(nome);
            if (produtosFiltrados.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Nenhum produto encontrado com nome  " + nome,
                        "Resultado vazio",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            String[][] novosDados = ProdutosLista.construirTabela(produtosFiltrados);
            tabela.setModel(new javax.swing.table.DefaultTableModel(novosDados, colunas));
        });

        // Configuração da busca por valor máximo
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

        // Painel horizontal para o campo e botão de busca por valor máximo
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

        // Ação do botão de busca por valor máximo
        btnValorMaximo.addActionListener(e -> {
            String valor = campoValorMaximo.getText().trim();
            if (!valor.isEmpty()) {
                try {
                    double valorMaximo = Double.parseDouble(valor);
                    List<ProdutoEntity> produtosFiltrados = produtoRepository.buscarProdutosComPrecoMaximo(valorMaximo);
                    String[][] novosDados = ProdutosLista.construirTabela(produtosFiltrados);
                    if (produtosFiltrados.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Nenhum produto encontrado com preço até R$ " + valorMaximo,
                                "Resultado vazio",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    tabela.setModel(new javax.swing.table.DefaultTableModel(novosDados, colunas));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido para o valor máximo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Caso campo vazio, recarrega todos os produtos
                List<ProdutoEntity> produtos = produtoRepository.listarTenis();
                String[][] novosDados = ProdutosLista.construirTabela(produtos);
                tabela.setModel(new javax.swing.table.DefaultTableModel(novosDados, colunas));
            }
        });

        // Botão para voltar ao menu administrativo principal
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

        // Ação do botão voltar: fecha esta tela e abre tela administrativa principal
        btnV.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        // Adiciona os painéis configurados na janela principal
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        // Torna a janela visível
        sistemaAdm.setVisible(true);
    }

    /**
     * Método principal para execução da aplicação.
     * Inicializa a tela de listagem de produtos.
     */
    public static void main(String[] args) {
        new ListarProdutos();
    }
}
