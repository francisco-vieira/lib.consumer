/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix.config;


/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */

public class Webhook implements java.io.Serializable {

    private String webhookUrl;
    private String chave;
    private String criacao;

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

    @Override
    public String toString() {
        return "Webhook [webhookUrl=" + webhookUrl + ", chave=" + chave + ", criacao=" + criacao + "]";
    }
}
