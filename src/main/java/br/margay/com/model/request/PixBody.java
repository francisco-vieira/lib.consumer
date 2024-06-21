/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.request;

import br.margay.com.model.request.pix.Calendario;
import br.margay.com.model.request.pix.Devedor;
import br.margay.com.model.request.pix.InfoAdicional;
import br.margay.com.model.request.pix.Localidade;
import br.margay.com.model.request.pix.Valor;

import java.io.Serializable;
import java.util.List;

/**
 * @author francisco.castro
 * Criado em 22/05/2024
 */
public class PixBody implements Serializable {

    private Calendario calendario;
    private Devedor devedor;
    private Localidade loc;
    private Valor valor;
    private String solicitacaoPagador;
    private String chave;
    private List<InfoAdicional> infoAdicionais;

    public PixBody() {
    }

    public PixBody(String chave, String solicitacaoPagador, Valor valor, Localidade loc, Calendario calendario, Devedor devedor, List<InfoAdicional> infoAdicionais) {
        this.chave = chave;
        this.solicitacaoPagador = solicitacaoPagador;
        this.valor = valor;
        this.loc = loc;
        this.calendario = calendario;
        this.devedor = devedor;
        this.infoAdicionais = infoAdicionais;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getSolicitacaoPagador() {
        return solicitacaoPagador;
    }

    public void setSolicitacaoPagador(String solicitacaoPagador) {
        this.solicitacaoPagador = solicitacaoPagador;
    }

    public Valor getValor() {
        return valor;
    }

    public void setValor(Valor valor) {
        this.valor = valor;
    }

    public Localidade getLoc() {
        return loc;
    }

    public void setLoc(Localidade loc) {
        this.loc = loc;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    public Devedor getDevedor() {
        return devedor;
    }

    public void setDevedor(Devedor devedor) {
        this.devedor = devedor;
    }

    public List<InfoAdicional> getInfoAdicionais() {
        return infoAdicionais;
    }

    public void setInfoAdicionais(List<InfoAdicional> infoAdicionais) {
        this.infoAdicionais = infoAdicionais;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String chave;
        private String solicitacaoPagador;
        private Valor valor;
        private Localidade loc;
        private Calendario calendario;
        private Devedor devedor;
        private List<InfoAdicional> infoAdicionais;

        public Builder chave(String chave) {
            this.chave = chave;
            return this;
        }

        public Builder solicitacaoPagador(String solicitacaoPagador) {
            this.solicitacaoPagador = solicitacaoPagador;
            return this;
        }

        public Builder calendario(Calendario calendario) {
            this.calendario = calendario;
            return this;
        }

        public Builder valor(Valor valor) {
            this.valor = valor;
            return this;
        }

        public Builder devedor(Devedor devedor) {
            this.devedor = devedor;
            return this;
        }

        public Builder loc(Localidade loc) {
            this.loc = loc;
            return this;
        }

        public Builder infoAdicionais(List<InfoAdicional> infoAdicionais) {
            this.infoAdicionais = infoAdicionais;
            return this;
        }

        public PixBody build() {
            return new PixBody(chave, solicitacaoPagador, valor, loc, calendario, devedor, infoAdicionais);
        }
    }
}
