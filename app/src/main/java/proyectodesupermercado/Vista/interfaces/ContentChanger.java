package proyectodesupermercado.Vista.interfaces;

import javax.swing.JPanel;
import java.awt.event.ActionListener;

public interface ContentChanger {
    void setContent(JPanel panel, String goBackText, ActionListener goBackAction);
}
