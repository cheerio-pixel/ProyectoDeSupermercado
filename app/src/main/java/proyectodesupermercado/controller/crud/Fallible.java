package proyectodesupermercado.controller.crud;

import java.util.Optional;

public interface Fallible {
    Optional<String> popLastError();
}
