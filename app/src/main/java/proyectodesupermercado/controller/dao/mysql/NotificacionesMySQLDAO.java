package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.dao.NotificacionesDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.modelo.NotificacionPendiente;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificacionesMySQLDAO implements NotificacionesDAO {
    private final DatabaseEnvironment dbEnv;

    public NotificacionesMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
    }
    @Override
    public List<NotificacionPendiente> pullFromUser(Usuario usuario) {
        long userId;
        if (usuario != null) {
            userId = usuario.getId();
        } else {
            return new ArrayList<>();
        }
        String query = "SELECT * FROM NotificacionDeCompra " +
                "INNER JOIN InventarioProducto " +
                "ON InventarioProducto.id = NotificacionDeCompra.idInventarioProducto " +
                "INNER JOIN ProductoRegistro " +
                "ON ProductoRegistro.id = InventarioProducto.idProductoRegistro " +
                "WHERE NotificacionDeCompra.idUsuario = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            List<NotificacionPendiente> res = new ArrayList<>();
            while (rs.next()) {
                res.add(
                        new NotificacionPendiente(
                                rs.getInt("ProductoRegistro.id"),
                                userId,
                                rs.getString("ProductoRegistro.nombre"),
                                rs.getInt("InventarioProducto.cantidad"),
                                rs.getTimestamp("NotificacionDeCompra.fechaDeCreacion")
                        )
                );
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isLimitActivated(Usuario usuario) {
        return getLimit(usuario) != -1;
    }

    @Override
    public int getLimit(Usuario usuario) {
        String query = "SELECT limite FROM LimiteDeInventario " +
                "WHERE idUsuario = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, usuario.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("LimiteDeInventario.limite");
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Timestamp> getLastNotificationDate(Usuario usuario) {
        String query = "SELECT fechaDeCreacion FROM NoficacionDeCompra " +
                "WHERE idUsuario = ? ORDER BY fechaDeCreacion DESC LIMIT 1";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, usuario.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getTimestamp("NoficacionDeCompra.fechaDeCreacion"));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void activateLimit(Usuario usuario, boolean activate, int limit) {
        String query;
        if (activate) {
            query = "INSERT INTO LimiteDeInventario SET idUsuario = ?, limite = ? " +
                    "ON DUPLICATE KEY UPDATE limite = ?";
        } else {
            query = "DELETE FROM LimiteDeInventario WHERE idUsuario = ?";
        }
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, usuario.getId());
            if (activate) {
                statement.setInt(2, limit);
                statement.setInt(3, limit);
            }
            statement.executeUpdate();
            deleteOrCreateNotifications(usuario, activate, limit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteOrCreateNotifications(Usuario usuario, boolean activate, int limit) throws SQLException {
        if (activate) {
            String query = "INSERT IGNORE NotificacionDeCompra(idInventarioProducto, idUsuario, fechaDeCreacion) " +
                    "SELECT InventarioProducto.id, ?, CURRENT_TIMESTAMP() FROM InventarioProducto WHERE InventarioProducto.cantidad <= ?";
            try (Connection connection = dbEnv.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, usuario.getId());
                statement.setInt(2, limit);
                statement.executeUpdate();
            }
        } else {
            String query = "DELETE FROM NotificacionDeCompra WHERE idUsuario = ?";
            try (Connection connection = dbEnv.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setLong(1, usuario.getId());
                statement.executeUpdate();
            }
        }
    }
}
