package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.controller.dao.DatabaseUtil;
import proyectodesupermercado.controller.dao.UsuarioDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.lib.databaseUtils.TableMapper;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class UsuarioMySQLDAO implements UsuarioDAO {
    private final TableMapper<Usuario> mapper;
    private final DatabaseEnvironment dbEnv;

    public UsuarioMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
        mapper = new TableMapper<>(Usuario.class);
    }

    @Override
    public Set<Usuario> listAll() {
        String query = "SELECT * FROM Usuario INNER JOIN Rol ON Rol.id = Usuario.rolId ORDER BY Usuario.nombre";
        try (Connection conn = dbEnv.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Set<Usuario> res = new HashSet<>();
            while (rs.next()) {
                res.add(mapper.mapResultSetToObject(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Usuario> listById(Object id) {
        String query = "SELECT * FROM Usuario INNER JOIN Rol ON Rol.id = Usuario.rolId WHERE id = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapper.mapResultSetToObject(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Usuario object) {
        String query = "INSERT INTO Usuario(id, nombre, contraseña, rolId) VALUES(?, ?, ?, ?)";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)
        ) {
            // TODO: This suffers from concurrency problems
            object.setId(DatabaseUtil.getLastInsertId(statement, "Usuario") + 1);
            statement.setLong(1, object.getId());
            statement.setString(2, object.getNombre());
            statement.setObject(3, object.getPassword());
            statement.setInt(4, object.getRol().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Object id, Usuario object) {
        String query = "UPDATE Usuario SET nombre = ?, contraseña = ?, rolId = ? WHERE id = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setString(1, object.getNombre());
            statement.setObject(2, object.getPassword());
            statement.setInt(3, object.getRol().getId());
            statement.setObject(4, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Can be used to check if user already exists
    @Override
    public Optional<Usuario> listByName(String name) {
        String query = "SELECT * FROM Usuario INNER JOIN Rol ON Usuario.rolId = Rol.id WHERE Usuario.nombre = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapper.mapResultSetToObject(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuario> listByFields(Rol rol,
                                      String nombre,
                                      Boolean isLogged) {
        String query = "SELECT * FROM Usuario INNER JOIN Rol ON Usuario.rolId = Rol.id ";
        Map<String, Object> conditions = new LinkedHashMap<>();
        if (rol != null) {
            conditions.put("Usuario.rolId = ?", rol.getId());
        }
        if (nombre != null) {
            conditions.put("SOUNDEX(Usuario.nombre) LIKE CONCAT(SOUNDEX(?), '%')", nombre);
        }
        if (isLogged != null) {
            conditions.put("Usuario.sesionActiva = ?", isLogged);
        }
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions.keySet());
        }
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query +
                     ((nombre != null) ? " OR Usuario.nombre LIKE CONCAT('%', ?, '%')" : "")
                     + " ORDER BY Usuario.nombre LIMIT 50")) {
            Collection<Object> values = conditions.values();
            int i = 1;
            for (Object value : conditions.values()) {
                statement.setObject(i, value);
                i++;
            }
            if (nombre != null) {
                statement.setObject(i, nombre);
            }
            ResultSet rs = statement.executeQuery();
            List<Usuario> res = new ArrayList<>(50);
            while (rs.next()) {
                res.add(mapper.mapResultSetToObject(rs));
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logUser(Usuario usuario) {
        String query = "UPDATE Usuario SET sesionActiva = ? WHERE id = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setBoolean(1, usuario.isAlreadyLogged());
            statement.setLong(2, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isUsernameInStorage(String username) {
        return listByName(username).isPresent();
    }

    @Override
    public boolean isUsernameInStorage(String username, Object exceptId) {
        String query = "SELECT id FROM Usuario WHERE nombre = ? AND id <> ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setObject(2, exceptId);
            return statement.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Object id, Usuario object) {
        String query = "DELETE FROM Usuario WHERE Usuario.id = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
