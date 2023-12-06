package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.ConditionsBuilder;
import proyectodesupermercado.controller.dao.InventarioProductoDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.modelo.InventarioProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class InventarioProductoMySQLDAO implements InventarioProductoDAO {
    private final DatabaseEnvironment dbEnv;

    public InventarioProductoMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
    }

    @Override
    public Set<InventarioProducto> listAll() {
        String query = "SELECT InventarioProducto.id, ProductoRegistro.id, ProductoRegistro.nombre, InventarioProducto.cantidad " +
                "FROM InventarioProducto " +
                "INNER JOIN ProductoRegistro " +
                "ON ProductoRegistro.id = InventarioProducto.idProductoRegistro " +
                "WHERE InventarioProducto.cantidad >= 0 " +
                "LIMIT 50";
        try (Connection conn = dbEnv.getConnection();
             Statement statement = conn.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(query);
            Set<InventarioProducto> res = new HashSet<>();
            while (rs.next()) {
                res.add(
                        new InventarioProducto(
                                rs.getInt("InventarioProducto.id"),
                                rs.getInt("ProductoRegistro.id"),
                                rs.getString("ProductoRegistro.nombre"),
                                rs.getInt("InventarioProducto.cantidad"))
                );
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<InventarioProducto> listById(Object id) {
        // TODO: Codigo repetitivo, refactoriza para que sea mas facil hacer cambios
        String query = "SELECT InventarioProducto.id, ProductoRegistro.id, ProductoRegistro.nombre, InventarioProducto.cantidad " +
                "FROM InventarioProducto " +
                "INNER JOIN ProductoRegistro " +
                "ON ProductoRegistro.id = InventarioProducto.idProductoRegistro " +
                "WHERE InventarioProducto.id = ? " +
                "LIMIT 1";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(new InventarioProducto(
                        rs.getInt("InventarioProducto.id"),
                        rs.getInt("ProductoRegistro.id"),
                        rs.getString("ProductoRegistro.nombre"),
                        rs.getInt("InventarioProducto.cantidad")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<InventarioProducto> searchByName(String name) {
        ConditionsBuilder builder = new ConditionsBuilder(
                "SELECT InventarioProducto.id, ProductoRegistro.id, ProductoRegistro.nombre, InventarioProducto.cantidad " +
                        "FROM InventarioProducto " +
                        "INNER JOIN ProductoRegistro " +
                        "ON ProductoRegistro.id = InventarioProducto.idProductoRegistro "
        ).addConditionIf(name != null && !name.isBlank(),
                "SOUNDEX(ProductoRegistro.nombre) " +
                        "LIKE CONCAT('%', SOUNDEX(?), '%') " +
                        "OR ProductoRegistro.nombre LIKE CONCAT('%', ?, '%') ",
                name, name
        ).addCondition(" InventarioProducto.cantidad >= 0 "
        ).setAtLast(" LIMIT 50");

        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(builder.commitConditions(" AND "))
        ) {
            int index = 1;
            for (Object param : builder.getParams()) {
                statement.setObject(index, param);
                index++;
            }

            ResultSet rs = statement.executeQuery();
            Set<InventarioProducto> res = new HashSet<>();
            while (rs.next()) {
                res.add(new InventarioProducto(
                        rs.getInt("InventarioProducto.id"),
                        rs.getInt("ProductoRegistro.id"),
                        rs.getString("ProductoRegistro.nombre"),
                        rs.getInt("InventarioProducto.cantidad")));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates only the quantity field
     *
     * @param id
     * @param object
     */

    @Override
    public void update(Object id, InventarioProducto object) {
        // TODO: Make this propagate errors better
        String query = "UPDATE InventarioProducto SET cantidad = ? WHERE id = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, object.getCantidad());
            statement.setObject(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
