/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com;

import br.margay.com.consume.auth.AuthorizationToken;
import br.margay.com.enums.pix.CertificateType;
import br.margay.com.request.model.cnpj.SuframaBody;
import br.margay.com.enums.cnpj.HostBase;
import br.margay.com.consume.impl.Consumer;
import br.margay.com.request.model.pix.Calendario;
import br.margay.com.request.model.pix.Devedor;
import br.margay.com.request.model.pix.InfoAdicional;
import br.margay.com.request.model.pix.Localidade;
import br.margay.com.request.model.pix.Valor;
import br.margay.com.request.model.pix.GrantType;
import br.margay.com.request.PixBody;
import br.margay.com.response.model.pix.AccessToken;
import br.margay.com.response.PixResponse;
import br.margay.com.util.ProcessorUtil;
import com.google.api.client.util.Strings;
import com.google.gson.Gson;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author colpv
 * Criado em 14/05/2024
 */
public class MainTest {

    Gson g = ProcessorUtil.gsonInstance();

    private final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwiY2xpZW50SWQiOiJDbGllbnRfSWRfYjNmOTlkYjQwZTFlYzIwNmRiODYxOTRmNGNiOTZjMDNkMzBkYTA1YSIsImFjY291bnQiOjU3MDg3NCwiYWNjb3VudF9jb2RlIjoiOWMxMDBjZjYwMjFmZTViM2YyNTI0MTkzNzRiM2UzMDAiLCJzY29wZXMiOlsiY29iLnJlYWQiLCJjb2Iud3JpdGUiLCJjb2J2LnJlYWQiLCJjb2J2LndyaXRlIiwiZ24uYmFsYW5jZS5yZWFkIiwiZ24ucGl4LmV2cC5yZWFkIiwiZ24ucGl4LmV2cC53cml0ZSIsImduLnBpeC5zZW5kLnJlYWQiLCJnbi5yZXBvcnRzLnJlYWQiLCJnbi5yZXBvcnRzLndyaXRlIiwiZ24uc2V0dGluZ3MucmVhZCIsImduLnNldHRpbmdzLndyaXRlIiwiZ24uc3BsaXQucmVhZCIsImduLnNwbGl0LndyaXRlIiwibG90ZWNvYnYucmVhZCIsImxvdGVjb2J2LndyaXRlIiwicGF5bG9hZGxvY2F0aW9uLnJlYWQiLCJwYXlsb2FkbG9jYXRpb24ud3JpdGUiLCJwaXgucmVhZCIsInBpeC5zZW5kIiwicGl4LndyaXRlIiwid2ViaG9vay5yZWFkIiwid2ViaG9vay53cml0ZSJdLCJleHBpcmVzSW4iOjM2MDAsImNvbmZpZ3VyYXRpb24iOnsieDV0I1MyNTYiOiJ1QnIxelpaL2NwT05GZ2ZOL0RHdmdFVkZSeVo5ZlNyNlR0VEJWWlI5WVB3PSJ9LCJpYXQiOjE3MTY1Mjg1NzEsImV4cCI6MTcxNjUzMjE3MX0.yvj1YYaQlS0TTkeS4eiAeJVpiMVTRJ_P1FrY4rqbMRQ";
    private final String tokenSicoob = "";
    @Test
    public void requestCNPJTest() {
        Consumer c = Consumer.getInstance();
        String res;
        c.setBase(HostBase.CNPJ_DEV);
        res = c.get("cnpj/61940292006682");
        Assertions.assertFalse(Strings.isNullOrEmpty(res));
        System.out.println(res);
    }

    @Test
    public void requestSuframaTest() {
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
    public void requestSicoobPixGET() {

        AuthorizationToken.authorization(tokenSicoob);
        Map<String, String> parans = new HashMap<>();
        parans.put("revisao", "978805810");

        Consumer c = Consumer.getInstance();
        c.setBase("https://sandbox.sicoob.com.br/sicoob/sandbox/pix/api/v2");

        c.header("Accept", "application/json");
        c.header("client_id", "9b5e603e428cc477a2841e2683c92d21");

        String result = c.get("cob/ITgRlxIWmI5AhxMjFoQy4bwTqU9z7xj4BDJ", parans);
        System.out.println(result);

    }


    @Test
//    SICOOB
    public void requestSicoobPixPOST() {
        AuthorizationToken.authorization(tokenSicoob);
        Consumer c = Consumer.getInstance();
        c.header("client_id", "9b5e603e428cc477a2841e2683c92d21");
        c.header("Accept", "application/json");
        c.setBase("https://sandbox.sicoob.com.br/sicoob/sandbox/pix/api");

        PixBody pixBody = PixBody.builder()
                .calendario(new Calendario(3600))
                .devedor(new Devedor("Fulano de tal", "36638219006"))
                .loc(new Localidade(0))
                .valor(new Valor("10.00"))
                .solicitacaoPagador("O peddido super")
                .chave("")
                .infoAdicionais(Collections.singletonList(new InfoAdicional("Campo um banco recebedor", "Informações adicionais para leitura do psp-recebedor")))
                .build();

        String json = g.toJson(pixBody);
        String result = c.post("/v2/cob", json);

        System.out.println(result);

    }


    @Test
    public void requestGetEfiPixGet() {

        AuthorizationToken.authorization(token);
        Consumer c = Consumer.getInstance("homologacao-570874-SociusHomologacao.p12", CertificateType.PKCS_12);
        c.header("scope", "cob.write");
        c.setBase("https://pix-h.api.efipay.com.br");

        String value = c.get("v2/cob/938f7c58d7654585b4bf6336d968206f");
        System.out.println(value);

    }

    @Test
    public void requestEfiPayPixPOST() {

        AuthorizationToken.authorization(token);

        Consumer c = Consumer.getInstance("homologacao-570874-SociusHomologacao.p12", CertificateType.PKCS_12);
        c.header("scope", "cob.read cob.write cobv.read cobv.write gn.balance.read gn.pix.evp.read gn.pix.evp.write gn.pix.send.read gn.reports.read gn.reports.write gn.settings.read gn.settings.write gn.split.read gn.split.write lotecobv.read lotecobv.write payloadlocation.read payloadlocation.write pix.read pix.send pix.write webhook.read webhook.write");
        c.setBase("https://pix-h.api.efipay.com.br");

        PixBody pixBody = PixBody.builder()
                .calendario(new Calendario(6000))
                .devedor(new Devedor("João de Maria", "36638219006"))
                .solicitacaoPagador("O peddido super")
                .chave("71cdf9ba-c695-4e3c-b010-abb521a3f1be")
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
    public void requestEfiAuthorization() {

        AuthorizationToken.authorization(
                "",
                ""
        );

        Consumer c = Consumer.getInstance(
                "homologacao-570874-SociusHomologacao.p12",
                "",
                CertificateType.PKCS_12);

        c.setBase("https://pix-h.api.efipay.com.br");

        String json = g.toJson(new GrantType("client_credentials"));

        Map<String, String> params = new HashMap<>();
        String res = c.post("/oauth/token", json, params, ContentType.APPLICATION_JSON);

        AccessToken token = g.fromJson(res, AccessToken.class);

        Assertions.assertFalse(Strings.isNullOrEmpty(token.getAccess_token()));
        System.out.println(token.getAccess_token());
        System.out.println(token.getScope());
    }

    @Test
    public void requestAPI2() throws Exception {

    }
}



