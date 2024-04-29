package br.jlinformatica.com.acbr.interf;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Pointer;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public interface ACBrMailLib extends Library {

    String JNA_LIBRARY_NAME = LibraryLoader.getLibraryName();
    ACBrMailLib INSTANCE = Native.load(JNA_LIBRARY_NAME, ACBrMailLib.class);

    class LibraryLoader {
        private static String library = "";
        private static ACBrMailLib instance = null;

        private LibraryLoader() {
        }

        public static String getLibraryName() {
            if (Objects.equals(library, "")) {
                if (Platform.isWindows()) {
                    library = Platform.is64Bit() ? "ACBrMail64" : "ACBrMail32";
                } else {
                    library = Platform.is64Bit() ? "acbrmail64" : "acbrmail32";
                }

            }
            return String.format("%s", resources(library));
        }

        private static Path resources(String library){
            Path path = Paths.get(library);
            return path.toAbsolutePath();
        }

        public static ACBrMailLib getInstance() {
            if (instance == null) {
                instance = INSTANCE;
            }
            return instance;
        }
    }

    int MAIL_Inicializar(PointerByReference libHandle, String eArqConfig, String eChaveCrypt);

    int MAIL_Finalizar(Pointer libHandler);

    int MAIL_Nome(Pointer libHandler, ByteBuffer buffer, IntByReference bufferSize);

    int MAIL_Versao(Pointer libHandler, ByteBuffer buffer, IntByReference bufferSize);

    int MAIL_UltimoRetorno(Pointer libHandler, ByteBuffer buffer, IntByReference bufferSize);

    int MAIL_ConfigImportar(Pointer libHandler, String eArqConfig);

    int MAIL_ConfigExportar(Pointer libHandler, ByteBuffer buffer, IntByReference bufferSize);

    int MAIL_ConfigLer(Pointer libHandler, String eArqConfig);

    int MAIL_ConfigGravar(Pointer libHandler, String eArqConfig);

    int MAIL_ConfigLerValor(Pointer libHandler, String eSessao, String eChave, ByteBuffer buffer, IntByReference bufferSize);

    int MAIL_ConfigGravarValor(Pointer libHandler, String eSessao, String eChave, String valor);

    int MAIL_SetSubject(Pointer libHandler, String eSubject);

    int MAIL_AddAddress(Pointer libHandler, String eEmail, String eName);

    int MAIL_AddReplyTo(Pointer libHandler, String eEmail, String eName);

    int MAIL_AddCC(Pointer libHandler, String eEmail, String eName);

    int MAIL_AddBCC(Pointer libHandler, String eEmail);

    int MAIL_ClearAttachment(Pointer libHandler);

    int MAIL_AddAttachment(Pointer libHandler, String eFileName, String eDescription, int aDisposition);

    int MAIL_AddBody(Pointer libHandler, String eBody);

    int MAIL_AddAltBody(Pointer libHandler, String eAltBody);

    int MAIL_SaveToFile(Pointer libHandler, String eFileName);

    int MAIL_Clear(Pointer libHandler);

    int MAIL_Send(Pointer libHandler);
}
