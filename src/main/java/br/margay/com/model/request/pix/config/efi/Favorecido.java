/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.efi;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/07/2024
 */
public class Favorecido implements Serializable {

    private String cpf;
    private String conta;

    public Favorecido() {
    }

    public Favorecido(String cpf, String conta) {
        this.cpf = cpf;
        this.conta = conta;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }
}
