/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.webhook.paginator;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class WebhookPaginator implements Serializable {

    private int paginaAtual;
    private int itensPorPagina;
    private int quantidadeDePaginas;
    private int quantidadeTotalDeItens;

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public void setPaginaAtual(int paginaAtual) {
        this.paginaAtual = paginaAtual;
    }

    public int getItensPorPagina() {
        return itensPorPagina;
    }

    public void setItensPorPagina(int itensPorPagina) {
        this.itensPorPagina = itensPorPagina;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public void setQuantidadeDePaginas(int quantidadeDePaginas) {
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    public int getQuantidadeTotalDeItens() {
        return quantidadeTotalDeItens;
    }

    public void setQuantidadeTotalDeItens(int quantidadeTotalDeItens) {
        this.quantidadeTotalDeItens = quantidadeTotalDeItens;
    }
}
