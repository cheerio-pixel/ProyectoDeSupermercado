/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.ProductoRegistro;
import proyectodesupermercado.modelo.Suplidor;

import java.util.Optional;

/**
 * @author cheerio-pixel
 */
public interface ControlProductoRegistro {
    ObjectTableModel<ProductoRegistro> refresh();

    ObjectTableModel<ProductoRegistro> search(String nombre, Suplidor suplidor);

    Optional<String> updateProductoRegistro(ProductoRegistro productoRegistro);

    Optional<String> deleteProductoRegistro(ProductoRegistro productoRegistro);

    Optional<String> insertProductoRegistro(ProductoRegistro productoRegistro);
}
