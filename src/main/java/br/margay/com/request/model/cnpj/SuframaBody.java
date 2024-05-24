/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.request.model.cnpj;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 15/05/2024
 */
public class SuframaBody implements Serializable {

    private String cnpj;
    private String inscricao;

    private SuframaBody() {
    }

    public SuframaBody(String cnpj, String inscricao) {
        this.cnpj = cnpj;
        this.inscricao = inscricao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getInscricao() {
        return inscricao;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String cnpj;
        private String inscricao;

        public Builder() {
        }

        public Builder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder inscricao(String inscricao) {
            this.inscricao = inscricao;
            return this;
        }

        public SuframaBody build() {
            return new SuframaBody(this.cnpj, this.inscricao);
        }
    }


}