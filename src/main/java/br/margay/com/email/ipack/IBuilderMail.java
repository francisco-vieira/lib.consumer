/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.email.ipack;

import br.margay.com.email.enums.RecipientType;
import br.margay.com.exception.CoreMailException;


import java.util.Map;
import java.util.Properties;

/**
 * @author colpv
 * Criado em 03/05/2024
 */
public interface IBuilderMail<T> {

    T configMail(Properties configMail) throws CoreMailException;

    boolean send() throws CoreMailException;

    T from(String email) throws CoreMailException;

    T from(String email, String username) throws CoreMailException;

    /**
     * @param emails map que recebe os valores de email e username
     * @throws CoreMailException lança exception se tiver algum erro
     */
    T to(Map<Account, RecipientType> emails) throws CoreMailException;

    T subject(String subject) throws CoreMailException;

    T body(String body) throws CoreMailException;

    T attachment(String[] filename) throws CoreMailException;

    T attachment(String[] filename, String content) throws CoreMailException;

    T text(String text) throws CoreMailException;

    T date(Long datetime) throws CoreMailException;

    /**
     * @param plain valor que define se o objeto será um texto puro <br>
     *              Esse method precisa ser chamado primeido do que os demais
     * @return retorna a class que envocou
     */
    T plain(boolean plain);

    /**
     * Esse method faz com que todos os valores passados sejam texto pleno.
     *
     * @param textInto valor que define texto que vai ser escrito no arquivo anexo<br>
     *                 Esse method precisa ser chamado primeido do que <strong>attachment</strong>
     * @return retorna a class invocada
     */
    T textIntoFile(String textInto);
}
