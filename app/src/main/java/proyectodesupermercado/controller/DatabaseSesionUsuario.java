package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.controller.authentication.Usuario;
import proyectodesupermercado.controller.dao.UsuarioDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;

import java.util.Optional;

public class DatabaseSesionUsuario implements SesionUsuario {
    private final UsuarioDAO usuarioDAO;
    private final StateBroker stateBroker;
    private Usuario loggedUser;

    public DatabaseSesionUsuario(DatabaseEnvironment dbEnv, StateBroker stateBroker) {
        this.stateBroker = stateBroker;
        usuarioDAO = new UsuarioDAO(dbEnv);
    }

    @Override
    public Usuario getLoggedUser() {
        return loggedUser;
    }

    @Override
    public Result<Usuario> logUser(String nombre, char[] contraseña) {
        Optional<Usuario> usuarioOptional = usuarioDAO.listByName(nombre)
                .filter(u -> u.checkPassword(contraseña));
        if (usuarioOptional.isEmpty()) {
            return new Result<>(null, "La contraseña o nombre de usuario es invalido.");
        }
        loggedUser = usuarioOptional.get();
        if (loggedUser.isAlreadyLogged()) {
            return new Result<>(null, "Ya hay alguien en sesion con este usuario");
        } else {
            loggedUser.setAlreadyLogged(true);
            usuarioDAO.logUser(loggedUser);
        }

        stateBroker.moveToLoggedScreen(loggedUser, this::logOut);
        return new Result<>(loggedUser, null);
    }

    @Override
    public void logOut() {
        if (loggedUser != null) {
            loggedUser.setAlreadyLogged(false);
            usuarioDAO.logUser(loggedUser);
        }
        stateBroker.moveToLogin(this);
    }
}
