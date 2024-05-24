/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.builder;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @author francisco
 * Criado em 26/02/2024
 */
public class BuildExclusionStrategy  implements ExclusionStrategy {

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaredClass().getSimpleName().endsWith("Builder");
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getSimpleName().endsWith("Builder");
    }
}