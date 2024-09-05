/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.enums.pix;

import br.margay.com.exception.ServiceException;

/**
 * @author francisco.vieira
 * Criado em 05/09/2024
 */
public enum StatusPix {

    ATIVA("Ativa"),
    REMOVIDA_PELO_USUARIO_RECEBEDOR("Removida pelo usuário recebedor"),
    REMOVIDA_PELO_PSP("Removida pelo PSP"),
    CONCLUIDA("Concluída"),

    EM_PROCESSAMENTO("Em processamento"),
    REALIZADO("Realizado"),
    NAO_REALIZADO("Não realizado"),

    DEVOLVIDO("Devolvido"),
    AGUARDANDO_PROCESSAMENTO("Aguardando processamento"),
    CONCLUIDO("Concluído");

    private final String descricao;
    StatusPix(String descricao) {
        this.descricao = descricao;

    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusPix finder(Object object) {

        for (StatusPix status : StatusPix.values()) {

            String desc = String.valueOf(object);
            if (object instanceof StatusPix && desc.equals(status.name())) {
                return status;
            }

            if (object instanceof String &&
                    (status.getDescricao().equalsIgnoreCase(desc) || status.name().equalsIgnoreCase(desc))) {
                return status;
            }

        }

        throw new ServiceException("Status not found");
    }



}
