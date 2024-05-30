/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.response.pix;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 23/05/2024
 */
public class AccessToken implements Serializable {
    private String access_token;
    private String token_type;
    private String scope;
    private long expires_in;
    private long refresh_expires_in;


    public AccessToken() {
    }

    public AccessToken(String access_token, String token_type, String scope, long expires_in, long refresh_expires_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.scope = scope;
        this.expires_in = expires_in;
        this.refresh_expires_in = refresh_expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public long getRefresh_expires_in() {
        return refresh_expires_in;
    }

    public void setRefresh_expires_in(long refresh_expires_in) {
        this.refresh_expires_in = refresh_expires_in;
    }
}
