/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.enums.email;

/**
 * @author colpv
 * Criado em 06/05/2024
 */
public enum RecipientType {

    TO("To"),
    CC("Cc"),
    BCC("Bcc");

    private final String value;

    RecipientType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
