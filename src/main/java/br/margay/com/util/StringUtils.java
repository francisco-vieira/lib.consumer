/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.util;

import br.margay.com.enums.pix.CertificateType;
import br.margay.com.exception.ServiceException;
import br.margay.com.model.KeyStorePix;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Base64;

/**
 * @author colpv
 * Criado em 24/05/2024
 */
public class StringUtils {

    public static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNull(String value) {
        return value == null;
    }

    public static byte[] decodeToBytes(String value) {
        return value == null ? null : Base64.getDecoder().decode(value);
    }

    public static String encodeToString(byte[] value) {
        return value == null ? null : Base64.getEncoder().encodeToString(value);
    }

    public static KeyStore base64ToKeyStore(KeyStorePix storePix) throws ServiceException {

        byte[] fileByte = decodeToBytes(storePix.getCertificate());

        try (FileInputStream stream = convertByteArrayInputStreamToFileInputStream(fileByte, storePix.getCertificateType())) {

            KeyStore keyStore = KeyStore.getInstance(storePix.getCertificateType().toString());
            char[] password = storePix.getPassword().toCharArray();
            keyStore.load(stream, password);

            return keyStore;
        } catch (ServiceException | IOException | KeyStoreException | NoSuchAlgorithmException |
                 CertificateException e) {
            throw new ServiceException(e);
        }
    }

    public static FileInputStream convertByteArrayInputStreamToFileInputStream(byte[] bytes, CertificateType type) throws IOException {
        String temp = "certificate".concat(type.getExtension());
        try (FileOutputStream fos = new FileOutputStream(temp)) {
            fos.write(bytes);
        }
        return new FileInputStream(temp);
    }

    public static String stringToValuePix(String value) {
        StringBuilder builder = new StringBuilder();
        for (Character c : value.toCharArray()) {
            if (Character.isDigit(c) || c == ',') {
                if(c == ',' && !builder.toString().contains(".")){
                    builder.append(".");
                }else{
                    builder.append(c);
                }
            }
        }
        return builder.toString();
    }

    public static String digitFilter(String str) {
        if(str == null || str.isEmpty()){
            return str;
        }

        StringBuilder builder = new StringBuilder();
        for (Character c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }

        return builder.toString();
    }

}
