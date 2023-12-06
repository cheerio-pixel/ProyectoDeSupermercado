package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.controller.dao.UsuarioDAO;
import proyectodesupermercado.modelo.Usuario;

import java.util.Optional;

public class DatabaseSesionUsuario implements SesionUsuario {
    private final UsuarioDAO usuarioDAO;
    private StateBroker stateBroker;
    private Usuario loggedUser;

    public DatabaseSesionUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void setStateBroker(StateBroker stateBroker) {
        this.stateBroker = stateBroker;
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
            loggedUser = null;
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
