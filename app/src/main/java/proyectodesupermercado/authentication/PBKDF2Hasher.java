package proyectodesupermercado.authentication;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PBKDF2Hasher implements Hasher {
    private static final long serialVersionUID = 1L;
    static private final int iterationCount = 65536;
    static private final int keyLenght = 128;

    @Override
    public byte[] hash(char[] charArray, byte[] salt) {
        KeySpec spec = new PBEKeySpec(charArray, salt, iterationCount, keyLenght);
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash;
        try {
            hash = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        return hash;
    }
}
