package proyectodesupermercado.controller.authentication;

import proyectodesupermercado.lib.databaseUtils.annotations.Id;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;

@Table
public enum Rol {
    Inventario(2, "IV"),
    Gerente(0, "GE"),
    AdminIT(1, "IT"),
    PuntoDeVenta(3, "PV"),
    Contabilidad(4, "CC");
    @Id
    int id;
    String nombre;

    Rol(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
