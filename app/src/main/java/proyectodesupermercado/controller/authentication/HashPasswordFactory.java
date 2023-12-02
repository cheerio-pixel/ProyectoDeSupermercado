package proyectodesupermercado.controller.authentication;

import proyectodesupermercado.App;

public abstract class HashPasswordFactory implements PasswordFactory {

    @Override
    public Password createPassword(char[] password) {
        byte[] salt = new byte[128];
        App.secureRandom.get().nextBytes(salt);
        return new HashPassword(getHasher().hash(password, salt), salt, getHasher());
    }

    protected abstract Hasher getHasher();

    public static class PBKDF2HashPasswordFactory extends HashPasswordFactory {
        private static final Hasher hasher = new PBKDF2Hasher();
        @Override
        protected Hasher getHasher() {
            return hasher;
        }
    }
}
