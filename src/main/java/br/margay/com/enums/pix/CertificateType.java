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
    PKCS_12("PKCS12", ".p12"),
    JKS("JKS", ".jks"),
    PEM("X.509", ".pem");

    private final String certificate;
    private final String extension;

    CertificateType(String certificate, String extension) {
        this.certificate = certificate;
        this.extension = extension;
    }

    public String toString() {
        return certificate;
    }

    public String getExtension() {
        return extension;
    }

}
