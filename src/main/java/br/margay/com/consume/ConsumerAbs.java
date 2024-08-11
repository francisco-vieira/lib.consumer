/*
 * Margay Sistema 2024
 */
package br.margay.com.consume;

import br.margay.com.consume.auth.AuthorizationToken;
import br.margay.com.exception.ServiceException;
import br.margay.com.ipack.IConsumer;
import br.margay.com.enums.pix.CertificateType;
import br.margay.com.enums.cnpj.HostBase;
import br.margay.com.model.KeyStorePix;
import br.margay.com.util.ProcessorUtil;
import br.margay.com.util.StringUtils;
import com.google.api.client.util.Strings;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Francisco Vieira
 */
public abstract class ConsumerAbs implements IConsumer<ConsumerAbs> {

    private final CloseableHttpClient http;

    protected HostBase hostBase;
    protected String strHostBase;
    protected Map<String, String> headers;
    private String prefixo;
    private String sufixo;

    private static final String AUTHORIZATION = "Authorization";

    protected ConsumerAbs() {
        http = HttpClients.createDefault();
        this.headers = new HashMap<>();
    }

    protected ConsumerAbs(String path, String password, CertificateType certf) throws ServiceException {
        try {
            this.headers = new HashMap<>();
            char[] pass = password.toCharArray();
            KeyStore keyStore = KeyStore.getInstance(certf.toString());
            Path certPath = Paths.get(path);
            try (FileInputStream fis = new FileInputStream(certPath.toFile())) {
                keyStore.load(fis, pass);
            }

            http = HttpClients.custom()
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(
                            SSLContexts.custom()
                                    .loadKeyMaterial(keyStore, pass)
                                    .build(),
                            NoopHostnameVerifier.INSTANCE))
                    .build();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    protected ConsumerAbs(KeyStorePix storePix) throws ServiceException {
        try {
            this.headers = new HashMap<>();

            String password = storePix.getPassword();
            if (StringUtils.isNull(password)) {
                password = "";
                storePix.setPassword(password);
            }

            char[] pass = password.toCharArray();

            KeyStore keyStore = StringUtils.base64ToKeyStore(storePix);

            http = HttpClients.custom()
                    .setSSLSocketFactory(new SSLConnectionSocketFactory(
                            SSLContexts.custom()
                                    .loadKeyMaterial(keyStore, pass)
                                    .build(),
                            NoopHostnameVerifier.INSTANCE))
                    .build();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public abstract RequestConfig requestConfig();

    private Map<String, String> headers() {
        return headers;
    }

    public abstract void setBase(HostBase hostBase);

    public abstract void setBase(String hostBase);

    public abstract String urlBase();

    public abstract void error(String error);

    @Override
    public void setKeyPref(String prefixo, String sufixo) throws ServiceException {
        this.prefixo = prefixo;
        this.sufixo = sufixo;
    }

    @Override
    public String get(String endpoint, Map<String, String> params) throws ServiceException {

        try {
            validaPref();
            HttpGet httpGet = new HttpGet(withBar(endpoint));
            httpGet.setConfig(requestConfig());
            headers().forEach(httpGet::setHeader);
            httpGet.setHeader(AUTHORIZATION, AuthorizationToken.getTokenAuthorization(prefixo, sufixo));

            URI uri = loadParams(params, httpGet.getURI());
            httpGet.setURI(uri);

            ResponseHandler<String> handler = getStringResponseHandler();
            String body = this.http.execute(httpGet, handler);
            error(body);
            return body;
        } catch (RuntimeException | IOException | URISyntaxException e) {
            ProcessorUtil.writeLogger(ConsumerAbs.class).severe(e.toString());
            throw new ServiceException(e);
        }
    }

    public String get(Map<String, String> params) throws ServiceException {
        return get("", params);
    }

    public String get(String endpoint) throws ServiceException {
        return this.get(endpoint, new HashMap<>());
    }

    private static ResponseHandler<String> getStringResponseHandler() {
        return (final HttpResponse response) -> {
            response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        };
    }

    public String post(String endpoint, String json) throws ServiceException {
        return this.post(endpoint, json, new HashMap<>());
    }

    public String post(String endpoint, String json, Map<String, String> params) throws ServiceException {
        return this.post(endpoint, json, params, ContentType.APPLICATION_JSON);
    }

    @Override
    public String post(String endpoint, String json, Map<String, String> params, ContentType type) throws ServiceException {

        try {


            StringEntity stringEntity = new StringEntity(json, type);
            HttpPost httpPost = getHttpPost(endpoint, params);
            httpPost.setEntity(stringEntity);
            headers().forEach(httpPost::setHeader);

            httpPost.setHeader("Content-Type", type.getMimeType());
            ResponseHandler<String> handler = (final HttpResponse response) -> {
                response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();
                for (Header r : response.getHeaders(AUTHORIZATION)) {
                    AuthorizationToken.authorization(r.getValue(), prefixo, sufixo);
                }
                return entity != null ? EntityUtils.toString(entity) : null;
            };
            String body = this.http.execute(httpPost, handler);
            error(body);

            return body;
        } catch (RuntimeException | IOException | URISyntaxException e) {
            ProcessorUtil.writeLogger(ConsumerAbs.class)
                    .severe(e.toString());
            throw new ServiceException(e);
        }
    }

    public Boolean delete(String endpoint) throws ServiceException {
        return this.delete(endpoint, new HashMap<>());
    }

    public Boolean delete(String endpoint, Map<String, String> params) throws ServiceException {
        try {
            validaPref();
            HttpDelete httpDelete = new HttpDelete(withBar(endpoint));
            httpDelete.setHeader(AUTHORIZATION, AuthorizationToken.getTokenAuthorization(prefixo, sufixo));
            httpDelete.setConfig(requestConfig());

            URI uri = loadParams(params, httpDelete.getURI());
            httpDelete.setURI(uri);

            ResponseHandler<String> handler = getStringResponseHandler();
            String body = this.http.execute(httpDelete, handler);
            error(body);

            return Boolean.valueOf(body);
        } catch (Exception e) {
            ProcessorUtil.writeLogger(ConsumerAbs.class).severe(e.toString());
            throw new ServiceException(e);
        }

    }

    public void filePost(String endpoint, ContentType type, String file) throws ServiceException {
        this.filePost(endpoint, type, file, new HashMap<>());
    }

    public void filePost(String endpoint, ContentType type, String file, Map<String, String> params) throws ServiceException {

        try {
            if (Strings.isNullOrEmpty(file)) {
                throw new FileNotFoundException("File notfound exception");
            }
            FileEntity fileEntity = new FileEntity(new File(file), type);
            fileEntity.setContentEncoding("UTF-8");
            HttpPost httpPost = getHttpPost(endpoint, params);
            headers().forEach(httpPost::setHeader);
            httpPost.setEntity(fileEntity);
            ResponseHandler<String> handler = getStringResponseHandler();
            error(this.http.execute(httpPost, handler));

        } catch (IOException | URISyntaxException e) {
            ProcessorUtil.writeLogger(ConsumerAbs.class).severe(e.toString());
            throw new ServiceException(e);
        }

    }

    private HttpPost getHttpPost(String endpoint, Map<String, String> params) throws URISyntaxException {
        validaPref();
        HttpPost httpPost = new HttpPost(withBar(endpoint));
        httpPost.setConfig(requestConfig());

        if (AuthorizationToken.isTokenValid()) {
            httpPost.setHeader(AUTHORIZATION, AuthorizationToken.getTokenAuthorization(prefixo, sufixo));
        }
        URI uri = loadParams(params, httpPost.getURI());
        httpPost.setURI(uri);
        return httpPost;
    }

    private URI loadParams(Map<String, String> params, URI uri) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(uri);
        if (Objects.nonNull(params)) {
            params.forEach(builder::addParameter);
        }
        return builder.build();
    }

    private String withBar(String endpoint) {

        String url = urlBase();
        if (Objects.equals(HostBase.CNPJ_DEV.toString(), url)) {
            endpoint = endpoint.replace("cnpj", "");
        }

        if (!endpoint.isEmpty() && !endpoint.startsWith("/")) {
            endpoint = "/".concat(endpoint);
        }

        return urlBase().concat(endpoint);
    }

    @Override
    public byte[] gerarQrCode(String text, int width, int heigth) throws ServiceException {
        return ProcessorUtil.generateQrCode(text, width, heigth);
    }

    @Override
    public void gerarQrCodeImage(String text, int width, int height, String filePath) throws ServiceException {
        ProcessorUtil.generateQRCodeImage(text, width, height, filePath);
    }

    @Override
    public void gerarQrCodeImage(String text, int width, int height) throws ServiceException {
        gerarQrCodeImage(text, width, height, "qrcode.png");
    }

    private void validaPref() {
        if (StringUtils.isEmpty(prefixo) && StringUtils.isEmpty(sufixo)) {
            throw new ServiceException("Prefixo e sufixo são obrigatórios.\nPasse esses valores no method: setKeyPref");
        }
    }

}
