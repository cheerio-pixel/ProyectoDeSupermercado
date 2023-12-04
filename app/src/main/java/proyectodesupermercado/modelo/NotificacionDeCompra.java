package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.tableModel.TableModelColumn;

import java.sql.Timestamp;

public class NotificacionDeCompra {
    private int userId;
    @TableModelColumn(name = "Fecha", index = 1)
    private final Timestamp fecha;
    @TableModelColumn(name = "Nombre", index = 2)
    private final String nombreProducto;
    @TableModelColumn(name = "Cantidad", index = 3)
    private final int cantidad;

    public NotificacionDeCompra(Timestamp fecha, String nombreProducto, int cantidad) {
        this.fecha = fecha;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
