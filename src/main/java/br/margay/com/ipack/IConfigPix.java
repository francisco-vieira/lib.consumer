/**
 * Margay Sistemas
 * https://www.margay.com.br
 * emails: suporte@margay.com.br, contato@margay.com.br
 * celular: (93) 99123-4885
 */
package br.margay.com.ipack;

/**
 * @author colpv
 * Criado em 27/05/2024
 */
public interface IConfigPix  extends java.io.Serializable{
    String getScopes();
    String getChavePix();
    String getClienteId();
    String getClienteSecret();
    String getCertificado();
    String getChavePrivada();
    String getSenha();
    String getCnpj();
    String getToken();
    String getDeveloperApplicationKey();
    String getAccountID();


}
