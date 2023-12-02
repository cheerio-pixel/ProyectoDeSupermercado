package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.InventarioProducto;

import java.util.Optional;

public interface ControlInventario {
    ObjectTableModel<InventarioProducto> search(String prompt);

    Optional<String> editProduct(InventarioProducto producto);

    ObjectTableModel<InventarioProducto> refreshInitialModel();
}
