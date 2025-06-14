package org.grsstreet.view.adm.produto;

import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.repository.ProdutoRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;
import org.grsstreet.view.adm.cliente.RemoverCliente;

import javax.swing.*;
import java.awt.*;

public class RemoverProduto {

        public RemoverProduto () {
            JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
            sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sistemaAdm.setSize(1080, 720);
            sistemaAdm.setLocationRelativeTo(null);
            sistemaAdm.setLayout(new BorderLayout());

            // Cabeçalho
            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(Color.DARK_GRAY);
            headerPanel.setPreferredSize(new Dimension(700, 40));

            JLabel titleLabel = new JLabel("Remover Produto", SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            headerPanel.add(titleLabel, BorderLayout.CENTER);

            // Painel principal branco
            JPanel panelAdm = new JPanel();
            panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
            panelAdm.setBackground(Color.WHITE);
            panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));

            // Label e campo CPF
            JLabel labelNome = new JLabel("Nome");
            labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
            labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelAdm.add(labelNome);

            JTextField campoNome = new JTextField();
            campoNome .setPreferredSize(new Dimension(300, 60));
            campoNome .setMaximumSize(new Dimension(300, 60));
            campoNome .setFont(new Font("Arial", Font.PLAIN, 20));
            campoNome .setAlignmentX(Component.CENTER_ALIGNMENT);
            campoNome .setBackground(Color.WHITE);
            panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));
            panelAdm.add(campoNome );

            // Botão Remover
            JButton btn = new JButton("Remover");
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(300,60));
            btn.setPreferredSize(new Dimension (300,60));
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground(Color.GRAY);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
            panelAdm.add(btn);

            JButton btnV = new JButton("Voltar");
            btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnV.setMaximumSize(new Dimension(300,60));
            btnV.setPreferredSize(new Dimension (300,60));
            btnV.setFont(new Font("Arial", Font.BOLD, 20));
            btnV.setBackground(Color.GRAY);
            btnV.setForeground(Color.WHITE);
            btnV.setFocusPainted(false);
            btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
            panelAdm.add(btnV);

            btnV.addActionListener(e ->{
                JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
                new TelaAdmPrincipal();
                sistemaAdm.dispose();
            });





            // Rodapé cinza escuro
            JPanel footerPanel = new JPanel();
            footerPanel.setBackground(Color.DARK_GRAY);
            footerPanel.setPreferredSize(new Dimension(700, 60));
            panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
            panelAdm.add(footerPanel);

            // Ação do botão
            btn.addActionListener(e -> {
                String nome = campoNome.getText().trim();

                ProdutoRepository produtoRepository = new ProdutoRepository();
                produtoRepository.deletarNomeProduto(nome);


                campoNome.setText("");
            });


            // Adiciona os painéis à janela
            sistemaAdm.add(headerPanel, BorderLayout.NORTH);
            sistemaAdm.add(panelAdm, BorderLayout.CENTER);

            sistemaAdm.setVisible(true);
        }

        public static void main(String[] args) {
            new RemoverProduto();
        }
    }
