package proyectodesupermercado.Vista.roles;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
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
        group.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        group.addChangeListener(e -> {
            group.setPreferredSize(
                    group.getSelectedComponent().getPreferredSize()
            );

            JFrame frame = ((JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, group));
            if (frame != null) {
                frame.pack();
            }
        });
        group.setTabPlacement(tabPlacement);
        add(group);
    }

    public void addTab(String title, JComponent component) {
        group.addTab(title, component);
    }
}
