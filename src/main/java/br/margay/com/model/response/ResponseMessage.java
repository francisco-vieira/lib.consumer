/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 06/09/2024
 */
public class ResponseMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;
    private String message;


    public ResponseMessage() {
    }

    public ResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }
    public String getStatus() {
        return status;
    }

    public ResponseMessage status(String status) {
        this.status = status;
        return this;
    }
    public String getMessage() {
        return message;
    }

    public ResponseMessage message(String message) {
        this.message = message;
        return this;
    }

}
