package proyectodesupermercado.lib.databaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseEnvironment {
    private String url, user, password;
    public DatabaseEnvironment(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
