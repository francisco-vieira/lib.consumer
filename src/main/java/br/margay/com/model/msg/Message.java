/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.msg;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 20/05/2024
 */
public class Message implements Serializable {

    private int httpCode;

    private int httpStatus;

    private String httpMessage;

    private String message;

    private String moreInformation;

    public Message() {
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getMoreInformation() {
        return moreInformation;
    }
}
