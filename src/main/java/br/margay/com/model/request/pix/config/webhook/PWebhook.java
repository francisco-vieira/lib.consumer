/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.webhook;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class PWebhook implements Serializable {

    private String inicio;
    private String fim;
    private PaginatorWebhook paginacao;

    public PWebhook() {
    }

    public PWebhook(PaginatorWebhook paginacao, String fim, String inicio) {
        this.paginacao = paginacao;
        this.fim = fim;
        this.inicio = inicio;
    }

    public String getInicio() {
        return inicio;
    }

    public String getFim() {
        return fim;
    }

    public PaginatorWebhook getPaginacao() {
        return paginacao;
    }
}
