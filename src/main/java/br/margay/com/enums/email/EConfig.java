/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.enums.email;

/**
 * @author colpv
 * Criado em 05/05/2024
 */
public enum EConfig {

    AUTH("mail.smtp.auth"),
    START_TLS_ENABLE("mail.smtp.starttls.enable"),
    HOST("mail.smtp.host"),
    PORT("mail.smtp.port"),
    TRUST("mail.smtp.ssl.trust"),

    CONNECTION_TIMEOUT("mail.smtp.connectiontimeout"),
    TIME_OUT("mail.smtp.timeout"),
    WRITE_TIMEOUT("mail.smtp.writetimeout"),
    SSL_ENABLE("mail.smtp.ssl.enable");

    private final String param;

    EConfig(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return this.param;
    }
}
