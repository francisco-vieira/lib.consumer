package br.margay.com.consume.auth;

import br.margay.com.exception.ServiceException;
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

    public AuthorizationToken(String token) {

        if (!Strings.isNullOrEmpty(token) && tokenType == AuthorizationType.TOKEN_BEARER) {
            Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
            if (!token.contains(tokenType.toString())) {
                token = tokenType.toString().concat(token);
            }
            prefs.put(keyPref(), token);
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
        new AuthorizationToken(AuthorizationToken.token);
    }

    /**
     * Method que captura token do basic para gerar token de acesso
     *
     * @param username nome do usuario
     * @param password senha do usuario
     */
    public static void authorization(String username, String password) {
        if (!isTokenValid()) {
            try {

                if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
                    throw new ServiceException("Username or password should not be null or empty");
                }

                String auth = username + ":" + password;
                AuthorizationToken.token = DatatypeConverter.printBase64Binary(auth.getBytes(StandardCharsets.UTF_8));
                tokenType = AuthorizationType.TOKEN_BASIC;
                new AuthorizationToken(AuthorizationToken.token);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }

        }
    }

    public void reset() {
        Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
        prefs.remove(keyPref());
    }

    public static String getTokenAuthorization() {

        if (tokenType == AuthorizationType.TOKEN_BASIC) {
            return tokenType.toString().concat(token);
        }

        if (token == null || System.currentTimeMillis() > tokenExpirationTime) {
            return null;
        }

        Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
        return prefs.get(keyPref(), null);
    }

    private static String keyPref() {
        return AuthorizationType.TOKEN_BEARER.name();
    }

    public static synchronized boolean isTokenValid() {
        return (token != null && System.currentTimeMillis() <= tokenExpirationTime)
                || tokenType == AuthorizationType.TOKEN_BASIC;
    }

    public static synchronized boolean isTokenValidBearer() {
        return (token != null && System.currentTimeMillis() <= tokenExpirationTime);
    }

}
