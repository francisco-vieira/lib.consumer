/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.webhook.paginator;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class WebhookParam implements Serializable {

    private String inicio;
    private String fim;
    private WebhookPaginator paginacao;

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public WebhookPaginator getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(WebhookPaginator paginacao) {
        this.paginacao = paginacao;
    }
}
