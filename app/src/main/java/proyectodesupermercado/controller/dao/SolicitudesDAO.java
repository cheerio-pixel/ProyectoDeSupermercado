package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.crud.Insertable;
import proyectodesupermercado.controller.crud.Listable;
import proyectodesupermercado.modelo.SolicitudCompra;
import proyectodesupermercado.modelo.SolicitudCompraProducto;

import java.sql.Timestamp;
import java.util.List;
import proyectodesupermercado.controller.crud.Updatable;

public interface SolicitudesDAO extends
        Listable<SolicitudCompra>,
        Insertable<SolicitudCompra>,
        Updatable<SolicitudCompra>{
    List<SolicitudCompra> searchByFields(Timestamp fechaDeInicio, Timestamp fechaDeFinalizacon, boolean noAceptados);

    List<SolicitudCompraProducto> listAllProductsOf(Object id);
    
    
}
