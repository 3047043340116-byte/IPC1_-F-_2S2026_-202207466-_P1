package datos;

import modelo.Usuario;

public class UsuarioDatos {

    private Usuario[] usuarios;
    private int cantidad;

    public UsuarioDatos(int capacidad) {
        usuarios = new Usuario[capacidad];
        cantidad = 0;
    }

    public boolean registrarUsuario(Usuario usuario) {
        if (usuario == null
                || usuario.getUsuario() == null
                || usuario.getUsuario().trim().isEmpty()
                || usuario.getContrasena() == null
                || usuario.getContrasena().isEmpty()
                || usuario.getRol() == null
                || usuario.getRol().trim().isEmpty()) {
            return false;
        }

        if (!rolValido(usuario.getRol())
                || buscarUsuario(usuario.getUsuario()) != null
                || cantidad >= usuarios.length) {
            return false;
        }

        usuarios[cantidad] = usuario;
        cantidad++;
        return true;
    }

    private boolean rolValido(String rol) {
        return "Administrador".equals(rol)
                || "Auxiliar".equals(rol);
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        if (nombreUsuario == null) {
            return null;
        }

        for (int i = 0; i < cantidad; i++) {
            if (usuarios[i].getUsuario().equals(nombreUsuario)) {
                return usuarios[i];
            }
        }

        return null;
    }

    public Usuario autenticar(String nombreUsuario, String contrasena) {
        Usuario usuario = buscarUsuario(nombreUsuario);

        if (usuario != null
                && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }

        return null;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Usuario getUsuario(int indice) {
        if (indice >= 0 && indice < cantidad) {
            return usuarios[indice];
        }

        return null;
    }
}
