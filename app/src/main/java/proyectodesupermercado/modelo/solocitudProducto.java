/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

/**
 *
 * @author IA
 */

public class solocitudProducto extends Productos {
    
    private double  pricePurchasePerUnit;
    
    
     public solocitudProducto(long id, String description, double precePerUnit, int quantity)
     {
        super(id, description, precePerUnit, quantity);
    }
     
     public double getTotalPricePurchase()
     {
         
         
        return this.pricePurchasePerUnit;
 
         
     }
    
    
    
    
}
