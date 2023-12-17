package proyectodesupermercado.Vista.utils;

import proyectodesupermercado.Vista.TableUtils;
import proyectodesupermercado.Vista.interfaces.ControlManejoSolicitudes;
import proyectodesupermercado.lib.swingadditions.OnDoubleClick;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.SolicitudCompra;
import proyectodesupermercado.modelo.SolicitudCompraProducto;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.util.concurrent.atomic.AtomicReference;

public class PopupSolicitudesOnDoubleClick {
    public static MouseAdapter create(Component parent,
                                      JTable table,
                                      AtomicReference<ObjectTableModel<SolicitudCompra>> mainModel,
                                      ControlManejoSolicitudes accionesManejoSolicitudes) {
        return new OnDoubleClick(
                s -> {
                    int index = TableUtils.getSelectedIndex(table, "ERROR AL SELECCIONAR");
                    if (index == -1) {
                        return;
                    }
                    JPanel panel = new JPanel();
                    panel.add(new JScrollPane(
                            new UnmutableTable(
                                    new ObjectTableModel<>(
                                            SolicitudCompraProducto.class,
                                            accionesManejoSolicitudes.getProductsOf(mainModel.get().getRow(index))
                                    )
                            )
                    ));
                    JOptionPane.showMessageDialog(parent, panel);
                }
        );
    }
}
