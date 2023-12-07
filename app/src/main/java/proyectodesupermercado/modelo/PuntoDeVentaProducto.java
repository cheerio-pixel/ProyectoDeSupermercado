package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.tableModel.TableModelColumn;

public class PuntoDeVentaProducto {
    private long id;
    private long idProductoRegistro;
    @TableModelColumn(name = "Cantidad", index = 2)
    private int cantidad;
    @TableModelColumn(name = "Producto", index = 1)
    private String nombre;
    @TableModelColumn(name = "Impuestos", index = 3)
    private double taxes;
    @TableModelColumn(name = "Precio por unidad", index = 4)
    private double precioPorUnidad;
    @TableModelColumn(name = "Total", index = 5)
    private double total;

    public PuntoDeVentaProducto() {
    }

    public PuntoDeVentaProducto(long idProductoRegistro, int cantidad) {
        this.idProductoRegistro = idProductoRegistro;
        this.cantidad = cantidad;
    }

    public PuntoDeVentaProducto(long id, long idProductoRegistro, int cantidad, String nombre, double precioPorUnidad) {
        this.id = id;
        this.idProductoRegistro = idProductoRegistro;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precioPorUnidad = precioPorUnidad;
    }

    public long getId() {
        return id;
    }

    public long getIdProductoRegistro() {
        return idProductoRegistro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public double getPrecioPorUnidad() {
        return precioPorUnidad;
    }
}
