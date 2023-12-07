package proyectodesupermercado.controller.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseUtil {
    private DatabaseUtil() {
    }

    public static int getLastInsertId(Statement statement, String tableName) throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT id FROM " + tableName + " ORDER BY id DESC");
        if (rs.next()) {
            return rs.getInt("id");
        }
        return 0;
    }
    
}
