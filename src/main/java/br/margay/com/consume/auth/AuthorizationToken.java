package br.margay.com.consume.auth;

import br.margay.com.exception.ServiceException;
import br.margay.com.util.StringUtils;
import com.google.common.base.Strings;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 * @author francisco
 */
public class AuthorizationToken {

    private static final Logger logger = Logger.getLogger(AuthorizationToken.class.getName());

    private static String token;
    private static AuthorizationType tokenType;
    private static long expirationTime;
    private static long tokenExpirationTime;

    public AuthorizationToken(String token) {

        configurePreferencesDirectory();

        if (!Strings.isNullOrEmpty(token) && tokenType != AuthorizationType.TOKEN_BASIC) {
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
        if (expirationTime == 0) {
            expirationTime = 60L * 60L * 1000L;
        }
        AuthorizationToken.tokenExpirationTime = System.currentTimeMillis() + expirationTime;
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

        if (token == null || System.currentTimeMillis() >= tokenExpirationTime) {
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

    public static void expirationTime(long expirationTime) {
        AuthorizationToken.expirationTime = expirationTime * 1000L;
    }

    public static void configurePreferencesDirectory() {
        try {

            String customPrefsDir = System.getProperty("user.home");

            String userPrefsDir = System.getProperty("java.util.prefs.userRoot");
            Path defaultPrefsPath = StringUtils.isEmpty(userPrefsDir) ? null : Paths.get(userPrefsDir);

            if (defaultPrefsPath == null || !Files.exists(defaultPrefsPath) || !Files.isWritable(defaultPrefsPath)) {
                logger.warning("O diretório padrão de preferências não está acessível.");
                logger.warning("Usando diretório personalizado.");

                System.setProperty("java.util.prefs.userRoot", customPrefsDir);
                System.setProperty("java.util.prefs.systemRoot", customPrefsDir);

                Path customDirPath = Paths.get(customPrefsDir);
                if (!Files.exists(customDirPath)) {

                    if (Files.getFileStore(customDirPath).supportsFileAttributeView(PosixFileAttributeView.class)) {
                        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxr-x---");
                        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
                        Files.createDirectories(customDirPath, attr);
                        logger.info("Diretório de preferências personalizado criado com permissões POSIX: rwxr-x---");
                    } else {
                        Files.createDirectories(customDirPath);
                        logger.info("Diretório de preferências personalizado criado sem permissões POSIX.");
                    }
                    logger.info("Diretório de preferências personalizado criado com sucesso.");
                } else {
                    logger.info("Diretório de preferências: OK");
                }
            } else {
                logger.info("Diretório padrão de preferências está acessível.");
            }
        } catch (IOException e) {
            logger.severe("Erro ao configurar o diretório de preferências: " + e.getMessage());
        }
    }

}
