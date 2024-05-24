/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.consume.auth;

/**
 * @author colpv
 * Criado em 23/05/2024
 */
public enum AuthorizationType {

    TOKEN_BEARER("Bearer "),
    TOKEN_BASIC("Basic ");

    private  final String value;

    AuthorizationType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
