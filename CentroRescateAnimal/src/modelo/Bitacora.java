package modelo;

public class Bitacora {
    private String fecha;
    private String usuario;
    private String accion;
    private String detalle;

    public Bitacora(String fecha, String usuario, String accion, String detalle) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.accion = accion;
        this.detalle = detalle;
    }

    public String getFecha() { return fecha; }
    public String getUsuario() { return usuario; }
    public String getAccion() { return accion; }
    public String getDetalle() { return detalle; }
}
