package org.grsstreet.service;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;

import java.util.List;

/**
 * Classe utilitária responsável por construir uma matriz de dados com informações dos clientes.
 * Útil para exibição em interfaces gráficas como tabelas (ex: JTable).
 */
public class ClienteLista {

    /**
     * Constrói uma tabela (matriz de String) com dados de clientes, pessoas e seus endereços.
     *
     * Cada linha da matriz representa um cliente, com as seguintes colunas:
     * [0] Nome
     * [1] CPF
     * [2] Data de Nascimento
     * [3] Senha
     * [4] Rua
     * [5] Bairro
     * [6] Município
     * [7] Estado
     * [8] CEP
     *
     * @param clientes  Lista de clientes
     * @param pessoas   Lista de dados pessoais correspondentes
     * @param enderecos Lista de endereços correspondentes
     * @return matriz de strings com os dados formatados
     */
    public static String[][] construirTabela(
            List<ClienteEntity> clientes,
            List<PessoaEntity> pessoas,
            List<EnderecoEntity> enderecos) {

        String[][] dados = new String[clientes.size()][9]; // 9 colunas por cliente

        for (int i = 0; i < clientes.size(); i++) {
            ClienteEntity cliente = clientes.get(i);
            PessoaEntity pessoa = pessoas.get(i);
            EnderecoEntity endereco = enderecos.get(i);

            // Dados pessoais
            dados[i][0] = pessoa.getNome();
            dados[i][1] = pessoa.getCpf();
            dados[i][2] = pessoa.getDataDeNascimento().toString();
            dados[i][3] = cliente.getSenha(); // ⚠️ Senha em texto pode ser um risco de segurança

            // Dados de endereço (checando se não é null)
            dados[i][4] = (endereco != null) ? endereco.getRua() : "";
            dados[i][5] = (endereco != null) ? endereco.getBairro() : "";
            dados[i][6] = (endereco != null) ? endereco.getMunicipio() : "";
            dados[i][7] = (endereco != null) ? endereco.getEstado() : "";
            dados[i][8] = (endereco != null) ? endereco.getCep() : "";
        }

        return dados;
    }
}
