/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.jd.listadetareas.ListaDeTareas;
import com.jd.listadetareas.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juand
 */
@WebServlet(name = "SvRegistro", urlPatterns = {"/SvRegistro"})
public class SvRegistro extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Se recibe la informacion ingresada desde index.jsp
        System.out.println("---INFORMACION DESDE SvRegistro---");
        String usuario = request.getParameter("usuario");
        System.out.println("usuario: " + usuario);
        String cedula = request.getParameter("cedula");
        System.out.println("cedula: " + cedula);
        String contraseña = request.getParameter("contraseña");
        System.out.println("contraseña: " + contraseña);
        
        ArrayList<Usuarios> misUsuarios = ListaDeTareas.cargarUsuario(getServletContext());
        

        // Verificar si ya existe un usuario con la cédula proporcionada
        boolean cedulaExistente = false;
        for (Usuarios usuarios : misUsuarios) {
            if (usuarios.getCedula().equals(cedula)) {
                cedulaExistente = true;
                break;
            }
        }
        if (cedulaExistente) {
            // Ya existe un usuario con esa cédula, muestra un mensaje de error
            // En caso de un error en el registro, redirigir a la página de inicio con una alerta
            response.sendRedirect("index.jsp?alert=registro-error");
        } else {
            // No existe un usuario con esa cédula, crea el nuevo usuario
            // Crear un nuevo objeto Usuario y establecer los valores
            Usuarios nuevoUsuario = new Usuarios();
            nuevoUsuario.setCedula(cedula);
            nuevoUsuario.setUsuario(usuario);
            nuevoUsuario.setContraseña(contraseña);

            // Agregar el nuevo usuario a la lista de usuarios
            misUsuarios.add(nuevoUsuario);

            // Guardar la lista de usuarios en el archivo usuarios.txt
            ListaDeTareas.guardarUsuario(misUsuarios, getServletContext());

            // Redireccionar a la página web destino
            response.sendRedirect("index.jsp?alert=registro-success");
            
            
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
