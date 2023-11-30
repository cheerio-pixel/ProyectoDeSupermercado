/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyectodesupermercado.controller;

import proyectodesupermercado.controller.authentication.Usuario;

/**
 *
 * @author DELL
 */
public interface SesionUsuario {
    Usuario getLoggedUser();

    Result<Usuario> logUser(String nombre, char[] contraseña);

    void logOut();
}
