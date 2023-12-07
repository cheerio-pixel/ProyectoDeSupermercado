/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectodesupermercado.controller.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import proyectodesupermercado.controller.dao.ProductoManejoGananciasDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;
import proyectodesupermercado.modelo.ProductoManejoGanancias;
import proyectodesupermercado.controller.ConditionsBuilder;
import proyectodesupermercado.lib.databaseUtils.TableMapper;

/**
 *
 * @author DELL
 */
public class ManejoGananciasMySQLDAO implements ProductoManejoGananciasDAO {
    
    private final DatabaseEnvironment dbEnv;
    
    public ManejoGananciasMySQLDAO(DatabaseEnvironment dbEnv) {
        this.dbEnv = dbEnv;
}

    @Override
    public List<ProductoManejoGanancias> searchByFields(String producto) {
         ConditionsBuilder builder = new ConditionsBuilder(
               "SELECT ProductoRegistro.nombre, TransaccionProducto.cantidad, TransaccionProducto.cantidad * TransaccionProducto.precioPorUnidad, Transaccion.fechaDeCreacion " +
                        "FROM TransaccionProducto " +
                        "INNER JOIN ProductoRegistro " +
                        "ON TransaccionProducto.idProductoRegistro = ProductoRegistro.id " +
                        "INNER JOIN Transaccion " +
                        "ON TransaccionProducto.idTransaccion = Transaccion.id "
        ).addConditionIf(producto != null && !producto.isBlank(),
                "SOUNDEX(ProductoRegistro.nombre) LIKE " +
                        "CONCAT('%', SOUNDEX(?), '%') " +
                        "AND ProductoRegistro.nombre LIKE " +
                        "CONCAT('%', ?, '%')",
                producto, producto
        ).setAtLast(" LIMIT 50");
        String query = builder.commitConditions("");
       try (Connection conn = dbEnv.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            int index = 1;
            for (Object param : builder.getParams()) {
                statement.setObject(index, param);
                index++;
            }
            ResultSet rs = statement.executeQuery();
            List<ProductoManejoGanancias> res = new ArrayList<ProductoManejoGanancias>();
            while (rs.next()) {
                res.add(
                        new ProductoManejoGanancias(
                                rs.getString("ProductoRegistro.nombre"),
                                rs.getInt("TransaccionProducto.cantidad"),
                                rs.getInt("TransaccionProducto.cantidad * TransaccionProducto.precioPorUnidad"),
                                rs.getString("Transaccion.fechaDeCreacion"))
                );
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } 

        @Override
    public Set<ProductoManejoGanancias> listAll() {
        String query = "SELECT ProductoRegistro.nombre, TransaccionProducto.cantidad, TransaccionProducto.cantidad * TransaccionProducto.precioPorUnidad, Transaccion.fechaDeCreacion " +
                "FROM TransaccionProducto " +
                "INNER JOIN ProductoRegistro " +
                "ON TransaccionProducto.idProductoRegistro = ProductoRegistro.id " +
                "INNER JOIN Transaccion " +
                "ON TransaccionProducto.idTransaccion = Transaccion.id " +
                "LIMIT 50";
        try (Connection conn = dbEnv.getConnection();
             Statement statement = conn.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(query);
            Set<ProductoManejoGanancias> res = new HashSet<>();
            while (rs.next()) {
                res.add(
                        new ProductoManejoGanancias(
                                rs.getString("ProductoRegistro.nombre"),
                                rs.getInt("TransaccionProducto.cantidad"),
                                rs.getInt("TransaccionProducto.cantidad * TransaccionProducto.precioPorUnidad"),
                                rs.getString("Transaccion.fechaDeCreacion"))
                );
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
@Override
public List<ProductoManejoGanancias> searchByFecha(String fecha) {
    ConditionsBuilder builder = new ConditionsBuilder(
        "SELECT ProductoRegistro.nombre, TransaccionProducto.cantidad, TransaccionProducto.cantidad * TransaccionProducto.precioPorUnidad, Transaccion.fechaDeCreacion " +
        "FROM TransaccionProducto " +
        "INNER JOIN ProductoRegistro " +
        "ON TransaccionProducto.idProductoRegistro = ProductoRegistro.id " +
        "INNER JOIN Transaccion " +
        "ON TransaccionProducto.idTransaccion = Transaccion.id "
    ).addConditionIf(fecha != null && !fecha.isBlank(),
        "Transaccion.fechaDeCreacion = ?", fecha
    ).setAtLast(" LIMIT 50");

    String query = builder.commitConditions("");

    try (Connection conn = dbEnv.getConnection();
         PreparedStatement statement = conn.prepareStatement(query)) {
        int index = 1;
        for (Object param : builder.getParams()) {
            statement.setObject(index, param);
            index++;
        }

        ResultSet rs = statement.executeQuery();
        List<ProductoManejoGanancias> res = new ArrayList<>();
        while (rs.next()) {
            res.add(
                new ProductoManejoGanancias(
                    rs.getString("ProductoRegistro.nombre"),
                    rs.getInt("TransaccionProducto.cantidad"),
                    rs.getInt("TransaccionProducto.cantidad * TransaccionProducto.precioPorUnidad"),
                    rs.getString("Transaccion.fechaDeCreacion"))
            );
        }
        return res;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}


    @Override
    public Optional<ProductoManejoGanancias> listById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
