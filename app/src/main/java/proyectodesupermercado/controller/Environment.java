package proyectodesupermercado.controller;

import proyectodesupermercado.controller.authentication.Usuario;
import proyectodesupermercado.controller.dao.UsuarioDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;

import javax.swing.JFrame;
import java.util.Optional;

public class Environment implements SesionUsuario {
    private Usuario loggedUser;
    private final DatabaseEnvironment dbEnv;
    private final StateBroker stateBroker;
    private final UsuarioDAO usuarioDAO;
    private final JFrame frame;

    public Environment(DatabaseEnvironment dbEnv, StateBroker stateBroker, JFrame frame) {
        this.dbEnv = dbEnv;
        this.stateBroker = stateBroker;
        this.frame = frame;
        usuarioDAO = new UsuarioDAO(dbEnv);
    }

    @Override
    public Usuario getLoggedUser() {
        return loggedUser;
    }

    @Override
    public Result<Usuario> logUser(String nombre, char[] contraseña) {
        Optional<Usuario> usuarioOptional = usuarioDAO.listByName(nombre);
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
