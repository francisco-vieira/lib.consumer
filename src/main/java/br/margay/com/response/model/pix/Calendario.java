/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.response.model.pix;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 22/05/2024
 */
public class Calendario implements Serializable {

    private int expiracao;
    private String  criacao;

    public Calendario() {
    }

    public Calendario(int expiracao) {
        this.expiracao = expiracao;
    }

    public Calendario(int expiracao, String  criacao) {
        this.expiracao = expiracao;
        this.criacao = criacao;
    }

    public int getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(int expiracao) {
        this.expiracao = expiracao;
    }

    public String  getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }
}
