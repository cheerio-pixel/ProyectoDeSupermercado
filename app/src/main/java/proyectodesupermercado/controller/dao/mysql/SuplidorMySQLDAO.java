package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.dao.SuplidorDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.lib.databaseUtils.TableMapper;
import proyectodesupermercado.modelo.Suplidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SuplidorMySQLDAO implements SuplidorDAO {
    private final DatabaseEnvironment dbEnv;
    private final TableMapper<Suplidor> suplidorTableMapper;

    public SuplidorMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
        this.suplidorTableMapper = new TableMapper<>(Suplidor.class);
    }

    @Override
    public Set<Suplidor> listAll() {
        String query = "SELECT * FROM Suplidor LIMIT 50";
        try (Connection conn = dbEnv.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Set<Suplidor> res = new HashSet<>(50);
            while (rs.next()) {
                res.add(suplidorTableMapper.mapResultSetToObject(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Suplidor> listById(Object id) {
        String query = "SELECT * FROM Suplidor WHERE Suplidor.id = ? LIMIT 1";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                return Optional.of(suplidorTableMapper.mapResultSetToObject(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
