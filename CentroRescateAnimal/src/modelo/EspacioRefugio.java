package modelo;

public class EspacioRefugio {

    private int fila;
    private int columna;
    private String codigoAnimal;

    public EspacioRefugio(int fila, int columna) {

        this.fila = fila;
        this.columna = columna;
        this.codigoAnimal = "";
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getCodigoAnimal() {
        return codigoAnimal;
    }

    public void setCodigoAnimal(String codigoAnimal) {
        this.codigoAnimal = codigoAnimal;
    }

    public boolean estaDisponible() {
        return codigoAnimal == null
                || codigoAnimal.isEmpty();
    }

    @Override
    public String toString() {

        if (estaDisponible()) {
            return "Disponible";
        }

        return codigoAnimal;
    }
}