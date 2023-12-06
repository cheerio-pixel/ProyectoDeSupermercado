package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.crud.Insertable;
import proyectodesupermercado.controller.crud.Listable;
import proyectodesupermercado.modelo.SolicitudCompra;

import java.sql.Timestamp;
import java.util.List;

public interface SolicitudesDAO extends Listable<SolicitudCompra>, Insertable<SolicitudCompra> {
    List<SolicitudCompra> searchByFields(Timestamp fechaDeInicio, Timestamp fechaDeFinalizacon, boolean noAceptados);
}
