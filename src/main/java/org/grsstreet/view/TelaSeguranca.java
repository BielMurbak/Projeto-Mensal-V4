package org.grsstreet.view;

import javax.swing.*;
import java.awt.*;

public class TelaSeguranca extends JFrame {

    public TelaSeguranca() {
        setTitle("Segurança e Privacidade - GR's Street");
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color headerColor = new Color(200, 200, 200);

        // Cabeçalho
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(headerColor);
        header.setPreferredSize(new Dimension(0, 100));
        JLabel titulo = new JLabel("Segurança e Privacidade - GR's Street", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        header.add(titulo, BorderLayout.CENTER);

        // Painel central com layout vertical para os textos
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100)); // padding

        Font fonteTexto = new Font("Arial", Font.PLAIN, 18);

        centerPanel.add(createLabel("Na GR's Street, sua segurança é prioridade.", fonteTexto, true));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createLabel("• Seus dados são criptografados com segurança", fonteTexto, false));
        centerPanel.add(createLabel("• Nunca compartilhamos suas informações com terceiros", fonteTexto, false));
        centerPanel.add(createLabel("• Utilizamos plataformas confiáveis para pagamentos", fonteTexto, false));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createLabel("Compromissos:", fonteTexto, true));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(createLabel("• Certificado SSL ativo", fonteTexto, false));
        centerPanel.add(createLabel("• Autenticação segura para usuários", fonteTexto, false));
        centerPanel.add(createLabel("• Conformidade com a Lei Geral de Proteção de Dados (LGPD)", fonteTexto, false));
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(createLabel("Você pode comprar com tranquilidade em nossa loja.", fonteTexto, false));

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
        JLabel rodape = new JLabel("GR's Street - Privacidade e Segurança", SwingConstants.CENTER);
        rodape.setFont(new Font("Arial", Font.PLAIN, 14));
        footer.add(rodape);

        // Painel inferior com botão acima do rodapé
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BorderLayout());
        painelInferior.add(painelBotao, BorderLayout.NORTH);
        painelInferior.add(footer, BorderLayout.SOUTH);

        // Add tudo ao frame
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
