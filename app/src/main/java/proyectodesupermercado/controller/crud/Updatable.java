package proyectodesupermercado.controller.crud;

public interface Updatable<T> {
    void update(Object id, T object);
}
