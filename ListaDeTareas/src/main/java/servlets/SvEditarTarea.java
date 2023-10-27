/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import com.jd.listadetareas.ListaEnlazada;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author juand
 */
@WebServlet(name = "SvEditarTarea", urlPatterns = {"/SvEditarTarea"})
public class SvEditarTarea extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvEditarTarea</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvEditarTarea at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        // Obtén la sesión
        HttpSession session = request.getSession();

        // Obtén los parámetros enviados desde el formulario de edición
        int id = Integer.parseInt(request.getParameter("id"));
        String nuevoTitulo = request.getParameter("titulo");
        String nuevaDescripcion = request.getParameter("descripcion");
        String nuevaFecha = request.getParameter("fechavencimiento");

        // Obtén la lista de tareas desde la sesión
        ListaEnlazada listaTareas = (ListaEnlazada) session.getAttribute("listaTareas");

        if (listaTareas != null) {
            // Realiza validaciones, por ejemplo, verifica si la tarea con el ID proporcionado existe
            if (listaTareas.localizarPorId(id) != null) {
                // Actualiza la tarea en tu lista de tareas
                listaTareas.editarTarea(id, nuevoTitulo, nuevaDescripcion, nuevaFecha);

                // Guarda la lista actualizada en la sesión
                session.setAttribute("listaTareas", listaTareas);

                // Guarda la lista actualizada en el archivo de texto
                ListaEnlazada.guardarLista(listaTareas, getServletContext());

                // Después de editar la tarea exitosamente en tu servlet
                session.setAttribute("tareaEditadaExitosamente", true);
            }
        }

        // Redirige a la página de tareas (o la página que desees)
        response.sendRedirect("login.jsp");
    
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
