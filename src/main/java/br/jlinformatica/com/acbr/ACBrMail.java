package br.jlinformatica.com.acbr;

import br.jlinformatica.com.acbr.enums.ACBrSessao;
import br.jlinformatica.com.acbr.enums.MailAttachmentDisposition;
import br.jlinformatica.com.acbr.interf.ACBrMailLib;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import java.awt.*;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class ACBrMail extends ACBrLibBase {

    private static ACBrMail instance;

    private ACBrMail() throws Exception {
        Path path = Paths.get("ACBrLib.ini");
        if (!Files.exists(path)) {
            path =  path.resolve(path);
            Files.createFile(path);
        }

        PointerByReference handle = new PointerByReference();
        int ret = ACBrMailLib.INSTANCE.MAIL_Inicializar(handle, toUTF8(path.toFile().getAbsolutePath()), toUTF8(""));
        checkResult(ret);
        setHandle(handle.getValue());
    }

    public static ACBrMail getInstance() throws Exception {
        if (Objects.isNull(instance)) {
            instance = new ACBrMail();
        }
        return instance;
    }

    public static ACBrMail getInstance(String eArqConfig, String eChaveCrypt) throws Exception {
        if (Objects.isNull(instance)) {
            instance = new ACBrMail(eArqConfig, eChaveCrypt);
        }
        return instance;
    }


    private ACBrMail(String eArqConfig, String eChaveCrypt) throws Exception {
        PointerByReference handle = new PointerByReference();
        int ret = ACBrMailLib.INSTANCE.MAIL_Inicializar(handle, toUTF8(eArqConfig), toUTF8(eChaveCrypt));
        checkResult(ret);
        setHandle(handle.getValue());
    }

    @Override
    protected void dispose() throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_Finalizar(getHandle());
        checkResult(ret);
    }

    public String nome() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(STR_BUFFER_LEN);
        IntByReference bufferLen = new IntByReference(STR_BUFFER_LEN);

        int ret = ACBrMailLib.INSTANCE.MAIL_Nome(getHandle(), buffer, bufferLen);
        checkResult(ret);

        return fromUTF8(buffer, bufferLen.getValue());
    }

    public String versao() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(STR_BUFFER_LEN);
        IntByReference bufferLen = new IntByReference(STR_BUFFER_LEN);

        int ret = ACBrMailLib.INSTANCE.MAIL_Versao(getHandle(), buffer, bufferLen);
        checkResult(ret);

        return fromUTF8(buffer, bufferLen.getValue());
    }

    public void configLer() throws Exception {
        configLer("");
    }

    public void configLer(String eArqConfig) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_ConfigLer(getHandle(), toUTF8(eArqConfig));
        checkResult(ret);
    }

    public void configGravar() throws Exception {
        configGravar("");
    }

    public void configGravar(String eArqConfig) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_ConfigGravar(getHandle(), toUTF8(eArqConfig));
        checkResult(ret);
    }

    @Override
    public String configLerValor(ACBrSessao eSessao, String eChave) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(STR_BUFFER_LEN);
        IntByReference bufferLen = new IntByReference(STR_BUFFER_LEN);

        int ret = ACBrMailLib.INSTANCE.MAIL_ConfigLerValor(getHandle(), toUTF8(eSessao.name()), toUTF8(eChave), buffer, bufferLen);
        checkResult(ret);

        return processResult(buffer, bufferLen);
    }

    @Override
    public void configGravarValor(ACBrSessao eSessao, String eChave, Object value) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_ConfigGravarValor(getHandle(), toUTF8(eSessao.name()), toUTF8(eChave), toUTF8(value.toString()));
        checkResult(ret);
    }

    public void setSubject(String eMail) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_SetSubject(getHandle(), toUTF8(eMail));
        checkResult(ret);
    }

    public void addAddress(String eMail, String eName) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_AddAddress(getHandle(), toUTF8(eMail), toUTF8(eName));
        checkResult(ret);
    }

    public void addReplyTo(String eMail, String eName) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_AddReplyTo(getHandle(), toUTF8(eMail), toUTF8(eName));
        checkResult(ret);
    }

    public void addCC(String eMail, String eName) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_AddCC(getHandle(), toUTF8(eMail), toUTF8(eName));
        checkResult(ret);
    }

    public void addBCC(String eMail) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_AddBCC(getHandle(), toUTF8(eMail));
        checkResult(ret);
    }

    public void clearAttachment() throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_ClearAttachment(getHandle());
        checkResult(ret);
    }

    public void addAttachment(String eFileName, String eDescription, MailAttachmentDisposition aDisposition) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_AddAttachment(getHandle(), toUTF8(eFileName),
                toUTF8(eDescription), aDisposition.asInt());
        checkResult(ret);
    }

    public void addBody(String eBody) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_AddBody(getHandle(), toUTF8(eBody));
        checkResult(ret);
    }

    public void addAltBody(String eAltBody) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_AddAltBody(getHandle(), toUTF8(eAltBody));
        checkResult(ret);
    }

    public void saveToFile(String eFileName) throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_SaveToFile(getHandle(), toUTF8(eFileName));
        checkResult(ret);
    }

    public void clear() throws Exception {
        int ret = ACBrMailLib.INSTANCE.MAIL_Clear(getHandle());
        checkResult(ret);
    }

    public void send() throws Exception {
        Pointer g = getHandle();
        int ret = ACBrMailLib.INSTANCE.MAIL_Send(g);
        checkResult(ret);
    }

    public void ConfigImportar(String eArqConfig) throws Exception {

        int ret = ACBrMailLib.INSTANCE.MAIL_ConfigImportar(getHandle(), eArqConfig);
        checkResult(ret);

    }

    public String ConfigExportar() throws Exception {

        ByteBuffer buffer = ByteBuffer.allocate(STR_BUFFER_LEN);
        IntByReference bufferLen = new IntByReference(STR_BUFFER_LEN);

        int ret = ACBrMailLib.INSTANCE.MAIL_ConfigExportar(getHandle(), buffer, bufferLen);
        checkResult(ret);

        return fromUTF8(buffer, bufferLen.getValue());

    }

    @Override
    protected void ultimoRetorno(ByteBuffer buffer, IntByReference bufferLen) {
        ACBrMailLib.INSTANCE.MAIL_UltimoRetorno(getHandle(), buffer, bufferLen);
    }
}
