package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.interfaces.BuscableEnInventario;
import proyectodesupermercado.Vista.interfaces.ControlPuntoDeVenta;
import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.Vista.paneles.PanelPuntoDeVenta;

import javax.swing.JPanel;

public class PuntoDeVentaViewCreator implements ViewCreator {
    private final SesionUsuario sesionUsuario;
    private final ControlPuntoDeVenta accionesPuntoDeVenta;
    private final BuscableEnInventario buscador;

    public PuntoDeVentaViewCreator(SesionUsuario sesionUsuario, ControlPuntoDeVenta accionesPuntoDeVenta, BuscableEnInventario buscador) {
        this.sesionUsuario = sesionUsuario;
        this.accionesPuntoDeVenta = accionesPuntoDeVenta;
        this.buscador = buscador;
    }

    @Override
    public JPanel create() {
        return new PanelPuntoDeVenta(sesionUsuario, accionesPuntoDeVenta, buscador);
    }
}
