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

/**
 * Tela para cadastro de clientes no sistema GR's Street.
 * Permite a entrada de dados pessoais e endereço via CEP,
 * valida os dados e persiste no banco de dados.
 */
public class TelaCadastro extends JFrame {

    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField nascimentoField;
    private JTextField cepField;
    private JPasswordField senhaField;
    private JPasswordField repetirSenhaField;

    /**
     * Construtor que configura a interface gráfica do cadastro.
     */
    public TelaCadastro() {
        setTitle("GR's Street - Cadastro");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout vertical
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40, 300, 40, 300));

        // Título da tela
        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo da tela
        JLabel subtitulo = new JLabel("Cadastrar", JLabel.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 32));
        subtitulo.setForeground(Color.DARK_GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adiciona títulos ao painel principal
        painelPrincipal.add(titulo);
        painelPrincipal.add(Box.createVerticalStrut(30)); // Espaço vertical
        painelPrincipal.add(subtitulo);
        painelPrincipal.add(Box.createVerticalStrut(40));

        // Cria os campos de entrada para os dados do cliente
        nomeField = new JTextField();
        cpfField = new JTextField();
        nascimentoField = new JTextField();
        cepField = new JTextField();
        senhaField = new JPasswordField();
        repetirSenhaField = new JPasswordField();

        // Adiciona campos ao painel principal com labels
        painelPrincipal.add(criarCampo("Nome:", nomeField));
        painelPrincipal.add(criarCampo("CPF:", cpfField));
        painelPrincipal.add(criarCampo("Data de Nascimento (yyyy/MM/dd):", nascimentoField));
        painelPrincipal.add(criarCampo("CEP (xxxxx-xxx):", cepField));
        painelPrincipal.add(criarCampo("Senha:", senhaField));
        painelPrincipal.add(criarCampo("Repetir Senha:", repetirSenhaField));

        // Painel para os botões de ação
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.setMaximumSize(new Dimension(600, 60));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnCadastrar.setPreferredSize(new Dimension(180, 50));

        JButton btnVoltarLogin = new JButton("Voltar ao Login");
        btnVoltarLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnVoltarLogin.setPreferredSize(new Dimension(180, 50));

        // Ação do botão "Voltar ao Login" fecha essa tela e abre a tela de login
        btnVoltarLogin.addActionListener(e -> {
            dispose();
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
        });

        // Ação do botão "Cadastrar" dispara o método que cadastra o cliente
        btnCadastrar.addActionListener(e -> {
            cadastrarCliente();
        });

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnVoltarLogin);

        // Espaço e adição do painel de botões
        painelPrincipal.add(Box.createVerticalStrut(30));
        painelPrincipal.add(painelBotoes);

        // Usa JScrollPane para permitir scroll se a janela ficar pequena
        JScrollPane scrollPane = new JScrollPane(painelPrincipal);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setContentPane(scrollPane);
    }

    /**
     * Método auxiliar para criar um painel com label e campo de entrada.
     *
     * @param labelText Texto da label
     * @param campo Campo de entrada (JTextField, JPasswordField etc)
     * @return JPanel configurado contendo a label e o campo
     */
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

    /**
     * Método que valida os dados do formulário e realiza o cadastro do cliente.
     * Faz validações locais, consulta a API ViaCEP para preencher endereço,
     * e persiste os dados utilizando os repositórios.
     */
    private void cadastrarCliente() {
        String nome = nomeField.getText().trim();
        String cpf = cpfField.getText().trim();
        String nascimento = nascimentoField.getText().trim();
        String cep = cepField.getText().trim();
        String senha = new String(senhaField.getPassword());
        String repetirSenha = new String(repetirSenhaField.getPassword());

        // Verifica campos obrigatórios
        if (nome.isEmpty() || cpf.isEmpty() || nascimento.isEmpty() || cep.isEmpty() || senha.isEmpty() || repetirSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se senhas coincidem
        if (!senha.equals(repetirSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação da data de nascimento
        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            dataNascimento = LocalDate.parse(nascimento, formatter);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato yyyy/MM/dd.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Consulta dados do endereço via API ViaCEP usando o CEP informado
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

            // Verifica se o CEP é válido
            if (json.has("erro")) {
                JOptionPane.showMessageDialog(this, "CEP não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Preenche os dados do endereço a partir da resposta da API
            enderecoAPI.setRua(json.optString("logradouro", ""));
            enderecoAPI.setBairro(json.optString("bairro", ""));
            enderecoAPI.setMunicipio(json.optString("localidade", ""));
            enderecoAPI.setEstado(json.optString("uf", ""));
            enderecoAPI.setCep(cep);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar o CEP: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Persistência dos dados no banco via repositórios
        try {
            PessoaRepository pessoaRepository = new PessoaRepository();
            EnderecoRepository enderecoRepository = new EnderecoRepository();
            ClienteRepository clienteRepository = new ClienteRepository();

            PessoaEntity pessoa = new PessoaEntity();
            pessoa.setNome(nome);
            pessoa.setCpf(cpf);
            pessoa.setTipo(TipoPessoa.CLIENTE);
            pessoa.setDataDeNascimento(dataNascimento);

            // Primeiro salva o endereço e a pessoa
            enderecoRepository.salvar(enderecoAPI);
            pessoaRepository.salvar(pessoa);

            ClienteEntity cliente = new ClienteEntity();
            cliente.setSenha(senha);
            cliente.setPessoa(pessoa);
            cliente.setEnderecoEntity(enderecoAPI);

            // Salva o cliente no banco
            clienteRepository.salvar(cliente);

            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");

            // Limpa os campos do formulário após sucesso
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

    /**
     * Método principal para iniciar a aplicação.
     * Executa a criação e exibição da tela de cadastro na thread Swing.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaCadastro().setVisible(true);
        });
    }
}
