package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.BuscableEnInventario;
import proyectodesupermercado.controller.dao.InventarioProductoDAO;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.InventarioProducto;

import java.util.Comparator;
import java.util.stream.Collectors;

public class DatabaseInventarioBuscador implements BuscableEnInventario {
    protected final InventarioProductoDAO inventarioProductoDAO;

    public DatabaseInventarioBuscador(InventarioProductoDAO inventarioProductoDAO) {
        this.inventarioProductoDAO = inventarioProductoDAO;
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
    public ObjectTableModel<InventarioProducto> refreshInitialModel() {
        return new ObjectTableModel<>(
                InventarioProducto.class,
                inventarioProductoDAO.listAll().stream()
                        .sorted(Comparator.comparingInt(InventarioProducto::getCantidad))
                        .collect(Collectors.toList())
        );
    }
}
