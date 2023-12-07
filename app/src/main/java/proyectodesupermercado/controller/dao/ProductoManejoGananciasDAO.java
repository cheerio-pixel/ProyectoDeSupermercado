/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyectodesupermercado.controller.dao;

import java.util.List;
import java.util.Set;
import proyectodesupermercado.controller.crud.Listable;
import proyectodesupermercado.modelo.ProductoManejoGanancias;
/**
 *
 * @author DELL
 */
public interface ProductoManejoGananciasDAO extends Listable<ProductoManejoGanancias>{
    List<ProductoManejoGanancias> searchByFecha(String fecha);
    List<ProductoManejoGanancias> searchByFields(String producto);
    @Override
    Set<ProductoManejoGanancias> listAll();
}