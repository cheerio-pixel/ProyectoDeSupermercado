package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.interfaces.ControlProductoRegistro;
import proyectodesupermercado.Vista.paneles.CreacionProductos;
import proyectodesupermercado.Vista.paneles.Creador_De_solicitud_De_Compra;
import proyectodesupermercado.Vista.paneles.Gestor_De_Empresas;

import javax.swing.JPanel;

public class GerenteViewCreator implements ViewCreator {

    private final ControlProductoRegistro controlProductoRegistro;

    public GerenteViewCreator(ControlProductoRegistro controlProductoRegistro) {
        this.controlProductoRegistro = controlProductoRegistro;
    }

    @Override
    public JPanel create() {
        GeneralGroup group = new GeneralGroup();
        group.addTab("Gestor de Empresas", new Gestor_De_Empresas());
        group.addTab("Manejador de compras", new Creador_De_solicitud_De_Compra());
        group.addTab("Crear/Borrar productos", new CreacionProductos(
                controlProductoRegistro
        ));
        return group;
    }
}