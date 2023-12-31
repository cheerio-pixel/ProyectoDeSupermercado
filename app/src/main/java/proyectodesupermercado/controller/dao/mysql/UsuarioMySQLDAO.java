package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.ConditionsBuilder;
import proyectodesupermercado.controller.authentication.HashPasswordFactory;
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
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UsuarioMySQLDAO implements UsuarioDAO {
    private final TableMapper<Usuario> mapper;
    private final DatabaseEnvironment dbEnv;

    public UsuarioMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
        mapper = new TableMapper<>(Usuario.class);
    }

    public static void initTestUsers(UsuarioMySQLDAO usuarioDAO) {
        Usuario user = new Usuario(
                "TestPOV",
                new HashPasswordFactory
                        .PBKDF2HashPasswordFactory()
                        .createPassword("test".toCharArray()),
                Rol.PuntoDeVenta
        );
        usuarioDAO.insert(user);
        usuarioDAO.insert(new Usuario(
                "TestGe",
                new HashPasswordFactory
                        .PBKDF2HashPasswordFactory()
                        .createPassword("test".toCharArray()),
                Rol.Gerente
        ));
        usuarioDAO.insert(new Usuario(
                "TestInv",
                new HashPasswordFactory
                        .PBKDF2HashPasswordFactory()
                        .createPassword("test".toCharArray()),
                Rol.Inventario
        ));
        usuarioDAO.insert(new Usuario(
                "TestIT",
                new HashPasswordFactory
                        .PBKDF2HashPasswordFactory()
                        .createPassword("test".toCharArray()),
                Rol.AdminIT
        ));
        usuarioDAO.update(0, user);
        if (usuarioDAO.isUsernameInStorage(user.getNombre())) {
            usuarioDAO.listByName(user.getNombre())
                    .map(u -> u.checkPassword("test".toCharArray()))
                    .ifPresent(System.out::println);
        } else {
            usuarioDAO.insert(user);
        }
    }

    @Override
    public Set<Usuario> listAll() {
        String query = "SELECT * FROM Usuario INNER JOIN Rol ON Rol.id = Usuario.rolId";
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
        ConditionsBuilder builder = new ConditionsBuilder(
                "SELECT * FROM Usuario INNER JOIN Rol ON Usuario.rolId = Rol.id "
        ).addConditionIf(rol != null,
                "Usuario.rolId = ?",
                rol != null ? rol.getId() : null
        ).addConditionIf((nombre != null) && !nombre.isBlank(),
                "SOUNDEX(Usuario.nombre) LIKE CONCAT(SOUNDEX(?), '%') " +
                        "OR Usuario.nombre LIKE CONCAT('%', ?, '%')",
                nombre, nombre
        ).addConditionIf(isLogged != null,
                "Usuario.sesionActiva = ?",
                isLogged
        ).setAtLast(" LIMIT 50");

        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     builder.commitConditions(" AND "))) {
            int i = 1;
            for (Object param : builder.getParams()) {
                statement.setObject(i, param);
                i++;
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
