package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.tableModel.TableModelColumn;

public class ProductoPuntoDeVenta {
    private long id;
    @TableModelColumn(name = "Nombre", index = 1)
    private String nombre;
    @TableModelColumn(name = "Precio por unidad", index = 2)
    private double precioPorUnidad;
    @TableModelColumn(name = "Cantidad", index = 3)
    private int cantidad;
    @TableModelColumn(name = "Impuestos aplicados", index = 4)
    private double impuestos;
    @TableModelColumn(name = "Total", index = 5)
    private double total;
}
