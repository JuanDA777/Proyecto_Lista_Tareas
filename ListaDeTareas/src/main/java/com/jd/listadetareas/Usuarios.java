/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jd.listadetareas;

/**
 *
 * @author juand
 */
public class Usuarios {
    String usuario;
    String cedula;
    String contraseña;

    public Usuarios() {
    }

    public Usuarios(String usuario, String cedula, String contraseña) {
        this.usuario = usuario;
        this.cedula = cedula;
        this.contraseña = contraseña;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

}