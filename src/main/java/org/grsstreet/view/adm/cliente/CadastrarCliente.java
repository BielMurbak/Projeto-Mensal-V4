package org.grsstreet.view.adm.cliente;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.enums.TipoPessoa;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;

import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.repository.EnderecoRepository;
import org.grsstreet.repository.PessoaRepository;
import org.grsstreet.view.adm.TelaAdmPrincipal;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CadastrarCliente extends JFrame {

    public CadastrarCliente() {

        Color backgroundColor = new Color(30, 30, 30);
        Color buttonColor = new Color(45, 120, 200);
        Color TextColor = Color.WHITE;
        Color headerColor = new Color(20, 20, 20);

        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 730);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Cadastrar Cliente", SwingConstants.CENTER);
        titleLabel.setForeground(TextColor);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout((panelAdm),BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(new Font("Arial", Font.PLAIN, 22));
        labelNome.setForeground(TextColor);
        labelNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(300, 30));
        campoNome.setMaximumSize(new Dimension(300, 30));
        campoNome.setFont(new Font("Arial", Font.PLAIN, 30));
        campoNome.setBackground(Color.DARK_GRAY);
        campoNome.setForeground(TextColor);
        campoNome.setCaretColor(TextColor);
        campoNome.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(labelNome);
        panelAdm.add(campoNome);



        JLabel labelNascimento = new JLabel("Data de Nascimento yyyy/mm/dd");
        labelNascimento.setFont(new Font("Arial", Font.PLAIN, 22));
        labelNascimento.setForeground(TextColor);
        labelNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoNascimento = new JTextField();
        campoNascimento.setPreferredSize(new Dimension(300, 30));
        campoNascimento.setMaximumSize(new Dimension(300, 30));
        campoNascimento.setFont(new Font("Arial", Font.PLAIN, 30));
        campoNascimento.setBackground(Color.DARK_GRAY);
        campoNascimento.setForeground(TextColor);
        campoNascimento.setCaretColor(TextColor);
        campoNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(labelNascimento);
        panelAdm.add(campoNascimento);


        JLabel labelCpf = new JLabel("CPF");
        labelCpf.setFont(new Font("Arial", Font.BOLD, 22)); // Negrito para destaque
        labelCpf.setForeground(TextColor);
        labelCpf.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoCpf = new JTextField();
        campoCpf.setPreferredSize(new Dimension(300, 30));
        campoCpf.setMaximumSize(new Dimension(300, 30));
        campoCpf.setFont(new Font("Arial", Font.PLAIN, 30));
        campoCpf.setBackground(Color.DARK_GRAY);
        campoCpf.setForeground(TextColor);
        campoCpf.setCaretColor(TextColor);
        campoCpf.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(labelCpf);
        panelAdm.add(campoCpf);



        JLabel labelCep = new JLabel("CEP");
        labelCep.setFont(new Font("Arial", Font.BOLD, 22));
        labelCep.setForeground(TextColor);
        labelCep.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoCep = new JTextField();
        campoCep.setPreferredSize(new Dimension(300, 30));
        campoCep.setMaximumSize(new Dimension(300, 30));
        campoCep.setFont(new Font("Arial", Font.PLAIN, 30));
        campoCep.setBackground(Color.DARK_GRAY);
        campoCep.setForeground(TextColor);
        campoCep.setCaretColor(TextColor);
        campoCep.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(labelCep);
        panelAdm.add(campoCep);



        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setFont(new Font("Arial", Font.BOLD, 22));
        labelSenha.setForeground(TextColor);
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(300, 30));
        campoSenha.setMaximumSize(new Dimension(300, 30));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 30));
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setBackground(Color.DARK_GRAY); //
        campoSenha.setForeground(TextColor);
        campoSenha.setCaretColor(TextColor);

        panelAdm.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAdm.add(labelSenha);
        panelAdm.add(campoSenha);



        JButton btn = new JButton("Criar");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(300,20));
        btn.setPreferredSize(new Dimension (300,20));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(buttonColor);
        btn.setFocusPainted(false);
        btn.setForeground(TextColor);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0,40)));
        panelAdm.add(btn);

        JButton btnV = new JButton("Voltar");
        btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnV.setMaximumSize(new Dimension(300,20));
        btnV.setPreferredSize(new Dimension (300,20));
        btnV.setFont(new Font("Arial", Font.BOLD, 20));
        btnV.setBackground(buttonColor);
        btnV.setForeground(TextColor);
        btnV.setFocusPainted(false);
        btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 40)));
        panelAdm.add(btnV);

        btnV.addActionListener(event ->{
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        btn.addActionListener(e -> {
            String nomeCliente = campoNome.getText().trim();
            String cpfCliente = campoCpf.getText().trim();
            String cepCliente = campoCep.getText().trim();
            String dataNascCliente = campoNascimento.getText().trim();
            String senhaCliente = campoSenha.getText().trim();

            EnderecoEntity enderecoAPI = new EnderecoEntity();

           try {
              //   Conecta-se à API do ViaCEP para obter os dados de endereço
              String url = "https://viacep.com.br/ws/" + cepCliente + "/json/";

               HttpURLConnection conexao = (HttpURLConnection) new URL(url).openConnection();
               conexao.setRequestMethod("GET");

                 BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
               StringBuilder resposta = new StringBuilder();
               String linha;

                while ((linha = reader.readLine()) != null) {
               resposta.append(linha);  // Lê e concatena as linhas da resposta
               }
                reader.close();
                // Converte a resposta para um objeto JSON
                JSONObject json = new JSONObject(resposta.toString());

                //Cria um objeto EnderecoAPI e preenche com os dados da API


              enderecoAPI.setRua(json.getString("logradouro"));
             enderecoAPI.setBairro(json.getString("bairro"));
              enderecoAPI.setMunicipio(json.getString("localidade"));
             enderecoAPI.setEstado(json.getString("uf"));
               enderecoAPI.setCep(cepCliente);

       } catch (RuntimeException | IOException ex) {
               throw new RuntimeException(ex);
           }

            try {
                PessoaRepository pessoaRepository = new PessoaRepository();
                PessoaEntity pessoa = new PessoaEntity();
                EnderecoRepository enderecoRepository  = new EnderecoRepository();
                ClienteRepository clienteRepository = new ClienteRepository();
                ClienteEntity cliente = new ClienteEntity();

                pessoa.setNome(nomeCliente);
                pessoa.setCpf(cpfCliente);
                pessoa.setTipo(TipoPessoa.CLIENTE);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate dataNascimento = LocalDate.parse(dataNascCliente, formatter);
                pessoa.setDataDeNascimento(dataNascimento);
                enderecoRepository.salvar(enderecoAPI);
                pessoaRepository.salvar(pessoa);
                cliente.setSenha(senhaCliente);

                cliente.setPessoa(pessoa);
                cliente.setEnderecoEntity(enderecoAPI);
                clienteRepository.salvar(cliente);


                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

                campoNome.setText("");
                campoCpf.setText("");
                campoCep.setText("");
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
        new CadastrarCliente();
    }

}