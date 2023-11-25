package proyectodesupermercado.lib.databaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDAO<T> extends DatabaseDAO<T> {


    public MySQLDAO(Class<T> clazz, DatabaseEnvironment dbEnv, TableMapper<T> tableMapper) {
        super(clazz, dbEnv, tableMapper);
    }

    @Override
    protected Object getLastInsertedId(Statement statement) throws SQLException {
        try {
            ResultSet reSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
            if (reSet.first()) {
                return reSet.getObject(1);
            }
            return -1;
        } catch (SQLException e) {
            System.err.println("An error occurred while retriving last inserted id");
            throw new RuntimeException(e);
        }
    }
}
