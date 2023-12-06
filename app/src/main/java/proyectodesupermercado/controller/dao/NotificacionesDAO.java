package proyectodesupermercado.controller.dao;


import proyectodesupermercado.modelo.NotificacionPendiente;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface NotificacionesDAO {
    List<NotificacionPendiente> notificationsOf(Usuario usuario);

    boolean isLimitActivated(Usuario usuario);

    int getLimit(Usuario usuario);

    Optional<Timestamp> getLastNotificationDate(Usuario usuario);

    void activateLimit(Usuario usuario, boolean activate, int limit);
}
