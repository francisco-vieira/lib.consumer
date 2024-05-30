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
public class Valor implements Serializable {

    private String original;

    public Valor() {
    }

    public Valor(String original) {
        this.original = original;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
