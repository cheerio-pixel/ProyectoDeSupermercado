package proyectodesupermercado.Vista;

import javax.swing.JOptionPane;
import java.awt.Component;

public final class ReportInView {
    private ReportInView() {
    }

    private static void messageDialog(Component parent, String msg, int type) {
        JOptionPane.showMessageDialog(
                parent,
                msg, "",
                type
        );
    }

    public static void error(Component parent, String msg) {
        messageDialog(
                parent, msg,
                JOptionPane.ERROR_MESSAGE
        );
    }

    public static void warning(Component parent, String msg) {
        messageDialog(
                parent, msg,
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static void info(Component parent, String msg) {
        messageDialog(
                parent, msg,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
