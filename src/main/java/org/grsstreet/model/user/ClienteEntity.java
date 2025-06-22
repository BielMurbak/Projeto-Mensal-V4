package org.grsstreet.model.user;

import org.grsstreet.model.address.EnderecoEntity;

import javax.persistence.*;

/**
 * Representa um cliente no sistema.
 * Contém informações de login, dados pessoais e endereço.
 */
@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do cliente

    @Column(name = "senha", nullable = false)
    private String senha; // Senha de acesso do cliente

    /**
     * Relação 1:1 com a entidade Pessoa.
     * Armazena os dados pessoais do cliente (nome, CPF, email, etc.).
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private PessoaEntity pessoa;

    /**
     * Relação 1:1 com a entidade Endereco.
     * Armazena o endereço completo do cliente.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private EnderecoEntity enderecoEntity;

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public PessoaEntity getPessoa() { return pessoa; }

    public void setPessoa(PessoaEntity pessoa) { this.pessoa = pessoa; }

    public EnderecoEntity getEnderecoEntity() { return enderecoEntity; }

    public void setEnderecoEntity(EnderecoEntity enderecoEntity) { this.enderecoEntity = enderecoEntity; }
}
