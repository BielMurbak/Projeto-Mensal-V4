package org.grsstreet.model.address;

import javax.persistence.*;

@Entity(name = "endereco") // Mapeia a classe para a tabela 'endereco'
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera ID automático
    private Long id;

    @Column(name = "rua", nullable = false) // Campo obrigatório: rua
    private String rua;

    @Column(name = "bairro", nullable = false) // Campo obrigatório: bairro
    private String bairro;

    @Column(name = "municipio", nullable = false) // Campo obrigatório: município
    private String municipio;

    @Column(name = "estado", nullable = false) // Campo obrigatório: estado
    private String estado;

    @Column(name = "cep", nullable = false) // Campo obrigatório: CEP
    private String cep;

    // Construtor com todos os campos (exceto ID)
    public EnderecoEntity(String rua, String bairro, String municipio, String estado, String cep) {
        this.rua = rua;
        this.bairro = bairro;
        this.municipio = municipio;
        this.estado = estado;
        this.cep = cep;
    }

    // Construtor padrão obrigatório para JPA
    public EnderecoEntity() {
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
