package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.PuntoDeVentaProducto;
import proyectodesupermercado.modelo.PuntoDeVentaStock;
import proyectodesupermercado.modelo.Usuario;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.OptionalDouble;

public interface ControlPuntoDeVenta {
    ObjectTableModel<PuntoDeVentaStock> pullPendienteStock(Usuario usuario);

    Optional<String> removeStock(PuntoDeVentaStock puntoDeVentaStock);

    PuntoDeVentaStock createStock(Usuario usuario, Timestamp fechaDeCreacion);

    ObjectTableModel<PuntoDeVentaProducto> pullProductosFrom(PuntoDeVentaStock puntoDeVentaStock);

    Optional<String> confirmStock(PuntoDeVentaStock puntoDeVentaStock);

    Optional<String> removeProduct(PuntoDeVentaProducto puntoDeVentaProducto);

    Optional<String> addProduct(PuntoDeVentaStock puntoDeVentaStock, PuntoDeVentaProducto puntoDeVentaProducto);

    OptionalDouble getTotal(PuntoDeVentaStock stock);

}
