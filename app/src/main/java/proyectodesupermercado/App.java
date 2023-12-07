package proyectodesupermercado;

import proyectodesupermercado.Vista.AppFrame;
import proyectodesupermercado.Vista.interfaces.SesionUsuario;
import proyectodesupermercado.Vista.roles.AdminITViewCreator;
import proyectodesupermercado.Vista.roles.GerenteViewCreator;
import proyectodesupermercado.Vista.roles.InventarioViewCreator;
import proyectodesupermercado.Vista.roles.PuntoDeVentaViewCreator;
import proyectodesupermercado.controller.DatabaseControlInventario;
import proyectodesupermercado.controller.DatabaseControlProductoRegistro;
import proyectodesupermercado.controller.DatabaseControlUsuario;
import proyectodesupermercado.controller.DatabaseSesionUsuario;
import proyectodesupermercado.controller.StateBroker;
import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.controller.dao.SuplidorDAO;
import proyectodesupermercado.controller.dao.mysql.InventarioProductoMySQLDAO;
import proyectodesupermercado.controller.dao.mysql.ProductoRegistroMySQLDAO;
import proyectodesupermercado.controller.dao.mysql.SuplidorMySQLDAO;
import proyectodesupermercado.controller.dao.RolDAO;
import proyectodesupermercado.controller.dao.mysql.UsuarioMySQLDAO;
import proyectodesupermercado.lib.databaseUtils.DatabaseEnvironment;

import javax.swing.JFrame;
import java.security.SecureRandom;
import java.util.Map;
import proyectodesupermercado.controller.authentication.HashPasswordFactory;
import proyectodesupermercado.modelo.Usuario;

public class App {
    public static ThreadLocal<SecureRandom> secureRandom;
    static {
        // init mysql driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("No se pudo cargar 'com.mysql.jdbc.Driver'");
            System.exit(-1);
        }

        // init secureRandom
        secureRandom = ThreadLocal.withInitial(SecureRandom::new);
    }
    
    public static void main(String[] args) {

        DatabaseEnvironment dbEnv = new DatabaseEnvironment(
                "jdbc:mysql://localhost:3306/ProyectoFinal?useSSL=false",
                "root",
                "darkwister171531"
        );
        RolDAO.initRoles(dbEnv);
        UsuarioMySQLDAO usuarioDAO = new UsuarioMySQLDAO(dbEnv);
//         Ctrl + / comentar/descomentar netbeans
//        Usuario user = new Usuario(
//                "TestPOV",
//                new HashPasswordFactory
//                        .PBKDF2HashPasswordFactory()
//                        .createPassword("test".toCharArray()),
//                Rol.PuntoDeVenta
//        );
//        usuarioDAO.insert(user);
//        usuarioDAO.insert(new Usuario(
//                "TestGe",
//                new HashPasswordFactory
//                        .PBKDF2HashPasswordFactory()
//                        .createPassword("test".toCharArray()),
//                Rol.Gerente
//        ));
//        usuarioDAO.insert(new Usuario(
//                "TestInv",
//                new HashPasswordFactory
//                        .PBKDF2HashPasswordFactory()
//                        .createPassword("test".toCharArray()),
//                Rol.Inventario
//        ));
//        usuarioDAO.insert(new Usuario(
//                "TestIT",
//                new HashPasswordFactory
//                        .PBKDF2HashPasswordFactory()
//                        .createPassword("test".toCharArray()),
//                Rol.AdminIT
//        ));
//        usuarioDAO.update(0, user);
//        if (usuarioDAO.userNameExists(user.getNombre())) {
//            usuarioDAO.listByName(user.getNombre())
//                    .map(u -> u.checkPassword("test".toCharArray()))
//                    .ifPresent(System.out::println);
//        } else {
//            usuarioDAO.insert(user);
//        }

        SuplidorDAO suplidorDAO = new SuplidorMySQLDAO(dbEnv);

        AppFrame changer = new AppFrame();
        StateBroker stateBroker = new StateBroker(
                changer,
                changer::dispose,
                Map.of(
                        Rol.PuntoDeVenta, new PuntoDeVentaViewCreator(),
                        Rol.Inventario, new InventarioViewCreator(
                                new DatabaseControlInventario(
                                        new InventarioProductoMySQLDAO(dbEnv)
                                )
                        ),
                        Rol.Gerente, new GerenteViewCreator(
                                new DatabaseControlProductoRegistro(
                                        new ProductoRegistroMySQLDAO(
                                                dbEnv
                                        ),
                                        suplidorDAO
                                )
                        ),
                        Rol.AdminIT, new AdminITViewCreator(
                                new DatabaseControlUsuario(
                                        new UsuarioMySQLDAO(dbEnv)
                                ),
                                new HashPasswordFactory.PBKDF2HashPasswordFactory()
                        )
                )
        );
        SesionUsuario sesionUsuario = new DatabaseSesionUsuario(
                usuarioDAO,
                stateBroker
        );
        changer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        stateBroker.moveToLogin(sesionUsuario);
        changer.setTitle("Ventana");
        changer.pack();
        changer.setVisible(true);
        changer.setLocationRelativeTo(null);
        Runtime.getRuntime().addShutdownHook(new Thread(sesionUsuario::logOut));
    }
}