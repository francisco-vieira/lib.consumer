/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.request.pix;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 22/05/2024
 */
public class GrantType implements Serializable {

    private String grant_type;

    public GrantType(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }


}
