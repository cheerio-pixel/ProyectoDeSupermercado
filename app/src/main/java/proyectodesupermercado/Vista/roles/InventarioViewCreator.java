package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.interfaces.ControlInventario;
import proyectodesupermercado.Vista.paneles.ControlSolicitudes;
import proyectodesupermercado.Vista.paneles.Manejo_de_inventario;

public class InventarioViewCreator implements ViewCreator {
    private final ControlInventario controlInventario;

    public InventarioViewCreator(ControlInventario controlInventario) {
        this.controlInventario = controlInventario;
    }

    public GeneralGroup create() {
        GeneralGroup panel = new GeneralGroup();
        panel.addTab("Inventario", new Manejo_de_inventario(controlInventario));
        panel.addTab("Solicitudes", new ControlSolicitudes());
        return panel;
    }
}
