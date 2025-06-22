package org.grsstreet.view.adm;

import org.grsstreet.view.TelaLogin;
import org.grsstreet.view.adm.administrador.CadastrarAdm;
import org.grsstreet.view.adm.administrador.ListarAdms;
import org.grsstreet.view.adm.administrador.RemoverAdm;
import org.grsstreet.view.adm.cliente.CadastrarCliente;
import org.grsstreet.view.adm.cliente.ListarClientes;
import org.grsstreet.view.adm.cliente.RemoverCliente;
import org.grsstreet.view.adm.produto.CadastrarProduto;
import org.grsstreet.view.adm.produto.ListarProdutos;
import org.grsstreet.view.adm.produto.RemoverProduto;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

/**
 * Tela principal do sistema administrativo.
 * Exibe botões para acesso a diferentes funcionalidades, como cadastro, listagem e remoção
 * de clientes, produtos e administradores, além de acesso ao sistema usuário e finalização do programa.
 */
public class TelaAdmPrincipal extends JFrame {

    /**
     * Construtor que configura a interface gráfica da tela principal administrativa.
     * Cria botões, painel, cabeçalho, scroll e adiciona listeners para navegação.
     */
    public TelaAdmPrincipal() {
        // Definição das cores principais da interface
        Color backgroundColor = new Color(30, 30, 30);  // Fundo escuro
        Color buttonColor = new Color(45, 120, 200);    // Botões azuis
        Color textColor = Color.WHITE;                   // Texto branco nos botões
        Color headerColor = new Color(20, 20, 20);       // Cabeçalho mais escuro

        // Configuração da janela principal
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null); // Centralizar tela
        sistemaAdm.setLayout(new BorderLayout());

        // Cabeçalho da janela
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(headerColor);
        headerPanel.setPreferredSize(new Dimension(700, 60));

        JLabel titleLabel = new JLabel("Sistema Adm GR's", SwingConstants.CENTER);
        titleLabel.setForeground(textColor);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal com botões organizados em coluna vertical
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento inicial

        // Lista de botões da interface
        String[] botoes = {
                "1-Cadastrar cliente", "2-Listar clientes", "3-Remover cliente",
                "4-Cadastrar produto", "5-Listar produto", "6-Remover produto",
                "7-Adicionar administrador", "8-Listar administrador", "9-Remover administrador",
                "10-Acessar Sistema User", "11-Encerrar programa",
        };

        // Criar botões e configurar ações para cada um
        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(300, 60));
            btn.setPreferredSize(new Dimension(300, 60));
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            btn.setBackground(buttonColor);
            btn.setForeground(textColor);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelAdm.add(Box.createRigidArea(new Dimension(0, 60))); // Espaçamento entre botões

            // Efeitos de hover (mouse entra/sai)
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonColor.brighter());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonColor);
                }
            });

            panelAdm.add(btn);

            // Define ações para cada botão
            switch (texto.toLowerCase()) {
                case "1-cadastrar cliente":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando cadastrar cliente");
                        new CadastrarCliente();
                        dispose(); // Fecha a tela atual
                    });
                    break;
                case "2-listar clientes":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando listar cliente");
                        new ListarClientes();
                        dispose();
                    });
                    break;
                case "3-remover cliente":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando remover cliente");
                        new RemoverCliente();
                        dispose();
                    });
                    break;
                case "4-cadastrar produto":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando cadastrar produto");
                        new CadastrarProduto();
                        dispose();
                    });
                    break;
                case "5-listar produto":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando listar produto");
                        new ListarProdutos();
                        dispose();
                    });
                    break;
                case "6-remover produto":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando remover produto");
                        new RemoverProduto();
                        dispose();
                    });
                    break;
                case "7-adicionar administrador":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando adicionar administrador");
                        new CadastrarAdm();
                        dispose();
                    });
                    break;
                case "8-listar administrador":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando listar administrador");
                        new ListarAdms();
                        dispose();
                    });
                    break;
                case "9-remover administrador":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando remover administrador");
                        new RemoverAdm();
                        dispose();
                    });
                    break;
                case "10-acessar sistema user":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Acessando sistema de login");
                        new TelaLogin();
                        dispose();
                    });
                    break;
                case "11-encerrar programa":
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Encerrando programa");
                        exit(1);
                    });
                    break;
                case "12-acessar sistema de edicao":
                    // Ação a implementar
                    btn.addActionListener(e -> {
                        JOptionPane.showMessageDialog(this, "Funcionalidade ainda não implementada");
                    });
                    break;
                default:
                    // Nenhuma ação específica
                    break;
            }
        }

        // Rodapé vazio para espaçamento
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(backgroundColor);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(footerPanel);

        // Scroll pane para o painel principal para rolar quando necessário
        JScrollPane scrollPane = new JScrollPane(panelAdm);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Customização visual da barra de rolagem vertical
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(80, 80, 80); // Parte móvel da barra
                this.trackColor = new Color(200, 200, 200); // Fundo da barra
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(Color.DARK_GRAY); // Cor da seta para cima
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(Color.DARK_GRAY); // Cor da seta para baixo
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
        });

        // Adiciona os componentes na janela principal
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(scrollPane, BorderLayout.CENTER);

        sistemaAdm.setVisible(true);
    }

    /**
     * Ponto de entrada da aplicação para abrir a tela principal do administrador.
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        new TelaAdmPrincipal();
    }
}
