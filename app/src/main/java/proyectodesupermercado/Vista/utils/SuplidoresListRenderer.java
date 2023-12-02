package proyectodesupermercado.Vista.utils;

import proyectodesupermercado.modelo.Suplidor;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.Component;

public class SuplidoresListRenderer extends DefaultListCellRenderer {

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Object res = value;
        if (value != null) {
            res = ((Suplidor) value).getNombre();
        }
        return super.getListCellRendererComponent(list, res, index, isSelected, cellHasFocus);
    }
}
