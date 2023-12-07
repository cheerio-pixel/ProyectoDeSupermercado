package proyectodesupermercado.Vista.utils;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.Vector;

public class UnmutableTable extends JTable {
    public UnmutableTable() {
    }

    public UnmutableTable(TableModel dm) {
        super(dm);
    }

    public UnmutableTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
    }

    public UnmutableTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
    }

    public UnmutableTable(int numRows, int numColumns) {
        super(numRows, numColumns);
    }

    public UnmutableTable(Vector<? extends Vector> rowData, Vector<?> columnNames) {
        super(rowData, columnNames);
    }

    public UnmutableTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
