package org.grsstreet.session;

import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.AdministradorEntity;

/**
 * Classe responsável por armazenar a sessão atual da aplicação,
 * guardando o usuário logado, seja ele um cliente ou um administrador.
 *
 * Essa classe utiliza variáveis estáticas para manter o estado da sessão
 * durante a execução do programa.
 */
public class Sessao {

    private static ClienteEntity clienteLogado;
    private static AdministradorEntity administradorLogado;

    /**
     * Obtém o cliente que está atualmente logado na sessão.
     *
     * @return o objeto ClienteEntity logado ou null se nenhum cliente estiver logado
     */
    public static ClienteEntity getClienteLogado() {
        return clienteLogado;
    }

    /**
     * Define o cliente que está logado na sessão.
     *
     * @param cliente o objeto ClienteEntity que será setado como logado
     */
    public static void setClienteLogado(ClienteEntity cliente) {
        clienteLogado = cliente;
    }

    /**
     * Obtém o administrador que está atualmente logado na sessão.
     *
     * @return o objeto AdministradorEntity logado ou null se nenhum administrador estiver logado
     */
    public static AdministradorEntity getAdministradorLogado() {
        return administradorLogado;
    }

    /**
     * Define o administrador que está logado na sessão.
     *
     * @param administrador o objeto AdministradorEntity que será setado como logado
     */
    public static void setAdministradorLogado(AdministradorEntity administrador) {
        administradorLogado = administrador;
    }

    /**
     * Limpa a sessão, removendo quaisquer usuários logados.
     * Deve ser chamado, por exemplo, ao fazer logout.
     */
    public static void limpar() {
        clienteLogado = null;
        administradorLogado = null;
    }
}
