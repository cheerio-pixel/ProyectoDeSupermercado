package proyectodesupermercado.controller.crud;

public interface Deleteable<T> {
    void delete(Object id, T object);
}
