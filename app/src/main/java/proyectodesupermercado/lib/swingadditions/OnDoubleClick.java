package proyectodesupermercado.lib.swingadditions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

/**
 * Creates a mouse adapter that triggers each time there is double click
 * The consumer function should interpret that it's dealing with the source of the click event.
 */
public class OnDoubleClick extends MouseAdapter {
    private final Consumer<Object> doubleClickAction;

    public OnDoubleClick(Consumer<Object> doubleClickAction) {
        this.doubleClickAction = doubleClickAction;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() > 1) {
            doubleClickAction.accept(e.getSource());
        }
    }
}
