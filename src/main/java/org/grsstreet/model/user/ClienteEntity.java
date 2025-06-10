package org.grsstreet.model.user;


import org.grsstreet.model.address.EnderecoEntity;

import javax.persistence.*;

@Entity(name = "cliente")
    public class ClienteEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "senha", nullable = false)
        private String senha;

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "pessoa_id")
        private PessoaEntity pessoa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private EnderecoEntity enderecoEntity;


    public EnderecoEntity getEnderecoEntity() {
        return enderecoEntity;
    }

    public void setEnderecoEntity(EnderecoEntity enderecoEntity) {
        this.enderecoEntity = enderecoEntity;
    }


        public void setPessoa(PessoaEntity pessoa) {
            this.pessoa = pessoa;
        }

        public PessoaEntity getPessoa() {
            return pessoa;
        }

    public void setPessoaEntity(PessoaEntity pessoa) {
    }
}
