package org.grsstreet.session;

import org.grsstreet.model.user.ClienteEntity;
import org.grsstreet.model.user.AdministradorEntity;

public class Sessao {

    private static ClienteEntity clienteLogado;
    private static AdministradorEntity administradorLogado;

    public static ClienteEntity getClienteLogado() {
        return clienteLogado;
    }

    public static void setClienteLogado(ClienteEntity cliente) {
        clienteLogado = cliente;
    }

    public static AdministradorEntity getAdministradorLogado() {
        return administradorLogado;
    }

    public static void setAdministradorLogado(AdministradorEntity administrador) {
        administradorLogado = administrador;
    }

    public static void limpar() {
        clienteLogado = null;
        administradorLogado = null;
    }
}