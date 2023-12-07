package proyectodesupermercado.controller.crud;

import proyectodesupermercado.modelo.Usuario;

import java.util.List;

public interface ListableFromUser<T> {
    List<T> pullFromUser(Usuario usuario);
}
