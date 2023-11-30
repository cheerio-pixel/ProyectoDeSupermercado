package proyectodesupermercado.controller.dao;

import java.util.Optional;
import java.util.Set;

public interface GenericDAO<T> {
    Set<T> listAll();

    Optional<T> listById(Object id);

    void insert(T object);

    void update(Object id, T object);
}
