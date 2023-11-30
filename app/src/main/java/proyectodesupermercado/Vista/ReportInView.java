package proyectodesupermercado.Vista;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public final class ReportInView {
    private ReportInView() {
    }

    public static void error(JComponent parent, String msg) {
        JOptionPane.showMessageDialog(
                parent,
                msg, "",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
