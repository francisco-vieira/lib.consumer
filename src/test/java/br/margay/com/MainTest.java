/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com;

import br.margay.com.consume.auth.AuthorizationToken;
import br.margay.com.enums.pix.CertificateType;
import br.margay.com.enums.pix.Endpoint;
import br.margay.com.enums.pix.PSPPix;
import br.margay.com.util.Supplement;
import br.margay.com.model.KeyStoreAPI;

import br.margay.com.model.request.pix.*;
import br.margay.com.model.request.pix.config.efi.ConfiguracaoSplit;
import br.margay.com.model.request.pix.config.efi.Favorecido;
import br.margay.com.model.request.pix.config.efi.Lancamento;
import br.margay.com.model.request.pix.config.efi.MinhaParte;
import br.margay.com.model.request.pix.config.efi.Repasse;
import br.margay.com.model.request.pix.config.efi.Split;

import br.margay.com.model.request.cnpj.SuframaBody;
import br.margay.com.enums.cnpj.HostBase;
import br.margay.com.consume.impl.Consumer;

import br.margay.com.model.response.pix.AccessToken;

import br.margay.com.model.response.pix.PixResponse;
import br.margay.com.model.response.pix.config.PConfigPix;
import br.margay.com.util.ProcessorUtil;
import com.google.api.client.util.Strings;
import com.google.gson.Gson;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author colpv
 * Criado em 14/05/2024
 */
class MainTest {

    Gson g = ProcessorUtil.gsonInstance();

    PConfigPix configPix = PConfigPix.build()

            .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiY2xpZW50SWQiOiJDbGllbnRfSWRfYjNmOTlkYjQwZTFlYzIwNmRiODYxOTRmNGNiOTZjMDNkMzBkYTA1YSIsImFjY291bnQiOjU3MDg3NCwiYWNjb3VudF9jb2RlIjoiOWMxMDBjZjYwMjFmZTViM2YyNTI0MTkzNzRiM2UzMDAiLCJzY29wZXMiOlsiY29iLnJlYWQiLCJjb2Iud3JpdGUiLCJjb2J2LnJlYWQiLCJjb2J2LndyaXRlIiwiZ24uYmFsYW5jZS5yZWFkIiwiZ24uaW5mcmFjdGlvbnMucmVhZCIsImduLmluZnJhY3Rpb25zLndyaXRlIiwiZ24ucGl4LmV2cC5yZWFkIiwiZ24ucGl4LmV2cC53cml0ZSIsImduLnBpeC5zZW5kLnJlYWQiLCJnbi5xcmNvZGVzLnBheSIsImduLnFyY29kZXMucmVhZCIsImduLnJlcG9ydHMucmVhZCIsImduLnJlcG9ydHMud3JpdGUiLCJnbi5zZXR0aW5ncy5yZWFkIiwiZ24uc2V0dGluZ3Mud3JpdGUiLCJnbi5zcGxpdC5yZWFkIiwiZ24uc3BsaXQud3JpdGUiLCJsb3RlY29idi5yZWFkIiwibG90ZWNvYnYud3JpdGUiLCJwYXlsb2FkbG9jYXRpb24ucmVhZCIsInBheWxvYWRsb2NhdGlvbi53cml0ZSIsInBpeC5yZWFkIiwicGl4LnNlbmQiLCJwaXgud3JpdGUiLCJ3ZWJob29rLnJlYWQiLCJ3ZWJob29rLndyaXRlIl0sImV4cGlyZXNJbiI6MzYwMCwiY29uZmlndXJhdGlvbiI6eyJ4NXQjUzI1NiI6InVCcjF6WlovY3BPTkZnZk4vREd2Z0VWRlJ5WjlmU3I2VHRUQlZaUjlZUHc9In0sImlhdCI6MTcyNjU2OTE1NCwiZXhwIjoxNzI2NTcyNzU0fQ.EeHRAaWaHhMAKhzi7R7GT0I2588BWQyNZ1YzpT2Zzk4")
            .scopes("cob.read cob.write cobv.read cobv.write gn.balance.read gn.pix.evp.read gn.pix.evp.write gn.pix.send.read gn.reports.read gn.reports.write gn.settings.read gn.settings.write gn.split.read gn.split.write lotecobv.read lotecobv.write payloadlocation.read payloadlocation.write pix.read pix.send pix.write webhook.read webhook.write")
            .certificado("C:\\develops\\certificate-hml.p12")
            .clienteId("")
            .clienteSecret("")
            .chavePix("")
            .psp(PSPPix.EFI)
            .build();

    private final String tokenSicoob = "1301865f-c6bc-38f3-9f49-666dbcfc59c3";
    private final String clientIdSicoob = "9b5e603e428cc477a2841e2683c92d21";

    @Test
    void requestCNPJTest() {
        Consumer c = Consumer.getInstance();
        String res;
        c.setBase(HostBase.CNPJ_DEV);
        res = c.get("cnpj/61940292006682");
        Assertions.assertFalse(Strings.isNullOrEmpty(res));
        System.out.println(res);
    }

    @Test
    void requestSuframaTest() {
        Consumer c = Consumer.getInstance();
        c.setBase(HostBase.CNPJ_PUBLICA);
        SuframaBody b = SuframaBody.builder()
                .cnpj("61940292006682")
                .inscricao("210140267")
                .build();
        String res = g.toJson(b);
        res = c.post("suframa", res);

        Assertions.assertFalse(Strings.isNullOrEmpty(res));
        System.out.println(res);

    }

    @Test
    void requestSicoobPixGET() {

        AuthorizationToken.authorization(tokenSicoob);
        Map<String, String> parans = new HashMap<>();
        parans.put("revisao", "978805810");

        Consumer c = Consumer.getInstance();
        c.setBase("https://sandbox.sicoob.com.br/sicoob/sandbox/pix/api/v2");

        c.header("Accept", "application/json");
        c.header("client_id", clientIdSicoob);

        String result = c.get("cob/ITgRlxIWmI5AhxMjFoQy4bwTqU9z7xj4BDJ", parans);
        System.out.println(result);

    }

    @Test
//    SICOOB
    void requestSicoobPixPOST() {
        AuthorizationToken.authorization(tokenSicoob);
        Consumer c = Consumer.getInstance();
        c.header("client_id", clientIdSicoob);
        c.header("Accept", "application/json");
        c.setBase("https://sandbox.sicoob.com.br/sicoob/sandbox/pix/api");

        PixBody pixBody = PixBody.builder()
                .calendario(new Calendario(3600))
                .devedor(new Devedor("Fulano de tal", "36638219006"))
                .valor(new Valor("10.00"))
                .solicitacaoPagador("O peddido super")
                .chave("7d9f0335-8dcc-4054-9bf9-0dbd61d36950")
                .infoAdicionais(Collections.singletonList(new InfoAdicional("Campo um banco recebedor", "Informações adicionais para leitura do psp-recebedor")))
                .build();

        String json = g.toJson(pixBody);
        String result = c.post(Endpoint.CHARGE_PIX_CREATE_IMMEDIATE.router(), json);

        System.out.println(result);

    }


    @Test
    void requestEfiPixGet() {

        AuthorizationToken.authorization(configPix.getToken());
        Consumer c = Consumer.getInstance(configPix.getCertificado(), CertificateType.PKCS_12);
        c.header("scope", configPix.getScopes());
        c.setBase(configPix.getPspPix().getAmbiente(2));

        String value = c.get(Endpoint.CHARGE_PIX_DETAIL.router("938f7c58d7654585b4bf6336d968206f"));
        System.out.println(value);

    }

    @Test
    void requestEfiPixPOST() {

        AuthorizationToken.authorization(configPix.getToken());

        Consumer c = Consumer.getInstance(configPix.getCertificado(), CertificateType.PKCS_12);
        c.header("scope", configPix.getScopes());
        c.setBase(configPix.getPspPix().getAmbiente(2));

        PixBody pixBody = PixBody.builder()
                .calendario(new Calendario(6000))
                .devedor(new Devedor("João de Maria", "36638219006"))
                .solicitacaoPagador("O peddido super")
                .chave(configPix.getChavePix())
                .valor(new Valor("10.34"))
                .infoAdicionais(Collections.singletonList(new InfoAdicional("valor", "Informações sobre pix")))
                .build();

        String json = g.toJson(pixBody);

        Map<String, String> params = new HashMap<>();
        String res = c.post(Endpoint.CHARGE_PIX_CREATE_IMMEDIATE.router(), json, params);

        PixResponse response = g.fromJson(res, PixResponse.class);
        String stringPix = response.getPixCopiaECola();
        c.gerarQrCodeImage(stringPix, 300, 300);

        Assertions.assertFalse(Strings.isNullOrEmpty(stringPix));
        System.out.println(stringPix);
    }

    @Test
    void requestEfiAuthorization() {

        AuthorizationToken.authorization(configPix.getClienteId(), configPix.getClienteSecret());

        Consumer c = Consumer.getInstance(configPix.getCertificado(), CertificateType.PKCS_12);

        c.setBase(configPix.getPspPix().getAmbiente(2));

        String json = g.toJson(new GrantType(Supplement.grant_type));

        Map<String, String> params = new HashMap<>();
        String res = c.post(Endpoint.AUTHORIZE.router(), json, params, ContentType.APPLICATION_JSON);

        AccessToken token = g.fromJson(res, AccessToken.class);

        Assertions.assertFalse(Strings.isNullOrEmpty(token.getAccess_token()));
        System.out.println("TOKEN: \n" + token.getAccess_token()+"\n");
        System.out.println(token.getScope());
    }

    @Test
    void submitEmail() throws Exception {

//        ProcessMail mail = SubmitMail.newInstance("","");
//        mail.setFrom("");
//        mail.send();

//        BuilderMail builderMail = BuilderMail.getInstance("", "");
//        builderMail.from()
//                .attachment()
//                .date()
//                .send();

    }

    @Test
    void processCertificadoTest() throws Exception {

        AuthorizationToken.authorization(configPix.getClienteId(), configPix.getClienteSecret());

        PSPPix pspPix = PSPPix.EFI;
        Map<PSPPix, KeyStoreAPI> result = ProcessorUtil.loadKeyStore(configPix.getCertificado(), CertificateType.PKCS_12, pspPix);

        KeyStoreAPI storePix = result.get(pspPix);

        Consumer c = Consumer.getInstance(storePix);

        c.setBase(pspPix.getAmbiente(2));

        String json = g.toJson(new GrantType(Supplement.grant_type));

        Map<String, String> params = new HashMap<>();
        String res = c.post(Endpoint.AUTHORIZE.router(), json, params, ContentType.APPLICATION_JSON);

        AccessToken token = g.fromJson(res, AccessToken.class);

        Assertions.assertFalse(Strings.isNullOrEmpty(token.getAccess_token()));
        System.out.println(token.getAccess_token());
        System.out.println(token.getScope());


    }

    @Test
    void configuracaoSplitPixEfi() throws Exception {

        AuthorizationToken.authorization(configPix.getToken());

        Consumer c = Consumer.getInstance(configPix.getCertificado(), CertificateType.PKCS_12);
        c.header("scope", "gn.split.write");
        c.setBase(configPix.getPspPix().getAmbiente(2));


       List<Repasse>  repasses = Collections.singletonList(Repasse.builder()
                       .favorecido(new Favorecido("94271564656", "1234567"))
                       .tipo("PORCENTAGEM")
                       .valor("15.00")
                .build());

        ConfiguracaoSplit configuracaoSplit = ConfiguracaoSplit.builder()
                .id("SplitEstatico0012")
                .descricao("Batatinha frita 1, 2, 3")
                .lancamento(new Lancamento(false))
                .split(Split.builder()
                        .divisaoTarifa("assumir_total")
                        .minhaParte(new MinhaParte("porcetagem", "60.00"))
                        .repasses(repasses)
                        .build())
                .build();


        String endpoint = Endpoint.SPLIT_PIX_CONFIG.router();
        String json = g.toJson(configuracaoSplit);
        String res = c.post(endpoint, json);
        System.out.println(res);

    }
}



