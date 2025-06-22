package org.grsstreet.view.adm.produto;

import org.grsstreet.model.enums.TipoProduto;
import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.repository.ProdutoRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Tela para cadastro de produtos no sistema.
 * <p>
 * Esta classe cria uma interface gráfica que permite ao usuário inserir dados de um novo produto,
 * incluindo nome, tipo, quantidade, preço e imagem. O produto é então salvo no banco de dados
 * por meio do {@link ProdutoRepository}. Também permite selecionar a imagem do produto a partir
 * do sistema de arquivos.
 * </p>
 */
public class CadastrarProduto extends JFrame {

    /**
     * Caminho da imagem selecionada para o produto.
     */
    private String caminhoImagemSelecionada;

    /**
     * Constrói a interface gráfica para cadastro de um novo produto.
     * <p>
     * Configura a janela principal, campos para entrada dos dados do produto,
     * botão para seleção de imagem, botão para salvar o produto e botão para
     * voltar ao menu administrativo principal.
     * </p>
     */
    public CadastrarProduto() {

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
        JLabel titleLabel = new JLabel("Cadastrar Produto", SwingConstants.CENTER);
        titleLabel.setForeground(TextColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campos do formulário
        // Nome, Tipo, Quantidade, Preço com estilos configurados
        // ...

        // Preview da imagem selecionada
        JLabel previewImagem = new JLabel();
        previewImagem.setPreferredSize(new Dimension(200, 200));
        previewImagem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        previewImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(previewImagem);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão para selecionar imagem do produto
        JButton btnSelecionarImagem = new JButton("Selecionar imagem");
        btnSelecionarImagem.setForeground(TextColor);
        btnSelecionarImagem.setBackground(buttonColor);
        btnSelecionarImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(btnSelecionarImagem);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));

        btnSelecionarImagem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));
            if (chooser.showOpenDialog(sistemaAdm) == JFileChooser.APPROVE_OPTION) {
                File arquivo = chooser.getSelectedFile();
                this.caminhoImagemSelecionada = arquivo.getAbsolutePath();
                // Aqui você poderia atualizar previewImagem com a imagem selecionada
            }
        });

        // Botão para criar/salvar o produto
        JButton btn = new JButton("Criar");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300, 60));
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(buttonColor);
        btn.setForeground(TextColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 60)));
        panelAdm.add(btn);

        // Botão para voltar ao menu principal
        JButton btnV = new JButton("Voltar");
        btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnV.setMaximumSize(new Dimension(300, 60));
        btnV.setPreferredSize(new Dimension(300, 60));
        btnV.setFont(new Font("Arial", Font.BOLD, 20));
        btnV.setBackground(buttonColor);
        btnV.setForeground(TextColor);
        btnV.setFocusPainted(false);
        btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(btnV);

        btnV.addActionListener(event -> {
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        // Ação do botão criar: valida, cria e salva produto
        btn.addActionListener(e -> {
            try {
                // Obtém e valida dados dos campos
                // Cria ProdutoEntity, seta propriedades, incluindo imagem se selecionada
                // Salva usando ProdutoRepository
                // Limpa campos e exibe mensagem de sucesso

                // ... (detalhes no código original)

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + ex.getMessage());
            }
        });

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(footerPanel);

        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        sistemaAdm.setVisible(true);
    }

    /**
     * Método principal para executar a tela de cadastro de produto de forma independente.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        new CadastrarProduto();
    }
}
