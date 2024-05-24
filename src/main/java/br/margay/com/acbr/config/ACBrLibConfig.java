package br.margay.com.acbr.config;

import br.margay.com.acbr.ACBrLibBase;
import br.margay.com.acbr.enums.ACBrSessao;

public abstract class ACBrLibConfig<T extends ACBrLibBase> extends ACBrLibConfigBase<T> {

    private final PrincipalConfig<T> principal;
    private final SistemaConfig<T> sistema;

    protected ACBrLibConfig(T acbrlib, ACBrSessao sessao) {
        super(acbrlib, sessao);

        principal = new PrincipalConfig<>(acbrlib);
        sistema = new SistemaConfig<>(acbrlib);
    }

    public PrincipalConfig<T> getPrincipal(){
        return principal;
    }

    public SistemaConfig<T> getSistema(){
        return sistema;
    }

}