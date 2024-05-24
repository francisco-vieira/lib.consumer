package br.margay.com.email.ipack;


import br.margay.com.email.enums.RecipientType;

import java.io.Serializable;
import java.util.Map;

public interface IMail extends Serializable {

    Long getId();

    boolean isPlain();

    Map<Account, RecipientType> getTo();

    String getFrom();

    String getUserFrom();

    String getTemplateOrText();

    String getTextIntoFile();

    Class<?> getClazz();

    String getSubject();

    String[] getAnexos();

    String getUsername();

    String getPassword();

    Long getDate();

    Map<String, String> getParamenters();
}
