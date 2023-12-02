package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.Usuario;

import java.util.Optional;

public interface ControlEditarCrearUsuarios {
    ObjectTableModel<Usuario> search(String prompt, boolean isSesionActiva, Rol rol);

    Optional<String> deleteUser(Usuario usuarioSimple);

    Optional<String> updateUser(Usuario usuarioSimple);

    Optional<String> createUser(Usuario usuarioSimple);

    ObjectTableModel<Usuario> refresh();
}
