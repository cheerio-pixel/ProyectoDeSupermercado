/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.databaseUtils.annotations.Column;
import proyectodesupermercado.lib.databaseUtils.annotations.Id;
import proyectodesupermercado.lib.databaseUtils.annotations.ManyToOne;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;

/**
 * @author cheerio-pixel
 */
@Table
public class ProductoRegistro {
    @Id
    private long id;
    private final String nombre;
    @ManyToOne(joinColumn = "idSuplidor")
    private final Suplidor suplidor;
    @Column(name = "precioPorUnidad")
    private final double precioDeVenta;
    private boolean enInventario;
    public ProductoRegistro(String nombre, Suplidor suplidor, double precioDeVenta) {
        this.nombre = nombre;
        this.suplidor = suplidor;
        this.precioDeVenta = precioDeVenta;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Suplidor getSuplidor() {
        return suplidor;
    }

    public long getSuplidorId() {
        if (suplidor == null) {
            return -1;
        }
        return suplidor.getId();
    }

    public double getPrecioDeVenta() {
        return precioDeVenta;
    }
}
