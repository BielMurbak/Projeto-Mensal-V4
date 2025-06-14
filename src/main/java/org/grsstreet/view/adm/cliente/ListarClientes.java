package org.grsstreet.view.adm.cliente;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;
import org.grsstreet.repository.ClienteRepository;
import org.grsstreet.repository.EnderecoRepository;
import org.grsstreet.repository.PessoaRepository;
import org.grsstreet.service.ClienteLista;
import org.grsstreet.view.adm.TelaAdmPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.List; // Import correto para coleções


public class ListarClientes {

    public ListarClientes () {
        JFrame sistemaAdm = new JFrame("Sistema Adm GR's street");
        sistemaAdm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sistemaAdm.setSize(1080, 720);
        sistemaAdm.setLocationRelativeTo(null);
        sistemaAdm.setLayout(new BorderLayout());

        // Cabeçalho
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.DARK_GRAY);
        headerPanel.setPreferredSize(new Dimension(700, 40));

        JLabel titleLabel = new JLabel("Listar Clientes", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Painel principal
        JPanel panelAdm = new JPanel();
        panelAdm.setLayout(new BoxLayout(panelAdm, BoxLayout.Y_AXIS));
        panelAdm.setBackground(Color.DARK_GRAY);  // cor igual à da imagem
        panelAdm.add(Box.createRigidArea(new Dimension(0, 10)));

        // Painel com os dados da tabela
        JPanel dadosAdm = new JPanel();
        dadosAdm.setLayout(new BoxLayout(dadosAdm, BoxLayout.Y_AXIS)); // CORRIGIDO
        dadosAdm.setBackground(Color.LIGHT_GRAY);
        dadosAdm.setMaximumSize(new Dimension(1000, 400)); // ajuda a garantir visibilidade
        dadosAdm.setAlignmentX(Component.CENTER_ALIGNMENT); // centraliza horizontalmente

        EnderecoRepository enderecoRepository = new EnderecoRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        PessoaRepository pessoaRepository = new PessoaRepository();

        List<ClienteEntity> clientes = clienteRepository.listarTodosClientes();
        List<PessoaEntity> pessoas = pessoaRepository.listarTodosPessoas();
        List<EnderecoEntity> enderecos = enderecoRepository.listarEnderecoPessoas();

        String[] colunas = {"Nome", "Cpf", "data de nascimento","senha","rua","bairro","municipio","estado","Cep"};
        String[][] dados = ClienteLista.construirTabela(clientes, pessoas, enderecos);

        JTable tabela = new JTable(dados, colunas);
        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(900, 200)); // GARANTE TAMANHO VISÍVEL
        dadosAdm.add(scrollPane);

        panelAdm.add(dadosAdm); // ESSENCIAL PARA EXIBIR A TABELA

        // Rodapé
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setPreferredSize(new Dimension(700, 60));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 50)));

        panelAdm.add(footerPanel);


        JButton btnV = new JButton("Voltar");
        btnV.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnV.setMaximumSize(new Dimension(300,60));
        btnV.setPreferredSize(new Dimension (300,60));
        btnV.setFont(new Font("Arial", Font.BOLD, 20));
        btnV.setForeground(Color.WHITE);
        btnV.setFocusPainted(false);
        btnV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelAdm.add(Box.createRigidArea(new Dimension(0, 100)));
        panelAdm.add(btnV);

        btnV.addActionListener(e ->{
            JOptionPane.showMessageDialog(null, "Voltando ao menu adm", "Voltando", JOptionPane.INFORMATION_MESSAGE);
            new TelaAdmPrincipal();
            sistemaAdm.dispose();
        });


        // Adiciona os painéis à janela
        sistemaAdm.add(headerPanel, BorderLayout.NORTH);
        sistemaAdm.add(panelAdm, BorderLayout.CENTER);

        sistemaAdm.setVisible(true);
    }

    public static void main(String[] args) {
        new ListarClientes();
    }
}
