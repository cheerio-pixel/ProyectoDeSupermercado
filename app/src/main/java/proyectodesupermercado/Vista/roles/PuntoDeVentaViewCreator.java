package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.paneles.PanelPuntoDeVenta;

import javax.swing.JPanel;

public class PuntoDeVentaViewCreator implements ViewCreator {

    @Override
    public JPanel create() {
        return new PanelPuntoDeVenta();
    }
}
