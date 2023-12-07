package proyectodesupermercado.Vista.roles;


import proyectodesupermercado.Vista.interfaces.ControlManejoGanancias;
import proyectodesupermercado.Vista.paneles.PanelManejoGanancias;
import proyectodesupermercado.Vista.paneles.PanelVentasYCompras;

import javax.swing.JPanel;

public class ContabilidadViewCreator implements ViewCreator {
    private final ControlManejoGanancias controlManejoGanancias;

    public ContabilidadViewCreator(ControlManejoGanancias controlManejoGanancias) {
        this.controlManejoGanancias = controlManejoGanancias;
    }

    @Override
    public JPanel create() {
        GeneralGroup group = new GeneralGroup();
        group.addTab("Ventas y Compras", new PanelVentasYCompras());
        group.addTab("Ganancias", new PanelManejoGanancias(controlManejoGanancias));
        return group;
    }
}
