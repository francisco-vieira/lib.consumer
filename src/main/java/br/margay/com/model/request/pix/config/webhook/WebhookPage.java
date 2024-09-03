/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.webhook;

import br.margay.com.model.request.pix.config.webhook.paginator.WebhookParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class WebhookPage implements Serializable {

  private WebhookParam parametros;
  private List<Webhook> webhooks;

    public WebhookParam getParametros() {
        return parametros;
    }

    public void setParametros(WebhookParam parametros) {
        this.parametros = parametros;
    }

    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }
}
