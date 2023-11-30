package proyectodesupermercado.controller.authentication;

import proyectodesupermercado.lib.databaseUtils.annotations.Column;
import proyectodesupermercado.lib.databaseUtils.annotations.ManyToOne;
import proyectodesupermercado.lib.databaseUtils.annotations.Table;

@Table
public class Usuario {
    private long id;
    private String nombre;
    @Column(name = "contraseña", isJavaObject = true)
    private Password password;
    @Column(name = "sesionActiva")
    private boolean alreadyLogged;
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
}
