/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.webhook;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class PWebhookResp implements Serializable {

    private PWebhook parametros;
    List<WebhookResp> webhooks;

    public PWebhookResp() {
    }

    public PWebhookResp(PWebhook parametros, List<WebhookResp> webhooks) {
        this.parametros = parametros;
        this.webhooks = webhooks;
    }

    public PWebhook getParametros() {
        return parametros;
    }

    public void setParametros(PWebhook parametros) {
        this.parametros = parametros;
    }

    public List<WebhookResp> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(List<WebhookResp> webhooks) {
        this.webhooks = webhooks;
    }
}
