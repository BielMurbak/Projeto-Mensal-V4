package org.grsstreet.session;

import org.grsstreet.model.user.ClienteEntity;

public class SessaoUsuario {
    public static ClienteEntity clienteLogado;

    public static void setCliente(ClienteEntity cliente) {
        clienteLogado = cliente;
    }

    public static ClienteEntity getCliente() {
        return clienteLogado;
    }

    public static void limpar() {
        clienteLogado = null;
    }
}