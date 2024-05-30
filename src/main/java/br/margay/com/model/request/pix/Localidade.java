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
public class Localidade implements Serializable {
    private int id;

    public Localidade(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
