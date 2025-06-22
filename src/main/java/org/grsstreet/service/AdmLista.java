package org.grsstreet.service;

import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.PessoaEntity;

import java.util.List;

/**
 * Classe utilitária responsável por construir uma tabela (matriz de String)
 * contendo informações dos administradores para exibição em interfaces (ex: JTable).
 */
public class AdmLista {

    /**
     * Constrói uma tabela de dados (matriz de Strings) a partir de uma lista de administradores.
     * Cada linha representa um administrador e contém:
     * [0] Nome, [1] CPF, [2] Data de nascimento, [3] Senha.
     *
     * @param adms Lista de administradores
     * @return matriz de dados com as informações formatadas
     */
    public static String[][] construirTabela(List<AdministradorEntity> adms) {
        String[][] dados = new String[adms.size()][4]; // 4 colunas fixas

        for (int i = 0; i < adms.size(); i++) {
            AdministradorEntity adm = adms.get(i);
            PessoaEntity pessoa = adm.getPessoaEntity();  // Obtém os dados pessoais associados

            dados[i][0] = pessoa.getNome();                              // Nome do admin
            dados[i][1] = pessoa.getCpf();                               // CPF
            dados[i][2] = pessoa.getDataDeNascimento().toString();       // Data de nascimento
            dados[i][3] = adm.getSenha();                                // Senha (⚠️ cuidado com exibição)
        }

        return dados;
    }
}
