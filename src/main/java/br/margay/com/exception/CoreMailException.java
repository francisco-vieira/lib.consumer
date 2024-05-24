/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.exception;

/**
 * @author colpv
 * Criado em 05/05/2024
 */
public class CoreMailException extends RuntimeException {

    public CoreMailException() {
        super();
    }

    public CoreMailException(String s) {
        super(s);
    }

    public CoreMailException(String s, Exception e) {
        super(s, e);
    }

    public CoreMailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreMailException(Throwable cause) {
        super(cause);
    }

    protected CoreMailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
