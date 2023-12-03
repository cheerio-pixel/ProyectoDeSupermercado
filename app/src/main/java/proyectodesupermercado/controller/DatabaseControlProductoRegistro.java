package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlProductoRegistro;
import proyectodesupermercado.controller.dao.ProductoRegistroDAO;
import proyectodesupermercado.controller.dao.SuplidorDAO;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.ProductoRegistro;
import proyectodesupermercado.modelo.Suplidor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DatabaseControlProductoRegistro implements ControlProductoRegistro {

    private final ProductoRegistroDAO productoRegistroDAO;
    private final SuplidorDAO suplidorDAO;

    public DatabaseControlProductoRegistro(ProductoRegistroDAO productoRegistroDAO,
                                           SuplidorDAO suplidorDAO) {
        this.productoRegistroDAO = productoRegistroDAO;
        this.suplidorDAO = suplidorDAO;
    }

    @Override
    public ObjectTableModel<ProductoRegistro> refresh() {
        return new ObjectTableModel<>(
                ProductoRegistro.class,
                productoRegistroDAO.listAll()
                        .stream()
                        .sorted(Comparator.comparing(ProductoRegistro::getNombre))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<Suplidor> supplyAllSuplidores() {
        return suplidorDAO.listAll().stream()
                .sorted(Comparator.comparing(Suplidor::getNombre))
                .collect(Collectors.toList());
    }

    @Override
    public ObjectTableModel<ProductoRegistro> search(String nombre, Suplidor suplidor) {
        return new ObjectTableModel<>(
                ProductoRegistro.class,
                productoRegistroDAO.searchByFields(nombre, suplidor)
                        .stream()
                        .sorted(Comparator.comparing(ProductoRegistro::getNombre))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<String> updateProductoRegistro(ProductoRegistro productoRegistro) {
        // TODO: I should be using SQL code states to report back what is the problem in case it's raised
        productoRegistroDAO.update(productoRegistro.getId(), productoRegistro);
        return Optional.empty();
    }

    @Override
    public Optional<String> deleteProductoRegistro(ProductoRegistro productoRegistro) {
        productoRegistroDAO.delete(productoRegistro.getId(), productoRegistro);
        return productoRegistroDAO.popLastError();
    }

    @Override
    public Optional<String> insertProductoRegistro(ProductoRegistro productoRegistro) {
        productoRegistroDAO.insert(productoRegistro);
        return Optional.empty();
    }
}
