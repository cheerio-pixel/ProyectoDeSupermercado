package proyectodesupermercado.lib.databaseUtils;

import proyectodesupermercado.lib.databaseUtils.annotations.Id;
import proyectodesupermercado.lib.databaseUtils.exceptions.AnnotationIdNotFoundException;

import javax.annotation.
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DatabaseDAO<T> implements DataAccessObject<T> {
    private Class<T> clazz;
    private @Nonnull String tableName;
    private @Nonnull String idName;
    private Field idField;
    private DatabaseEnvironment dbEnv;
    private TableMapper<T> tableMapper;
    public DatabaseDAO(Class<T> clazz, DatabaseEnvironment dbEnv, TableMapper<T> tableMapper) {
        this.clazz = clazz;
        this.dbEnv = dbEnv;
        this.tableMapper = tableMapper;
        tableName = tableMapper.getTableName();


        Id id = null;
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                id = field.getAnnotation(Id.class);
                // Set id field accessible, so that later we can
                // access the attributes of such field
                field.setAccessible(true);
                idField = field;
                break;
            }
        }
        if (id == null) {
            throw new AnnotationIdNotFoundException("Class " + clazz.getCanonicalName() + " should have an 'Id' field declared with 'Id' Annotation.");
        }
        if (id.name().equals("")) {
            idName = idField.getName().toLowerCase();
        } else {
            idName = id.name();
        }
    }

    /**
     * Allow null by default
     * @param object
     */
    public void update(T object) {
        update(object, true);
    }

    @Override
    public void update(T object, boolean allowNull) {
        Map<String, Object> mapFieldNameWithObjectValue = tableMapper.getNamespace(object);

        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (Map.Entry<String, Object> entry : mapFieldNameWithObjectValue.entrySet()) {

            if (entry.getValue() != null || allowNull || !entry.getKey().equals(idName)) {
                columns.add(entry.getKey());
                values.add(entry.getValue());
            }
        }
        if (columns.isEmpty()) {
            return;
        }

        String partialStatement = "UPDATE " +
                tableName +
                " SET " +
                columns.stream()
                        .map(c -> String.format("%s = ?", c))
                        .collect(Collectors.joining(", ")) +
                " WHERE " +
                idName +
                " = ?";
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(partialStatement);
        ) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            statement.setObject(values.size() + 1, getId(object));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(T object) {
        Map<String, Object> mapFieldNameWithObjectValue = tableMapper.getNamespace(object);

        List<String> columns = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        Id id = idField.getAnnotation(Id.class);
        for (Map.Entry<String, Object> entry : mapFieldNameWithObjectValue.entrySet()) {
            switch (id.idStrategy()) {
                case MANUAL: {
                    if (entry.getValue() != null) {
                        columns.add(entry.getKey());
                        values.add(entry.getValue());
                    }
                } break;
                case IDENTITY: {
                    if (entry.getValue() != null && ! entry.getKey().equals(idName)) {
                        columns.add(entry.getKey());
                        values.add(entry.getValue());
                    }
                } break;
            }
        }
        if (columns.isEmpty()) {
            return;
        }
        String partialStatement = "INSERT INTO " + tableName +
                " (" + String.join(", ", columns) + ")" +
                " VALUES " +
                "(" + String.join(", ", "?".repeat(values.size()).split("")) + ")";
        System.out.println(partialStatement);
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(partialStatement);)
        {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }
            statement.executeUpdate();
            setId(object, getLastInsertedId(statement));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected abstract Object getLastInsertedId(Statement statement) throws SQLException;

    @Override
    public void delete(T object) {
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM " + tableName + " WHERE " + idName +  " = ?")) {
            statement.setObject(1, getId(object));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected void setId(T object, Object id) {
        try {
            idField.set(object, id);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected Object getId(T object) {
        try {
            // Weird use but makes sense.
            return idField.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot access 'idField': " + e.getMessage());
        }
    }
    @Override
    public Collection<T> listAll() {
        try (Connection conn = dbEnv.getConnection();
             Statement statement = conn.createStatement()) {
            // TODO: Make join with many-to-one dependencies
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
            Set<T> res = new HashSet<>();
            while (rs.next()) {
                res.add(tableMapper.mapResultSetToObject(rs));
            }
            return res;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return Set.of();
        }
    }

    @Override
    public Optional<T> listById(Object id) {
        try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE " + idName + " = ?")) {
            statement.setObject(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(tableMapper.mapResultSetToObject(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected Class<T> getClazz() {
        return clazz;
    }

    @Nonnull
    protected String getTableName() {
        return tableName;
    }

    @Nonnull
    protected String getIdName() {
        return idName;
    }

    protected Field getIdField() {
        return idField;
    }

    protected DatabaseEnvironment getDbEnv() {
        return dbEnv;
    }

    protected TableMapper<T> getTableMapper() {
        return tableMapper;
    }
}
