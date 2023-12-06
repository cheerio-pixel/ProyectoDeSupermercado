package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.tableModel.TableModelColumn;

import java.sql.Timestamp;

public class NotificacionPendiente {
    // String del formato x10000 <NOMBRE TRUNCADO>
    @TableModelColumn(index = 1)
    private String cantidadMasnombre;
    @TableModelColumn(index = 2)
    private Timestamp fecha;
    @TableModelColumn(index = 3, isEditable = true)
    private boolean seleccionado;
    private long userId;
    private long idProductoRegistro;
    private final String nombreProducto;
    private final int cantidad;

    public NotificacionPendiente(
            long idProductoRegistro,
            long userId,
            String nombreProducto,
            int cantidad,
            Timestamp fecha
    ) {
        this.idProductoRegistro = idProductoRegistro;
        this.userId = userId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.fecha = fecha;

        String text = nombreProducto;
        if (text.length() > 20) {
            text = text.substring(0, 20 - 3) + "...";
        }
        this.cantidadMasnombre = "x" + cantidad + " " + text;
    }

    public long getIdProductoRegistro() {
        return idProductoRegistro;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }
}
