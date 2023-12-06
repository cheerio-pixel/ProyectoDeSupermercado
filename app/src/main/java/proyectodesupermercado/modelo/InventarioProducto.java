package proyectodesupermercado.modelo;


import proyectodesupermercado.lib.tableModel.TableModelColumn;

/**
 *
 */
public class InventarioProducto {
    private final long id;
    private final long idProductoRegistro;
    @TableModelColumn(name = "Nombre", index = 1)
    private final String nombre;
    @TableModelColumn(name = "Cantidad", index = 2)
    private int cantidad;

    public InventarioProducto(long id, long idProductoRegistro, String nombre, int cantidad) {
        this.id = id;
        this.idProductoRegistro = idProductoRegistro;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public long getIdProductoRegistro() {
        return idProductoRegistro;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
