package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.InventarioProducto;

import java.util.Optional;

public interface ControlInventario extends BuscableEnInventario {

    Optional<String> editProduct(InventarioProducto producto);

    Optional<String> descontinuaProducto(InventarioProducto producto);
}
