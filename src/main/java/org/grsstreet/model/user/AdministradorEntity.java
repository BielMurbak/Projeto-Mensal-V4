package org.grsstreet.model.user;

import javax.persistence.*;

/**
 * Representa um administrador no sistema.
 * Cada administrador possui uma pessoa associada (PessoaEntity) e uma senha para acesso.
 */
@Entity
@Table(name = "administrador")
public class AdministradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único do administrador

    @Column(name = "senha", nullable = true)
    private String senha; // Senha de acesso (pode ser criptografada futuramente)

    /**
     * Relação 1:1 com PessoaEntity.
     * Define os dados pessoais (nome, email, etc.) do administrador.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private PessoaEntity pessoaEntity;

    /**
     * Construtor padrão exigido pelo Hibernate.
     */
    public AdministradorEntity() {}

    /**
     * Construtor com parâmetros.
     * @param id ID do administrador
     * @param senha Senha de acesso
     */
    public AdministradorEntity(Long id, String senha) {
        this.id = id;
        this.senha = senha;
    }

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public PessoaEntity getPessoaEntity() { return pessoaEntity; }

    public void setPessoaEntity(PessoaEntity pessoaEntity) { this.pessoaEntity = pessoaEntity; }

    /**
     * Método reservado para uso futuro (ex: autenticação por CPF).
     */
    public String getCpf() {
        return null;
    }
}
