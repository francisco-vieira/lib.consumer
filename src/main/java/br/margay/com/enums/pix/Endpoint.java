/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.enums.pix;

/**
 * @author francisco.vieira
 * Criado em 02/07/2024
 */
public enum Endpoint {

    AUTHORIZE("/oauth/token", "POST"),
    PIX_CONFIG_WEBHOOK("/v2/webhook/:chave", "PUT"),
    PIX_DETAIL_WEBHOOK("/v2/webhook/:chave", "GET"),
    PIX_LIST_WEBHOOK("/v2/webhook", "GET"),
    PIX_DELETE_WEBHOOK("/v2/webhook/:chave", "DELETE"),
    PIX_CREATE_CHARGE("/v2/cob/:txid", "PUT"),
    PIX_CREATE_IMMEDIATE_CHARGE("/v2/cob", "POST"),
    PIX_DETAIL_CHARGE("/v2/cob/:txid", "GET"),
    PIX_UPDATE_CHARGE("/v2/cob/:txid", "PATCH"),
    PIX_LIST_CHARGES("/v2/cob", "GET"),
    PIX_DEVOLUTION("/v2/pix/:e2eId/devolucao/:id", "PUT"),
    PIX_DETAIL_DEVOLUTION("/v2/pix/:e2eId/devolucao/:id", "GET"),
    PIX_SEND("/v2/gn/pix/:idEnvio", "PUT"),
    PIX_SEND_DETAIL("/v2/gn/pix/enviados/:e2eId", "GET"),
    PIX_SEND_DETAIL_ID("/v2/gn/pix/enviados/id-envio/:idEnvio", "GET"),
    PIX_SEND_LIST("/v2/gn/pix/enviados", "GET"),
    PIX_DETAIL_RECEIVED("/v2/pix/:e2eId", "GET"),
    PIX_RECEIVED_LIST("/v2/pix", "GET"),
    PIX_GENERATE_QR_CODE("/v2/loc/:id/qrcode", "GET"),
    PIX_CREATE_LOCATION("/v2/loc", "POST"),
    PIX_LOCATION_LIST("/v2/loc", "GET"),
    PIX_DETAIL_LOCATION("/v2/loc/:id", "GET"),
    PIX_UNLINK_TXID_LOCATION("/v2/loc/:id/txid", "DELETE"),
    PIX_CREATE_EVP("/v2/gn/evp", "POST"),
    PIX_LIST_EVP("/v2/gn/evp", "GET"),
    PIX_DELETE_EVP("/v2/gn/evp/:chave", "DELETE"),
    PIX_SPLIT_DETAIL_CHARGE("/v2/gn/split/cob/:txid", "GET"),
    PIX_SPLIT_LINK_CHARGE("/v2/gn/split/cob/:txid/vinculo/:splitConfigId", "PUT"),
    PIX_SPLIT_UNLINK_CHARGE("/v2/gn/split/cob/:txid/vinculo/:splitConfigId", "DELETE"),
    PIX_SPLIT_DETAIL_DUE_CHARGE("/v2/gn/split/cobv/:txid", "GET"),
    PIX_SPLIT_LINK_DUE_CHARGE("/v2/gn/split/cobv/:txid/vinculo/:splitConfigId", "PUT"),
    PIX_SPLIT_UNLINK_DUE_CHARGE("/v2/gn/split/cobv/:txid/vinculo/:splitConfigId", "DELETE"),
    PIX_SPLIT_CONFIG("/v2/gn/split/config", "POST"),
    PIX_SPLIT_CONFIG_ID("/v2/gn/split/config/:id", "PUT"),
    PIX_SPLIT_DETAIL_CONFIG("/v2/gn/split/config/:id", "GET"),
    GET_ACCOUNT_BALANCE("/v2/gn/saldo", "GET"),
    UPDATE_ACCOUNT_CONFIG("/v2/gn/config", "PUT"),
    LIST_ACCOUNT_CONFIG("/v2/gn/config", "GET"),
    PIX_CREATE_DUE_CHARGE("/v2/cobv/:txid", "PUT"),
    PIX_UPDATE_DUE_CHARGE("/v2/cobv/:txid", "PATCH"),
    PIX_DETAIL_DUE_CHARGE("/v2/cobv/:txid", "GET"),
    PIX_LIST_DUE_CHARGES("/v2/cobv/", "GET"),
    CREATE_REPORT("/v2/gn/relatorios/extrato-conciliacao", "POST"),
    DETAIL_REPORT("/v2/gn/relatorios/:id", "GET"),
    PIX_CREATE_DUE_CHARGE_BATCH("/v2/lotecobv/:id", "PUT"),
    PIX_UPDATE_DUE_CHARGE_BATCH("/v2/lotecobv/:id", "PATCH"),
    PIX_DETAIL_DUE_CHARGE_BATCH("/v2/lotecobv/:id", "GET"),
    PIX_LIST_DUE_CHARGE_BATCH("/v2/lotecobv", "GET"),
    MED_LIST("/v2/gn/infracoes", "GET"),
    MED_DEFENSE("/v2/gn/infracoes/:idInfracao/defesa", "POST");

    private final String route;
    private final String method;

    Endpoint(String route, String method) {
        this.route = route;
        this.method = method;
    }

    public String getRoute() {
        return route;
    }

    public String getMethod() {
        return method;
    }
}

