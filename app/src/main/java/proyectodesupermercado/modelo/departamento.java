/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

/**
 *
 * @author IA
 */
public class departamento extends group {
    private String nombre;
    
     public departamento(long id, String nombre) {
        super(id);
        this.nombre = nombre;
    }
    
}