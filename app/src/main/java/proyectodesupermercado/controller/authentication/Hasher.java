package proyectodesupermercado.controller.authentication;

import java.io.Serializable;

public interface Hasher extends Serializable {
    byte[] hash(char[] charArray, byte[] salt);
}
