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

public class Group<T> {
    private final long id;
    private final ArrayList<T> productos ;

    public Group(long id) {
        this.id = id;
        this.productos = new ArrayList<>();
    }
    
}
