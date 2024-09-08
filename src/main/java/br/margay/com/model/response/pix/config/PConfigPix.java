/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.response.pix.config;

import br.margay.com.enums.pix.PSPPix;
import br.margay.com.ipack.IConfigPix;

/**
 * @author francisco.vieira
 * Criado em 27/05/2024
 */
public class PConfigPix implements IConfigPix {

    private String scopes;
    private String chavePix;
    private String clienteId;
    private String clienteSecret;

    private String certificado;
    private String chavePrivada;
    private String senha;


    private String cnpj;
    private String token;

    private String developerApplicationKey;
    private String accountID;

    private PSPPix pspPix;

    public PConfigPix() {
    }

    private PConfigPix(String scopes, String chavePix, String clienteId, String clienteSecret,
                      String certificado, String chavePrivada, String senha, String cnpj,
                      String token, String developerApplicationKey, String accountID, PSPPix pspPix) {
        this.scopes = scopes;
        this.chavePix = chavePix;
        this.clienteId = clienteId;
        this.clienteSecret = clienteSecret;
        this.certificado = certificado;
        this.chavePrivada = chavePrivada;
        this.senha = senha;
        this.cnpj = cnpj;
        this.token = token;
        this.developerApplicationKey = developerApplicationKey;
        this.accountID = accountID;
        this.pspPix = pspPix;
    }


    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteSecret() {
        return clienteSecret;
    }

    public void setClienteSecret(String clienteSecret) {
        this.clienteSecret = clienteSecret;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(String chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeveloperApplicationKey() {
        return developerApplicationKey;
    }

    public void setDeveloperApplicationKey(String developerApplicationKey) {
        this.developerApplicationKey = developerApplicationKey;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public PSPPix getPspPix() {
        return pspPix;
    }

    public void setPspPix(PSPPix pspPix) {
        this.pspPix = pspPix;
    }

    public static Builder build(){
        return new Builder();
    }

    public static class Builder {
        private String scopes;
        private String chavePix;
        private String clienteId;
        private String clienteSecret;

        private String certificado;
        private String chavePrivada;
        private String senha;


        private String cnpj;
        private String token;

        private String developerApplicationKey;
        private String accountID;

        private PSPPix pspPix;

        public Builder scopes(String scopes) {
            this.scopes = scopes;
            return this;
        }

        public Builder chavePix(String chavePix) {
            this.chavePix = chavePix;
            return this;
        }

        public Builder clienteId(String clienteId) {
            this.clienteId = clienteId;
            return this;
        }

        public Builder clienteSecret(String clienteSecret) {
            this.clienteSecret = clienteSecret;
            return this;
        }

        public Builder certificado(String certificado) {
            this.certificado = certificado;
            return this;
        }

        public Builder chavePrivada(String chavePrivada) {
            this.chavePrivada = chavePrivada;
            return this;
        }

        public Builder senha(String senha) {
            this.senha = senha;
            return this;
        }

        public Builder cnpj(String cnpj) {
            this.cnpj = cnpj;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder developerApplicationKey(String developerApplicationKey) {
            this.developerApplicationKey = developerApplicationKey;
            return this;
        }

        public Builder accountID(String accountID) {
            this.accountID = accountID;
            return this;
        }

        public Builder psp(PSPPix psp) {
            this.pspPix = psp;
            return this;
        }

        public PConfigPix build() {
            return new PConfigPix(scopes,
                    chavePix, clienteId,
                    clienteSecret, certificado,
                    chavePrivada, senha, cnpj,
                    token, developerApplicationKey,
                    accountID, pspPix);
        }
    }

}
