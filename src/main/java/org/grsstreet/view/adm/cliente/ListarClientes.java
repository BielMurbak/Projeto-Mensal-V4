package org.grsstreet.view.adm.cliente;

import javax.swing.*;
import java.awt.*;


public class ListarClientes {

        public ListarClientes () {
            JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
            sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sistemaAdm.setSize(1080, 720);
            sistemaAdm.setLocationRelativeTo(null);
            sistemaAdm.setLayout(new BorderLayout());

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(Color.DARK_GRAY);
            headerPanel.setPreferredSize(new Dimension(700, 40));

            JLabel titleLabel = new JLabel("Listar Clientes", SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            headerPanel.add(titleLabel, BorderLayout.CENTER);
            JPanel panelAdm = new JPanel();
            panelAdm.setLayout(new BoxLayout((panelAdm),BoxLayout.Y_AXIS));
            panelAdm.setBackground(Color.lightGray);
            panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

            


            JPanel footerPanel = new JPanel();
            footerPanel.setBackground(Color.DARK_GRAY);
            footerPanel.setPreferredSize(new Dimension(700, 60));
            panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
            panelAdm.add(footerPanel);

            sistemaAdm.add(headerPanel, BorderLayout.NORTH);
            sistemaAdm.add(panelAdm, BorderLayout.CENTER);

            sistemaAdm.setVisible(true);
        }

    }
