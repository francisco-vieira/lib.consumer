/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix.config;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class WebhookBody implements Serializable {

    private String webhookUrl;

    public WebhookBody() {
    }

    public WebhookBody(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }


    public static WebhookBody getInstance(String webhookUrl) {
        return new WebhookBody(webhookUrl);
    }


    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

}
