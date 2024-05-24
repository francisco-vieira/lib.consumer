/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.response.model.pix;

import java.io.Serializable;

/**
 * @author colpv
 * Criado em 22/05/2024
 */
public class Localidade implements Serializable {

    private int id;
    private String location;
    private String tipoCob;
    private String criacao;

    public Localidade(int id) {
        this.id = id;
    }

    public Localidade(int id, String location, String tipoCob, String criacao) {
        this.id = id;
        this.location = location;
        this.tipoCob = tipoCob;
        this.criacao = criacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTipoCob() {
        return tipoCob;
    }

    public void setTipoCob(String tipoCob) {
        this.tipoCob = tipoCob;
    }

    public String getCriacao() {
        return criacao;
    }

    public void setCriacao(String criacao) {
        this.criacao = criacao;
    }
}
