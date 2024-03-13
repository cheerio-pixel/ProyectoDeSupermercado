package proyectodesupermercado.lib.databaseUtils;

import static io.github.pixee.security.ObjectInputFilters.createSafeObjectInputStream;
import proyectodesupermercado.lib.databaseUtils.annotations.Column;
import proyectodesupermercado.lib.databaseUtils.annotations.Id;
import proyectodesupermercado.lib.databaseUtils.annotations.ManyToOne;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;
import proyectodesupermercado.lib.databaseUtils.exceptions.AnnotationIdNotFoundException;
import proyectodesupermercado.lib.databaseUtils.exceptions.AnnotationTableNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TableMapper<T> {
    private final Map<String, Field> fieldToTableColumn;
    private final Class<T> clazz;
    private final String tableName;
    public TableMapper(Class<T> clazz) {
        this.clazz = clazz;
        this.fieldToTableColumn = mapFields();

        Table table = clazz.getAnnotation(Table.class);
        if (table == null) {
            throw new AnnotationTableNotFoundException("Class " + clazz.getCanonicalName() + " should have a 'Table' annotation.");
        }
        if (table.name().isEmpty()) {
            tableName = clazz.getSimpleName().toLowerCase();
        } else {
            tableName = table.name();
        }
    }

    public String getTableName() {
        return tableName;
    }

    /**
     * Get Table mapper of one-to-many relations
     * @return
     */
//    public Collection<TableMapper<?>> getManyToOne() {
////        fieldToTableColumn.entrySet().stream()
////                .
//    }
    public T mapResultSetToObject(ResultSet resultSet) {
        return mapResultSetToObject(resultSet, true);
    }
    /**
     * Takes care of taking the current row of result set and converting it into an object
     * @param resultSet The result set at current row.
     * @param useFullyQuallifiedNames Indicate if we want to use fully quallyfied names for table names
     * @return An object with all the specified attributes filled.
     */
    public T mapResultSetToObject(ResultSet resultSet, boolean useFullyQuallifiedNames) {
        T prototype = getNewObject();
        for (Map.Entry<String, Field> colToFieldEntry : fieldToTableColumn.entrySet()) {
            try {
                // Better alternative is to just get the value of current field in prototype
                // But paranoia
                Object fieldValue = TypeUtils.getDefaultValue(colToFieldEntry.getValue().getType());
                String fullyQuallifiedColumnName;
                if (colToFieldEntry.getValue().isAnnotationPresent(ManyToOne.class)) {
                    if (colToFieldEntry.getValue().getType().isEnum()) {
                        Object id = resultSet.getObject((useFullyQuallifiedNames ? tableName + "." : "") + colToFieldEntry.getKey());
                        for (Object constant : colToFieldEntry.getValue().getType().getEnumConstants()) {
                            if (getIdObject(constant).equals(id)) {
                                fieldValue = constant;
                            }
                        }
                    } else {
                        // Extract values from join result
                        // If id is not specified, then this as good as null
                        fieldValue = new TableMapper<>(colToFieldEntry.getValue().getType())
                                .mapResultSetToObject(resultSet);

                        if (fieldValue != null && getIdObject(fieldValue) == null) {
                            // Can't simply assign null
                            // Ummm, no, this is *always* going to be null
                            // fieldValue = TypeUtils.getDefaultValue(colToFieldEntry.getValue().getType());
                            fieldValue = null;
                        }
                    }
                } else if (isThere(resultSet, (fullyQuallifiedColumnName = (useFullyQuallifiedNames ? tableName + "." : "") + colToFieldEntry.getKey()))) {
                    Column col = colToFieldEntry.getValue().getAnnotation(Column.class);
                    if (col != null && col.isJavaObject()) {
                        Blob blob = resultSet.getBlob(fullyQuallifiedColumnName);
                        fieldValue = createSafeObjectInputStream(blob.getBinaryStream()).readObject();
                    } else {
                        fieldValue = resultSet.getObject(fullyQuallifiedColumnName);
                    }
                }
                // If the current field is an identity and it's value is null
                // Then the object
                // This Fixes OUTER JOINs, let's see how much it stands
                if (colToFieldEntry.getValue().isAnnotationPresent(Id.class)
                        && fieldValue == null) {
                    // Why null and not prototype? No value has null id,
                    // So this will fail with null pointer exception instead
                    // of unspecified behaviour of default id object (0 in case of integrals)
                    return null;
                }
                colToFieldEntry.getValue().set(prototype, fieldValue);
            } catch (SQLException e) {
                System.err.println("An error ocurred while mapping result set to class " + clazz.getCanonicalName());
                System.err.println(e.getMessage());
                System.exit(-1);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return prototype;
    }
    private boolean isThere(ResultSet rs, String column){
        try{
            rs.findColumn(column);
            return true;
        } catch (SQLException sqlex){
            return false;
        }
    }
    private Map<String, Field> mapFields() {
        Map<String, Field> res = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            // While you are at it, set the field accessible.
            field.setAccessible(true);
            res.put(getColumnNameOfField(field), field);
        }
        return res;
    }
    private String getColumnNameOfField(Field field) {
        Column col = field.getAnnotation(Column.class);
        if (col != null && !col.name().isEmpty()) {
            return col.name();
        }
        Id id = field.getAnnotation(Id.class);
        if (id != null && !id.name().isEmpty()) {
            return id.name();
        }

        ManyToOne otherColumn = field.getAnnotation(ManyToOne.class);
        if (otherColumn != null) {
            return otherColumn.joinColumn();
        }
        return field.getName();
    }
    private T getNewObject() {
        T object;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Class "+ clazz.getCanonicalName() + " should have a zero-args constructor.");
        }
        return object;
    }

    /**
     * Having an object that has an annotated field id, return the current value of such object
     * @param object The target object
     * @return The id object
     */
    private Object getIdObject(Object object) {
        Class<?> clazz = object.getClass();
        Field idField = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .map(f -> {
                    f.setAccessible(true);
                    return f;
                })
                .orElseThrow(() -> new AnnotationIdNotFoundException(
                        "The class " + clazz.getCanonicalName() + " should have an 'id' annotation."
                ));
        try {
            return idField.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Wraps the fields and it's corresponding values of an object into a map.
     * The column annotation is used to change the name of the corresponding field.
     * NOTE: Skips fields with the type 'java.util.Collection'
     * @param object The object to take values from
     * @return A map representing the namespace of an object
     */
    public Map<String, Object> getNamespace(T object) {
        Map<String, Object> res = new HashMap<>();
        for (Map.Entry<String, Field> fieldStringEntry : fieldToTableColumn.entrySet()) {
            try {
                Field field = fieldStringEntry.getValue();
                Object fieldValue = field.get(object);
                if (Collection.class.isAssignableFrom(fieldStringEntry.getValue().getType())) {
                    continue;
                }
                if (field.isAnnotationPresent(ManyToOne.class)) {
                    fieldValue = getIdObject(fieldValue);
                }
//                if (fieldStringEntry.getValue().isAnnotationPresent(Id.class)) {
//                    continue;
//                }
                // Accept all nullable object
                res.put(getColumnNameOfField(fieldStringEntry.getValue()), fieldValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return res;
    }
}
