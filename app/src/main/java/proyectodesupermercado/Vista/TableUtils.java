package proyectodesupermercado.Vista;

import javax.swing.JTable;

public final class TableUtils {
    private TableUtils() {
    }

    public static int getSelectedIndex(JTable table, String nonSelectedMsg) {
        int index = table.convertColumnIndexToModel(table.getSelectedRow());
        if (index == -1) {
            ReportInView.warning(table, nonSelectedMsg);
        }
        return index;
    }
}
