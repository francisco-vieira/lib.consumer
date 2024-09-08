/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix.paginator.pages;

import br.margay.com.model.response.pix.Pix;
import br.margay.com.model.response.pix.PixResponse;
import br.margay.com.model.response.pix.paginator.PixParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 07/09/2024
 */
public class PixPage implements Serializable {

    private PixParam parametros;
    private List<PixResponse> cobs;

    public PixParam getParametros() {
        return parametros;
    }

    public void setParametros(PixParam parametros) {
        this.parametros = parametros;
    }

    public List<PixResponse> getCobs() {
        return cobs;
    }

    public void setCobs(List<PixResponse> cobs) {
        this.cobs = cobs;
    }

}
