package proyectodesupermercado.controller.dao;


import proyectodesupermercado.controller.crud.ListableFromUser;
import proyectodesupermercado.modelo.NotificacionPendiente;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Timestamp;
import java.util.Optional;

public interface NotificacionesDAO extends ListableFromUser<NotificacionPendiente> {

    boolean isLimitActivated(Usuario usuario);

    int getLimit(Usuario usuario);

    Optional<Timestamp> getLastNotificationDate(Usuario usuario);

    void activateLimit(Usuario usuario, boolean activate, int limit);
}
