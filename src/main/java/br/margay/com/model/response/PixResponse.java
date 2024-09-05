/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.model.response;


import br.margay.com.enums.pix.StatusPix;
import br.margay.com.model.response.pix.Devedor;
import br.margay.com.model.request.pix.InfoAdicional;
import br.margay.com.model.request.pix.Valor;
import br.margay.com.model.request.pix.config.efi.ConfiguracaoSplit;
import br.margay.com.model.response.pix.Calendario;
import br.margay.com.model.response.pix.Localidade;
import br.margay.com.model.response.pix.Pix;

import java.io.Serializable;
import java.util.List;

/**
 * @author colpv
 * Criado em 22/05/2024
 */

public final class PixResponse implements Serializable {

    private Calendario calendario;
    private Devedor devedor;
    private Localidade loc;
    private Valor valor;
    private String solicitacaoPagador;
    private String chave;
    private String location;
    private String pixCopiaECola;
    private String txid;
    private int revisao;
    private StatusPix status;
    private List<InfoAdicional> infoAdicionais;
    private ConfiguracaoSplit config;

    private List<Pix> pix;

    public PixResponse() {
    }

    public PixResponse(Calendario calendario, Devedor devedor, Localidade loc,
                       Valor valor,
                       String solicitacaoPagador, String chave, String location,
                       String pixCopiaECola, String txid, int revisao, StatusPix status,
                       List<InfoAdicional> infoAdicionais,
                       ConfiguracaoSplit config, List<Pix> pix) {
        this.calendario = calendario;
        this.devedor = devedor;
        this.loc = loc;
        this.valor = valor;
        this.solicitacaoPagador = solicitacaoPagador;
        this.chave = chave;
        this.location = location;
        this.pixCopiaECola = pixCopiaECola;
        this.txid = txid;
        this.revisao = revisao;
        this.status = status;
        this.infoAdicionais = infoAdicionais;
        this.config = config;
        this.pix = pix;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPixCopiaECola() {
        return pixCopiaECola;
    }

    public void setPixCopiaECola(String pixCopiaECola) {
        this.pixCopiaECola = pixCopiaECola;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public int getRevisao() {
        return revisao;
    }

    public void setRevisao(int revisao) {
        this.revisao = revisao;
    }

    public StatusPix getStatus() {
        return status;
    }

    public void setStatus(StatusPix status) {
        this.status = status;
    }

    public List<InfoAdicional> getInfoAdicionais() {
        return infoAdicionais;
    }

    public void setInfoAdicionais(List<InfoAdicional> infoAdicionais) {
        this.infoAdicionais = infoAdicionais;
    }

    public ConfiguracaoSplit getConfig() {
        return config;
    }

    public void setConfig(ConfiguracaoSplit config) {
        this.config = config;
    }

    public List<Pix> getPix() {
        return pix;
    }

    public void setPix(List<Pix> pix) {
        this.pix = pix;
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
        private String location;
        private String pixCopiaECola;
        private String txid;
        private int revisao;
        private StatusPix status;
        private List<InfoAdicional> infoAdicionais;

        private ConfiguracaoSplit config;

        private List<Pix> pix;

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

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder pixCopiaECola(String pixCopiaECola) {
            this.pixCopiaECola = pixCopiaECola;
            return this;
        }

        public Builder txid(String txid) {
            this.txid = txid;
            return this;
        }

        public Builder revisao(int revisao) {
            this.revisao = revisao;
            return this;
        }

        public Builder status(StatusPix status) {
            this.status = status;
            return this;
        }

        public Builder infoAdicionais(List<InfoAdicional> infoAdicionais) {
            this.infoAdicionais = infoAdicionais;
            return this;
        }

        public Builder config(ConfiguracaoSplit config) {
            this.config = config;
            return this;
        }

        public Builder pix(List<Pix> pix) {
            this.pix = pix;
            return this;
        }

        public PixResponse build() {
            return new PixResponse(calendario,
                    devedor,
                    loc,
                    valor,
                    solicitacaoPagador,
                    chave,
                    location,
                    pixCopiaECola,
                    txid,
                    revisao,
                    status,
                    infoAdicionais,
                    config,
                    pix);
        }
    }
}
