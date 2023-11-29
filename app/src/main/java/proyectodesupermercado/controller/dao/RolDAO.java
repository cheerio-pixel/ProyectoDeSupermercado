package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RolDAO {
    public static void initRoles(DatabaseEnvironment dbEnv) {
        // MYSQL SYNTAX
        String query = "INSERT IGNORE INTO Rol SET id = ?, nombre = ?";
        for (Rol rol : Rol.values()) {
            try (Connection conn = dbEnv.getConnection();
                 PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setLong(1, rol.getId());
                statement.setString(2, rol.getNombre());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
