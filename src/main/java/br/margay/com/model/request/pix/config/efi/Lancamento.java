/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.efi;

/**
 * @author francisco.vieira
 * Criado em 02/07/2024
 */
public class Lancamento implements java.io.Serializable {

    private boolean imediato;

    public Lancamento() {
    }

    public Lancamento(boolean imediato) {
        this.imediato = imediato;
    }

    // Getters e Setters
    public boolean isImediato() {
        return imediato;
    }

    public void setImediato(boolean imediato) {
        this.imediato = imediato;
    }

}
