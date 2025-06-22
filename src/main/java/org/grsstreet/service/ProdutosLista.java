package org.grsstreet.service;

import org.grsstreet.model.product.ProdutoEntity;

import java.util.List;

/**
 * Classe utilitária para construir uma tabela em formato de matriz String[][]
 * a partir de uma lista de produtos.
 */
public class ProdutosLista {

    /**
     * Constrói uma matriz de dados com as informações dos produtos.
     * Cada linha representa um produto e as colunas são:
     * Nome, Preço, Imagem, Quantidade e Tipo do produto.
     *
     * @param produtos lista de produtos
     * @return matriz String[][] com dados para exibição em tabela
     */
    public static String[][] construirTabela(List<ProdutoEntity> produtos) {
        // 5 colunas: Nome, Preço, Imagem, Quantidade, Tipo
        String[][] dados = new String[produtos.size()][5];

        for (int i = 0; i < produtos.size(); i++) {
            ProdutoEntity produto = produtos.get(i);

            dados[i][0] = produto.getNome();
            dados[i][1] = String.valueOf(produto.getPreco());
            dados[i][2] = produto.getImagem();
            dados[i][3] = String.valueOf(produto.getQuantidade());
            dados[i][4] = String.valueOf(produto.getTipo());
        }

        return dados;
    }
}
