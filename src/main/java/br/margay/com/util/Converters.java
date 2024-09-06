/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.util;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author francisco.vieira
 * Criado em 29/05/2024
 */
public class Converters {

    private Converters() {
    }

    public static LocalDateTime toLocalDateTime(String dateString) {

        if (StringUtils.isEmpty(dateString)) {
            return null;
        }
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
            return zonedDateTime.toLocalDateTime();
        } catch (DateTimeParseException e) {
            Logger.getLogger(Converters.class.getName())
                    .log(Level.WARNING, e.getMessage(), e);
            return null;
        }

    }

    public static <T> T objectToClass(Object object, Class<T> clazz) {

        if (!clazz.isInstance(object)) {
            throw new UnsupportedOperationException("Object is not an instance of " + clazz.getName());
        }
        return clazz.cast(object);
    }


    public static String charsToString(char[] chars) {
        if (chars == null || chars.length == 0) {
            return "";
        }
        return new String(chars);
    }

    public static char[] stringToChars(String string) {
        if (string == null || string.isEmpty()) {
            return new char[0];
        }
        return string.toCharArray();
    }

}
