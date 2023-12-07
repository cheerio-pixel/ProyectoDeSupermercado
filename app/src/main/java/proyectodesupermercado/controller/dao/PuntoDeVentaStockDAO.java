package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.crud.Deleteable;
import proyectodesupermercado.controller.crud.Insertable;
import proyectodesupermercado.controller.crud.ListableFromUser;
import proyectodesupermercado.modelo.PuntoDeVentaProducto;
import proyectodesupermercado.modelo.PuntoDeVentaStock;

import java.util.List;
import java.util.Optional;

public interface PuntoDeVentaStockDAO
        extends ListableFromUser<PuntoDeVentaStock>,
        Deleteable<PuntoDeVentaStock>,
        Insertable<PuntoDeVentaStock> {
    PuntoDeVentaStock retriveLastInsertedStock();

    List<PuntoDeVentaProducto> listAllOf(PuntoDeVentaStock stock);

    Optional<String> confirmStock(PuntoDeVentaStock stock, List<PuntoDeVentaProducto> productos);

    void removeProduct(PuntoDeVentaProducto producto);

    void insertProducto(Object stockId, PuntoDeVentaProducto productoPuntoDeVenta);
}
