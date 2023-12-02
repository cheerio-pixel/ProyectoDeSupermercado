package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.controller.crud.CRUD;
import proyectodesupermercado.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAO extends CRUD<Usuario> {
    Optional<Usuario> listByName(String name);

    /**
     * Realiza una busqueda por los campos de un usuario, si alguno es nulo, este no sera considerado.
     *
     * @param rol
     * @param nombre
     * @param isLogged
     */
    List<Usuario> listByFields(Rol rol,
                               String nombre,
                               Boolean isLogged);
    /**
     * Change the logging state of the user in the store
     * @param usuario
     */
    void logUser(Usuario usuario);

    boolean isUsernameInStorage(String username);

    boolean isUsernameInStorage(String username, Object exceptId);
}
