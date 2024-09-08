/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix;

import br.margay.com.enums.pix.StatusPix;

/**
 * @author francisco.vieira
 * Criado em 08/09/2024
 */
public class Develocao implements java.io.Serializable {
    private String id;
    private String rtrId;
    private String valor;
    private Horario horario;
    private StatusPix status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRtrId() {
        return rtrId;
    }

    public void setRtrId(String rtrId) {
        this.rtrId = rtrId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public StatusPix getStatus() {
        return status;
    }

    public void setStatus(StatusPix status) {
        this.status = status;
    }
}
