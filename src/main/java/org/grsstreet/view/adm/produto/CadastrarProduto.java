package org.grsstreet.view.adm.produto;


import org.grsstreet.model.enums.TipoPessoa;
import org.grsstreet.model.enums.TipoProduto;
import org.grsstreet.model.product.ProdutoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.repository.EnderecoRepository;
import org.grsstreet.repository.PessoaRepository;
import org.grsstreet.repository.ProdutoRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CadastrarProduto {
    private String caminhoImagemSelecionada;
    public CadastrarProduto() {
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Cadastrar Produto", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout((panelAdm), BoxLayout.Y_AXIS));
        panelAdm.setBackground(Color.lightGray);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));


        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(300, 60));
        campoNome.setMaximumSize(new Dimension(300, 60));
        campoNome.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(labelNome);
        panelAdm.add(campoNome);

        JLabel labelTipo = new JLabel("Tipo");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoTipo = new JTextField();
        campoTipo.setPreferredSize(new Dimension(300, 60));
        campoTipo.setMaximumSize(new Dimension(300, 60));
        campoTipo.setFont(new Font("Arial", Font.PLAIN, 20));
        labelTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(labelTipo);
        panelAdm.add(campoTipo);

        JLabel labelQuantidade = new JLabel("Quantidade");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoQuantidade = new JTextField();
        campoQuantidade .setPreferredSize(new Dimension(300, 60));
        campoQuantidade.setMaximumSize(new Dimension(300, 60));
        campoQuantidade.setFont(new Font("Arial", Font.PLAIN, 20));
        labelQuantidade.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoQuantidade.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(labelQuantidade);
        panelAdm.add(campoQuantidade);

        JLabel labelPreco= new JLabel("Preço");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoPreco = new JTextField();
        campoPreco .setPreferredSize(new Dimension(300, 60));
        campoPreco.setMaximumSize(new Dimension(300, 60));
        campoPreco.setFont(new Font("Arial", Font.PLAIN, 20));
        labelPreco.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPreco.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(labelPreco);
        panelAdm.add(campoPreco);

        JLabel previewImagem = new JLabel();
        previewImagem.setPreferredSize(new Dimension(200, 200));
        previewImagem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        previewImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(previewImagem);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnSelecionarImagem = new JButton("Selecionar ProdutosLista");
        btnSelecionarImagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(btnSelecionarImagem);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 20)));

        btnSelecionarImagem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg","jpeg","png","gif"));
            if (chooser.showOpenDialog(sistemaAdm) == JFileChooser.APPROVE_OPTION) {
                File arquivo = chooser.getSelectedFile();
                // preview...
                this.caminhoImagemSelecionada = arquivo.getAbsolutePath();
            }
        });

        JButton btn = new JButton("Criar");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300,60));
        btn.setPreferredSize(new Dimension (300,60));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground( Color.GRAY);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0,60)));
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
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));
        panelAdm.add(btnV);

        btnV.addActionListener(event ->{
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        btn.addActionListener(e -> {
            try {
                String nomeTxt       = campoNome.getText().trim();
                String tipoTxt       = campoTipo.getText().trim();
                String quantidadeTxt = campoQuantidade.getText().trim();
                String precoTxt      = campoPreco.getText().trim();

                int quantidade = Integer.parseInt(quantidadeTxt);
                double preco = Double.parseDouble(precoTxt);

                // Cria e popula a entidade
                ProdutoEntity produto = new ProdutoEntity();
                produto.setNome(nomeTxt);
               if(tipoTxt.equalsIgnoreCase("tenis")){
                   produto.setTipo(TipoProduto.TENIS);
               }else if(tipoTxt.equalsIgnoreCase("bone")){
                   produto.setTipo(TipoProduto.BONE);
               }else if(tipoTxt.equalsIgnoreCase("calca")){
                   produto.setTipo(TipoProduto.CALCA);
               }else {
                   produto.setTipo(TipoProduto.CAMISA);
               }
                produto.setQuantidade(quantidade);
                produto.setPreco(preco);

                if (caminhoImagemSelecionada != null) {
                    produto.setImagem(caminhoImagemSelecionada);
                }

               ProdutoRepository produtoRepository = new ProdutoRepository();
                produtoRepository.salvar(produto);

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

                campoNome.setText("");
                campoQuantidade.setText("");
                campoPreco.setText("");
                campoTipo.setText("");
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
    public static void main(String[] args) {
        new CadastrarProduto();
    }
}
