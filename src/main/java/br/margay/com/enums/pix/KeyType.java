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
public enum KeyType {
    RSA("RSA"),
    DSA("DSA"),
    EC("EC");

    private final String keyType;

    KeyType(String keyType) {
        this.keyType = keyType;
    }

    public String toString() {
        return keyType;
    }

}
