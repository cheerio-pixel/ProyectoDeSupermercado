package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlEditarCrearUsuarios;
import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.controller.dao.UsuarioDAO;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.Usuario;

import java.util.ArrayList;
import java.util.Optional;

public class DatabaseControlUsuario implements ControlEditarCrearUsuarios {
    private final UsuarioDAO usuarioDAO;

    public DatabaseControlUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public ObjectTableModel<Usuario> search(String prompt, boolean isSesionActiva, Rol rol) {
        return new ObjectTableModel<>(Usuario.class, usuarioDAO.listByFields(rol, prompt, isSesionActiva));
    }

    @Override
    public Optional<String> deleteUser(Usuario usuarioSimple) {
        usuarioDAO.delete(usuarioSimple.getId(), usuarioSimple);
        return Optional.empty();
    }

    @Override
    public Optional<String> updateUser(Usuario usuarioSimple) {
        if (usuarioDAO.isUsernameInStorage(usuarioSimple.getNombre(), usuarioSimple.getId())) {
            return Optional.of(
                    "El nombre de usuario ya esta en uso."
            );
        }
        usuarioDAO.update(usuarioSimple.getId(), usuarioSimple);
        return Optional.empty();
    }

    @Override
    public Optional<String> createUser(Usuario usuarioSimple) {
        if (usuarioDAO.isUsernameInStorage(usuarioSimple.getNombre())) {
            return Optional.of(
                    "El nombre de usuario ya esta en uso."
            );
        }
        usuarioDAO.insert(usuarioSimple);
        return Optional.empty();
    }

    @Override
    public ObjectTableModel<Usuario> refresh() {
        return new ObjectTableModel<>(Usuario.class, new ArrayList<>(usuarioDAO.listAll()));
    }
}
