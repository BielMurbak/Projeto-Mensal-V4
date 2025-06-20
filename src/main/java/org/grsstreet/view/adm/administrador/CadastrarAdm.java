package org.grsstreet.view.adm.administrador;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.enums.TipoPessoa;
import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.grsstreet.repository.AdministradorRepository;
import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.repository.EnderecoRepository;
import org.grsstreet.repository.PessoaRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;
import org.grsstreet.view.adm.cliente.CadastrarCliente;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CadastrarAdm extends JFrame{

        public CadastrarAdm() {

            Color backgroundColor = new Color(30, 30, 30);
            Color buttonColor = new Color(45, 120, 200);
            Color TextColor = Color.WHITE;
            Color headerColor = new Color(20, 20, 20);

            JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
            sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sistemaAdm.setSize(1080, 720);
            sistemaAdm.setLocationRelativeTo(null);
            sistemaAdm.setLayout(new BorderLayout());

            JPanel headerPanel = new JPanel(new BorderLayout());
            headerPanel.setBackground(Color.DARK_GRAY);
            headerPanel.setPreferredSize(new Dimension(700, 40));

            JLabel titleLabel = new JLabel("Cadastrar Adm", SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            headerPanel.add(titleLabel, BorderLayout.CENTER);
            JPanel panelAdm = new JPanel();
            panelAdm.setLayout(new BoxLayout((panelAdm),BoxLayout.Y_AXIS));
            panelAdm.setBackground(backgroundColor);
            panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));


            JLabel labelNome = new JLabel("Nome");
            labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
            labelNome.setForeground(Color.WHITE);

            JTextField campoNome = new JTextField();
            campoNome.setPreferredSize(new Dimension(300, 60));
            campoNome.setMaximumSize(new Dimension(300, 60));
            campoNome.setFont(new Font("Arial", Font.PLAIN, 20));
            campoNome.setBackground(Color.DARK_GRAY); // apenas esse!
            campoNome.setForeground(Color.WHITE);     // texto branco
            campoNome.setCaretColor(Color.WHITE);     // cursor branco
            campoNome.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // borda branca

            labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
            panelAdm.add(labelNome);
            panelAdm.add(campoNome);



            JLabel labelNascimento = new JLabel("Data de Nascimento yyyy/MM/dd");
            labelNascimento.setFont(new Font("Arial", Font.PLAIN, 22));
            labelNascimento.setForeground(TextColor);

            JTextField campoNascimento = new JTextField();
            campoNascimento.setPreferredSize(new Dimension(300, 60));
            campoNascimento.setMaximumSize(new Dimension(300, 60));
            campoNascimento.setFont(new Font("Arial", Font.PLAIN, 20));

// Cor de fundo cinza escuro e texto branco
            campoNascimento.setBackground(Color.DARK_GRAY);
            campoNascimento.setForeground(Color.WHITE);
            campoNascimento.setCaretColor(Color.WHITE);
            campoNascimento.setBorder(BorderFactory.createLineBorder(Color.WHITE));

            labelNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);
            campoNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
            panelAdm.add(labelNascimento);
            panelAdm.add(campoNascimento);



            // CPF
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

// Senha
            JLabel labelSenha = new JLabel("Senha");
            labelSenha.setFont(new Font("Arial", Font.PLAIN, 22));
            labelSenha.setForeground(TextColor);

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



            JButton btn = new JButton("Criar");
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(300,60));
            btn.setBackground(buttonColor);
            btn.setForeground(TextColor);
            btn.setPreferredSize(new Dimension (300,60));
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelAdm.add(Box.createRigidArea(new Dimension(0,60)));
            panelAdm.add(btn);

            JButton btnV = new JButton("Voltar");
            btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnV.setMaximumSize(new Dimension(300,60));
            btnV.setPreferredSize(new Dimension (300,60));
            btnV.setFont(new Font("Arial", Font.BOLD, 20));
                     btnV.setBackground(buttonColor);
            btnV.setForeground(TextColor);
            btnV.setFocusPainted(false);
            btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
            panelAdm.add(btnV);

            btnV.addActionListener(event ->{
                JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
                new TelaAdmPrincipal();
                sistemaAdm.dispose();
            });

            btn.addActionListener(e -> {
                String nomeAdm = campoNome.getText().trim();
                String cpfAdm = campoCpf.getText().trim();
                String dataNascAdm = campoNascimento.getText().trim();
                String senhaAdm = campoSenha.getText().trim();


                try {
                    PessoaRepository pessoaRepository = new PessoaRepository();
                    PessoaEntity pessoa = new PessoaEntity();
                    AdministradorRepository administradorRepository = new AdministradorRepository();
                    AdministradorEntity adm = new AdministradorEntity();

                    pessoa.setNome(nomeAdm);
                    pessoa.setCpf(cpfAdm);
                    pessoa.setTipo(TipoPessoa.ADMINISTRADOR);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate dataNascimento = LocalDate.parse(dataNascAdm, formatter);
                    pessoa.setDataDeNascimento(dataNascimento);
                    pessoaRepository.salvar(pessoa);
                    adm.setSenha(senhaAdm);

                    adm.setPessoaEntity(pessoa);
                    administradorRepository.salvar(adm);


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
        new CadastrarAdm();
    }


    }
