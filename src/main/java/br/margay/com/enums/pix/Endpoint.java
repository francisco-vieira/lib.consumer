/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.enums.pix;

import br.margay.com.util.StringUtils;

/**
 * @author francisco.vieira
 * Criado em 02/07/2024
 * @apiNote Caso seja necessário passar uma variável na rota,
 * utilize o method {@code router} que suporta até duas variáveis.
 * <b>Exemplos de uso:</b><br/>
 * <ul>
 *   <li>{@code Endpoint.AUTHORIZE.router()}</li>
 *   <li>{@code Endpoint.PIX_CONFIG_WEBHOOK.router(var1)}</li>
 *   <li>{@code Endpoint.PIX_DETAIL_DEVOLUTION.router(var1, var2)}</li>
 * </ul>
 */
public enum Endpoint {

    AUTHORIZE("/oauth/token", "POST"),
    WEBHOOK_PIX_CONFIG("/v2/webhook/%s", "PUT"),
    WEBHOOK_PIX_DETAIL("/v2/webhook/%s", "GET"),
    WEBHOOK_PIX_LIST("/v2/webhook", "GET"),
    WEBHOOK_PIX_DELETE("/v2/webhook/%s", "DELETE"),

    CHARGE_PIX_CREATE("/v2/cob/%s", "PUT"),
    CHARGE_PIX_CREATE_IMMEDIATE("/v2/cob", "POST"),
    CHARGE_PIX_DETAIL("/v2/cob/%s", "GET"),
    CHARGE_PIX_UPDATE("/v2/cob/%s", "PATCH"),
    CHARGE_PIX_LIST("/v2/cob", "GET"),

    DEVOLUTION_PIX("/v2/pix/%s/devolucao/%s", "PUT"),
    DEVOLUTION_PIX_DETAIL("/v2/pix/%s/devolucao/%s", "GET"),

    SEND_PIX("/v2/gn/pix/%s", "PUT"),
    SEND_DETAIL_PIX("/v2/gn/pix/enviados/%s", "GET"),
    SEND_DETAIL_ID_PIX("/v2/gn/pix/enviados/id-envio/%s", "GET"),
    SEND_LIST_PIX("/v2/gn/pix/enviados", "GET"),

    RECEIVED_PIX_DETAIL("/v2/pix/%s", "GET"),
    RECEIVED_PIX_LIST("/v2/pix", "GET"),

    LOCATION_PIX_GENERATE_QR_CODE("/v2/loc/%s/qrcode", "GET"),
    LOCATION_PIX_CREATE("/v2/loc", "POST"),
    LOCATION_PIX_LIST("/v2/loc", "GET"),
    LOCATION_PIX_DETAIL("/v2/loc/%s", "GET"),
    LOCATION_PIX_UNLINK_TXID("/v2/loc/%s/txid", "DELETE"),

    EVP_PIX_CREATE("/v2/gn/evp", "POST"),
    EVP_PIX_LIST("/v2/gn/evp", "GET"),
    EVP_PIX_DELETE("/v2/gn/evp/%s", "DELETE"),

    SPLIT_PIX_DETAIL_CHARGE("/v2/gn/split/cob/%s", "GET"),
    SPLIT_PIX_LINK_CHARGE("/v2/gn/split/cob/%s/vinculo/%s", "PUT"),
    SPLIT_PIX_UNLINK_CHARGE("/v2/gn/split/cob/%s/vinculo/%s", "DELETE"),
    SPLIT_PIX_DETAIL_DUE_CHARGE("/v2/gn/split/cobv/%s", "GET"),
    SPLIT_PIX_LINK_DUE_CHARGE("/v2/gn/split/cobv/%s/vinculo/%s", "PUT"),
    SPLIT_PIX_UNLINK_DUE_CHARGE("/v2/gn/split/cobv/%s/vinculo/%s", "DELETE"),
    SPLIT_PIX_CONFIG("/v2/gn/split/config", "POST"),
    SPLIT_PIX_CONFIG_ID("/v2/gn/split/config/%s", "PUT"),
    SPLIT_PIX_DETAIL_CONFIG("/v2/gn/split/config/%s", "GET"),

    ACCOUNT_GET_BALANCE("/v2/gn/saldo", "GET"),
    ACCOUNT_UPDATE_CONFIG("/v2/gn/config", "PUT"),
    ACCOUNT_LIST_CONFIG("/v2/gn/config", "GET"),

    DUE_CREATE_CHARGE("/v2/cobv/%s", "PUT"),
    DUE_UPDATE_CHARGE("/v2/cobv/%s", "PATCH"),
    DUE_DETAIL_CHARGE("/v2/cobv/%s", "GET"),
    DUE_LIST_CHARGES("/v2/cobv/", "GET"),

    REPORT_CREATE("/v2/gn/relatorios/extrato-conciliacao", "POST"),
    REPORT_DETAIL("/v2/gn/relatorios/%s", "GET"),

    DUE_PIX_CREATE_CHARGE_BATCH("/v2/lotecobv/%s", "PUT"),
    DUE_PIX_UPDATE_CHARGE_BATCH("/v2/lotecobv/%s", "PATCH"),
    DUE_PIX_DETAIL_CHARGE_BATCH("/v2/lotecobv/%s", "GET"),
    DUE_PIX_LIST_CHARGE_BATCH("/v2/lotecobv", "GET"),

    MED_LIST("/v2/gn/infracoes", "GET"),
    MED_DEFENSE("/v2/gn/infracoes/%s/defesa", "POST");

    private final String router;
    private final String method;

    Endpoint(String router, String method) {
        this.router = router;
        this.method = method;
    }

    public String router() {
        validate(-1, router);
        return router;
    }


    public String router(String var1) {
        int count = StringUtils.countSymbol('%', router);
        if(count == 2){
            validate(1, router);
        }
        String format = String.format(router, var1);
        validate(1, format);
        return format;
    }

    public String router(String var1, String var2) {
        int count = StringUtils.countSymbol('%', router);
        if(count == 1){
            validate(2, router);
        }
        String format = String.format(router, var1, var2);
        validate(2, format);
        return format;
    }

    public void validate(int i, String format) {
        String msg = "Router %s PATH PARAMETER para essa requisição";
        int count = StringUtils.countSymbol('%', router);
        boolean equals =  router.equals(format);
        if (count == 0 &&  i >= count) {
            msg = String.format(msg, "não requer");
            throw new IllegalArgumentException(msg);

        } else if (count > 0 && equals) {
            msg = String.format(msg, "requer "+ count);
            throw new IllegalArgumentException(msg);
        }

    }

    public String getMethod() {
        return method;
    }
}

