package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ContentChanger;
import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.Vista.paneles.LoginPanel;
import proyectodesupermercado.Vista.roles.ViewCreator;
import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.modelo.Usuario;

import javax.swing.JPanel;
import java.util.Map;
import java.util.Optional;

public class StateBroker {
    private final ContentChanger changer;
    private final Runnable closeAplication;
    private final Map<Rol, ViewCreator> rolViewCreator;

    public StateBroker(
            ContentChanger changer,
            Runnable closeAplication,
            Map<Rol, ViewCreator> rolViewCreator
    ) {
        this.changer = changer;
        this.closeAplication = closeAplication;
        this.rolViewCreator = rolViewCreator;
    }

    public void moveToLogin(SesionUsuario sesionUsuario) {
        changer.setContent(
                new LoginPanel(sesionUsuario),
                "Cerrar aplicacion",
                evt -> closeAplication.run()
        );
    }

    public void moveToLoggedScreen(Usuario usuario, Runnable cerrarSesionAction) {
        // TODO: Hacer que esto logee en vez de lanzar una exeception
        JPanel content = Optional.ofNullable(rolViewCreator.get(usuario.getRol()))
                .map(ViewCreator::create)
                .orElseThrow(() -> new RuntimeException(
                        "Rol " + usuario.getRol().getNombre() + " no tiene una vista registrada."
                ));
        changer.setContent(
                content,
                "Cerrar sesion",
                evt -> cerrarSesionAction.run()
        );
    }
}
