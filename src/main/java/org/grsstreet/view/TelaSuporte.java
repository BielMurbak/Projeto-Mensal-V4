package org.grsstreet.view;

import javax.swing.*;
import java.awt.*;

public class TelaSuporte extends JFrame {

    public TelaSuporte() {
        setTitle("Suporte ao Cliente - GR's Street");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color headerColor = new Color(200, 200, 200);

        // Cabeçalho
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(headerColor);
        header.setPreferredSize(new Dimension(0, 100));
        JLabel titulo = new JLabel("Suporte ao Cliente - GR's Street", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        header.add(titulo, BorderLayout.CENTER);

        // Painel central com layout vertical para os textos
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100)); // padding

        Font fonteTexto = new Font("Arial", Font.PLAIN, 18);

        centerPanel.add(createLabel("Bem-vindo ao suporte da GR's Street", fonteTexto, true));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createLabel("• Ajuda com pedidos, trocas ou devoluções", fonteTexto, false));
        centerPanel.add(createLabel("• Problemas com login, senha ou cadastro", fonteTexto, false));
        centerPanel.add(createLabel("• Informações sobre produtos e entregas", fonteTexto, false));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createLabel("Entre em contato com nosso time de atendimento:", fonteTexto, true));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(createLabel("Email: suporte@grsstreet.com.br", fonteTexto, false));
        centerPanel.add(createLabel("WhatsApp: (45) 99999-9999", fonteTexto, false));
        centerPanel.add(createLabel("Telefone: (45) 4002-8922", fonteTexto, false));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(createLabel("Horário: Segunda a Sexta - 08h às 18h", fonteTexto, false));

        // Botão "Voltar ao Menu"
        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(Color.WHITE);
        painelBotao.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        JButton voltar = new JButton("Voltar ao Menu");
        voltar.setFont(new Font("Arial", Font.PLAIN, 16));
        voltar.setPreferredSize(new Dimension(180, 40));
        voltar.addActionListener(e -> dispose());
        painelBotao.add(voltar);

        voltar.addActionListener(e -> {
            this.dispose();
            new TelaMenuPrincipal().setVisible(true);
        });

        // Rodapé
        JPanel footer = new JPanel(new GridBagLayout());
        footer.setBackground(headerColor);
        footer.setPreferredSize(new Dimension(0, 60));
        JLabel rodape = new JLabel("GR's Street - Todos os direitos reservados", SwingConstants.CENTER);
        rodape.setFont(new Font("Arial", Font.PLAIN, 14));
        footer.add(rodape);

        // Painel inferior com botão e rodapé
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(painelBotao, BorderLayout.NORTH);
        painelInferior.add(footer, BorderLayout.SOUTH);

        // Add ao frame
        add(header, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JLabel createLabel(String text, Font font, boolean boldCenter) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        if (boldCenter) {
            label.setFont(font.deriveFont(Font.BOLD, font.getSize() + 2));
        } else {
            label.setFont(font);
        }
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

}
