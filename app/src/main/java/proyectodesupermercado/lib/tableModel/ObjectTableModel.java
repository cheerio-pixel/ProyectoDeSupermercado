package proyectodesupermercado.lib.tableModel;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ObjectTableModel<T> extends AbstractTableModel {
    private final List<T> objectList;
    private final List<Field> columns;
    private static final Map<Class<?>, List<Field>> fieldPool = new HashMap<>();
    public ObjectTableModel(Class<T> classSchema, List<T> objectList) {
        this.columns = pullColumns(classSchema);
        this.objectList = objectList;
    }
    private static List<Field> pullColumns(Class<?> clazz) {
        List<Field> res = fieldPool.get(clazz);
        if (res == null) {
            // Compute columns
            res = Arrays.stream(clazz.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(TableModelColumn.class))
                    .peek(f -> f.setAccessible(true))
                    .sorted(Comparator.comparingInt(f -> f.getAnnotation(TableModelColumn.class).index()))
                    .collect(Collectors.toUnmodifiableList());
            fieldPool.put(clazz, res);
        }
        return res;
    }

    public boolean contains(T row) {
        return objectList.contains(row);
    }

    public List<T> getObjectList() {
        return objectList;
    }

    public boolean addRow(T row) {
        boolean res = objectList.add(row);
        fireTableDataChanged();
        return res;
    }
    public T removeRow(int i) {
        T res = objectList.remove(i);
        fireTableRowsDeleted(i, i);
        return res;
    }
    public T removeRow(T object) {
        int row = objectList.indexOf(object);
        objectList.remove(object);
        fireTableRowsDeleted(row, row);
        return object;
    }
    public T getRow(int rowIndex) {
        return objectList.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return objectList.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int i, int i1) {
        T object = objectList.get(i);
        try {
            return columns.get(i1).get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        T object = objectList.get(rowIndex);
        try {
            columns.get(columnIndex).set(object, aValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getColumnName(int column) {
        String name = columns.get(column).getAnnotation(TableModelColumn.class).name();
        if (name.isEmpty()) {
            return columns.get(column).getName();
        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return TypeUtils.getClass(columns.get(columnIndex).getType());
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columns.get(columnIndex).getAnnotation(TableModelColumn.class).isEditable();
    }
}
