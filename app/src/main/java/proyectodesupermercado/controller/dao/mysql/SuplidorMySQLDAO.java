package proyectodesupermercado.controller.dao.mysql;

import proyectodesupermercado.controller.dao.DatabaseUtil;
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

    public SuplidorMySQLDAO() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
        public Set<Suplidor> listAll() {
            String query = "SELECT * FROM Suplidor LIMIT 50";
            try (Connection conn = dbEnv.getConnection();
                 Statement statement = conn.createStatement()) {
                ResultSet rs = statement.executeQuery();
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
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(suplidorTableMapper.mapResultSetToObject(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public Optional<Suplidor> newSuplidor(String nombre) {
        String nada ="";
        String query = "INSERT INTO Suplidor (id, nombre, direccion) VALUES\n" +
                       "(?, '?', '?')";
        
        
        try (Connection conn = dbEnv.getConnection();
                
             PreparedStatement statement = conn.prepareStatement(query)) {
             statement.setObject(1,1+DatabaseUtil.getLastInsertId(statement, "Suplidor") );
            statement.setObject(2, nombre);
            statement.setObject(3, nada);
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Optional<Suplidor> SolicDatos(String nombre) {
        String nada ="";
        String query = "SELECT\n" +
          "    PR.nombre AS ProductoNombre,\n" +
          "    PR.precioPorUnidad AS PrecioPorUnidad\n" +
          "FROM\n" +
          "    ProductoRegistro PR\n" +
          "JOIN\n" +
          "    Suplidor S ON PR.idSuplidor = S.id\n" +
          "WHERE\n" +
          "    S.nombre = '?';";
        
        
        try (Connection conn = dbEnv.getConnection();
                
             PreparedStatement statement = conn.prepareStatement(query)) {
             statement.setString(1, nombre);
            statement.executeQuery();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
       public Optional<Suplidor> ActualizarSuplidor(String nombre, String direccion ,String Telefono) {
        String query = "UPDATE Suplidor\n" +
                       "SET direccion = '?', telefono = '?'\n" +
                       "WHERE nombre = '?';";
        
        
        try (Connection conn = dbEnv.getConnection();
                
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, direccion);
            statement.setString(2, Telefono);
             statement.setString(3, nombre);
            statement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
       
        public Optional<Suplidor> Eliminar(String nombre) {
        String nada ="";
        String query = "DELETE FROM Suplidor\n" +
                        "WHERE nombre = '?';";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
             statement.setString(1, nombre);
            statement.executeQuery();
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    
    
    
    
    
    
}
