package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.interfaces.ControlInventario;
import proyectodesupermercado.Vista.paneles.Manejo_de_inventario;
import proyectodesupermercado.Vista.paneles.ProductosFueraDeServicio;

public class InventarioViewCreator implements ViewCreator {
    private final ControlInventario controlInventario;
    private final ControlInventario controlInventarioFueraDeServicio;

    public InventarioViewCreator(ControlInventario controlInventario, ControlInventario controlInventarioFueraDeServicio) {
        this.controlInventario = controlInventario;
        this.controlInventarioFueraDeServicio = controlInventarioFueraDeServicio;
    }

    public GeneralGroup create() {
        GeneralGroup panel = new GeneralGroup();
        panel.addTab("Inventario", new Manejo_de_inventario(controlInventario));
        panel.addTab("Fuera de servicio", new ProductosFueraDeServicio(controlInventarioFueraDeServicio));
//        panel.addTab("Solicitudes", new ControlSolicitudes());
        return panel;
    }
}
