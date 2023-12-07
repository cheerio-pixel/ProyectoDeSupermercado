/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.tableModel.TableModelColumn;

/**
 *
 * @author DELL
 */
public class ProductoManejoGanancias{
    public int id;
    @TableModelColumn(name = "Proctudo", index = 1)
    public String producto;
    @TableModelColumn(name = "Ventas", index = 2)
    public int ventas;
    @TableModelColumn(name = "Ganancias", index = 3)
    public int ganancias;
    @TableModelColumn(name = "Fecha", index = 4)
    public String fecha;
    
    public ProductoManejoGanancias(String Producto, int Ventas, int Ganancias, String Fecha){
        this.producto = Producto;
        this.ventas = Ventas;
        this.ganancias = Ganancias;
        this.fecha = Fecha;
    }
    
    public String getProducto() {
        return this.producto;
    }

    public int getVentas() {
        return ventas;
    }

    public int getGanancias() {
        return ganancias;
    }
    
    public String getFecha() {
        return fecha; 
    }
}
