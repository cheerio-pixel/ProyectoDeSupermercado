package proyectodesupermercado.Vista.roles;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

public class GeneralGroup extends JPanel {
    private final JTabbedPane group;

    /**
     * Defaults to BordeLayout and left placement of tabs
     */
    public GeneralGroup() {
        this(JTabbedPane.LEFT);
    }

    public GeneralGroup(int tabPlacement) {
        setLayout(new BorderLayout());
        group = new JTabbedPane();
        group.setTabPlacement(tabPlacement);
        add(group);
    }

    public void addTab(String title, JComponent component) {
        group.addTab(title, component);
    }
}
