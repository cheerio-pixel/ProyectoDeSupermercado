package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.SolicitudCompra;

import java.sql.Timestamp;
import java.util.Optional;

public interface ControlHistorialSolicitud {
    ObjectTableModel<SolicitudCompra> searchByFields(Timestamp fechaDeInicio, Timestamp fechaDeFinalizacon, boolean noAceptados);

    ObjectTableModel<SolicitudCompra> refreshInitialModel();
}
