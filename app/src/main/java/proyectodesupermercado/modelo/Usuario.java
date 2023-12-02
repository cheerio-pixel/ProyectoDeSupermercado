package proyectodesupermercado.modelo;

import proyectodesupermercado.controller.authentication.Password;
import proyectodesupermercado.controller.authentication.Rol;
import proyectodesupermercado.lib.databaseUtils.annotations.Column;
import proyectodesupermercado.lib.databaseUtils.annotations.ManyToOne;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;
import proyectodesupermercado.lib.tableModel.TableModelColumn;

@Table
public class Usuario implements Cloneable {
    private long id;
    @TableModelColumn(name = "Nombre", index = 1)
    private String nombre;
    @Column(name = "contraseña", isJavaObject = true)
    private Password password;
    @TableModelColumn(name = "Sesion Activa", index = 3)
    @Column(name = "sesionActiva")
    private boolean alreadyLogged;
    @TableModelColumn(name = "Rol", index = 2)
    @ManyToOne(joinColumn = "rolId")
    private Rol rol;
    public Usuario() {
    }

    public Usuario(String nombre, Password password, Rol rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    public Rol getRol() {
        return rol;
    }

    public boolean isAlreadyLogged() {
        return alreadyLogged;
    }

    public void setAlreadyLogged(boolean alreadyLogged) {
        this.alreadyLogged = alreadyLogged;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Password getPassword() {
        return password;
    }

    public boolean checkPassword(char[] password) {
        return this.password.checkPassword(password);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    @Override
    public Usuario clone() {
        try {
            return (Usuario) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
