/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.enums.cnpj;

/**
 * @author colpv
 * Criado em 14/05/2024
 */
public enum HostBase {
    /**
     * Endpoint cnpj GET - "cnpj/num_cnpj"
     * <br/>
     * Endpoint suframa POST -  SuframaBody{"cnpj":98765.., "inscricao":01234..} <br/>
     */
    CNPJ_PUBLICA("https://publica.cnpj.ws"),

    /**
     * Endpoint cnpj GET - "cnpj/num_cnpj"
     */
    CNPJ_RECEITA_WS("https://receitaws.com.br/v1"),

    /**
     * Endpoint cnpj GET - "/num_cnpj"
     */
    CNPJ_DEV("https://api.cnpjs.dev/v1");

    private final String url;

    HostBase(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
