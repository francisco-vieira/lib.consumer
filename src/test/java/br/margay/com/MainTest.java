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
import br.margay.com.model.KeyStorePix;
import br.margay.com.model.request.pix.config.efi.ConfiguracaoSplit;
import br.margay.com.model.request.pix.config.efi.Favorecido;
import br.margay.com.model.request.pix.config.efi.Lancamento;
import br.margay.com.model.request.pix.config.efi.MinhaParte;
import br.margay.com.model.request.pix.config.efi.Repasse;
import br.margay.com.model.request.pix.config.efi.Split;
import br.margay.com.model.response.pix.PConfigPix;
import br.margay.com.model.request.cnpj.SuframaBody;
import br.margay.com.enums.cnpj.HostBase;
import br.margay.com.consume.impl.Consumer;
import br.margay.com.model.request.pix.Calendario;
import br.margay.com.model.request.pix.Devedor;
import br.margay.com.model.request.pix.InfoAdicional;
import br.margay.com.model.request.pix.Valor;
import br.margay.com.model.request.pix.GrantType;
import br.margay.com.model.request.PixBody;
import br.margay.com.model.response.pix.AccessToken;
import br.margay.com.model.response.PixResponse;
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

            .token("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiY2xpZW50SWQiOiJDbGllbnRfSWRfYjNmOTlkYjQwZTFlYzIwNmRiODYxOTRmNGNiOTZjMDNkMzBkYTA1YSIsImFjY291bnQiOjU3MDg3NCwiYWNjb3VudF9jb2RlIjoiOWMxMDBjZjYwMjFmZTViM2YyNTI0MTkzNzRiM2UzMDAiLCJzY29wZXMiOlsiY29iLnJlYWQiLCJjb2Iud3JpdGUiLCJjb2J2LnJlYWQiLCJjb2J2LndyaXRlIiwiZ24uYmFsYW5jZS5yZWFkIiwiZ24ucGl4LmV2cC5yZWFkIiwiZ24ucGl4LmV2cC53cml0ZSIsImduLnBpeC5zZW5kLnJlYWQiLCJnbi5yZXBvcnRzLnJlYWQiLCJnbi5yZXBvcnRzLndyaXRlIiwiZ24uc2V0dGluZ3MucmVhZCIsImduLnNldHRpbmdzLndyaXRlIiwiZ24uc3BsaXQucmVhZCIsImduLnNwbGl0LndyaXRlIiwibG90ZWNvYnYucmVhZCIsImxvdGVjb2J2LndyaXRlIiwicGF5bG9hZGxvY2F0aW9uLnJlYWQiLCJwYXlsb2FkbG9jYXRpb24ud3JpdGUiLCJwaXgucmVhZCIsInBpeC5zZW5kIiwicGl4LndyaXRlIiwid2ViaG9vay5yZWFkIiwid2ViaG9vay53cml0ZSJdLCJleHBpcmVzSW4iOjM2MDAsImNvbmZpZ3VyYXRpb24iOnsieDV0I1MyNTYiOiJ1QnIxelpaL2NwT05GZ2ZOL0RHdmdFVkZSeVo5ZlNyNlR0VEJWWlI5WVB3PSJ9LCJpYXQiOjE3MjAyMTA3NzIsImV4cCI6MTcyMDIxNDM3Mn0.KN5DKw1uhGqQEd5xWJOAoltM5C9GDlAVrKz8_Jz30jg")
            .scopes("cob.read cob.write cobv.read cobv.write gn.balance.read gn.pix.evp.read gn.pix.evp.write gn.pix.send.read gn.reports.read gn.reports.write gn.settings.read gn.settings.write gn.split.read gn.split.write lotecobv.read lotecobv.write payloadlocation.read payloadlocation.write pix.read pix.send pix.write webhook.read webhook.write")
            .certificado("certificate.p12")
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
        String result = c.post("/v2/cob", json);

        System.out.println(result);

    }


    @Test
    void requestEfiPixGet() {

        AuthorizationToken.authorization(configPix.getToken());
        Consumer c = Consumer.getInstance(configPix.getCertificado(), CertificateType.PKCS_12);
        c.header("scope", configPix.getScopes());
        c.setBase(configPix.getPspPix().getAmbiente(2));

        String value = c.get("v2/cob/938f7c58d7654585b4bf6336d968206f");
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
        String res = c.post("v2/cob", json, params);

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

        String json = g.toJson(new GrantType("client_credentials"));

        Map<String, String> params = new HashMap<>();
        String res = c.post("/oauth/token", json, params, ContentType.APPLICATION_JSON);

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
        Map<PSPPix, KeyStorePix> result = ProcessorUtil.loadKeyStore(configPix.getCertificado(), CertificateType.PKCS_12, pspPix);

        KeyStorePix storePix = result.get(pspPix);

        Consumer c = Consumer.getInstance(storePix);

        c.setBase(pspPix.getAmbiente(2));

        String json = g.toJson(new GrantType("client_credentials"));

        Map<String, String> params = new HashMap<>();
        String res = c.post("/oauth/token", json, params, ContentType.APPLICATION_JSON);

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


        String endpoint = Endpoint.PIX_SPLIT_CONFIG.getRoute();
        String json = g.toJson(configuracaoSplit);
        String res = c.post(endpoint, json);
        System.out.println(res);

    }
}



