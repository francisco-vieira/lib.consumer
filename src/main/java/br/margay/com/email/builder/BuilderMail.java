/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.email.builder;


import br.margay.com.email.enums.EConfig;
import br.margay.com.email.enums.RecipientType;
import br.margay.com.email.ipack.Account;
import br.margay.com.exception.CoreMailException;
import br.margay.com.email.ipack.IBuilderMail;
import br.margay.com.util.StringUtils;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author colpv
 * Criado em 24/04/2024
 */
public class BuilderMail implements IBuilderMail<BuilderMail> {

    private final Message message;

    private Properties props = null;

    private static final Logger LOG = Logger.getLogger(BuilderMail.class.getName());
    private boolean isPlain = false;

    private String text;

    protected BuilderMail(String username, String password, Properties configMail) throws CoreMailException {
        this.configMail(configMail);
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                LOG.log(Level.INFO, "Authenticator Message");
                return new PasswordAuthentication(username, password);
            }
        };
        load();
        Session session = Session.getInstance(props, auth);
        message = new MimeMessage(session);
        LOG.log(Level.INFO, "Initialized session");
    }

    public static BuilderMail getInstance(String username, String password, Properties configMail) throws CoreMailException {
        return new BuilderMail(username, password, configMail);
    }

    public static BuilderMail getInstance(String username, String password) throws CoreMailException {
        return new BuilderMail(username, password, new Properties());
    }

    private List<InternetAddress> filterRecipientType(List<MailType> array, RecipientType type) {
        return Arrays.stream(array.toArray(new MailType[0]))
                .filter(f -> Objects.equals(f.recipientType(), type))
                .map(mailType -> {
                    try {
                        return mailType.internetAddress();
                    } catch (AddressException | UnsupportedEncodingException e) {
                        throw new CoreMailException(e);
                    }

                }).collect(Collectors.toList());
    }

    public boolean send() throws CoreMailException {
        try {
            Transport.send(message);
        } catch (MessagingException | NullPointerException e) {
            throw new CoreMailException(e);
        }
        LOG.log(Level.INFO, "Send MailMassege");
        return true;
    }

    private void textPlain(String data) throws CoreMailException {
        if (StringUtils.isNotEmpty(data)) {
            try {
                message.setContent(data, "text/plain; charset=utf-8");
            } catch (MessagingException e) {
                throw new CoreMailException(e);
            }
        }
    }

    private void load() {
        if (Objects.isNull(props) || props.isEmpty()) {
            LOG.log(Level.INFO, "Load properties");
            int timeout = 30000;
            props = new Properties();
            props.put(EConfig.AUTH.toString(), true);
            props.put(EConfig.START_TLS_ENABLE.toString(), "true");
            props.put(EConfig.HOST.toString(), "smtp.sparkpostmail.com");
            props.put(EConfig.PORT.toString(), "587");
            props.put(EConfig.TRUST.toString(), "*");

            props.put(EConfig.CONNECTION_TIMEOUT.toString(), String.valueOf(timeout));
            props.put(EConfig.TIME_OUT.toString(), String.valueOf(timeout));
            props.put(EConfig.WRITE_TIMEOUT.toString(), String.valueOf(timeout));
        }
    }

    private interface MailType {

        InternetAddress internetAddress() throws AddressException, UnsupportedEncodingException;

        RecipientType recipientType();

    }

    private BuilderMail html(String html) throws CoreMailException {
        try {
            message.setContent(html, "text/html; charset=utf-8");
        } catch (MessagingException e) {
            throw new CoreMailException(e);
        }
        return this;
    }

    public BuilderMail configMail(Properties configMail) throws CoreMailException {
        this.props = configMail;
        return this;
    }

    public BuilderMail from(String email) throws CoreMailException {
        return from(email, null);
    }

    public BuilderMail from(String email, String username) throws CoreMailException {

        try {
            InternetAddress address = new InternetAddress(email);
            if (StringUtils.isNotEmpty(username)) {
                address = new InternetAddress(email, username);
            }
            message.setFrom(address);
        } catch (UnsupportedEncodingException | MessagingException e) {
            throw new CoreMailException(e);
        }

        return this;
    }

    public BuilderMail to(Map<Account, RecipientType> emails) throws CoreMailException {
        try {
            List<MailType> array = new ArrayList<>();
            emails.forEach((mail, recipientType) -> {
                String personal = mail.personal();
                String address = mail.address();
                if (StringUtils.isNotEmpty(personal)) {
                    array.add(new MailType() {
                        @Override
                        public InternetAddress internetAddress() throws UnsupportedEncodingException {
                            return new InternetAddress(address, personal);
                        }

                        @Override
                        public RecipientType recipientType() {
                            return recipientType;
                        }
                    });

                } else {
                    array.add(new MailType() {
                        @Override
                        public InternetAddress internetAddress() throws AddressException {
                            return new InternetAddress(address);
                        }

                        @Override
                        public RecipientType recipientType() {
                            return recipientType;
                        }
                    });
                }
            });

            List<InternetAddress> to = filterRecipientType(array, RecipientType.TO);
            if (!to.isEmpty()) {
                message.setRecipients(Message.RecipientType.TO, to.toArray(new InternetAddress[0]));
            }

            List<InternetAddress> cc = filterRecipientType(array, RecipientType.CC);
            if (!cc.isEmpty()) {
                message.setRecipients(Message.RecipientType.CC, cc.toArray(new InternetAddress[0]));
            }

            List<InternetAddress> bcc = filterRecipientType(array, RecipientType.BCC);
            if (!bcc.isEmpty()) {
                message.setRecipients(Message.RecipientType.BCC, bcc.toArray(new InternetAddress[0]));
            }

        } catch (MessagingException e) {
            throw new CoreMailException(e);
        }
        return this;
    }

    public BuilderMail subject(String subject) throws CoreMailException {
        try {
            message.setSubject(subject);
        } catch (MessagingException e) {
            throw new CoreMailException(e);
        }
        return this;
    }

    public BuilderMail body(String body) throws CoreMailException {
        return html(body);
    }

    public BuilderMail attachment(String[] filename) throws CoreMailException {
        return attachment(filename, null);
    }

    public BuilderMail attachment(String[] arrayList, String content) throws CoreMailException {
        try {
            Multipart multipart = new MimeMultipart();

            if (StringUtils.isNotEmpty(content)) {
                MimeBodyPart contentText = new MimeBodyPart();
                if (isPlain) {
                    contentText.setContent(content, "text/plain; charset=utf-8");
                } else {
                    contentText.setContent(content, "text/html; charset=utf-8");
                }
                multipart.addBodyPart(contentText);
            }

            for (String attachment : arrayList) {
                MimeBodyPart contetFile = new MimeBodyPart();
                contetFile.attachFile(attachment);
                if (StringUtils.isNotEmpty(this.text)) {
                    contetFile.setText(this.text, "UTF-8");
                }
                multipart.addBodyPart(contetFile);
            }

            message.setContent(multipart);

        } catch (IOException | MessagingException e) {
            throw new CoreMailException(e);
        }
        return this;
    }

    public BuilderMail text(String text) throws CoreMailException {
        if (isPlain)
            textPlain(text);
        else {
            try {
                message.setText(text);
            } catch (MessagingException e) {
                throw new CoreMailException(e);
            }
        }
        return this;
    }

    public BuilderMail date(Long milliseconds) throws CoreMailException {
        try {
            DateFormat dateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.DEFAULT);
            Date date = new Date(milliseconds);
            String formattedDate = dateFormat.format(date);
            Date parsedDate = dateFormat.parse(formattedDate);
            message.setSentDate(parsedDate);
        } catch (CoreMailException | ParseException | MessagingException e) {
            throw new CoreMailException(e);
        }
        return this;
    }

    public BuilderMail plain(boolean plain) {
        isPlain = plain;
        return this;
    }

    public BuilderMail textIntoFile(String textInto) {
        this.text = textInto;
        return this;
    }

}
