package org.grsstreet.view;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.enums.TipoPessoa;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;

import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.repository.EnderecoRepository;
import org.grsstreet.repository.PessoaRepository;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaCadastro extends JFrame {

    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField nascimentoField;
    private JTextField cepField;
    private JPasswordField senhaField;
    private JPasswordField repetirSenhaField;

    public TelaCadastro() {
        setTitle("GR's Street - Cadastro");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 300, 40, 300));

        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Cadastrar", JLabel.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 32));
        subtitulo.setForeground(Color.DARK_GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(subtitulo);
        painelPrincipal.add(Box.createVerticalStrut(40));

        nomeField = new JTextField();
        cpfField = new JTextField();
        nascimentoField = new JTextField();
        cepField = new JTextField();
        senhaField = new JPasswordField();
        repetirSenhaField = new JPasswordField();

        painelPrincipal.add(criarCampo("Nome:", nomeField));
        painelPrincipal.add(criarCampo("CPF:", cpfField));
        painelPrincipal.add(criarCampo("Data de Nascimento (yyyy/MM/dd):", nascimentoField));
        painelPrincipal.add(criarCampo("CEP (xxxxx-xxx):", cepField));
        painelPrincipal.add(criarCampo("Senha:", senhaField));
        painelPrincipal.add(criarCampo("Repetir Senha:", repetirSenhaField));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setMaximumSize(new Dimension(600, 60));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCadastrar.setPreferredSize(new Dimension(180, 50));

        JButton btnVoltarLogin = new JButton("Voltar ao Login");
        btnVoltarLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltarLogin.setPreferredSize(new Dimension(180, 50));

        btnVoltarLogin.addActionListener(e -> {
            dispose();
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });

        btnCadastrar.addActionListener(e -> {
            cadastrarCliente();
        });

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnVoltarLogin);

        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(painelBotoes);

        JScrollPane scrollPane = new JScrollPane(painelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setContentPane(scrollPane);
    }

    private JPanel criarCampo(String labelText, JComponent campo) {
        JPanel painelCampo = new JPanel();
        painelCampo.setLayout(new BoxLayout(painelCampo, BoxLayout.Y_AXIS));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        campo.setPreferredSize(new Dimension(400, 35));
        campo.setMaximumSize(new Dimension(Short.MAX_VALUE, 35));
        campo.setFont(new Font("Arial", Font.PLAIN, 18));
        painelCampo.add(label);
        painelCampo.add(Box.createVerticalStrut(5));
        painelCampo.add(campo);
        painelCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCampo.setMaximumSize(new Dimension(600, 80));
        painelCampo.add(Box.createVerticalStrut(15));
        return painelCampo;
    }

    private void cadastrarCliente() {
        String nome = nomeField.getText().trim();
        String cpf = cpfField.getText().trim();
        String nascimento = nascimentoField.getText().trim();
        String cep = cepField.getText().trim();
        String senha = new String(senhaField.getPassword());
        String repetirSenha = new String(repetirSenhaField.getPassword());

        if (nome.isEmpty() || cpf.isEmpty() || nascimento.isEmpty() || cep.isEmpty() || senha.isEmpty() || repetirSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!senha.equals(repetirSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar formato da data
        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            dataNascimento = LocalDate.parse(nascimento, formatter);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato yyyy/MM/dd.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar dados do endereço pela API ViaCEP
        EnderecoEntity enderecoAPI = new EnderecoEntity();
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            HttpURLConnection conexao = (HttpURLConnection) new URL(url).openConnection();
            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(5000);
            conexao.setReadTimeout(5000);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            JSONObject json = new JSONObject(resposta.toString());

            if (json.has("erro")) {
                JOptionPane.showMessageDialog(this, "CEP não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            enderecoAPI.setRua(json.optString("logradouro", ""));
            enderecoAPI.setBairro(json.optString("bairro", ""));
            enderecoAPI.setMunicipio(json.optString("localidade", ""));
            enderecoAPI.setEstado(json.optString("uf", ""));
            enderecoAPI.setCep(cep);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar o CEP: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Persistir Pessoa, Endereco e Cliente no banco
        try {
            PessoaRepository pessoaRepository = new PessoaRepository();
            EnderecoRepository enderecoRepository = new EnderecoRepository();
            ClienteRepository clienteRepository = new ClienteRepository();

            PessoaEntity pessoa = new PessoaEntity();
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setTipo(TipoPessoa.CLIENTE);
            pessoa.setDataDeNascimento(dataNascimento);

            // Salvar endereço e pessoa antes de criar cliente
            enderecoRepository.salvar(enderecoAPI);
            pessoaRepository.salvar(pessoa);

            ClienteEntity cliente = new ClienteEntity();
            cliente.setSenha(senha);
            cliente.setPessoa(pessoa);
            cliente.setEnderecoEntity(enderecoAPI);

            clienteRepository.salvar(cliente);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

            // Limpar campos
            nomeField.setText("");
            cpfField.setText("");
            nascimentoField.setText("");
            cepField.setText("");
            senhaField.setText("");
            repetirSenhaField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaCadastro().setVisible(true);
        });
    }
}
