package br.jlinformatica.com.acbr.config;

import br.jlinformatica.com.acbr.ACBrLibBase;
import br.jlinformatica.com.acbr.enums.ACBrSessao;

import java.util.Date;

public final class SistemaConfig<T extends ACBrLibBase> extends ACBrLibConfigBase<T>{

    public SistemaConfig(T acbrlib) {
        super(acbrlib, ACBrSessao.Sistema);
    }

    public String getNome() throws Exception{
        return getProperty("Nome");
    }

    public void setNome(String value) throws Exception {
        this.setProperty("Nome", value);
    }

    public String getVersao() throws Exception{
        return getProperty("Versao");
    }

    public void setVersao(String value) throws Exception {
        this.setProperty("Versao", value);
    }

    public Date getData() throws Exception{
        return getDateProperty("Data");
    }

    public void setData(Date value) throws Exception {
        this.setDateProperty("Data", value);
    }

    public String getDescricao() throws Exception{
        return getProperty("Descricao");
    }

    public void setDescricao(String value) throws Exception {
        this.setProperty("Descricao", value);
    }

}
