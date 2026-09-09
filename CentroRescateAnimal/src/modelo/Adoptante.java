package modelo;

public class Adoptante {

    private String dpi;
    private String nombre;
    private String telefono;
    private String direccion;
    private String estado;

    public Adoptante(String dpi, String nombre, String telefono,
                     String direccion, String estado) {

        this.dpi = dpi;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getDpi() {
        return dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {

        return "DPI: " + dpi
                + " | Nombre: " + nombre
                + " | Teléfono: " + telefono
                + " | Dirección: " + direccion
                + " | Estado: " + estado;
    }
}