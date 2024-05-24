/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.enums.pix;

/**
 * @author colpv
 * Criado em 22/05/2024
 */
public enum CertificateType {
    PKCS_12("PKCS12");

    private final String certificate;

    CertificateType(String certificate) {
        this.certificate = certificate;
    }

    public String toString() {
        return certificate;
    }

}
