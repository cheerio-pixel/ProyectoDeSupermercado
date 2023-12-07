package proyectodesupermercado.Vista.roles;

import javax.swing.JPanel;
import java.sql.Connection;

public class ContabilidadViewCreator implements ViewCreator {
    public ContabilidadViewCreator() {
    }

    @Override
    public JPanel create() {
        GeneralGroup group = new GeneralGroup();
//        group.addTab("Ventas", new VentasPanel());
//        group.addTab("Ventas", new CompraVenta());
        return group;
    }
}
