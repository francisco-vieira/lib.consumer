/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model;

import br.margay.com.enums.pix.CertificateType;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 29/05/2024
 */
public class KeyStorePix implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;
    private String certificate;

    private CertificateType certificateType;

    public KeyStorePix(String password, String certificate, CertificateType certificateType) {
        this.password = password;
        this.certificate = certificate;
        this.certificateType = certificateType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {
        this.certificateType = certificateType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String password;
        private String certificate;
        private CertificateType certificateType;

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder certificate(String certificate) {
            this.certificate = certificate;
            return this;
        }

        public Builder certificateType(CertificateType certificateType) {
            this.certificateType = certificateType;
            return this;
        }

        public KeyStorePix build() {
            return new KeyStorePix(this.password, this.certificate, this.certificateType);
        }

    }
}
