package br.jlinformatica.com.acbr.config;

import br.jlinformatica.com.acbr.ACBrLibBase;
import br.jlinformatica.com.acbr.enums.ACBrSessao;
import br.jlinformatica.com.acbr.enums.CodResposta;
import br.jlinformatica.com.acbr.enums.NivelLog;
import br.jlinformatica.com.acbr.enums.TipoResposta;

public final class PrincipalConfig<T extends ACBrLibBase> extends ACBrLibConfigBase<T>{

    public PrincipalConfig(T acbrlib) {
        super(acbrlib, ACBrSessao.Proxy);
    }

    public TipoResposta getTipoResposta() throws Exception{
        return TipoResposta.valueOf(getIntProperty("TipoResposta"));
    }

    public void setTipoResposta(TipoResposta value) throws Exception {
        this.setIntProperty("TipoResposta", value.asInt());
    }

    public CodResposta getCodificacaoResposta() throws Exception{
        return CodResposta.valueOf(getIntProperty("CodificacaoResposta"));
    }

    public void setCodificacaoResposta(CodResposta value) throws Exception {
        this.setIntProperty("CodificacaoResposta", value.asInt());
    }

    public NivelLog getLogNivel() throws Exception{
        return NivelLog.valueOf(getIntProperty("LogNivel"));
    }

    public void setLogNivel(NivelLog value) throws Exception {
        this.setIntProperty("LogNivel", value.asInt());
    }

    public String getLogPath() throws Exception{
        return getProperty("LogPath");
    }

    public void setLogPath(String value) throws Exception {
        this.setProperty("LogPath", value);
    }
}
