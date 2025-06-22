package org.grsstreet.view.adm.administrador;

import org.grsstreet.model.enums.TipoPessoa;
import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.grsstreet.repository.AdministradorRepository;
import org.grsstreet.repository.PessoaRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tela para cadastro de Administrador no sistema.
 * Permite inserir dados básicos e salvar no banco via repositórios.
 */
public class CadastrarAdm extends JFrame {

    public CadastrarAdm() {

        // Cores utilizadas na interface
        Color backgroundColor = new Color(30, 30, 30);
        Color buttonColor = new Color(45, 120, 200);
        Color TextColor = Color.WHITE;

        // Configuração básica da janela principal
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null); // Centraliza na tela
        sistemaAdm.setLayout(new BorderLayout());

        // Painel do cabeçalho
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        // Título centralizado no cabeçalho
        JLabel titleLabel = new JLabel("Cadastrar Adm", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal com layout vertical para os campos e botões
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço vertical

        // --- Campo Nome ---
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        labelNome.setForeground(Color.WHITE);

        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(300, 60));
        campoNome.setMaximumSize(new Dimension(300, 60));
        campoNome.setFont(new Font("Arial", Font.PLAIN, 20));
        campoNome.setBackground(Color.DARK_GRAY);
        campoNome.setForeground(Color.WHITE);
        campoNome.setCaretColor(Color.WHITE);
        campoNome.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 40))); // Espaço antes do campo
        panelAdm.add(labelNome);
        panelAdm.add(campoNome);

        // --- Campo Data de Nascimento ---
        JLabel labelNascimento = new JLabel("Data de Nascimento yyyy/MM/dd");
        labelNascimento.setFont(new Font("Arial", Font.PLAIN, 22));
        labelNascimento.setForeground(TextColor);

        JTextField campoNascimento = new JTextField();
        campoNascimento.setPreferredSize(new Dimension(300, 60));
        campoNascimento.setMaximumSize(new Dimension(300, 60));
        campoNascimento.setFont(new Font("Arial", Font.PLAIN, 20));
        campoNascimento.setBackground(Color.DARK_GRAY);
        campoNascimento.setForeground(Color.WHITE);
        campoNascimento.setCaretColor(Color.WHITE);
        campoNascimento.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        labelNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(labelNascimento);
        panelAdm.add(campoNascimento);

        // --- Campo CPF ---
        JLabel labelCpf = new JLabel("CPF");
        labelCpf.setFont(new Font("Arial", Font.PLAIN, 22));
        labelCpf.setForeground(TextColor);

        JTextField campoCpf = new JTextField();
        campoCpf.setPreferredSize(new Dimension(300, 60));
        campoCpf.setMaximumSize(new Dimension(300, 60));
        campoCpf.setFont(new Font("Arial", Font.PLAIN, 20));
        campoCpf.setBackground(Color.DARK_GRAY);
        campoCpf.setForeground(Color.WHITE);
        campoCpf.setCaretColor(Color.WHITE);
        campoCpf.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        labelCpf.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoCpf.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(labelCpf);
        panelAdm.add(campoCpf);

        // --- Campo Senha ---
        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        labelSenha.setForeground(TextColor);

        // Ideal usar JPasswordField para esconder senha (pode substituir JTextField aqui)
        JTextField campoSenha = new JTextField();
        campoSenha.setPreferredSize(new Dimension(300, 60));
        campoSenha.setMaximumSize(new Dimension(300, 60));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        campoSenha.setBackground(Color.DARK_GRAY);
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setCaretColor(Color.WHITE);
        campoSenha.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 60)));
        panelAdm.add(labelSenha);
        panelAdm.add(campoSenha);

        // --- Botão Criar ---
        JButton btn = new JButton("Criar");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300, 60));
        btn.setBackground(buttonColor);
        btn.setForeground(TextColor);
        btn.setPreferredSize(new Dimension(300, 60));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelAdm.add(Box.createRigidArea(new Dimension(0, 60)));
        panelAdm.add(btn);

        // --- Botão Voltar ---
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

        // Evento do botão Voltar: abre a tela principal do admin e fecha essa
        btnV.addActionListener(event -> {
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        // Evento do botão Criar: captura dados, valida e salva no banco via repositórios
        btn.addActionListener(e -> {
            String nomeAdm = campoNome.getText().trim();
            String cpfAdm = campoCpf.getText().trim();
            String dataNascAdm = campoNascimento.getText().trim();
            String senhaAdm = campoSenha.getText().trim();

            try {
                // Criação das entidades e repositórios
                PessoaRepository pessoaRepository = new PessoaRepository();
                PessoaEntity pessoa = new PessoaEntity();
                AdministradorRepository administradorRepository = new AdministradorRepository();
                AdministradorEntity adm = new AdministradorEntity();

                // Preenchendo dados da pessoa
                pessoa.setNome(nomeAdm);
                pessoa.setCpf(cpfAdm);
                pessoa.setTipo(TipoPessoa.ADMINISTRADOR);

                // Conversão da data de nascimento para LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate dataNascimento = LocalDate.parse(dataNascAdm, formatter);
                pessoa.setDataDeNascimento(dataNascimento);

                // Salvando pessoa no banco
                pessoaRepository.salvar(pessoa);

                // Setando senha e vinculando pessoa ao administrador
                adm.setSenha(senhaAdm);
                adm.setPessoaEntity(pessoa);

                // Salvando administrador no banco
                administradorRepository.salvar(adm);

                // Mensagem de sucesso e limpar campos
                JOptionPane.showMessageDialog(null, "Adm cadastrado com sucesso!");
                campoNome.setText("");
                campoCpf.setText("");
                campoNascimento.setText("");
                campoSenha.setText("");

            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });

        // Painel rodapé (vazio, para espaçamento)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(footerPanel);

        // Adiciona os painéis à janela principal
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        sistemaAdm.setVisible(true);
    }

    public static void main(String[] args) {
        new CadastrarAdm(); // Executa a tela de cadastro
    }

}
