/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.controller;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import proyectodesupermercado.Vista.interfaces.ControlManejoGanancias;
import proyectodesupermercado.controller.dao.ProductoManejoGananciasDAO;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.InventarioProducto;
import proyectodesupermercado.modelo.ProductoManejoGanancias;

/**
 *
 * @author DELL
 */
public class DBControlManejoGanancias implements ControlManejoGanancias{

    private final ProductoManejoGananciasDAO productoManejoGananciasDAO;
   
    
    public DBControlManejoGanancias(ProductoManejoGananciasDAO productoManejoGananciasDAO) {
        this.productoManejoGananciasDAO = productoManejoGananciasDAO;
    }
    
    @Override
    public ObjectTableModel<ProductoManejoGanancias> buscar(String producto) {
        return new ObjectTableModel<>(
                ProductoManejoGanancias.class,
                productoManejoGananciasDAO.searchByFields(producto)
                        .stream()
                        .sorted(Comparator.comparing(ProductoManejoGanancias::getProducto))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ObjectTableModel<ProductoManejoGanancias> tablaInicial() {
    return new ObjectTableModel<>(
                ProductoManejoGanancias.class,
                productoManejoGananciasDAO.listAll().stream()
                        .sorted(Comparator.comparingInt(ProductoManejoGanancias::getVentas))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ObjectTableModel<ProductoManejoGanancias> buscarFecha(String fecha) {
    return new ObjectTableModel<>(
                ProductoManejoGanancias.class,
                productoManejoGananciasDAO.listAll().stream()
                        .sorted(Comparator.comparing(ProductoManejoGanancias::getProducto))
                        .collect(Collectors.toList())
        );
    }

    
}
