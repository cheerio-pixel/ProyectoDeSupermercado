package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.paneles.ManejadorDeUsuarios;

import javax.swing.JPanel;

public class AdminITViewCreator implements ViewCreator {
    @Override
    public JPanel create() {
        GeneralGroup group = new GeneralGroup();
        group.addTab("Usuarios", new ManejadorDeUsuarios());
        return group;
    }
}
