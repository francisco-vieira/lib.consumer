/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.util;

/**
 * @author francisco.vieira
 * Criado em 29/05/2024
 */
public class Converters {

    public static <T> T   objectToClass(Object object, Class<T> clazz) {

        if (!clazz.isInstance(object)) {
           throw  new UnsupportedOperationException("Object is not an instance of " + clazz.getName());
        }
        return clazz.cast(object);
    }


    public static String charsToString(char[] chars){
        if (chars == null || chars.length == 0){
            return "";
        }
        return new String(chars);
    }

    public static char[] stringToChars(String string){
        if (string == null || string.isEmpty()){
            return new char[0];
        }
        return string.toCharArray();
    }

}
