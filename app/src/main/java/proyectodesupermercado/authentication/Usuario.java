package proyectodesupermercado.authentication;

import proyectodesupermercado.lib.databaseUtils.annotations.Column;

public class Usuario {
    private long id;
    private String nombre;
    private Password password;
    @Column(name = "already_logged")
    private boolean alreadyLogged;
    private Rol rol;

    public Usuario() {
    }

    public boolean checkPassword(char[] password) {
        return this.password.checkPassword(password);
    }
}
