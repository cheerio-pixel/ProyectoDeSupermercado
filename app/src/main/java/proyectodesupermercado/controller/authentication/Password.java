package proyectodesupermercado.controller.authentication;

import java.io.Serializable;

public interface Password extends Serializable {
    boolean checkPassword(char[] password);
}
