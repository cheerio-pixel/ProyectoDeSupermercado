package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.crud.Listable;
import proyectodesupermercado.controller.crud.Updatable;
import proyectodesupermercado.modelo.InventarioProducto;

import java.util.Set;

public interface InventarioProductoDAO extends
        Listable<InventarioProducto>,
        Updatable<InventarioProducto> {
    Set<InventarioProducto> searchByName(String name);
}
