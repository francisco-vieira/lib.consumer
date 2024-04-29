package br.jlinformatica.com.acbr.config;

import br.jlinformatica.com.acbr.ACBrLibBase;
import br.jlinformatica.com.acbr.enums.ACBrSessao;

public final class ProxyConfig<T extends ACBrLibBase> extends ACBrLibConfigBase<T>{

    public ProxyConfig(T acbrlib) {
        super(acbrlib, ACBrSessao.Proxy);
    }

    public String getServidor() throws Exception{
        return getProperty("Servidor");
    }

    public void setServidor(String value) throws Exception {
        this.setProperty("Servidor", value);
    }

    public String getPorta() throws Exception{
        return getProperty("Porta");
    }

    public void setPorta(String value) throws Exception {
        this.setProperty("Porta", value);
    }

    public String getUsuario() throws Exception{
        return getProperty("Usuario");
    }

    public void setUsuario(String value) throws Exception {
        this.setProperty("Usuario", value);
    }

    public String getSenha() throws Exception{
        return getProperty("Senha");
    }

    public void setSenha(String value) throws Exception {
        this.setProperty("Senha", value);
    }
}