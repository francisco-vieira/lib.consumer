/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix.paginator.pages;

import br.margay.com.model.response.pix.config.Webhook;
import br.margay.com.model.response.pix.paginator.PixParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class WebhookPage implements Serializable {

  private PixParam parametros;
  private List<Webhook> webhooks;

    public PixParam getParametros() {
        return parametros;
    }

    public void setParametros(PixParam parametros) {
        this.parametros = parametros;
    }

    public List<Webhook> getWebhooks() {
        return webhooks;
    }

    public void setWebhooks(List<Webhook> webhooks) {
        this.webhooks = webhooks;
    }
}
