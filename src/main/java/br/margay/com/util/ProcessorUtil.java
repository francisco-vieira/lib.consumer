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
import br.margay.com.model.KeyStorePix;
import com.google.api.client.util.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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


    public static Map<PSPPix, KeyStorePix> loadKeyStore(String certif, CertificateType certificateType, PSPPix psp) throws ServiceException {
        return loadKeyStore(certif, "", certificateType, psp);
    }

    public static Map<PSPPix, KeyStorePix> loadKeyStore(String certif, String senha, CertificateType certificateType, PSPPix psp) throws ServiceException {

        Map<PSPPix, KeyStorePix> keyStoreMap = new HashMap<>();
        char[] password = senha.toCharArray();

        File file = new File(certif);
        try (InputStream stream = Files.newInputStream(file.toPath());  ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = stream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }
            String certificate = StringUtils.encodeToString(outputStream.toByteArray());
            KeyStorePix pix = KeyStorePix.builder()
                    .password(new String(password))
                    .certificate(certificate)
                    .certificateType(certificateType)
                    .build();

            keyStoreMap.put(psp, pix);
            return keyStoreMap;

        } catch (IOException ex) {
            throw new ServiceException(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
