package modelo;

public class Rescate {

    private String codigo;
    private String fecha;
    private String ubicacion;
    private String especie;
    private String descripcion;
    private String estado;

    public Rescate(String codigo,
                   String fecha,
                   String ubicacion,
                   String especie,
                   String descripcion,
                   String estado) {

        this.codigo = codigo;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.especie = especie;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getEspecie() {
        return especie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {

        return "Código: " + codigo
                + " | Fecha: " + fecha
                + " | Ubicación: " + ubicacion
                + " | Especie: " + especie
                + " | Descripción: " + descripcion
                + " | Estado: " + estado;
    }
}