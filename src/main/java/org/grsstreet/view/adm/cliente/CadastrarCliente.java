package org.grsstreet.view.adm.cliente;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;

import org.grsstreet.repository.PessoaRepository;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class CadastrarCliente {

    public CadastrarCliente() {
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Cadastrar Cliente", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout((panelAdm),BoxLayout.Y_AXIS));
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
        panelAdm.add(Box.createRigidArea(new Dimension(0,40)));
        panelAdm.add(labelNome);
        panelAdm.add(campoNome);



        JLabel labelNascimento = new JLabel("Data de Nascimento (dd/mm/aaaa)");
        labelNascimento.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoNascimento = new JTextField();
        campoNascimento.setPreferredSize(new Dimension(300, 60));
        campoNascimento.setMaximumSize(new Dimension(300, 60));
        campoNascimento.setFont(new Font("Arial", Font.PLAIN, 20));
        labelNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0,40)));

        panelAdm.add(labelNascimento);
        panelAdm.add(campoNascimento);


        JLabel labelCpf = new JLabel("CPF");
        labelCpf.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoCpf = new JTextField();
        campoCpf.setPreferredSize(new Dimension(300, 60));
        campoCpf.setMaximumSize(new Dimension(300, 60));
        campoCpf.setFont(new Font("Arial", Font.PLAIN, 20));
        labelCpf.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoCpf.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0,40)));
        panelAdm.add(labelCpf);
        panelAdm.add(campoCpf);


        JLabel labelCep = new JLabel("CEP");
        labelCep.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoCep = new JTextField();
        campoCep.setPreferredSize(new Dimension(300, 60));
        campoCep.setMaximumSize(new Dimension(300, 60));
        campoCep.setFont(new Font("Arial", Font.PLAIN, 20));
        labelCep.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoCep.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0,40)));
        panelAdm.add(labelCep);
        panelAdm.add(campoCep);


        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoSenha = new   JTextField();
        campoSenha.setPreferredSize(new Dimension(300, 60));
        campoSenha.setMaximumSize(new Dimension(300, 60));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 20));
        labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAdm.add(Box.createRigidArea(new Dimension(0,60)));
        panelAdm.add(labelSenha);
        panelAdm.add(campoSenha);


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

        btn.addActionListener(e -> {
            String nomeCliente = campoNome.getText().trim();
            String cpfCliente = campoCpf.getText().trim();
            String cepCliente = campoCep.getText().trim();
            String dataNascCliente = campoNascimento.getText().trim();
            String senhaCliente = campoSenha.getText().trim();

            try {
                // Conecta-se à API do ViaCEP para obter os dados de endereço
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

                // Cria um objeto EnderecoAPI e preenche com os dados da API

                EnderecoEntity enderecoAPI = new EnderecoEntity();
                enderecoAPI.setRua(json.getString("logradouro"));
                enderecoAPI.setBairro(json.getString("bairro"));
                enderecoAPI.setMunicipio(json.getString("localidade"));
                enderecoAPI.setEstado(json.getString("uf"));

            } catch (RuntimeException | IOException ex) {
                throw new RuntimeException(ex);
            }

            try {
                PessoaEntity pessoa = new PessoaEntity();
                pessoa.setNome(nomeCliente);
                pessoa.setCpf(cpfCliente);
                pessoa.setDataDeNascimento(LocalDate.parse(dataNascCliente));
                PessoaRepository pessoaRepository = new PessoaRepository();
                pessoaRepository.salvar(pessoa);

                ClienteEntity cliente = new ClienteEntity();
                cliente.setSenha(senhaCliente);
                cliente.setPessoaEntity(pessoa); // Associa a pessoa ao admin


            } catch (RuntimeException ex) {
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

}