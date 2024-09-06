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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.Set;
import java.util.UUID;

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
     * Method verifica se o valor eh null ou vazio
     *
     * @param value valor a verificada
     * @return string verificada
     */
    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Method verifica se o valor eh null
     *
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

        String catalinaHome = System.getenv("CATALINA_HOME");
        if (catalinaHome == null) {
            throw new IllegalArgumentException("CATALINA_HOME");
        }

        String dirpath = catalinaHome.concat("/conf/certificates");
        Path dir = Paths.get(dirpath);

        String certificate = "certificate".concat(type.getExtension());

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
            if (Files.getFileStore(dir).supportsFileAttributeView("posix")) {
                Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
                Files.setPosixFilePermissions(dir, perms);
            }
        }

        Path filePath = dir.resolve(certificate);

        Files.newOutputStream(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        ).close();

        if (Files.getFileStore(filePath).supportsFileAttributeView("posix")) {
            Set<PosixFilePermission> filePerms = PosixFilePermissions.fromString("rw-------");
            Files.setPosixFilePermissions(filePath, filePerms);
        }

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(bytes);
        }
        return new FileInputStream(filePath.toFile());
    }

    public static String stringToValuePix(String value) {

        StringBuilder builder = new StringBuilder();
        value = stringValidate(value);
        pointFilter(value, builder);
        value = builder.toString();

        String[] split = value.split("\\.");

        int length = 1;
        String prefix = split[length - 1];
        prefix = prefix.length() == length ? "0".concat(prefix) : prefix;

        int count = removeZero(prefix);

        if (count > 2) {
            prefix = prefix.substring(count - length);
        }

        if (prefix.isEmpty()) {
            prefix = "00";
        }

        String sufix;
        if (split.length > length) {
            sufix = split[length];
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
                String zero = str.substring(len - 1, len);
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
            value = value.
                    replace(" ", "").
                    trim();

            if (value.contains(",") && value.contains(".")) {
                value = value.replace(".", "");
            }

            if(value.contains(",")){
                StringBuilder result = new StringBuilder();
                for (char str : value.toCharArray()){
                    if(str == ',' && result.toString().contains(",")){
                        continue;
                    }
                    result.append(str);
                }
                value = result.toString();
            }

        } else {
            return "00";
        }
        return value;
    }

    /**
     *
     * @param str String que recebe filtro
     * @param filter valor passado como filtro (alp, ALPHABETIC ou 1 para letras, dig, DIGIT ou 2 para números)
     *               <br/>Valor null vai considerar letras.
     * @return String com valor filtrado.
     */
    public static String stringFilter(String str, Object filter) {

        if (str == null || str.isEmpty()) {
            return str;
        }

        Filter finder;

        if (filter == null) {
            finder = Filter.ALPHABETIC;
        }else{
            finder = Filter.finder(filter);
        }

        StringBuilder builder = new StringBuilder();
        for (Character c : str.toCharArray()) {

            if (finder == Filter.DIGIT && Character.isDigit(c)) {
                builder.append(c);
            }

            if (finder == Filter.ALPHABETIC && Character.isAlphabetic(c)) {
                builder.append(c);
            }


        }

        return builder.toString();
    }

   public enum Filter {

        ALPHABETIC(1,"alp"),
        DIGIT(2, "dig");

        private final int vInt;
        private final String vString;

        Filter(int vInt, String vString) {
            this.vInt = vInt;
            this.vString = vString;
        }

        public int getvInt() {
            return vInt;
        }

        public String getvString() {
            return vString;
        }

       public static Filter finder(Object object) {
           String r = String.valueOf(object);
           for (Filter filter : values()) {

               if (object instanceof Filter) {
                   Filter conv = Converters.objectToClass(object, Filter.class);
                   if (filter == conv) {
                       return filter;
                   }
               }

               if (object instanceof String &&
                       (r.equalsIgnoreCase(filter.getvString()) ||
                               r.equalsIgnoreCase(filter.name()))) {
                   return filter;
               }

               if (object instanceof Integer) {
                   int vInt = Integer.parseInt(r);
                   if (vInt == filter.getvInt()) {
                       return filter;
                   }
               }

           }
           throw new IllegalArgumentException("Filter not found");
       }
    }

    public static String keyGen(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String identificator(){
        return keyGen().replace("-", "");
    }

}
