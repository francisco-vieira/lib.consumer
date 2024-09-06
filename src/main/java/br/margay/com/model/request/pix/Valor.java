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
    private String modalidadeAlteracao;

    public Valor() {
    }

    public Valor(String original) {
        this.original = original;
    }

    public Valor(String original, String modalidadeAlteracao) {
        this.original = original;
        this.modalidadeAlteracao = modalidadeAlteracao;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getModalidadeAlteracao() {
        return modalidadeAlteracao;
    }

    public void setModalidadeAlteracao(String modalidadeAlteracao) {
        this.modalidadeAlteracao = modalidadeAlteracao;
    }

}
