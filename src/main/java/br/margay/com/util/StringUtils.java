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
 * @author francisco.vieira
 * Criado em 24/05/2024
 */
public class StringUtils {

    private StringUtils() {
        super();
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    /**
     *  Method verifica se o valor eh null ou vazio
     * @param value valor a verificada
     * @return string verificada
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     *  Method verifica se o valor eh null
     * @param value valor a verificada
     * @return string verificada
     */
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
        value = stringValidate(value);
        pointFilter(value, builder);
        value = builder.toString();

        String[] split = value.split("\\.");

        int length = 1;
        String prefix = split[0];
        prefix = prefix.length() == length ? "0".concat(prefix) : prefix;

        int count = removeZero(prefix);

        if (count > 2) {
            prefix = prefix.substring(count-1);
        }

        if(prefix.isEmpty()){
            prefix = "00";
        }

        String sufix;
        if (split.length > 1) {
            sufix = split[1];
            sufix = sufix.length() == length ? "0".concat(sufix) : sufix;
        } else {
            sufix = "00";
        }

        return prefix + ".".concat(sufix);
    }

    private static void pointFilter(String value, StringBuilder builder) {
        for (Character c : value.toCharArray()) {
            if (Character.isDigit(c) || (c == '.' || c == ',')) {
                boolean not = !builder.toString().contains(".");
                if ((c == ',' && not)) {
                    builder.append(".");
                } else {
                    builder.append(c);
                }
            }
        }
    }

    private static int removeZero(String prefix) {
        int count = 1;
        StringBuilder motege = new StringBuilder();
        for (char c : prefix.toCharArray()) {
            if (prefix.startsWith("0")) {
                motege.append(c);
                String str = motege.toString();
                int len = str.length();
                String zero = str.substring(len-1, len);
                if (zero.equals("0")) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private static String stringValidate(String value) {
        if (isNotEmpty(value)) {
            value = value.trim();
            if (value.contains(",") && value.contains(".")) {
                return value.replace(".", "");
            }

        } else {
            return "00";
        }
        return value;
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
