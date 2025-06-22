package org.grsstreet.view;

import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.repository.AdministradorRepository;
import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.session.Sessao;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    /**
     * Construtor da TelaLogin.
     * Inicializa a interface gráfica da tela de login do sistema GR's Street.
     */
    public TelaLogin() {
        // Configurações básicas da janela
        setTitle("GR's Street - Login");
        setSize(1080, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com layout BorderLayout e espaçamento
        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        setContentPane(painelPrincipal);

        // --- Título e subtítulo no topo da tela ---
        JLabel titulo = new JLabel("GR's Street", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 48));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Entrar", JLabel.CENTER);
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 32));
        subtitulo.setForeground(Color.DARK_GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel que agrupa título e subtítulo verticalmente
        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(new BoxLayout(painelTitulo, BoxLayout.Y_AXIS));
        painelTitulo.add(titulo);
        painelTitulo.add(Box.createVerticalStrut(30)); // Espaço vertical
        painelTitulo.add(subtitulo);
        painelTitulo.add(Box.createVerticalStrut(50)); // Espaço vertical

        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // --- Painel central com campos de usuário e senha ---
        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0));

        // Campo Usuário
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 22));
        JTextField campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(800, 40));
        campoUsuario.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 18));

        // Campo Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 22));
        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setPreferredSize(new Dimension(800, 40));
        campoSenha.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));
        campoSenha.setFont(new Font("Arial", Font.PLAIN, 18));

        // Adiciona componentes ao painel de campos, com espaçamento vertical
        painelCampos.add(lblUsuario);
        painelCampos.add(Box.createVerticalStrut(10));
        painelCampos.add(campoUsuario);
        painelCampos.add(Box.createVerticalStrut(30));
        painelCampos.add(lblSenha);
        painelCampos.add(Box.createVerticalStrut(10));
        painelCampos.add(campoSenha);

        painelPrincipal.add(painelCampos, BorderLayout.CENTER);

        // --- Painel inferior com botões Entrar e Cadastrar ---
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setPreferredSize(new Dimension(180, 50));
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setPreferredSize(new Dimension(180, 50));
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 16));

        painelBotoes.add(btnEntrar);
        painelBotoes.add(btnCadastrar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        /**
         * Ação do botão Entrar.
         * Realiza a autenticação do usuário ou administrador baseado na senha informada.
         * Exibe mensagens apropriadas e direciona para a tela correta.
         */
        btnEntrar.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            AdministradorRepository administradorRepository = new AdministradorRepository();
            ClienteRepository clienteRepository = new ClienteRepository();

            // Verifica se existe um administrador com a senha informada
            if (administradorRepository.buscarPorSenha(senha)) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                new TelaAdmPrincipal().setVisible(true); // Mostrar tela do admin
                dispose(); // Fechar a tela atual
                return;
            }

            // Busca cliente pelo senha informada
            ClienteEntity cliente = clienteRepository.buscarClientePorSenha(senha);

            if (cliente != null) {
                Sessao.setClienteLogado(cliente); // Armazena cliente na sessão
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                new TelaMenuPrincipal().setVisible(true); // Abre tela principal do cliente
                dispose(); // Fecha tela de login
            } else {
                // Caso usuário e senha não sejam encontrados
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!");
            }
        });

        /**
         * Ação do botão Cadastrar.
         * Fecha a tela atual e abre a tela de cadastro para novos usuários.
         */
        btnCadastrar.addActionListener(e -> {
            dispose();
            new TelaCadastro().setVisible(true);
        });

        setVisible(true); // Torna a janela visível
    }
}
