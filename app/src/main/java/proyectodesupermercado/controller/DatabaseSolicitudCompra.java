package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlManejoSolicitudes;
import proyectodesupermercado.controller.dao.NotificacionesDAO;
import proyectodesupermercado.controller.dao.SolicitudesDAO;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.NotificacionPendiente;
import proyectodesupermercado.modelo.SolicitudCompra;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class DatabaseSolicitudCompra implements ControlManejoSolicitudes {
    private SolicitudesDAO solicitudesDAO;
    private NotificacionesDAO notificacionesDAO;

    public DatabaseSolicitudCompra(SolicitudesDAO solicitudesDAO,
                                   NotificacionesDAO notificacionesDAO) {
        this.solicitudesDAO = solicitudesDAO;
        this.notificacionesDAO = notificacionesDAO;
    }

    @Override
    public ObjectTableModel<SolicitudCompra> refreshSolicitudCompra() {
        return new ObjectTableModel<>(
                SolicitudCompra.class,
                solicitudesDAO.listAll().stream()
                        .sorted(Collections.reverseOrder(Comparator.comparing(SolicitudCompra::getFecha)))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<String> creaNuevaSolicitud(SolicitudCompra solicitudCompra) {
        solicitudesDAO.insert(solicitudCompra);
        return Optional.empty();
    }

    @Override
    public boolean areNotificationsActivated(Usuario usuario) {
        return notificacionesDAO.isLimitActivated(usuario);
    }

    @Override
    public Optional<Timestamp> getDateOfLastNotification(Usuario usuario) {
        return notificacionesDAO.getLastNotificationDate(usuario);
    }

    @Override
    public OptionalInt getLimit(Usuario usuario) {
        if (notificacionesDAO.isLimitActivated(usuario)) {
            return OptionalInt.of(notificacionesDAO.getLimit(usuario));
        }
        return OptionalInt.empty();
    }

    @Override
    public Optional<String> activateLimit(Usuario usuario, boolean activate, int limit) {
        notificacionesDAO.activateLimit(usuario, activate, limit);
        return Optional.empty();
    }
}
