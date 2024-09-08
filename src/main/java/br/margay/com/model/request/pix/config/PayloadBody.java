/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class PayloadBody implements Serializable {

    private String tipoCob;

    public PayloadBody() {
    }

    private PayloadBody(String tipoCob) {
        this.tipoCob = tipoCob;
    }


    public static PayloadBody getInstance(String tipoCob) {
        return new PayloadBody(tipoCob);
    }


    public String getTipoCob() {
        return tipoCob;
    }

    public void setTipoCob(String tipoCob) {
        this.tipoCob = tipoCob;
    }

}
