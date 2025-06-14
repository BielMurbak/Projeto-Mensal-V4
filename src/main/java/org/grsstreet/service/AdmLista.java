package org.grsstreet.service;


import org.grsstreet.model.user.AdministradorEntity;
import org.grsstreet.model.user.PessoaEntity;
import java.util.List;




    public class AdmLista {

        public static String[][] construirTabela(
                List<AdministradorEntity> adms)
        {
            String[][] dados = new String[adms.size()][4];

            for (int i = 0; i < adms.size(); i++) {
                AdministradorEntity adm = adms.get(i);
                PessoaEntity pessoa = adm.getPessoaEntity();  // pega direto da associação

                dados[i][0] = pessoa.getNome();
                dados[i][1] = pessoa.getCpf();
                dados[i][2] = pessoa.getDataDeNascimento().toString();
                dados[i][3] = adm.getSenha();
            }

            return dados;
        }
    }

