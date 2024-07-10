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
public class ConfiguracaoSplit implements Serializable {

    private String id;
    private String txid;
    private Long revisao;
    private String status;
    private String descricao;

    private Lancamento lancamento;
    private Split split;


    public ConfiguracaoSplit() {
    }

    public ConfiguracaoSplit(String id, String txid, Long revisao, String status, String descricao, Lancamento lancamento, Split split) {
        this.id = id;
        this.txid = txid;
        this.revisao = revisao;
        this.status = status;
        this.descricao = descricao;
        this.lancamento = lancamento;
        this.split = split;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Long getRevisao() {
        return revisao;
    }

    public void setRevisao(Long revisao) {
        this.revisao = revisao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {

        private String id;
        private String txid;
        private String status;
        private Long revisao;
        private String descricao;
        private Lancamento lancamento;
        private Split split;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder txid(String txid) {
            this.txid = txid;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder revisao(Long revisao) {
            this.revisao = revisao;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder lancamento(Lancamento lancamento) {
            this.lancamento = lancamento;
            return this;
        }

        public Builder split(Split split) {
            this.split = split;
            return this;
        }

        public ConfiguracaoSplit build() {
            return new ConfiguracaoSplit(id, txid, revisao, status, descricao, lancamento, split);
        }
    }
}
