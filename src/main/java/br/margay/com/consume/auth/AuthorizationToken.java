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
    private static final String TOKEN_KEY = "auth_token";
    private static final long TOKEN_EXPIRATION_TIME = 30 * 60 * 1000;
    private static long tokenExpirationTime;

    public AuthorizationToken(String token) {
        if (!Strings.isNullOrEmpty(token)) {
            Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
            tokenExpirationTime = System.currentTimeMillis() + TOKEN_EXPIRATION_TIME;
            AuthorizationToken.token = token;
            prefs.put(TOKEN_KEY, AuthorizationToken.token);
        }
    }

    public static void authorization(String token) {
        if (!isTokenValid()) {
           String type = AuthorizationType.TOKEN_BEARER.toString();
            new AuthorizationToken(type.concat(token));
        }
    }

    public static void authorization(String username, String password) {
        if (!isTokenValid()) {
            try {

                if(Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
                    throw new ServiceException("Username or password should not be null or empty");
                }

                String auth = username + ":" + password;
                token = DatatypeConverter.printBase64Binary(auth.getBytes(StandardCharsets.UTF_8));
                String type = AuthorizationType.TOKEN_BASIC.toString();
                new AuthorizationToken(type.concat(token));
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }

        }
    }


    public void reset() {
        Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
        prefs.remove(TOKEN_KEY);
    }

    public static String getTokenAuthorization() {
        if (token == null || System.currentTimeMillis() > tokenExpirationTime) {
            return null;
        }
        Preferences prefs = Preferences.userNodeForPackage(AuthorizationToken.class);
        return prefs.get(TOKEN_KEY, null);
    }

    public static synchronized boolean isTokenValid() {
        return token != null && System.currentTimeMillis() <= tokenExpirationTime;
    }

}
