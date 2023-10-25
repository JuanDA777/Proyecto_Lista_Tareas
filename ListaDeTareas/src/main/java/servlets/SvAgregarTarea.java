/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.jd.listadetareas.ListaDeTareas;
import com.jd.listadetareas.ListaEnlazada;
import com.jd.listadetareas.Tareas;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author juand
 */
@WebServlet(name = "SvAgregarTarea", urlPatterns = {"/SvAgregarTarea"})
public class SvAgregarTarea extends HttpServlet {

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
            out.println("<title>Servlet SvAgregarTarea</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvAgregarTarea at " + request.getContextPath() + "</h1>");
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
        
        String tipo = request.getParameter("tipo");
        if (tipo != null && tipo.equals("delete")) {
            String idToDelete = request.getParameter("id");
            if (idToDelete != null && !idToDelete.isEmpty()) {
                HttpSession session = request.getSession();
                ListaEnlazada listaTareas = (ListaEnlazada) session.getAttribute("listaTareas");

                if (listaTareas != null) {
                    try {
                        int id = Integer.parseInt(idToDelete);
                        listaTareas.eliminarTarea(id);
                        // Guarda la lista actualizada en el archivo
                        ListaEnlazada.guardarLista(listaTareas, getServletContext());

                        // Agrega un atributo para indicar la eliminación exitosa
                        session.setAttribute("tareaEliminada", true);
                    } catch (NumberFormatException e) {
                        // Maneja la excepción si no se proporciona un ID válido
                        e.printStackTrace();
                    }
                }
            }
        }
        // Después de eliminar una tarea con éxito en tu servlet
        response.sendRedirect("login.jsp");
        
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
        //informacion ingresada desde login.jsp
        String id = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fechaVencimiento = request.getParameter("fechavencimiento");
        String posicion = request.getParameter("posicion"); // Obtén el valor del radio button
        String idAntesDe = request.getParameter("idAntesDe"); // Obtén la id antes de la cual agregar
        String idDespuesDe = request.getParameter("idDespuesDe"); // Obtén la id después de la cual agregar
        
        

        // Obtén la lista actualizada desde la sesión
        HttpSession session = request.getSession();
        ListaEnlazada listaTareas = (ListaEnlazada) session.getAttribute("listaTareas");

        if (listaTareas == null) {
            listaTareas = new ListaEnlazada();
            // Guárdala en la sesión
            session.setAttribute("listaTareas", listaTareas);
        }

        // Verifica si ya existe una tarea con el mismo ID
        if (listaTareas.tareaConIdExiste(Integer.parseInt(id))) {
            // Tarea con el mismo ID ya existe, muestra una alerta
            request.setAttribute("tareaExistente", true);

            // Redirige nuevamente a la página tareas.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        } else {
            Tareas nuevaTarea = new Tareas(Integer.parseInt(id), titulo, descripcion, fechaVencimiento);

            if (null == posicion) {
                // Por defecto o si se selecciona "primero", agregar al comienzo
                listaTareas.agregarTareaAlComienzo(nuevaTarea);
            } else {
                switch (posicion) {
                    case "ultimo":
                        // Agregar la tarea al final de la lista
                        listaTareas.agregarTareaAlFinal(nuevaTarea);
                        break;
                    case "antesDe":
                        if (idAntesDe != null && !idAntesDe.isEmpty()) {
                            // Agregar la tarea antes de la tarea con la ID especificada
                            listaTareas.agregarTareaAntesDe(Integer.parseInt(idAntesDe), nuevaTarea);
                        } else {
                            // Si no se proporciona una ID antes de la cual agregar, agregar al comienzo
                            listaTareas.agregarTareaAlComienzo(nuevaTarea);
                        }
                        break;
                    case "despuesDe":
                        if (idDespuesDe != null && !idDespuesDe.isEmpty()) {
                            // Agregar la tarea después de la tarea con la ID especificada
                            listaTareas.agregarTareaDespuesDe(Integer.parseInt(idDespuesDe), nuevaTarea);
                        } else {
                            // Si no se proporciona una ID después de la cual agregar, agregar al final
                            listaTareas.agregarTareaAlFinal(nuevaTarea);
                        }
                        break;
                    default:
                        // Por defecto o si se selecciona "primero", agregar al comienzo
                        listaTareas.agregarTareaAlComienzo(nuevaTarea);
                        break;
                }
            }

            // Guarda la tarea en el archivo
            ListaEnlazada.guardarLista(listaTareas, getServletContext());

            // Después de agregar una tarea exitosamente en tu servlet
            request.setAttribute("registroExitoso", true);

            // Redirige a la página tareas.jsp con el parámetro "registroExitoso"
            response.sendRedirect("login.jsp?registroExitoso=true");
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
