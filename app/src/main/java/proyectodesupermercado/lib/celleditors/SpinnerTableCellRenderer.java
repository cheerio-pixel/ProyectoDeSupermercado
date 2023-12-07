package proyectodesupermercado.lib.celleditors;

import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;

public class SpinnerTableCellRenderer implements TableCellRenderer {
    private JSpinner spinner;

    public SpinnerTableCellRenderer(JSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object o, boolean b, boolean b1, int i, int i1) {
        spinner.setValue(o);
        return spinner;
    }
}
