package org.grsstreet.service;

import org.grsstreet.model.address.EnderecoEntity;
import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.PessoaEntity;

import java.util.List;

public class ClienteLista {



    public static String[][] construirTabela(
            List<ClienteEntity> clientes,
            List<PessoaEntity> pessoas,
            List<EnderecoEntity> enderecos) {

        String[][] dados = new String[clientes.size()][9];

        for (int i = 0; i < clientes.size(); i++) {
            ClienteEntity cliente = clientes.get(i);
            PessoaEntity pessoa = pessoas.get(i);
            EnderecoEntity endereco = enderecos.get(i);

            dados[i][0] = pessoa.getNome();
            dados[i][1] = pessoa.getCpf();
            dados[i][2] = pessoa.getDataDeNascimento().toString();
            dados[i][3] = cliente.getSenha();

            dados[i][4] = (endereco != null) ? endereco.getRua() : "";
            dados[i][5] = (endereco != null) ? endereco.getBairro() : "";
            dados[i][6] = (endereco != null) ? endereco.getMunicipio() : "";
            dados[i][7] = (endereco != null) ? endereco.getEstado() : "";
            dados[i][8] = (endereco != null) ? endereco.getCep() : "";
        }

        return dados;
    }

}
