package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.ContentChanger;
import proyectodesupermercado.Vista.CreacionProductos;
import proyectodesupermercado.Vista.Creador_De_solicitud_De_Compra;
import proyectodesupermercado.Vista.Gestor_De_Empresas;
import proyectodesupermercado.Vista.LoginPanel;
import proyectodesupermercado.Vista.ManejadorDeUsuarios;
import proyectodesupermercado.Vista.Manejo_de_inventario;
import proyectodesupermercado.Vista.PanelPuntoDeVenta;
import proyectodesupermercado.controller.authentication.Usuario;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class StateBroker {
    private final ContentChanger changer;
    private final Runnable closeAplication;

    public StateBroker(ContentChanger changer, Runnable closeAplication) {
        this.changer = changer;
        this.closeAplication = closeAplication;
    }

    public void moveToLogin(SesionUsuario sesionUsuario) {
        changer.setContent(
                new LoginPanel(sesionUsuario),
                "Cerrar aplicacion",
                evt -> closeAplication.run()
        );
    }

    public void moveToLoggedScreen(Usuario usuario, Runnable cerrarSesionAction) {
        JPanel content = null;
        switch (usuario.getRol()) {
            case Inventario: {
                content = new Manejo_de_inventario();
            }
            break;
            case Gerente: {
                JTabbedPane tabs = new JTabbedPane();
                tabs.setTabPlacement(JTabbedPane.LEFT);
                tabs.addTab("Gestor de Empresas", new Gestor_De_Empresas());
                tabs.addTab("Manejador de compras", new Creador_De_solicitud_De_Compra());
                tabs.addTab("Crear/Borrar productos", new CreacionProductos());

                content = new JPanel(new BorderLayout());
                content.add(tabs);
            }
            break;
            case AdminIT: {
//                JTabbedPane tabs = new JTabbedPane();
//                tabs.setTabPlacement(JTabbedPane.LEFT);
//                tabs.addTab("Usuarios", new ManejadorDeUsuarios());
//                content = new JPanel(new BorderLayout());
//                content.add(tabs);

                content = new ManejadorDeUsuarios();
            }
            break;
            case PuntoDeVenta: {
                content = new PanelPuntoDeVenta();
            }
            break;
        }
        changer.setContent(
                content,
                "Cerrar sesion",
                evt -> cerrarSesionAction.run()

        );
    }
}
