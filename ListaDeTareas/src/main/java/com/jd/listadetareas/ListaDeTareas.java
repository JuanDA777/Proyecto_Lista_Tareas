/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jd.listadetareas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;

/**
 *
 * @author juand
 */
public class ListaDeTareas {
    public static ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
    
    public static void guardarUsuario(ArrayList<Usuarios> usuarios, ServletContext context) throws IOException {
        String relativePath = "/data/usuarios.txt";
        String absPath = context.getRealPath(relativePath);

        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(absPath))) {
            for (Usuarios usuario : usuarios) {
                String linea = usuario.getCedula() + ","
                        + usuario.getUsuario()+ ","
                        + usuario.getContraseña();
                escritor.write(linea);
                escritor.newLine();
            }
            System.out.println("Datos de usuarios guardados exitosamente en: usuarios.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los datos de usuarios: " + e.getMessage());
        }
    }
    
    public static ArrayList<Usuarios> cargarUsuario(ServletContext context) throws IOException {
        ArrayList<Usuarios> darUsuarios = new ArrayList<>();

        String relativePath = "/data/usuarios.txt";
        String absPath = context.getRealPath(relativePath);

        try (BufferedReader lector = new BufferedReader(new FileReader(absPath))) {
            String linea = lector.readLine();
            while (linea != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String cedula = datos[0];
                    String usuario = datos[1];
                    String contraseña = datos[2];

                    Usuarios nuevoUsuario = new Usuarios();
                    nuevoUsuario.setCedula(cedula);
                    nuevoUsuario.setUsuario(usuario);
                    nuevoUsuario.setContraseña(contraseña);

                    darUsuarios.add(nuevoUsuario);
                } else {
                    // Manejo del caso de que la línea no tenga los datos esperados
                    System.out.println("La línea no contiene los datos esperados: " + linea);
                }
                linea = lector.readLine();
            }
            System.out.println("Datos de usuarios cargados exitosamente desde: usuarios.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar los datos de usuarios: " + e.getMessage());
        }
        return darUsuarios;
    }
    
}
