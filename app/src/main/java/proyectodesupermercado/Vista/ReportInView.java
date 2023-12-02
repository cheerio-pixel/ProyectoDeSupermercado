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

    /**
     * Show a confirmation dialog
     *
     * @param parent Parent component for dialog
     * @param msg    Message to show to the user, could be a string or panel
     * @return True for YES, false for NO
     */
    public static boolean confirmYesOrNo(Component parent, Object msg) {
        return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                parent, msg, "", JOptionPane.YES_NO_OPTION
        );
    }
}
