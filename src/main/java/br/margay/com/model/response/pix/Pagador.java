/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.response.pix;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 22/05/2024
 */
public class Pagador implements Serializable {

    private String cpf;
    private String nome;
    private  String logradouro;
    private String cidade;
    private String uf;
    private String cep;

    public Pagador(String cpf, String nome, String logradouro, String cidade, String uf, String cep) {
        this.cpf = cpf;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    public static class Builder {

        private String cpf;
        private String nome;
        private String logradouro;
        private String cidade;
        private String uf;
        private String cep;

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }
        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }
        public Builder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }
        public Builder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }
        public Builder uf(String uf) {
            this.uf = uf;
            return this;
        }
        public Builder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public Pagador build() {
           return  new Pagador(cpf, nome, logradouro, cidade, uf, cep);
        }
    }
}
