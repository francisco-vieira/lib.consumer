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
public class Calendario implements Serializable {

    private int expiracao;
    private String criacao;
    private String dataDeVencimento;
    private int validadeAposVencimento;

    public Calendario() {
    }

    public Calendario(int expiracao) {
        this.expiracao = expiracao;
    }

    public Calendario(int expiracao, String criacao, String dataDeVencimento, int validadeAposVencimento) {
        this.expiracao = expiracao;
        this.criacao = criacao;
        this.dataDeVencimento = dataDeVencimento;
        this.validadeAposVencimento = validadeAposVencimento;
    }

    public int getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(int expiracao) {
        this.expiracao = expiracao;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }


    public String getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(String dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }

    public int getValidadeAposVencimento() {
        return validadeAposVencimento;
    }

    public void setValidadeAposVencimento(int validadeAposVencimento) {
        this.validadeAposVencimento = validadeAposVencimento;
    }

    public static class Builder {
        private int expiracao;
        private String criacao;
        private String dataDeVencimento;
        private int validadeAposVencimento;

        public Builder expiracao(int expiracao) {
            this.expiracao = expiracao;
            return this;
        }

        public Builder criacao(String criacao) {
            this.criacao = criacao;
            return this;
        }

        public Builder dataDeVencimento(String dataDeVencimento) {
            this.dataDeVencimento = dataDeVencimento;
            return this;
        }

        public Builder validadeAposVencimento(int validadeAposVencimento) {
            this.validadeAposVencimento = validadeAposVencimento;
            return this;

        }

        public Calendario build() {
            return new Calendario(expiracao, criacao, dataDeVencimento, validadeAposVencimento);
        }
    }
}
