package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.InventarioProducto;

public interface BuscableEnInventario {
    ObjectTableModel<InventarioProducto> search(String prompt);

    ObjectTableModel<InventarioProducto> refreshInitialModel();
}
