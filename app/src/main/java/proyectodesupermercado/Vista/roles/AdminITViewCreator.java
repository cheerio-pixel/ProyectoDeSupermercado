package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.interfaces.ControlEditarCrearUsuarios;
import proyectodesupermercado.Vista.paneles.ManejadorDeUsuarios;
import proyectodesupermercado.controller.authentication.PasswordFactory;

import javax.swing.JPanel;

public class AdminITViewCreator implements ViewCreator {
    private final ControlEditarCrearUsuarios accionesUsuarios;
    private final PasswordFactory passwordFactory;
    public AdminITViewCreator(ControlEditarCrearUsuarios accionesUsuarios, PasswordFactory passwordFactory) {
        this.accionesUsuarios = accionesUsuarios;
        this.passwordFactory = passwordFactory;
    }

    @Override
    public JPanel create() {
        GeneralGroup group = new GeneralGroup();
        group.addTab("Usuarios", new ManejadorDeUsuarios(
                accionesUsuarios, passwordFactory
        ));
        return group;
    }
}
