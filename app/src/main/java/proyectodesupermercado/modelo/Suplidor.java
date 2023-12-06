/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

import proyectodesupermercado.lib.databaseUtils.annotations.Id;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;

import java.util.List;
import java.util.Objects;

/**
 * @author cheerio-pixel
 */
@Table
public class Suplidor {
    @Id
    private long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String descripcion;
    private List<ProductoRegistro> productos;

    public Suplidor() {
    }

    public Suplidor(String nombre, String direccion, String telefono, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Suplidor suplidor = (Suplidor) object;
        return id == suplidor.id && Objects.equals(nombre, suplidor.nombre) && Objects.equals(direccion, suplidor.direccion) && Objects.equals(telefono, suplidor.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, direccion, telefono);
    }
}
