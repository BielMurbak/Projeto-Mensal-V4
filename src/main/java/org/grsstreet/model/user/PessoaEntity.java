package org.grsstreet.model.user;

import org.grsstreet.model.enums.TipoPessoa;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Representa uma pessoa no sistema.
 * Utilizada como base para cliente e administrador.
 */
@Entity
@Table(name = "pessoa")
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id; // Identificador único da pessoa

    @Column(name = "nome", nullable = false)
    private String nome; // Nome completo da pessoa

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf; // CPF único da pessoa

    @Column(name = "dataDeNascimento", nullable = false)
    private LocalDate dataDeNascimento; // Data de nascimento

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = true)
    private TipoPessoa tipo; // Tipo da pessoa (CLIENTE ou ADMINISTRADOR)

    // Construtor padrão
    public PessoaEntity() {}

    // Getters e Setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataDeNascimento() { return dataDeNascimento; }

    public void setDataDeNascimento(LocalDate dataDeNascimento) { this.dataDeNascimento = dataDeNascimento; }

    public TipoPessoa getTipo() { return tipo; }

    public void setTipo(TipoPessoa tipo) { this.tipo = tipo; }
}
