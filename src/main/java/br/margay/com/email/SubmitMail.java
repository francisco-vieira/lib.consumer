/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.email;

import br.margay.com.enums.email.RecipientType;
import br.margay.com.email.ipack.Account;
import br.margay.com.exception.CoreMailException;
import br.margay.com.email.impl.ProcessMail;
import br.margay.com.email.ipack.IMail;


import java.util.Map;
import java.util.Properties;

/**
 * @author colpv
 * Criado em 03/05/2024
 */
public interface SubmitMail {

    static boolean sendMail(IMail mail) throws CoreMailException {
        return ProcessMail.sendMail(mail);
    }

    static boolean sendMail(IMail mail, Properties config) throws CoreMailException {
        return ProcessMail.sendMail(mail, config);
    }

    static ProcessMail newInstance( String username, String password) throws CoreMailException {
        return ProcessMail.newInstance(username, password);
    }

    void setConfig(Properties config);
    void setFrom(String address, String personal);
    void setFrom(String address);
    void setDate(Long date);
    void setPlain(Boolean param);
    void setParameters(Map<String, String> parameters);
    void setBody(String templateOrText, Map<String, String> parameters);
    void setSubject(String subject);
    void setTo(Map<Account, RecipientType> emails);
    void SetTextIntoFile(String textInto);
    void setAnexos(String[] anexos, String content);
    void setAnexos(String[] anexos);
    void send();

}
