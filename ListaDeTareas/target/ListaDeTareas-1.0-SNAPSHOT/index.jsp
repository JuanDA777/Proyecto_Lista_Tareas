<%@include file= "templates/header.jsp" %>
<!-- Section: Design Block -->
<section class="background-radial-gradient overflow-hidden">
  

  <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
    <div class="row gx-lg-5 align-items-center mb-5">
      <div class="col-lg-6 mb-5 mb-lg-0" style="z-index: 10">
        <h1 class="my-5 display-5 fw-bold ls-tight" style="color: hsl(218, 81%, 95%)">
          Lista de tareas <br />
          <span style="color: hsl(218, 81%, 75%)">para organizar bien tu tiempo</span>
        </h1>
        <p class="mb-4 opacity-70" style="color: hsl(218, 81%, 85%)">
          Este programa te permitira crear una lista para que puedas 
          organziar tus actividades pendientes, puedes agregar un identificador
          fecha y una breve descripcion para que organices de la mejor manera
          tu tiempo.
        </p>
      </div>

      <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
        <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
        <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

        <div class="card bg-glass">
          <div class="card-body px-4 py-5 px-md-5">
            <form action="SvInicioSesion" method="post">
                
                <div class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;" id="errorAlert">
                    Datos incorrectos o usuario no existente. Vuelva a intentarlo.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <div class="alert alert-success alert-dismissible fade show" role="alert" style="display: none;" id="registroSuccessAlert">
                    ¡Registro exitoso! El usuario se añadio al sistema.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>

                <div class="alert alert-danger alert-dismissible fade show" role="alert" style="display: none;" id="registroErrorAlert">
                    La cedula ingresada ya esta registrada a un usuario existente. Vuelva a intentarlo con otro numero de cedula.
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>  
                
              <!-- Email input -->
              <div class="form-outline mb-4">
                  <input type="text" id="cedula" name="cedula" class="form-control" required=""/>
                <label class="form-label" for="form3Example3">Cedula Usuario</label>
              </div>

              <!-- Password input -->
              <div class="form-outline mb-4">
                  <input type="password" id="contraseña" name="contraseña" class="form-control" required=""/>
                <label class="form-label" for="form3Example4">Contraseña</label>
              </div>

              <!-- Submit button -->
              <button type="submit" class="btn btn-primary btn-block mb-4">
                Iniciar sesion
              </button>

              <!-- Register buttons -->
              <div class="text-lg-start">
                <p>No estas registrado?</p>
                <button type="button" class="btn btn-primary btn-block mb-4" data-bs-toggle="modal" data-bs-target="#registerUsers">Registrate aqui!</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- Ventana modal -->
<div class="modal fade" id="registerUsers" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">Registro de nuevo usuario</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="SvRegistro" method="post">
                <div class="modal-body">
                    <div class="mb-3 row">
                        <label for="nombre" class="col-sm-2 col-form-label">Usuario</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="nombre" name="usuario" placeholder="Ingresa el nombre de usuario" required="">
                        </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="cedula" class="col-sm-2 col-form-label">Cédula</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="cedula" name="cedula" placeholder="Ingresa la cédula" required oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                            </div>
                        </div>
                    <div class="mb-3 row">
                        <label for="contraseña" class="col-sm-2 col-form-label">Contraseña</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="contraseña" name="contraseña" placeholder="Ingresa la contraseña" required="">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit"  class="btn btn-primary">Registrar</button>
                </div>
            </form> 
        </div>
    </div>
</div>


<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Obtén la alerta por su ID
        const registroErrorAlert = document.getElementById('registroErrorAlert');

        // Verifica si hay un parámetro de alerta en la URL (por ejemplo, '?alert=registro-error')
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('alert') && urlParams.get('alert') === 'registro-error') {
            // Muestra la alerta de registro si el parámetro de alerta es 'registro-error'
            registroErrorAlert.style.display = 'block';
        }
    });
</script>

<!-- JavaScript para mostrar la alerta de registro exitoso cuando sea necesario -->
<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Obtén la alerta por su ID
        const registroSuccessAlert = document.getElementById('registroSuccessAlert');

        // Verifica si hay un parámetro de alerta en la URL (por ejemplo, '?alert=registro-success')
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('alert') && urlParams.get('alert') === 'registro-success') {
            // Muestra la alerta de registro exitoso si el parámetro de alerta es 'registro-success'
            registroSuccessAlert.style.display = 'block';
        }
    });
</script>
<%@include file= "templates/footer.jsp" %>