package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlInventario;
import proyectodesupermercado.Vista.modelo.InventarioProducto;
import proyectodesupermercado.controller.dao.InventarioProductoDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseControlInventario implements ControlInventario {
    private final InventarioProductoDAO inventarioProductoDAO;

    public DatabaseControlInventario(DatabaseEnvironment dbEnv) {
        this.inventarioProductoDAO = new InventarioProductoDAO(dbEnv);
    }

    @Override
    public ObjectTableModel<InventarioProducto> search(String prompt) {
        return new ObjectTableModel<>(
                InventarioProducto.class,
                inventarioProductoDAO.searchByName(prompt).stream()
                        .sorted(Comparator.comparingInt(InventarioProducto::getCantidad))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<String> editProduct(InventarioProducto producto) {
        // TODO: Lacks way of propagating errors
        inventarioProductoDAO.update(producto.getId(), producto);
        return Optional.empty();
    }

    @Override
    public ObjectTableModel<InventarioProducto> refreshInitialModel() {
        return new ObjectTableModel<>(
                InventarioProducto.class,
                inventarioProductoDAO.listAll().stream()
                        .sorted(Comparator.comparingInt(InventarioProducto::getCantidad))
                        .collect(Collectors.toList())
        );
    }
}
