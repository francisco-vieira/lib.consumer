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
 * Criado em 29/05/2024
 */
public enum PSPPix {

    BRADESCO(1, "Bradesco", "", "", ""),
    SICREDI(2, "Sicredi", "", "", ""),
    SICOOB(3, "Sicoob", "", "", ""),
    SHIPAY(4, "Shipay", "", "", ""),
    SANTANDER(5, "Santander", "", "", ""),
    PIXDV(6, "PixPDV", "", "", ""),
    PAG_SEGURO(7, "PagSeguro", "", "", ""),
    ITAU(8, "Itau", "", "", ""),
    INTER(9, "Inter", "", "", ""),
    EFI(10, "Efi", "https://pix.api.efipay.com.br", "https://pix-h.api.efipay.com.br", ""), //GerenciaNet
    BANCO_BRASIL(11, "Banco do Brasil", "", "", ""),
    AILOS(12, "Ailos", "", "", ""),
    MATERA(13, "Matera", "", "", ""),
    CIELO(14, "Cielo", "", "", ""),
    MECADO_PAGO(15, "MercadoPago", "", "", "");

    private final Integer codigo;
    private final String descricao;
    private final String producao;
    private final String homologacao;
    private final String teste;

    PSPPix(Integer codigo, String descricao, String producao, String homologacao, String teste) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.producao = producao;
        this.homologacao = homologacao;
        this.teste = teste;
    }

    public static PSPPix getValueOf(Object object) {
        for (PSPPix e : PSPPix.values()) {
            if ((object instanceof String || object instanceof PSPPix) && object.equals(e.toString())) {
                return e;
            }

            if (object instanceof Integer) {
                Integer code = Integer.valueOf(object.toString());
                if (e.codigo.equals(code)) {
                    return e;
                }
            }
        }

        throw new IllegalArgumentException("PSP inválido");
    }


    public int getCodigo() {
        return codigo;
    }

    /**
     * @param code recebe 1 ou test para TESTE , 2 ou hml para homologacao, 3 ou prd para producao
     * @return host do ambiente
     */
    public String getAmbiente(Object code) {
        String ambiente = String.valueOf(code);

        if (code instanceof Integer) {
            int cod = Integer.parseInt(ambiente);
            if (cod == 1) {
                return this.teste;
            } else if (cod == 2) {
                return this.homologacao;
            } else if (cod == 3) {
                return this.producao;
            }
        }

        if (code instanceof String) {
            if ("test".equalsIgnoreCase(ambiente)) {
                return this.teste;
            } else if ("hml".equalsIgnoreCase(ambiente)) {
                return this.homologacao;
            } else if ("prd".equalsIgnoreCase(ambiente)) {
                return this.producao;
            }
        }
        throw new ServiceException("Ambiente não suportado");
    }

    @Override
    public String toString() {
        return descricao;
    }

}
