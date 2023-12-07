/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.lib.tableModel.ObjectTableModel;
import proyectodesupermercado.modelo.ProductoManejoGanancias;

/**
 *
 * @author DELL
 */
public interface ControlManejoGanancias {
    
    ObjectTableModel<ProductoManejoGanancias> buscar(String producto);
    ObjectTableModel<ProductoManejoGanancias> buscarFecha(String fecha);
    ObjectTableModel<ProductoManejoGanancias> tablaInicial();
}
