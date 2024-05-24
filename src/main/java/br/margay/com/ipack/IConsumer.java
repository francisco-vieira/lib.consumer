/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 991663577
 */
package br.margay.com.ipack;

import br.margay.com.exception.ServiceException;

import org.apache.http.entity.ContentType;

import java.util.Map;

/**
 * @author colpv
 * Criado em 10/05/2024
 */
public interface IConsumer<T> {

    //   GET
    String get(String point) throws ServiceException;

    String get(Map<String, String> params) throws ServiceException;

    String get(String point, Map<String, String> params) throws ServiceException;

    //   POST
    String post(String point, String json) throws ServiceException;

    String post(String point, String json, Map<String, String> params) throws ServiceException;

    String post(String point, String json, Map<String, String> params, ContentType type) throws ServiceException;

    //   DELETE
    Boolean delete(String point) throws ServiceException;

    Boolean delete(String point, Map<String, String> params) throws ServiceException;

    void filePost(String point, ContentType type, String file) throws ServiceException;

    void filePost(String point, ContentType type, String file, Map<String, String> params) throws ServiceException;

    T header(String key, String value);

    byte[] gerarQrCode(String text, int width, int heigth) throws ServiceException;

   void gerarQrCodeImage(String text, int width, int height, String filePath) throws ServiceException;
   void gerarQrCodeImage(String text, int width, int height) throws ServiceException;

}
