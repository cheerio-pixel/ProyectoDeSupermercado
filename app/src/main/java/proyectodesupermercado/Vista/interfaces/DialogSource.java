package proyectodesupermercado.Vista.interfaces;


import java.util.Optional;

@FunctionalInterface
public interface DialogSource<T> {
    /**
     * Intenta enviar un objeto de tipo T a la fuente establecida
     *
     * @param object
     * @return Mensaje de error
     */
    Optional<String> accept(T object);
}
