package br.margay.com.consume.auth;

import br.margay.com.exception.ServiceException;
import br.margay.com.util.StringUtils;
import com.google.common.base.Strings;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.util.prefs.Preferences;

/**
 * @author francisco
 */
public class AuthorizationToken {

    private static String token;
    private static AuthorizationType tokenType;
    private static final long TOKEN_EXPIRATION_TIME = 30L * 60L * 1000L;
    private static long tokenExpirationTime;
    private static String prefixo;
    private static String sufixo;

    public AuthorizationToken(String prefixo, String sufixo, String token) {
        if (StringUtils.isEmpty(prefixo) && StringUtils.isEmpty(sufixo)) {
            throw new ServiceException("Prefixo e sufixo são obrigatórios");
        }

        if (!Strings.isNullOrEmpty(token) && tokenType == AuthorizationType.TOKEN_BEARER) {
            Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
            prefs.put(keyPref(prefixo, sufixo), getToken());
        }

    }

    /**
     * Method armazenar token
     *
     * @param token basic or bearer conforme necessario
     */
    public static void authorization(String token) {

        AuthorizationToken.tokenExpirationTime = System.currentTimeMillis() + TOKEN_EXPIRATION_TIME;
        AuthorizationToken.token = token;
        tokenType = AuthorizationType.TOKEN_BEARER;
        new AuthorizationToken(AuthorizationToken.prefixo, AuthorizationToken.sufixo, AuthorizationToken.token);
    }

    /**
     * Method que captura token do basic para gerar token de acesso
     *
     * @param username nome do usuario
     * @param password senha do usuario
     * @param prefixo  valor inicial para gerar chave
     * @param sufixo   valor final para gerar chave
     */
    public static void authorization(String username, String password, String prefixo, String sufixo) {
        if (!isTokenValid()) {
            try {

                if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
                    throw new ServiceException("Username or password should not be null or empty");
                }

                String auth = username + ":" + password;
                AuthorizationToken.token = DatatypeConverter.printBase64Binary(auth.getBytes(StandardCharsets.UTF_8));
                tokenType = AuthorizationType.TOKEN_BASIC;
                AuthorizationToken.prefixo = prefixo;
                AuthorizationToken.sufixo = sufixo;
                new AuthorizationToken(AuthorizationToken.prefixo, AuthorizationToken.sufixo, AuthorizationToken.token);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }

        }
    }

    public void reset(String prefixo, String sufixo) {
        Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
        prefs.remove(keyPref(prefixo, sufixo));
    }

    public static String getTokenAuthorization() {

        if (tokenType == AuthorizationType.TOKEN_BASIC) {
            return getToken();
        }

        if (token == null || System.currentTimeMillis() > tokenExpirationTime) {
            return null;
        }

        Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
        return prefs.get(keyPref(prefixo, sufixo), null);
    }

    private static String keyPref(String prefixo, String sufixo) {
        return String.format("%s.%s", prefixo, sufixo);
    }

    public static synchronized boolean isTokenValid() {
        return (token != null && System.currentTimeMillis() <= tokenExpirationTime)
                || tokenType == AuthorizationType.TOKEN_BASIC;
    }

    public static String getToken() {
        return tokenType.toString().concat(token);
    }

}
