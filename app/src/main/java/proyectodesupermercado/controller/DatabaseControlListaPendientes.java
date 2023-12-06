package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlListaPendientes;
import proyectodesupermercado.controller.dao.NotificacionesDAO;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.NotificacionPendiente;
import proyectodesupermercado.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseControlListaPendientes implements ControlListaPendientes {
    private NotificacionesDAO notificacionesDAO;
    private List<NotificacionPendiente> notificacionPendientes;

    public DatabaseControlListaPendientes(NotificacionesDAO notificacionesDAO) {
        this.notificacionesDAO = notificacionesDAO;
    }

    @Override
    public ObjectTableModel<NotificacionPendiente> pullNotifications(Usuario usuario) {
        return new ObjectTableModel<>(
                NotificacionPendiente.class,
                notificacionPendientes = new ArrayList<>(notificacionesDAO.notificationsOf(usuario))
        );
    }

    @Override
    public List<NotificacionPendiente> getSelected() {
        return notificacionPendientes.stream()
                .filter(NotificacionPendiente::isSeleccionado)
                .collect(Collectors.toList());
    }
}
