package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.tableModel.TableModelColumn;

import java.util.Objects;

public class SolicitudCompraProducto {
    private long id;
    private long idProductoRegistro;
    @TableModelColumn(name = "Producto", index = 1)
    private String nombre;
    @TableModelColumn(name = "Cantidad", index = 2, isEditable = true)
    private int cantidad;
    @TableModelColumn(name = "Precio de compra", index = 3, isEditable = true)
    private double precioDeCompra;

    public SolicitudCompraProducto(long idProductoRegistro, String nombre, int cantidad, double precioDeCompra) {
        this.idProductoRegistro = idProductoRegistro;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioDeCompra = precioDeCompra;
    }

    public static SolicitudCompraProducto getDefault(long idProductoRegistro, String nombre) {
        return new SolicitudCompraProducto(idProductoRegistro, nombre, 1, 1.99);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SolicitudCompraProducto that = (SolicitudCompraProducto) object;
        return idProductoRegistro == that.idProductoRegistro;
    }
   
    public long getIdProductoRegistro() {
        return idProductoRegistro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioDeCompra() {
        return precioDeCompra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProductoRegistro);
    }
}
