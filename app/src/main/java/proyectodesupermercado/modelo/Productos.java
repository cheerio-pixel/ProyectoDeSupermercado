/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

/**
 *
 * @author IA
 */
public class Productos {
    private long id;
    private String description;
    private double precePerUnit;
    private int quantity ;
    
    Productos(long id , String description ,double precePerUnit , int quantity)
    {
        this.id =id ;
        this.description = description;
        this.precePerUnit = precePerUnit;
        this.quantity = quantity;
        
    }
    
    
}
