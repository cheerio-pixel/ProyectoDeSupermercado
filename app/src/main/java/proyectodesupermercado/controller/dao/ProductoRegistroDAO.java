package proyectodesupermercado.controller.dao;

import proyectodesupermercado.controller.crud.CRUD;
import proyectodesupermercado.controller.crud.Fallible;
import proyectodesupermercado.modelo.ProductoRegistro;
import proyectodesupermercado.modelo.Suplidor;

import java.util.List;

public interface ProductoRegistroDAO extends CRUD<ProductoRegistro>, Fallible {
    List<ProductoRegistro> searchByFields(String nombre, Suplidor suplidor);
}
