package org.grsstreet.view.adm.administrador;

import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.repository.AdministradorRepository;
import org.grsstreet.repository.PessoaRepository;
import org.grsstreet.service.AdmLista;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Tela para listar todos os administradores cadastrados no sistema.
 * Exibe uma tabela com as informações principais e permite voltar para o menu principal.
 */
public class ListarAdms extends JFrame {

    public ListarAdms() {
        // Configurações básicas da janela
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null); // Centraliza a janela
        sistemaAdm.setLayout(new BorderLayout());

        // Definição das cores usadas na interface
        Color backgroundColor = new Color(30, 30, 30);
        Color buttonColor = new Color(45, 120, 200);
        Color TextColor = Color.WHITE;

        // Cabeçalho da janela
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.darkGray);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Listar Adms", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal que contém os componentes da tela
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(backgroundColor);
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço vertical

        // Painel para conter a tabela de administradores
        JPanel dadosAdm = new JPanel();
        dadosAdm.setLayout(new BoxLayout(dadosAdm, BoxLayout.Y_AXIS));
        dadosAdm.setBackground(backgroundColor);
        dadosAdm.setMaximumSize(new Dimension(1000, 400)); // Limita tamanho para melhor visualização
        dadosAdm.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o painel horizontalmente

        // Repositórios para acesso aos dados
        AdministradorRepository administradorRepository = new AdministradorRepository();
        PessoaRepository pessoaRepository = new PessoaRepository();

        // Recupera a lista de administradores do banco de dados
        List<AdministradorEntity> adms = administradorRepository.listarTodosAdm();

        // Define as colunas da tabela
        String[] colunas = {"Nome", "Cpf", "data de nascimento", "senha"};

        // Monta os dados da tabela no formato String[][] usando o método auxiliar da classe AdmLista
        String[][] dados = AdmLista.construirTabela(adms);

        // Criação da JTable com os dados e colunas definidos
        JTable tabela = new JTable(dados, colunas);
        tabela.setFillsViewportHeight(true); // Faz a tabela preencher a altura do viewport

        // Adiciona a tabela em um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(900, 200)); // Tamanho visível do scroll pane

        dadosAdm.add(scrollPane); // Adiciona o scroll pane ao painel de dados

        panelAdm.add(dadosAdm); // Adiciona o painel de dados ao painel principal

        // Rodapé (atualmente vazio, para espaçamento)
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(backgroundColor);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 50))); // Espaço extra
        panelAdm.add(footerPanel);

        // Botão Voltar para retornar ao menu principal do administrador
        JButton btnV = new JButton("Voltar");
        btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnV.setMaximumSize(new Dimension(300, 60));
        btnV.setPreferredSize(new Dimension(300, 60));
        btnV.setFont(new Font("Arial", Font.BOLD, 20));
        btnV.setBackground(buttonColor);
        btnV.setForeground(Color.WHITE);
        btnV.setFocusPainted(false);
        btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelAdm.add(Box.createRigidArea(new Dimension(0, 100))); // Espaço antes do botão
        panelAdm.add(btnV);

        // Evento do botão Voltar: fecha essa tela e abre a TelaAdmPrincipal
        btnV.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });

        panelAdm.add(Box.createRigidArea(new Dimension(0, 50))); // Espaço abaixo do botão

        // Adiciona os painéis na janela principal
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        sistemaAdm.setVisible(true); // Torna a janela visível
    }

    public static void main(String[] args) {
        new ListarAdms(); // Executa a tela de listagem de administradores
    }
}
