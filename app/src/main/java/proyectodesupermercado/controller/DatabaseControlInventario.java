package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlInventario;
import proyectodesupermercado.controller.dao.InventarioProductoDAO;
import proyectodesupermercado.modelo.InventarioProducto;

import java.util.Optional;

public class DatabaseControlInventario extends DatabaseInventarioBuscador implements ControlInventario {

    public DatabaseControlInventario(InventarioProductoDAO inventarioProductoDAO) {
        super(inventarioProductoDAO);
    }


    @Override
    public Optional<String> editProduct(InventarioProducto producto) {
        // TODO: Lacks way of propagating errors
        inventarioProductoDAO.update(producto.getId(), producto);
        return Optional.empty();
    }

    @Override
    public Optional<String> descontinuaProducto(InventarioProducto producto) {
        producto.setCantidad(-1);
        inventarioProductoDAO.update(producto.getId(), producto);
        return Optional.empty();
    }
}
