package proyectodesupermercado.lib.databaseUtils;

import java.util.Collection;
import java.util.Optional;

public interface DataAccessObject<T> {
    /**
     * Commits changes to object to persistance storage
     * @param object Object to commit
     */
    void update(T object, boolean allowNull);

    /**
     * Creates new entry of object in persistance storage.
     * @param object
     */
    void insert(T object);

    /**
     * Detach object from
     */
    void delete(T object);

    /**
     * Select all elements in the storage
     * @return
     */
    Collection<T> listAll();
    Optional<T> listById(Object id);
}
