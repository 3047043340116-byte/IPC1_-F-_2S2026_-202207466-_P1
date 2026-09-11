package controlador;

import datos.UsuarioDatos;
import modelo.Usuario;
import persistencia.UsuarioPersistencia;

public class LoginController {

    private UsuarioDatos datosUsuarios;
    private UsuarioPersistencia persistencia;

    public LoginController() {
        datosUsuarios = new UsuarioDatos(10);
        persistencia = new UsuarioPersistencia("usuarios.txt");

        cargarUsuariosIniciales();
    }

    private void cargarUsuariosIniciales() {
        boolean cargado = persistencia.cargar(datosUsuarios);

        if (!cargado || datosUsuarios.getCantidad() == 0) {
            datosUsuarios.registrarUsuario(
                    new Usuario("admin", "admin123", "Administrador")
            );

            datosUsuarios.registrarUsuario(
                    new Usuario("auxiliar", "auxiliar123", "Auxiliar")
            );

            persistencia.guardar(datosUsuarios);
        }
    }

    public Usuario iniciarSesion(String usuario, String contrasena) {
        return datosUsuarios.autenticar(usuario, contrasena);
    }
}
