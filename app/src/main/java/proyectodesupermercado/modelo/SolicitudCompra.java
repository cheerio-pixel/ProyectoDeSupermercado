package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.tableModel.TableModelColumn;

import java.sql.Timestamp;
import java.util.List;

public class SolicitudCompra {
    @TableModelColumn(name = "ID", index = 1)
    private long id;
    @TableModelColumn(name = "Creacion", index = 2)
    private final Timestamp fecha;
    @TableModelColumn(name = "Acepatdo", index = 3)
    private boolean aceptado;
    private List<SolicitudCompraProducto> productos;

    public SolicitudCompra(Timestamp fecha, List<SolicitudCompraProducto> productos) {
        this.fecha = fecha;
        this.productos = productos;
    }

    public SolicitudCompra(long id, Timestamp fecha, boolean aceptado) {
        this.id = id;
        this.fecha = fecha;
        this.aceptado = aceptado;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public List<SolicitudCompraProducto> getProductos() {
        return productos;
    }

    public long getId() {
        return id;
    }
}
