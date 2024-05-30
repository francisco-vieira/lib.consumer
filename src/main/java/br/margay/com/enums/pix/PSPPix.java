/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.enums.pix;

/**
 * @author francisco.vieira
 * Criado em 29/05/2024
 */
public enum PSPPix {

    BRADESCO(1, "Bradesco", "", ""),
    SICREDI(2, "Sicredi", "", ""),
    SICOOB(3, "Sicoob", "", ""),
    SHIPAY(4, "Shipay", "", ""),
    SANTANDER(5, "Santander", "", ""),
    PIXDV(6, "PixPDV", "", ""),
    PAG_SEGURO(7, "PagSeguro", "", ""),
    ITAU(8, "Itau", "", ""),
    INTER(9, "Inter", "", ""),
    EFI(10, "Efi", "https://pix.api.efipay.com.br", "https://pix-h.api.efipay.com.br"), //GerenciaNet
    BANCO_BRASIL(11, "Banco do Brasil", "", ""),
    AILOS(12, "Ailos", "", ""),
    MATERA(13, "Matera", "", ""),
    CIELO(14, "Cielo", "", ""),
    MECADO_PAGO(15, "MercadoPago", "", "");

    private final Integer codigo;
    private final String descricao;
    private final String producao;
    private final String homologacao;

    PSPPix(Integer codigo, String descricao, String producao, String homologacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.producao = producao;
        this.homologacao = homologacao;
    }

    public static PSPPix getValueOf(Object object) {
        for (PSPPix e : PSPPix.values()) {
            if (object instanceof String || object instanceof PSPPix) {
                if (object.equals(e.toString())) {
                    return e;
                }
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

    public String getProducao() {
        return producao;
    }

    public String getHomologacao() {
        return homologacao;
    }

    public String toString() {
        return descricao;
    }

}
