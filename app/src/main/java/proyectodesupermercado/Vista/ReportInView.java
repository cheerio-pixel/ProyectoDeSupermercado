package proyectodesupermercado.Vista;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public final class ReportInView {
    private ReportInView() {
    }

    private static void messageDialog(JComponent parent, String msg, int type) {
        JOptionPane.showMessageDialog(
                parent,
                msg, "",
                type
        );
    }

    public static void error(JComponent parent, String msg) {
        messageDialog(
                parent, msg,
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void warning(JComponent parent, String msg) {
        messageDialog(
                parent, msg,
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static void info(JComponent parent, String msg) {
        messageDialog(
                parent, msg,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
