/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

/**
 *
 * @author IA
 */
public class TransactionProduct extends Productos {
    private double taxes;
    private double totalAfterTaxes;

    TransactionProduct(long id, String description, double precePerUnit, int quantity) {
        super(id, description, precePerUnit, quantity);
    }
}

