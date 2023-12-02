package proyectodesupermercado.controller.crud;

public interface CRUD<T> extends
        Insertable<T>,
        Listable<T>,
        Updatable<T>,
        Deleteable<T> {
}
