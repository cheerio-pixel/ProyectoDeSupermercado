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
    private String nombre;
    @ManyToOne(joinColumn = "idSuplidor")
    private Suplidor suplidor;
    @Column(name = "precioPorUnidad")
    private double precioDeVenta;

    public ProductoRegistro() {

    }

}
