package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlPuntoDeVenta;
import proyectodesupermercado.Vista.interfaces.TaxApplier;
import proyectodesupermercado.controller.dao.PuntoDeVentaStockDAO;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.PuntoDeVentaProducto;
import proyectodesupermercado.modelo.PuntoDeVentaStock;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DatabaseControlPuntoDeVenta implements ControlPuntoDeVenta {
    private final PuntoDeVentaStockDAO puntoDeVentaStockDAO;
    private final TaxApplier taxApplier;

    public DatabaseControlPuntoDeVenta(PuntoDeVentaStockDAO puntoDeVentaStockDAO, TaxApplier taxApplier) {
        this.puntoDeVentaStockDAO = puntoDeVentaStockDAO;
        this.taxApplier = taxApplier;
    }

    @Override
    public ObjectTableModel<PuntoDeVentaStock> pullPendienteStock(Usuario usuario) {
        return new ObjectTableModel<>(
                PuntoDeVentaStock.class,
                puntoDeVentaStockDAO.pullFromUser(usuario)
        );
    }

    @Override
    public Optional<String> removeStock(PuntoDeVentaStock puntoDeVentaStock) {
        puntoDeVentaStockDAO.delete(puntoDeVentaStock.getId(), puntoDeVentaStock);
        return Optional.empty();
    }

    @Override
    public PuntoDeVentaStock createStock(Usuario usuario, Timestamp fechaDeCreacion) {
        puntoDeVentaStockDAO.insert(
                new PuntoDeVentaStock(
                        usuario.getId(),
                        Timestamp.from(Instant.now())
                )
        );
        return puntoDeVentaStockDAO.retriveLastInsertedStock();
    }

    private Stream<PuntoDeVentaProducto> applyTaxesAndSetTotal(List<PuntoDeVentaProducto> target) {
        return target.stream()
                .peek(prod -> prod.setTaxes(taxApplier.getAddedTax(prod.getPrecioPorUnidad() * prod.getCantidad())))
                .peek(prod -> prod.setTotal(prod.getPrecioPorUnidad() * prod.getCantidad() + prod.getTaxes()));
    }

    @Override
    public ObjectTableModel<PuntoDeVentaProducto> pullProductosFrom(PuntoDeVentaStock puntoDeVentaStock) {
        return new ObjectTableModel<>(
                PuntoDeVentaProducto.class,
                applyTaxesAndSetTotal(puntoDeVentaStockDAO.listAllOf(puntoDeVentaStock))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<String> confirmStock(PuntoDeVentaStock puntoDeVentaStock) {
        return puntoDeVentaStockDAO.confirmStock(puntoDeVentaStock, puntoDeVentaStockDAO.listAllOf(puntoDeVentaStock).stream()
                .peek(prod -> prod.setTaxes(taxApplier.getAddedTax(prod.getPrecioPorUnidad() * prod.getCantidad())))
                .peek(prod -> prod.setTotal(prod.getPrecioPorUnidad() * prod.getCantidad() + prod.getTaxes()))
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<String> removeProduct(PuntoDeVentaProducto puntoDeVentaProducto) {
        puntoDeVentaStockDAO.removeProduct(puntoDeVentaProducto);
        return Optional.empty();
    }

    @Override
    public Optional<String> addProduct(PuntoDeVentaStock puntoDeVentaStock, PuntoDeVentaProducto puntoDeVentaProducto) {
        puntoDeVentaStockDAO.insertProducto(puntoDeVentaStock.getId(), puntoDeVentaProducto);
        return Optional.empty();
    }

    @Override
    public OptionalDouble getTotal(PuntoDeVentaStock stock) {
        return applyTaxesAndSetTotal(puntoDeVentaStockDAO.listAllOf(stock))
                .mapToDouble(PuntoDeVentaProducto::getTotal)
                .reduce(Double::sum);
    }
}
