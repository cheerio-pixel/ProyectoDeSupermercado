/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.databaseUtils.annotations.Id;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;

import java.util.List;

/**
 * @author cheerio-pixel
 */
@Table
public class Suplidor {
    @Id
    private long id;
    private String nombre;
    private String direccion;
    private List<String> telefonos;

    public String getNombre() {
        return nombre;
    }

    public long getId() {
        return id;
    }
}
