/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix;

import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 06/09/2024
 */
public class PixTransaction {

    private List<Pix> pix;

    // Getters and Setters
    public List<Pix> getPix() {
        return pix;
    }

    public void setPix(List<Pix> pix) {
        this.pix = pix;
    }

}

