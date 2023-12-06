package proyectodesupermercado.controller;

import proyectodesupermercado.Vista.interfaces.ControlHistorialSolicitud;
import proyectodesupermercado.controller.dao.SolicitudesDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.SolicitudCompra;

import java.sql.Timestamp;
import java.util.ArrayList;

public class DatabaseControlHistorialSolicitudes implements ControlHistorialSolicitud {
    private SolicitudesDAO solicitudesDAO;

    public DatabaseControlHistorialSolicitudes(SolicitudesDAO solicitudesDAO) {
        this.solicitudesDAO = solicitudesDAO;
    }

    @Override
    public ObjectTableModel<SolicitudCompra> searchByFields(Timestamp fechaDeInicio, Timestamp fechaDeFinalizacon, boolean noAceptados) {
        return new ObjectTableModel<>(
                SolicitudCompra.class,
                solicitudesDAO.searchByFields(
                        fechaDeInicio, fechaDeFinalizacon, noAceptados
                )
        );
    }

    @Override
    public ObjectTableModel<SolicitudCompra> refreshInitialModel() {
        return new ObjectTableModel<>(
                SolicitudCompra.class,
                new ArrayList<>(solicitudesDAO.listAll())
        );
    }
}
