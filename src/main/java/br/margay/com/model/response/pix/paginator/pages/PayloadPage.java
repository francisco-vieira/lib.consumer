/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix.paginator.pages;

import br.margay.com.model.response.pix.Localidade;
import br.margay.com.model.response.pix.paginator.PixParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 07/09/2024
 */
public class PayloadPage implements Serializable {

    private PixParam parametros;
    private List<Localidade> loc;

    public PixParam getParametros() {
        return parametros;
    }

    public void setParametros(PixParam parametros) {
        this.parametros = parametros;
    }

    public List<Localidade> getLoc() {
        return loc;
    }

    public void setLoc(List<Localidade> loc) {
        this.loc = loc;
    }
}
