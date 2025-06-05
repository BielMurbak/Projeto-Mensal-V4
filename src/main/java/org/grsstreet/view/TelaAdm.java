package org.grsstreet.view;

import javax.swing.*;
import java.awt.*;

public class TelaAdm extends JFrame{

    public TelaAdm(){
        JFrame sistemaAdm = new JFrame ("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080,720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 60));

        JLabel titleLabel = new JLabel("Sistema Adm GR's", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);


        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout((panelAdm),BoxLayout.Y_AXIS));
        panelAdm.setBackground(Color.lightGray);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));


        String[]  botoes = {
                "1-Cadastrar cliente", "2-Listar clientes", "3-Remover cliente",
                "4-Cadastrar produto", "5-Listar produto", "6-Remover produto","7-Adicionar administrador","8-Listar administrador","9-Remover administrador",
                "10-Acessar Sistema User ","11-Encerrar programa"

        };

        for(String texto : botoes ){
            JButton btn = new JButton(texto);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(300,60));
            btn.setPreferredSize(new Dimension (300,60));
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setBackground( Color.GRAY);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelAdm.add(Box.createRigidArea(new Dimension(0,60)));
            panelAdm.add(btn);

        }

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
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
}


