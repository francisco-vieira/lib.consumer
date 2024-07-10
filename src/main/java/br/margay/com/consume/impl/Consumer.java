/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.consume.impl;

import br.margay.com.model.msg.Message;
import br.margay.com.exception.ServiceException;
import br.margay.com.enums.pix.CertificateType;
import br.margay.com.enums.cnpj.HostBase;
import br.margay.com.consume.ConsumerAbs;
import br.margay.com.model.KeyStorePix;
import br.margay.com.util.ProcessorUtil;
import com.google.api.client.util.Strings;
import com.google.gson.Gson;
import org.apache.http.client.config.RequestConfig;

/**
 * @author colpv
 * Criado em 10/05/2024
 */
public class Consumer extends ConsumerAbs {

    private Consumer() {
        super();
    }

    public Consumer(String path, String password, CertificateType certificateType) {
        super(path, password, certificateType);
    }

    public Consumer(KeyStorePix storePix) {
        super(storePix);
    }

    public static Consumer getInstance() {
        return new Consumer();
    }

    public static Consumer getInstance(String path, String password, CertificateType certificateType) {
        return new Consumer(path, password, certificateType);
    }

    public static Consumer getInstance(String path, CertificateType certificateType) {
        return getInstance(path, "", certificateType);
    }

    public static Consumer getInstance(KeyStorePix storePix) {
        return new Consumer(storePix);
    }

    @Override
    public void setBase(HostBase hostBase) {
        if (hostBase != null) {
            this.strHostBase = null;
        }
        this.hostBase = hostBase;
    }

    @Override
    public void setBase(String hostBase) {
        if (!Strings.isNullOrEmpty(hostBase)) {
            this.hostBase = null;
        }
        this.strHostBase = hostBase;
    }

    @Override
    public RequestConfig requestConfig() {
        final int TIME_OUT = 1000 * 60;
        return RequestConfig.custom()
                .setConnectTimeout(TIME_OUT)
                .setSocketTimeout(TIME_OUT)
                .build();
    }

    @Override
    public String urlBase() {

        if (!Strings.isNullOrEmpty(strHostBase)) {
            return strHostBase;
        }

        if (Strings.isNullOrEmpty(strHostBase) && hostBase == null) {
            throw new ServiceException("Host base not fund");
        }

        return hostBase.toString();
    }

    @Override
    public void error(String error) {
        Gson gson = ProcessorUtil.gsonInstance();

        if (error.contains("<html>")) {
            return;
        }

        Message msg = gson.fromJson(error, Message.class);
        if (msg.getHttpCode() > 299) {
            ProcessorUtil.writeLogger(Consumer.class)
                    .severe(error);
        }

    }

    @Override
    public ConsumerAbs header(String key, String value) {
        headers.put(key, value);
        return this;
    }

}
