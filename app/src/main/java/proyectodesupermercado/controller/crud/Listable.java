package proyectodesupermercado.controller.crud;

import java.util.Optional;
import java.util.Set;

public interface Listable<T> {
    Set<T> listAll();

    Optional<T> listById(Object id);
}
