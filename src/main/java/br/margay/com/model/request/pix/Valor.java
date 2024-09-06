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

    private Double original;
    private String modalidadeAlteracao;

    public Valor() {
    }

    public Valor(Double original) {
        this.original = original;
    }

    public Valor(Double original, String modalidadeAlteracao) {
        this.original = original;
        this.modalidadeAlteracao = modalidadeAlteracao;
    }

    public Double getOriginal() {
        return original;
    }

    public void setOriginal(Double original) {
        this.original = original;
    }

    public String getModalidadeAlteracao() {
        return modalidadeAlteracao;
    }

    public void setModalidadeAlteracao(String modalidadeAlteracao) {
        this.modalidadeAlteracao = modalidadeAlteracao;
    }

}
