/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.response.pix.paginator;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class PixParam implements Serializable {

    private String inicio;
    private String fim;
    private PixPaginator paginacao;

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public PixPaginator getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(PixPaginator paginacao) {
        this.paginacao = paginacao;
    }
}
