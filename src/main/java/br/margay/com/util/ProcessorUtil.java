/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.util;

import br.margay.com.email.builder.BuildExclusionStrategy;
import br.margay.com.consume.adapter.StringAdapter;
import br.margay.com.enums.pix.CertificateType;
import br.margay.com.enums.pix.PSPPix;
import br.margay.com.exception.ServiceException;
import br.margay.com.model.KeyStoreAPI;
import com.google.api.client.util.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author colpv
 * Criado em 14/05/2024
 */
public class ProcessorUtil {

    private static Gson gson = null;

    private ProcessorUtil() {

    }

    public static Gson gsonInstance() {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.setExclusionStrategies(new BuildExclusionStrategy());
            builder.registerTypeAdapter(String.class, new StringAdapter());
            gson = builder.create();
            Logger.getLogger("genericJson")
                    .info("Gson init");
        }
        return gson;
    }


    public static Logger writeLogger(Class<?> t) {
        try {

            LogManager.getLogManager().readConfiguration(t.getResourceAsStream("/logging.properties"));
            return Logger.getLogger(t.getName());
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

    public static void generateQRCodeImage(String text, int width, int height, String filePath) throws ServiceException {
        try {

            if(StringUtils.isEmpty(text)){
                throw new ServiceException("text is empty");
            }
            
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            Path path = Paths.get(filePath);
            if (Strings.isNullOrEmpty(filePath) || filePath.equalsIgnoreCase(".")) {
                path = Paths.get("qrcode.png");
            }

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException | IOException e) {
            throw new ServiceException(e);
        }
    }

    public static byte[] generateQrCode(String text, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new ServiceException(e);
        }
    }


    public static Map<PSPPix, KeyStoreAPI> loadKeyStore(String certif, CertificateType certificateType, PSPPix psp) throws ServiceException {
        return loadKeyStore(certif, "", certificateType, psp);
    }

    public static Map<PSPPix, KeyStoreAPI> loadKeyStore(String certif, String senha, CertificateType certificateType, PSPPix psp) throws ServiceException {

        Map<PSPPix, KeyStoreAPI> keyStoreMap = new EnumMap<>(PSPPix.class);
        char[] password = senha.toCharArray();

        File file = new File(certif);
        try (InputStream stream = Files.newInputStream(file.toPath());  ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = stream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
            String certificate = StringUtils.encodeToString(outputStream.toByteArray());
            KeyStoreAPI pix = KeyStoreAPI.builder()
                    .password(new String(password))
                    .certificate(certificate)
                    .certificateType(certificateType)
                    .pspPix(psp)
                    .build();

            keyStoreMap.put(psp, pix);
            return keyStoreMap;

        } catch (Exception ex) {
            throw new ServiceException(ex);
        }

    }

    public static boolean expiredCertificate(Path filePath) {

        if (Files.exists(filePath)) {

            String fileName = filePath.getFileName().toString().toLowerCase();
            if (fileName.endsWith(".p12") || fileName.endsWith(".pfx")) {
                return checkPKCS12(filePath);
            } else if (fileName.endsWith(".der") || fileName.endsWith(".pem")) {
                return checkDEROuPem(filePath);
            }

        }
        return true;

    }

    private static boolean checkPKCS12(Path filePath) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
                keyStore.load(fis, null);
            }
            String alias = keyStore.aliases().nextElement();
            Certificate certificate = keyStore.getCertificate(alias);

            return isCertificateExpired(certificate);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private static boolean checkDEROuPem(Path filePath) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try (FileInputStream fis = new FileInputStream(filePath.toFile())) {
                Certificate certificate = certificateFactory.generateCertificate(fis);
                return isCertificateExpired(certificate);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private static boolean isCertificateExpired(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            Date expirationDate = x509Certificate.getNotAfter();
            Date currentDate = new Date();
            return currentDate.after(expirationDate);
        }
        return false;
    }

}
