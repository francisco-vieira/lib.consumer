/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.util;

import br.margay.com.builder.BuildExclusionStrategy;
import br.margay.com.consume.adapter.StringAdapter;
import br.margay.com.exception.ServiceException;
import com.google.api.client.util.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new ServiceException(e);
        }
    }


}
