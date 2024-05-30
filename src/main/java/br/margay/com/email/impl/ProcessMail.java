/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.email.impl;

import br.margay.com.email.builder.BuilderMail;
import br.margay.com.email.SubmitMail;
import br.margay.com.email.enums.RecipientType;
import br.margay.com.email.ipack.Account;
import br.margay.com.exception.CoreMailException;
import br.margay.com.email.ipack.IMail;
import br.margay.com.util.StringUtils;
import com.google.common.base.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author colpv
 * Criado em 03/05/2024
 */
public final class ProcessMail implements SubmitMail {

    private static final Logger logger = Logger.getLogger(ProcessMail.class.getName());

    private final BuilderMail builder;

    private ProcessMail( String username,  String password) {
        builder = BuilderMail.getInstance(username, password);
    }

    public static boolean sendMail(IMail mail, Properties config) throws CoreMailException {

        String templateOrText = mail.getTemplateOrText();
        String html = !Strings.isNullOrEmpty(templateOrText) ? templateOrText : loadHtmlFile();
        String body = replaceParameters(html, mail.getParamenters());

        return BuilderMail.getInstance(mail.getUsername(), mail.getPassword(), config)
                .from(mail.getFrom(), mail.getUserFrom())
                .date(System.currentTimeMillis())
                .plain(mail.isPlain())
                .body(body)
                .subject(mail.getSubject())
                .to(mail.getTo())
                .textIntoFile(mail.getTextIntoFile())
                .attachment(mail.getAnexos(), body)
                .send();
    }

    public static boolean sendMail(IMail mail) throws CoreMailException {
        return sendMail(mail, new Properties());
    }

    public static ProcessMail newInstance(String username, String password) {
        return new ProcessMail(username, password);
    }

    public void setConfig(Properties config) {
        builder.configMail(config);
    }

    public void setFrom(String address, String personal) {
        builder.from(address, personal);
    }

    public void setFrom(String address) {
        builder.from(address);
    }

    public void setDate(Long date) {
        builder.date(date);
    }

    public void setPlain(Boolean param) {
        builder.plain(param);
    }

    public void setParameters(Map<String, String> parameters) {
        this.setBody("", parameters);
    }

    public void setBody(String templateOrText, Map<String, String> parameters) {
        String html = StringUtils.isNotEmpty(templateOrText) ? templateOrText : loadHtmlFile();
        String body = replaceParameters(html, parameters);
        builder.body(body);
    }

    public void setSubject(String subject) {
        builder.subject(subject);
    }

    public void setTo(Map<Account, RecipientType> emails) {
        builder.to(emails);
    }

    public void SetTextIntoFile(String textInto) {
        builder.textIntoFile(textInto);
    }

    public void setAnexos(String[] anexos, String content) {
        builder.attachment(anexos, content);
    }

    public void setAnexos(String[] anexos) {
        builder.attachment(anexos);
    }

    public void send() {
        builder.send();
    }

    private static String replaceParameters(String htmlContent, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            htmlContent = htmlContent.replace("$".concat(entry.getKey()), entry.getValue());
        }
        return htmlContent;
    }

    private static String loadHtmlFile() {
        try {
            return new String(Files.readAllBytes(Paths.get("src", "main", "resources", "templates/email.html")));
        } catch (IOException e) {
            logger.log(Level.INFO, e.getMessage());
            return "";
        }
    }

}
