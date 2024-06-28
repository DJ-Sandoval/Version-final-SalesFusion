
package seguridad;

import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author Jose
 */
public class ClavesSecurity {
    private static final SecureRandom random = new SecureRandom();
    
    public static String generateVerificationKey() {
        byte[] bytes = new byte[16]; // 16 bytes = 128 bits
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().encodeToString(bytes);
    }
    
    public static boolean verifyVerificationKey(String providedKey, String expectedKey) {
        return providedKey.equals(expectedKey);
    }
    
}
