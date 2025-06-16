package org.grsstreet.view.adm;

import org.grsstreet.view.TelaMenuPrincipal;
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

public class TelaAdmPrincipal extends JFrame{

    public TelaAdmPrincipal(){
        Color backgroundColor = new Color(30, 30, 30);      // fundo geral escuro
        Color buttonColor = new Color(45, 120, 200);        // botões em azul
        Color TextColor = Color.WHITE;               // texto branco nos botões
        Color headerColor = new Color(20, 20, 20);


        JFrame sistemaAdm = new JFrame ("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080,720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(headerColor);
        headerPanel.setPreferredSize(new Dimension(700, 60));

        JLabel titleLabel = new JLabel("Sistema Adm GR's", new ImageIcon(""),SwingConstants.CENTER);
        titleLabel.setForeground(TextColor);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerPanel.add(titleLabel, BorderLayout.CENTER);


        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout((panelAdm),BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));


        String[]  botoes = {
                "1-Cadastrar cliente", "2-Listar clientes", "3-Remover cliente",
                "4-Cadastrar produto", "5-Listar produto", "6-Remover produto","7-Adicionar administrador","8-Listar administrador","9-Remover administrador",
                "10-Acessar Sistema User ","11-Encerrar programa"

        };

        for(String texto : botoes ) {
            JButton btn = new JButton(texto);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(300, 60));
            btn.setPreferredSize(new Dimension(300, 60));
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            btn.setBackground(buttonColor);
            btn.setForeground(TextColor);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelAdm.add(Box.createRigidArea(new Dimension(0, 60)));


            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonColor.brighter());
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(buttonColor);
                }
            });
            panelAdm.add(btn);

            if (texto.equals("1-Cadastrar cliente")) {
                btn.addActionListener(e -> {
                    new CadastrarCliente();
                    dispose();
                });
            } else if (texto.equalsIgnoreCase("2-Listar clientes"))
            btn.addActionListener(e -> {
                new ListarClientes();
                dispose();
            });
            else if(texto.equalsIgnoreCase("3-Remover cliente"))
            btn.addActionListener(e -> {
                new RemoverCliente();
                dispose();
            });

            else if(texto.equalsIgnoreCase("7-Adicionar administrado"))
                btn.addActionListener(e -> {
                    new CadastrarAdm();
                    dispose();
                });
            else if(texto.equalsIgnoreCase("8-Listar administrador"))
                btn.addActionListener(e -> {
                    new ListarAdms();
                    dispose();
                });
            else if(texto.equalsIgnoreCase("9-Remover administrador"))
                btn.addActionListener(e -> {
                    new RemoverAdm();
                    dispose();
                });
            else if(texto.equalsIgnoreCase("4-Cadastrar Produto"))
                btn.addActionListener(e -> {
                    new CadastrarProduto();
                    dispose();
                });
            else if(texto.equalsIgnoreCase("5-Listar produto"))
                btn.addActionListener(e -> {
                    new ListarProdutos();
                    dispose();
                });
            else if(texto.equalsIgnoreCase("6-Remover produto"))
                btn.addActionListener(e -> {
                    new RemoverProduto();
                    dispose();

                });
            else if(texto.equalsIgnoreCase("10-Acessar Sistema User"))
                btn.addActionListener(e -> {
                    new TelaMenuPrincipal();
                    dispose();
                });
            else if(texto.equalsIgnoreCase("11-Encerrar programa"))
                btn.addActionListener(e -> {
                    dispose();
                });


        }

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(backgroundColor);
        footerPanel.setPreferredSize(new Dimension(700,60));
        panelAdm.add(Box.createRigidArea(new Dimension(0,40)));

        panelAdm.add(footerPanel);

        JScrollPane scrollPane = new JScrollPane(panelAdm);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(80, 80, 80); // parte que se move
                this.trackColor = new Color(200, 200, 200); // fundo da barra
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(Color.DARK_GRAY); // cor da seta para cima
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setBackground(Color.DARK_GRAY); // cor da seta para baixo
                button.setBorder(BorderFactory.createEmptyBorder());
                return button;
            }
        });


        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(scrollPane, BorderLayout.CENTER);

        sistemaAdm.setVisible(true);



    }
    public static void main(String[] args) {
        new TelaAdmPrincipal();
    }
}


