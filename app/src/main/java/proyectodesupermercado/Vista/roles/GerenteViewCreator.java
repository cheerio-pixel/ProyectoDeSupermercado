package proyectodesupermercado.Vista.roles;

import proyectodesupermercado.Vista.interfaces.BuscableEnInventario;
import proyectodesupermercado.Vista.interfaces.ControlHistorialSolicitud;
import proyectodesupermercado.Vista.interfaces.ControlListaPendientes;
import proyectodesupermercado.Vista.interfaces.ControlManejoSolicitudes;
import proyectodesupermercado.Vista.interfaces.ControlProductoRegistro;
import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.Vista.paneles.ControlSolicitudes;
import proyectodesupermercado.Vista.paneles.CreacionProductos;
import proyectodesupermercado.Vista.paneles.Gestor_De_Empresas;
import proyectodesupermercado.controller.dao.mysql.SuplidorMySQLDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;

import javax.swing.JPanel;

public class GerenteViewCreator implements ViewCreator {

    private final ControlProductoRegistro controlProductoRegistro;
    private final ControlManejoSolicitudes accionesManejoSolicitudes;
    private final ControlListaPendientes accionesListaPendientes;
    private final BuscableEnInventario buscador;
    private final ControlHistorialSolicitud controlHistorialSolicitud;
    private final SesionUsuario sesionUsuario;
    private final SuplidorMySQLDAO suplidorDAO;
    private final DatabaseEnvironment dbEnv;

    public GerenteViewCreator(ControlProductoRegistro controlProductoRegistro,
                              ControlManejoSolicitudes accionesManejoSolicitudes,
                              BuscableEnInventario buscador,
                              ControlListaPendientes accionesListaPendientes,
                              ControlHistorialSolicitud controlHistorialSolicitud,
                              SesionUsuario sesionUsuario,
                              SuplidorMySQLDAO suplidorDAO,
                              DatabaseEnvironment dbEnv) {
        this.controlProductoRegistro = controlProductoRegistro;
        this.accionesManejoSolicitudes = accionesManejoSolicitudes;
        this.buscador = buscador;
        this.accionesListaPendientes = accionesListaPendientes;
        this.controlHistorialSolicitud = controlHistorialSolicitud;
        this.sesionUsuario = sesionUsuario;
        this.suplidorDAO = suplidorDAO;
        this.dbEnv = dbEnv;
    }

    @Override
    public JPanel create() {
        GeneralGroup group = new GeneralGroup();
        group.addTab("Gestor de Empresas", new Gestor_De_Empresas(suplidorDAO, dbEnv));
        group.addTab("Control de solicitudes", new ControlSolicitudes(
                accionesManejoSolicitudes,
                accionesListaPendientes,
                buscador,
                controlHistorialSolicitud,
                sesionUsuario

        ));
        group.addTab("Crear/Borrar productos", new CreacionProductos(
                controlProductoRegistro
        ));
        return group;
    }
}