/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.request.pix;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 22/05/2024
 */
public class Calendario implements Serializable {
    private long expiracao;

    public Calendario() {
    }

    public Calendario(long expiracao) {
        this.expiracao = expiracao;
    }

    public long getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(int expiracao) {
        this.expiracao = expiracao;
    }
}
