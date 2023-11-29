package proyectodesupermercado.controller.authentication;

import java.util.Arrays;

public class HashPassword implements Password {
    private static final long serialVersionUID = 1L;
    private final byte[] salt;
    private final byte[] password;
    private final Hasher hasher;

    public HashPassword(byte[] password, byte[] salt, Hasher hasher) {
        this.password = password;
        this.salt = salt;
        this.hasher = hasher;
    }

    @Override
    public boolean checkPassword(char[] password) {
        return Arrays.equals(hasher.hash(password, salt), this.password);
    }
}
