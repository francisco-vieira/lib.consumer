/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.vieira
 * Criado em 05/09/2024
 */
public class Pix implements Serializable {

    private String endToEndId;
    private String txid;
    private String valor;
    private String chave;
    private String horario;
    private Pagador pagador;
    private String infoPagador;
    private List<Develocao> devolucoes;

    public Pix(String endToEndId, String txid, String valor, String chave, String horario, Pagador pagador,
               String infoPagador, List<Develocao> devolucoes) {
        this.endToEndId = endToEndId;
        this.txid = txid;
        this.valor = valor;
        this.chave = chave;
        this.horario = horario;
        this.pagador = pagador;
        this.infoPagador = infoPagador;
        this.devolucoes = devolucoes;
    }

    public String getEndToEndId() {
        return endToEndId;
    }

    public void setEndToEndId(String endToEndId) {
        this.endToEndId = endToEndId;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Pagador getPagador() {
        return pagador;
    }

    public void setPagador(Pagador pagador) {
        this.pagador = pagador;
    }

    public String getInfoPagador() {
        return infoPagador;
    }

    public void setInfoPagador(String infoPagador) {
        this.infoPagador = infoPagador;
    }

    public List<Develocao> getDevolucoes() {
        return devolucoes;
    }

    public void setDevolucoes(List<Develocao> devolucoes) {
        this.devolucoes = devolucoes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String endToEndId;
        private String txid;
        private String valor;
        private String chave;
        private String horario;
        private Pagador pagador;
        private String infoPagador;
        private List<Develocao> devolucoes;

        public Builder endToEndId(String endToEndId) {
            this.endToEndId = endToEndId;
            return this;
        }

        public Builder txid(String txid) {
            this.txid = txid;
            return this;
        }

        public Builder valor(String valor) {
            this.valor = valor;
            return this;
        }

        public Builder chave(String chave) {
            this.chave = chave;
            return this;
        }

        public Builder horario(String horario) {
            this.horario = horario;
            return this;
        }

        public Builder pagador(Pagador pagador) {
            this.pagador = pagador;
            return this;
        }

        public Builder infoPagador(String infoPagador) {
            this.infoPagador = infoPagador;
            return this;
        }

        public Builder devolucoes(List<Develocao> devolucoes) {
            this.devolucoes = devolucoes;
            return this;
        }

        public Pix build() {

            return new Pix(endToEndId, txid, valor, chave, horario, pagador, infoPagador, devolucoes);

        }
    }


}
