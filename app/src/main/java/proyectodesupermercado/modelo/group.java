/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

/**
 *
 * @author IA
 */
import java.util.ArrayList;

public class group <T> {
    private long id;
    private ArrayList<T> productos ;
    
    public group(long id) {
        this.id = id;
        this.productos = new ArrayList<>();
    }
    
}
