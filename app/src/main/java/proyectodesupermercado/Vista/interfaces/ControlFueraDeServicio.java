package proyectodesupermercado.Vista.interfaces;

import proyectodesupermercado.modelo.InventarioProducto;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ControlFueraDeServicio extends BuscableEnInventario {
    Optional<String> restablecer(InventarioProducto producto);
}
