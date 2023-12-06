package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.NotificacionPendiente;
import proyectodesupermercado.modelo.Usuario;

import java.util.List;

public interface ControlListaPendientes {
    // Return model of notifications
    ObjectTableModel<NotificacionPendiente> pullNotifications(Usuario usuario);

    /**
     * Pulls a list of the elements in the view
     *
     * @return
     */
    List<NotificacionPendiente> getSelected();
}
