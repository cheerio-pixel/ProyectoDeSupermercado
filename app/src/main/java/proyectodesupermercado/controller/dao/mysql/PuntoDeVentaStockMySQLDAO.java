package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.dao.DatabaseUtil;
import proyectodesupermercado.controller.dao.PuntoDeVentaStockDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.lib.databaseUtils.TableMapper;
import proyectodesupermercado.modelo.PuntoDeVentaProducto;
import proyectodesupermercado.modelo.PuntoDeVentaStock;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PuntoDeVentaStockMySQLDAO implements PuntoDeVentaStockDAO {
    private final TableMapper<PuntoDeVentaStock> tableMapper;
    private final DatabaseEnvironment dbEnv;
    private PuntoDeVentaStock lastInsertedStock;

    public PuntoDeVentaStockMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
        this.tableMapper = new TableMapper<>(PuntoDeVentaStock.class);
    }

    @Override
    public void delete(Object id, PuntoDeVentaStock object) {
        try (Connection conn = dbEnv.getConnection()) {
            try {
                conn.setAutoCommit(false);
                delete(id, object, conn, true);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Object id, PuntoDeVentaStock object, Connection connection,
                       boolean isMoveBackToStorage) throws SQLException {
        String queryDeleteStock = "DELETE FROM PuntoDeVentaStock " +
                "WHERE id = ?";
        String queryDeletePuntoDeVentaProducto = "DELETE FROM PuntoDeVentaProducto " +
                "WHERE idPuntoDeVentaStock = ?";
        try (PreparedStatement statementDeletePuntoDeVentaProducto = connection.prepareStatement(queryDeletePuntoDeVentaProducto);
             PreparedStatement statementDeleteStock = connection.prepareStatement(queryDeleteStock)
        ) {
            if (isMoveBackToStorage) {
                String queryMovePuntoDeVentaProducto = "UPDATE InventarioProducto " +
                        "INNER JOIN ProductoRegistro " +
                        "ON ProductoRegistro.id = InventarioProducto.idProductoRegistro " +
                        "INNER JOIN PuntoDeVentaProducto " +
                        "ON ProductoRegistro.id = PuntoDeVentaProducto.idProductoRegistro " +
                        "SET InventarioProducto.cantidad = InventarioProducto.cantidad + PuntoDeVentaProducto.cantidad " +
                        "WHERE idPuntoDeVentaStock = ?";
                try (PreparedStatement statementMovePuntoDeVentaProducto = connection.prepareStatement(queryMovePuntoDeVentaProducto)) {
                    statementMovePuntoDeVentaProducto.setObject(1, id);
                    statementMovePuntoDeVentaProducto.executeUpdate();
                }
            }

            statementDeletePuntoDeVentaProducto.setObject(1, id);
            statementDeletePuntoDeVentaProducto.executeUpdate();

            statementDeleteStock.setObject(1, id);
            statementDeleteStock.executeUpdate();

//                if (connection == null) {
//                    conn.commit();
//                }
        }
    }

    @Override
    public void insert(PuntoDeVentaStock object) {
        String query = "INSERT INTO PuntoDeVentaStock(id, usuarioId, fechaDeInicio) " +
                "VALUES (?, ?, ?)";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            int id = DatabaseUtil.getLastInsertId(statement, "PuntoDeVentaStock") + 1;
            statement.setInt(1, id);
            statement.setLong(2, object.getUsuarioId());
            statement.setTimestamp(3, object.getFechaDeCreacion());
            statement.executeUpdate();
            object.setId(id);
            lastInsertedStock = object;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PuntoDeVentaStock> pullFromUser(Usuario usuario) {
        String query = "SELECT * FROM PuntoDeVentaStock WHERE usuarioId = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setObject(1, usuario.getId());
            ResultSet rs = statement.executeQuery();
            List<PuntoDeVentaStock> res = new ArrayList<>();
            while (rs.next()) {
                res.add(
                        tableMapper.mapResultSetToObject(rs)
                );
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PuntoDeVentaStock retriveLastInsertedStock() {
        return lastInsertedStock;
    }

    @Override
    public List<PuntoDeVentaProducto> listAllOf(PuntoDeVentaStock stock) {
        String query = "SELECT * FROM PuntoDeVentaProducto " +
                "JOIN ProductoRegistro " +
                "ON ProductoRegistro.id = PuntoDeVentaProducto.idProductoRegistro " +
                "WHERE idPuntoDeVentaStock = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, stock.getId());
            ResultSet rs = statement.executeQuery();
            List<PuntoDeVentaProducto> res = new ArrayList<>();
            while (rs.next()) {
                res.add(
                        new PuntoDeVentaProducto(
                                rs.getLong("PuntoDeVentaProducto.id"),
                                rs.getLong("PuntoDeVentaProducto.idProductoRegistro"),
                                rs.getInt("PuntoDeVentaProducto.cantidad"),
                                rs.getString("ProductoRegistro.nombre"),
                                rs.getDouble("ProductoRegistro.precioPorUnidad")
                        )
                );
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<String> confirmStock(PuntoDeVentaStock stock, List<PuntoDeVentaProducto> productos) {
        String queryCreateTransaction = "INSERT INTO Transaccion(id, fechaDeCreacion) " +
                "VALUES (?, ?)";
        String queryMoveToTransaction = "INSERT INTO TransaccionProducto(id, idProductoRegistro, " +
                "idTransaccion, cantidad, impuestosAñadidos, precioPorUnidad) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbEnv.getConnection()) {
            try (PreparedStatement statementCreateTransaction = conn.prepareStatement(queryCreateTransaction);
                 PreparedStatement statementMoveToTransaction = conn.prepareStatement(queryMoveToTransaction)

            ) {
                conn.setAutoCommit(false);
                int index = 1 + DatabaseUtil.getLastInsertId(statementCreateTransaction, "Transaccion");
                statementCreateTransaction.setInt(1, index);
                statementCreateTransaction.setTimestamp(2, stock.getFechaDeCreacion());
                statementCreateTransaction.executeUpdate();

                for (PuntoDeVentaProducto prod : productos) {
                    statementMoveToTransaction.setLong(1, 1 + DatabaseUtil
                            .getLastInsertId(statementMoveToTransaction, "TransaccionProducto"));
                    statementMoveToTransaction.setLong(2, prod.getIdProductoRegistro());
                    statementMoveToTransaction.setInt(3, index);
                    statementMoveToTransaction.setInt(4, prod.getCantidad());
                    statementMoveToTransaction.setDouble(5, prod.getTaxes());
                    statementMoveToTransaction.setDouble(6, prod.getPrecioPorUnidad());
                    statementMoveToTransaction.executeUpdate();
                }

                delete(stock.getId(), stock, conn, false);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                return Optional.of("Ocurrio un erro al procesar la Transaccion");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void removeProduct(PuntoDeVentaProducto producto) {
        String query = "DELETE FROM PuntoDeVentaProducto " +
                "WHERE id = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, producto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertProducto(Object stockId, PuntoDeVentaProducto productoPuntoDeVenta) {
        String query = "INSERT INTO PuntoDeVentaProducto (id, idPuntoDeVentaStock, " +
                "idProductoRegistro, cantidad) " +
                "VALUES (?, ?, ?, ?)";
        String queryMoveFromInventario = "UPDATE InventarioProducto " +
                "INNER JOIN ProductoRegistro " +
                "ON InventarioProducto.idProductoRegistro = ProductoRegistro.id " +
                "INNER JOIN PuntoDeVentaProducto ON PuntoDeVentaProducto.idProductoRegistro = ProductoRegistro.id " +
                "SET InventarioProducto.cantidad = InventarioProducto.cantidad - PuntoDeVentaProducto.cantidad " +
                "WHERE ProductoRegistro.id = ?";
        try (Connection conn = dbEnv.getConnection()) {
            try (PreparedStatement statement = conn.prepareStatement(query);
                 PreparedStatement statementMoveFromInventario = conn.prepareStatement(queryMoveFromInventario)) {
                conn.setAutoCommit(false);
                statement.setInt(1, 1 + DatabaseUtil.getLastInsertId(statement, "PuntoDeVentaProducto"));
                statement.setObject(2, stockId);
                statement.setLong(3, productoPuntoDeVenta.getIdProductoRegistro());
                statement.setInt(4, productoPuntoDeVenta.getCantidad());
                statement.executeUpdate();

                statementMoveFromInventario.setLong(1, productoPuntoDeVenta.getIdProductoRegistro());
                statementMoveFromInventario.executeUpdate();


                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
