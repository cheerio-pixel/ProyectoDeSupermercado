package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.authentication.Usuario;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.lib.databaseUtils.TableMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UsuarioDAO implements GenericDAO<Usuario> {
    private final TableMapper<Usuario> mapper;
    private final DatabaseEnvironment dbEnv;

    public UsuarioDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
        mapper = new TableMapper<>(Usuario.class);
    }

    @Override
    public Set<Usuario> listAll() {
        String query = "SELECT * FROM Usuario INNER JOIN Rol ON Rol.id = Usuario.rolId";
        try (Connection conn = dbEnv.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Set<Usuario> res = new HashSet<>();
            if (rs.next()) {
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

    /**
     * Change the logging state of the user in the store
     *
     * @param usuario
     */
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

    public boolean userNameExists(String username) {
        return listByName(username).isPresent();
    }
}
