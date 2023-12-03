package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.ConditionsBuilder;
import proyectodesupermercado.controller.dao.DatabaseUtil;
import proyectodesupermercado.controller.dao.ProductoRegistroDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.lib.databaseUtils.TableMapper;
import proyectodesupermercado.modelo.ProductoRegistro;
import proyectodesupermercado.modelo.Suplidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProductoRegistroMySQLDAO implements ProductoRegistroDAO {
    private final DatabaseEnvironment dbEnv;
    private final TableMapper<ProductoRegistro> productoRegistroTableMapper;
    private String errorMsg;

    public ProductoRegistroMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
        this.productoRegistroTableMapper = new TableMapper<>(ProductoRegistro.class);
    }

    @Override
    public Optional<String> popLastError() {
        Optional<String> res = Optional.ofNullable(errorMsg);
        errorMsg = null;
        return res;

    }

    @Override
    public void delete(Object id, ProductoRegistro object) {
        String query = "DELETE FROM ProductoRegistro WHERE id = ?";
        String deleteAllowedDependencies = "DELETE FROM InventarioProducto WHERE idProductoRegistro = ? AND cantidad < 0";
        try (Connection conn = dbEnv.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(query);
                 PreparedStatement deleteStatement = conn.prepareStatement(deleteAllowedDependencies)) {
                // BOY OH BOY, FINNALY, USING A TRANSACTION
                conn.setAutoCommit(false);
                deleteStatement.setObject(1, id);
                deleteStatement.executeUpdate();
                statement.setObject(1, id);
                statement.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                // MySQL report of what went wrong
                conn.rollback();
                if (e.getErrorCode() == 1217) {
                    errorMsg = "No se puede borrar este producto, ya hay datos que prescinden de esta informacion";
                } else {
                    // I promise that in my next projects, I will
                    // at least send back the error in a string.
                    throw e;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(ProductoRegistro object) {
        String query = "INSERT INTO ProductoRegistro(id, idSuplidor, nombre, precioPorUnidad) VALUES (?, ?, ?, ?)";
        String queryInventario = "INSERT INTO InventarioProducto(id, idProductoRegistro, cantidad) " +
                "VALUES (?, ?, ?)";
        try (Connection conn = dbEnv.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(query);
                 PreparedStatement statementInventario = conn.prepareStatement(queryInventario)) {
                conn.setAutoCommit(false);

                long productoRegistroId = DatabaseUtil.getLastInsertId(statement, "ProductoRegistro") + 1;
                statement.setLong(1, productoRegistroId);
                long idSuplidor = object.getSuplidorId();
                if (idSuplidor == -1) {
                    statement.setObject(2, null);
                } else {
                    statement.setLong(2, idSuplidor);
                }
                statement.setString(3, object.getNombre());
                statement.setDouble(4, object.getPrecioDeVenta());

                statement.executeUpdate();

                statementInventario.setLong(
                        1,
                        DatabaseUtil.getLastInsertId(statementInventario, "InventarioProducto")
                                + 1
                );
                statementInventario.setObject(2, productoRegistroId);
                statementInventario.setInt(3, -1);
                statementInventario.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<ProductoRegistro> listAll() {
        String query = "SELECT * FROM ProductoRegistro " +
                "LEFT JOIN Suplidor " +
                "ON Suplidor.id = ProductoRegistro.idSuplidor " +
                "LIMIT 50";
        try (Connection conn = dbEnv.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Set<ProductoRegistro> res = new HashSet<>();
            while (rs.next()) {
                res.add(productoRegistroTableMapper
                        .mapResultSetToObject(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ProductoRegistro> listById(Object id) {
        String query = "SELECT * FROM ProductoRegistro " +
                "LEFT JOIN Suplidor " +
                "ON Suplidor.id = ProductoRegistro.idSuplidor " +
                "WHERE ProductoRegistro.id = ?" +
                "LIMIT 1";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(productoRegistroTableMapper.mapResultSetToObject(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Object id, ProductoRegistro object) {
        String query = "UPDATE ProductoRegistro " +
                "SET idSuplidor = ?, " +
                "nombre = ?, " +
                "precioPorUnidad = ? " +
                "WHERE id = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            long idSuplidor = object.getSuplidorId();
            if (idSuplidor == -1) {
                statement.setObject(1, null);
            } else {
                statement.setLong(1, idSuplidor);
            }
            statement.setString(2, object.getNombre());
            statement.setDouble(3, object.getPrecioDeVenta());
            statement.setObject(4, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductoRegistro> searchByFields(String nombre, Suplidor suplidor) {
        ConditionsBuilder builder = new ConditionsBuilder(
                "SELECT * FROM ProductoRegistro " +
                        "LEFT JOIN Suplidor " +
                        "ON Suplidor.id = ProductoRegistro.idSuplidor "
        ).addConditionIf(nombre != null && !nombre.isBlank(),
                "SOUNDEX(ProductoRegistro.nombre) LIKE " +
                        "CONCAT('%', SOUNDEX(?), '%') " +
                        "OR ProductoRegistro.nombre LIKE " +
                        "CONCAT('%', ?, '%')",
                nombre, nombre
        ).addConditionIf(suplidor != null
                        && suplidor.getId() != -1,
                "ProductoRegistro.idSuplidor = ?",
                suplidor != null ? suplidor.getId() : 0
        ).setAtLast(" LIMIT 50");
        String query = builder.commitConditions(" AND ");
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            int index = 1;
            for (Object param : builder.getParams()) {
                statement.setObject(index, param);
                index++;
            }
            ResultSet rs = statement.executeQuery();
            List<ProductoRegistro> res = new ArrayList<>();
            while (rs.next()) {
                res.add(productoRegistroTableMapper.mapResultSetToObject(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
