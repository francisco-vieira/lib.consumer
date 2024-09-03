/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.webhook;


/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */

public class WebhookResp implements java.io.Serializable {

    private String webhookUrl;
    private String chave;
    private String criacao;

    public WebhookResp() {
    }

    public WebhookResp(String webhookUrl, String chave, String criacao) {
        this.webhookUrl = webhookUrl;
        this.chave = chave;
        this.criacao = criacao;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }


    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{
        private String webhook;
        private String chave;
        private String criacao;
        public Builder webhook(String webhook) {
            this.webhook = webhook;
            return this;
        }
        public Builder chave(String chave) {
            this.chave = chave;
            return this;
        }

        public Builder criacao(String criacao) {
            this.criacao = criacao;
            return this;

        }
        public WebhookResp build(){
            return new WebhookResp(webhook, chave, criacao);
        }
    }


}
