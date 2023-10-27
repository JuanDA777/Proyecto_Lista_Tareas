<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jd.listadetareas.ListaEnlazada"%>
<%@include file= "templates/header.jsp" %>
<h6>Bienvenido <%= session.getAttribute("usuario")%>/<a href="index.jsp">Cerrar sesion</a></h6> 
<div class="container p-4">
    <div class="row">
        <div class="col-md-4">
            <div class="card card-body">
                <h1>Insertar nueva tarea</h1>
                <form action="SvAgregarTarea" method="POST">
                    <div>
                        <label class="visually-hidden" for="id"></label>
                        <div class="input-group">
                            <div class="input-group-text">Id</div>
                            <input type="text" class="form-control" id="id" name="id" required="">
                        </div>
                    </div><br>

                    <div>
                        <label class="visually-hidden" for="titulo"></label>
                        <div class="input-group">
                            <div class="input-group-text">Titulo:</div>
                            <input type="text" class="form-control" id="titulo" name="titulo" required="">
                        </div>
                    </div><br>
                    
                    <div>
                        <label class="visually-hidden" for="descripcion"></label>
                        <div class="input-group">
                            <div class="input-group-text">Descripcion:</div>
                            <textarea id="descripcion" name="descripcion" rows="5" cols="10" required=""></textarea>
                        </div>
                    </div><br>
                    
                    <div>
                        <label class="visually-hidden" for="fechavencimiento"></label>
                        <div class="input-group">
                            <div class="input-group-text">Fecha de vencimiento</div>
                            <input type="date" class="form-control" id="fechavencimiento" name="fechavencimiento" required="">
                        </div>
                    </div><br>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="posicion" id="primeroRadio" value="primero">
                        <label class="form-check-label" for="primeroRadio">
                            Primero en la lista
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="posicion" id="ultimoRadio" value="ultimo">
                        <label class="form-check-label" for="ultimoRadio">
                            Ultimo en la lista
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="posicion" id="antesDeRadio" value="antesDe">
                        <label class="form-check-label" for="antesDeRadio">
                            Despues de Tarea con ID:
                        </label>
                        <input type="text" name="idAntesDe" id="idAntesDe" placeholder="ID">
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="posicion" id="despuesDeRadio" value="despuesDe">
                        <label class="form-check-label" for="despuesDeRadio">
                            Antes de Tarea con ID:
                        </label>
                        <input type="text" name="idDespuesDe" id="idDespuesDe" placeholder="ID">
                    </div><br>
                    
                    <div>
                        <button type="submit" class="btn btn-primary">Agregar Tarea</button>
                    </div>
                </form>
            </div>
        </div>
            <div class="col-md-8">
                
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <div class="container-fluid">
                      <a class="navbar-brand">Organizador</a>
                      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                      </button>
                      <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" >
                              Ordenar tareas
                            </a>
                            <ul class="dropdown-menu">
                              <li><a class="dropdown-item" id="ordenamiento-nombre" data-value="nombre"   href="#">Por Id</a></li>
                              <li><a class="dropdown-item" id="ordenamiento-puntos" data-value="puntos"  href="#">Por Titulo</a></li>
                              <li><a class="dropdown-item" id="ordenamiento-raza" data-value="raza"  href="#">Por fecha  </a></li>

                            </ul>
                        </li>
                        </ul>
                        <form class="d-flex" action="SvAgregarTarea" method="GET">
                            <input class="form-control me-2" type="search" name="nombre" placeholder="ID" aria-label="ID">
                            <button class="btn btn-outline-success" type="submit">Buscar</button>
                        </form>
                      </div>
                    </div>
                </nav>
                
                <table class="table table-bordered table-dark">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Titulo</th>
                            <th>Descripcion</th>
                            <th>Fecha de vencimiento</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        <%
                             ListaEnlazada listaTareas = ( ListaEnlazada) session.getAttribute("listaTareas");

                            if (listaTareas != null) {
                                 ListaEnlazada.Nodo current = listaTareas.inicio;
                                while (current != null) {
                        %>
                            <tr>
                                <td><%= current.tarea.getId()%></td>
                                <td><%= current.tarea.getTitulo()%></td>
                                <td><%= current.tarea.getDescripcion()%></td>
                                <td><%= current.tarea.getFecha()%></td>
                                <td>
                                    <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#mostrarId" onclick="showTareaDetails(<%= current.tarea.getId()%>, '<%= current.tarea.getTitulo()%>', '<%= current.tarea.getDescripcion()%>', '<%= current.tarea.getFecha()%>')"><i class="fas fa-eye"></i></a> <!-- Icono de ventana modal -->
                                    
                                    <a href="#" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#editarTareaModal"
                                        data-id="<%= current.tarea.getId()%>"
                                        data-titulo="<%= current.tarea.getTitulo()%>"
                                        data-descripcion="<%= current.tarea.getDescripcion()%>"
                                        data-fecha="<%= current.tarea.getFecha()%>">
                                         <i class="fas fa-pencil-alt"></i>
                                     </a>
                                    
                                    <a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmacionModal" onclick="sacarId(<%= current.tarea.getId()%>)"><i class="fas fa-trash-alt"></i></a>
                                    
                                    
                                </td>
                                

                            </tr>
                        <%
                                    current = current.siguiente;
                                }
                            } else {
                                %>
                                    <tr>
                                        <td colspan="5">No hay tareas disponibles</td>
                                    </tr>
                                <%
                            }
                        %>
                    </tbody>
            

                </table>
            </div>
        </div>
</div>



<!-- Modal -->
<div class="modal fade" id="mostrarId" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="staticBackdropLabel">Mostrar detalles de la tarea</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="tarea-details">
                    <p><strong>ID:</strong> <span id="tarea-id"></span></p>
                    <p><strong>Título:</strong> <span id="tarea-titulo"></span></p>
                    <p><strong>Descripción:</strong> <span id="tarea-descripcion"></span></p>
                    <p><strong>Fecha:</strong> <span id="tarea-fecha"></span></p>
                </div>
              </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editarTareaModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Editar Tarea</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                
                <form action="SvEditarTarea" method="POST">
                    
                    <div>
                        <label class="visually-hidden" for="id"></label>
                        <div class="input-group">
                            <div class="input-group-text">Id</div>
                            <input type="text" class="form-control" id="id" name="id" required>
                        </div>
                    </div><br>

                    <div>
                        <label class="visually-hidden" for="titulo"></label>
                        <div class="input-group">
                            <div class="input-group-text">Titulo:</div>
                            <input type="text" class="form-control" id="titulo" name="titulo" required="">
                        </div>
                    </div><br>
                    
                    <div>
                        <label class="visually-hidden" for="descripcion"></label>
                        <div class="input-group">
                            <div class="input-group-text">Descripcion:</div>
                            <textarea id="descripcion" name="descripcion" rows="5" cols="10" required=""></textarea>
                            
                        </div>
                    </div><br>
                    
                    <div>
                        <label class="visually-hidden" for="fechavencimiento"></label>
                        <div class="input-group">
                            <div class="input-group-text">Fecha de vencimiento</div>
                            <input type="date" class="form-control" id="fechavencimiento" name="fechavencimiento" required="">
                        </div>
                    </div><br>
                    
                    <!-- Botón para guardar cambios -->
                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                </form>
                
              </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="confirmacionModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Eliminar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Está seguro de que desea eliminar la tarea?
              </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-danger" onclick="eliminarTarea()">Eliminar</button>
            </div>
        </div>
    </div>
</div>



<%
    ListaEnlazada lista = (ListaEnlazada) session.getAttribute("listaTareas");
    boolean listaVacia = (lista == null) || lista.verificarContenido();
%>
<script>
    var listaVacia = <%= listaVacia%>;
    var radios = document.querySelectorAll(".form-check-input");
    var labels = document.querySelectorAll(".form-check-label");
    var idAntesDeInput = document.getElementById("idAntesDe");
    var idDespuesDeInput = document.getElementById("idDespuesDe");

    if (listaVacia) {
        radios.forEach(function (radio) {
            radio.style.display = "none";
        });
        labels.forEach(function (label) {
            label.style.display = "none";
        });
        idAntesDeInput.style.display = "none";
        idDespuesDeInput.style.display = "none";
    } else {
        radios.forEach(function (radio) {
            radio.style.display = "block";
        });
        labels.forEach(function (label) {
            label.style.display = "block";
        });
        idAntesDeInput.style.display = "block";
        idDespuesDeInput.style.display = "block";
    }
    
    //mostrar tareas
    function showTareaDetails(id, titulo, descripcion, fecha) {
        var modal = $('#mostrarId');
        modal.find('#tarea-id').text(id);
        modal.find('#tarea-titulo').text(titulo);
        modal.find('#tarea-descripcion').text(descripcion);
        modal.find('#tarea-fecha').text(fecha);
    }
    
    //
    var tareaId;

    function sacarId(id) {
        tareaId = id; // Guarda el id de la tarea para usarlo en la función eliminarTarea
        console.log(tareaId);
        console.log("funciona????");
    }

    function eliminarTarea() {
        
    location.href = "SvAgregarTarea?tipo=delete&id=" + tareaId;
    tareaId = null;
        
    }
</script>


<%@include file= "templates/footer.jsp" %>