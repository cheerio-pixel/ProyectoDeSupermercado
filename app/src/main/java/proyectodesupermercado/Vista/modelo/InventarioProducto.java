package proyectodesupermercado.Vista.modelo;


import proyectodesupermercado.lib.tableModel.Column;

/**
 *
 */
public class InventarioProducto {
    private final long id;
    @Column(name = "Nombre", index = 1)
    private final String nombre;
    @Column(name = "Cantidad", index = 2)
    private int cantidad;

    public InventarioProducto(long id, String nombre, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
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
