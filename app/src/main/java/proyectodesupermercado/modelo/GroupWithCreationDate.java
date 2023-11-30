/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.modelo;

/**
 *
 * @author IA
 */
import java.sql.Timestamp;

public class GroupWithCreationDate<T> extends Group<T> {
    
    private final Timestamp fechaDeCreacion = new Timestamp(System.currentTimeMillis());

    public GroupWithCreationDate(long id) {
        super(id);
    }
}
