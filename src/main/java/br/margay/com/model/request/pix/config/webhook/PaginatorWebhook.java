/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.model.request.pix.config.webhook;

import java.io.Serializable;

/**
 * @author francisco.vieira
 * Criado em 02/09/2024
 */
public class PaginatorWebhook implements Serializable {

    private int paginaAtual;
    private int itensPorPagina;
    private int quantidadeDePaginas;
    private int quantidadeTotalDeItens;

    public PaginatorWebhook() {

    }

    public PaginatorWebhook(int paginaAtual, int itensPorPagina, int quantidadeDePaginas, int quantidadeTotalDeItens) {
        this.paginaAtual = paginaAtual;
        this.itensPorPagina = itensPorPagina;
        this.quantidadeDePaginas = quantidadeDePaginas;
        this.quantidadeTotalDeItens = quantidadeTotalDeItens;
    }

    public int getPaginaAtual() {
        return paginaAtual;
    }

    public int getItensPorPagina() {
        return itensPorPagina;
    }

    public int getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public int getQuantidadeTotalDeItens() {
        return quantidadeTotalDeItens;
    }
}
