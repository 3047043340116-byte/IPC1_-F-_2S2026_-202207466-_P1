package modelo;

public class Animal {

    private String codigo;
    private String nombre;
    private String especie;
    private String estado;

    public Animal(String codigo, String nombre, String especie, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especie = especie;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
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
                + " | Nombre: " + nombre
                + " | Especie: " + especie
                + " | Estado: " + estado;
    }
}