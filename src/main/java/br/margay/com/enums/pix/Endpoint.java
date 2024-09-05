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
    PIX_CONFIG_WEBHOOK("/v2/webhook/", "PUT"),
    PIX_DETAIL_WEBHOOK("/v2/webhook/", "GET"),
    PIX_LIST_WEBHOOK("/v2/webhook", "GET"),
    PIX_DELETE_WEBHOOK("/v2/webhook/", "DELETE"),
    PIX_CREATE_CHARGE("/v2/cob/", "PUT"),
    PIX_CREATE_IMMEDIATE_CHARGE("/v2/cob", "POST"),
    PIX_DETAIL_CHARGE("/v2/cob/", "GET"),
    PIX_UPDATE_CHARGE("/v2/cob/", "PATCH"),
    PIX_LIST_CHARGES("/v2/cob", "GET"),
    PIX_DEVOLUTION("/v2/pix//devolucao/", "PUT"),
    PIX_DETAIL_DEVOLUTION("/v2/pix//devolucao/", "GET"),
    PIX_SEND("/v2/gn/pix/Envio", "PUT"),
    PIX_SEND_DETAIL("/v2/gn/pix/enviados/", "GET"),
    PIX_SEND_DETAIL_ID("/v2/gn/pix/enviados/id-envio/Envio", "GET"),
    PIX_SEND_LIST("/v2/gn/pix/enviados", "GET"),
    PIX_DETAIL_RECEIVED("/v2/pix/", "GET"),
    PIX_RECEIVED_LIST("/v2/pix", "GET"),
    PIX_GENERATE_QR_CODE("/v2/loc//qrcode", "GET"),
    PIX_CREATE_LOCATION("/v2/loc", "POST"),
    PIX_LOCATION_LIST("/v2/loc", "GET"),
    PIX_DETAIL_LOCATION("/v2/loc/", "GET"),
    PIX_UNLINK_TXID_LOCATION("/v2/loc//txid", "DELETE"),
    PIX_CREATE_EVP("/v2/gn/evp", "POST"),
    PIX_LIST_EVP("/v2/gn/evp", "GET"),
    PIX_DELETE_EVP("/v2/gn/evp/", "DELETE"),
    PIX_SPLIT_DETAIL_CHARGE("/v2/gn/split/cob/", "GET"),
    PIX_SPLIT_LINK_CHARGE("/v2/gn/split/cob//vinculo/", "PUT"),
    PIX_SPLIT_UNLINK_CHARGE("/v2/gn/split/cob//vinculo/", "DELETE"),
    PIX_SPLIT_DETAIL_DUE_CHARGE("/v2/gn/split/cobv/", "GET"),
    PIX_SPLIT_LINK_DUE_CHARGE("/v2/gn/split/cobv//vinculo/", "PUT"),
    PIX_SPLIT_UNLINK_DUE_CHARGE("/v2/gn/split/cobv//vinculo/", "DELETE"),
    PIX_SPLIT_CONFIG("/v2/gn/split/config", "POST"),
    PIX_SPLIT_CONFIG_ID("/v2/gn/split/config/", "PUT"),
    PIX_SPLIT_DETAIL_CONFIG("/v2/gn/split/config/", "GET"),
    GET_ACCOUNT_BALANCE("/v2/gn/saldo", "GET"),
    UPDATE_ACCOUNT_CONFIG("/v2/gn/config", "PUT"),
    LIST_ACCOUNT_CONFIG("/v2/gn/config", "GET"),
    PIX_CREATE_DUE_CHARGE("/v2/cobv/", "PUT"),
    PIX_UPDATE_DUE_CHARGE("/v2/cobv/", "PATCH"),
    PIX_DETAIL_DUE_CHARGE("/v2/cobv/", "GET"),
    PIX_LIST_DUE_CHARGES("/v2/cobv/", "GET"),
    CREATE_REPORT("/v2/gn/relatorios/extrato-conciliacao", "POST"),
    DETAIL_REPORT("/v2/gn/relatorios/", "GET"),
    PIX_CREATE_DUE_CHARGE_BATCH("/v2/lotecobv/", "PUT"),
    PIX_UPDATE_DUE_CHARGE_BATCH("/v2/lotecobv/", "PATCH"),
    PIX_DETAIL_DUE_CHARGE_BATCH("/v2/lotecobv/", "GET"),
    PIX_LIST_DUE_CHARGE_BATCH("/v2/lotecobv", "GET"),
    MED_LIST("/v2/gn/infracoes", "GET"),
    MED_DEFENSE("/v2/gn/infracoes/Infracao/defesa", "POST");

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

