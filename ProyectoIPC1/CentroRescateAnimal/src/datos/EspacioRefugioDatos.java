package datos;

import modelo.EspacioRefugio;

public class EspacioRefugioDatos {

    private EspacioRefugio[][] espacios;

    private int filas;
    private int columnas;

    public EspacioRefugioDatos(int filas, int columnas) {

        this.filas = filas;
        this.columnas = columnas;

        espacios = new EspacioRefugio[filas][columnas];

        inicializarMatriz();
    }

    private void inicializarMatriz() {

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                espacios[i][j] =
                        new EspacioRefugio(i, j);
            }
        }
    }

    // ==========================================
    // VALIDAR POSICION
    // ==========================================

    private boolean posicionValida(
            int fila,
            int columna) {

        return fila >= 0
                && fila < filas
                && columna >= 0
                && columna < columnas;
    }

    // ==========================================
    // CONSULTAR ESPACIO
    // ==========================================

    public EspacioRefugio consultarEspacio(
            int fila,
            int columna) {

        if (!posicionValida(fila, columna)) {
            return null;
        }

        return espacios[fila][columna];
    }

    // ==========================================
    // ASIGNAR ANIMAL
    // ==========================================

    public boolean asignarAnimal(
            int fila,
            int columna,
            String codigoAnimal) {

        if (!posicionValida(fila, columna)) {
            return false;
        }

        if (codigoAnimal == null
                || codigoAnimal.trim().isEmpty()) {

            return false;
        }

        if (!espacios[fila][columna].estaDisponible()) {
            return false;
        }

        if (buscarAnimal(codigoAnimal) != null) {
            return false;
        }

        espacios[fila][columna]
                .setCodigoAnimal(codigoAnimal);

        return true;
    }

    // ==========================================
    // LIBERAR ESPACIO
    // ==========================================

    public boolean liberarEspacio(
            int fila,
            int columna) {

        if (!posicionValida(fila, columna)) {
            return false;
        }

        if (espacios[fila][columna].estaDisponible()) {
            return false;
        }

        espacios[fila][columna]
                .setCodigoAnimal("");

        return true;
    }

    // ==========================================
    // BUSCAR ANIMAL
    // ==========================================

    public EspacioRefugio buscarAnimal(
            String codigoAnimal) {

        if (codigoAnimal == null) {
            return null;
        }

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                if (codigoAnimal.equals(
                        espacios[i][j].getCodigoAnimal())) {

                    return espacios[i][j];
                }
            }
        }

        return null;
    }

    // ==========================================
    // MOSTRAR MATRIZ
    // ==========================================

    public void mostrarMatriz() {

        System.out.println("\nMATRIZ DEL REFUGIO");

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                System.out.print(
                        "[" + espacios[i][j] + "] "
                );
            }

            System.out.println();
        }
    }

    // ==========================================
    // CONTAR DISPONIBLES
    // ==========================================

    public int contarDisponibles() {

        int disponibles = 0;

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                if (espacios[i][j].estaDisponible()) {
                    disponibles++;
                }
            }
        }

        return disponibles;
    }

    // ==========================================
    // CONTAR OCUPADOS
    // ==========================================

    public int contarOcupados() {

        int ocupados = 0;

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < columnas; j++) {

                if (!espacios[i][j].estaDisponible()) {
                    ocupados++;
                }
            }
        }

        return ocupados;
    }

    // ==========================================
    // GETTERS
    // ==========================================

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public EspacioRefugio[][] getEspacios() {
        return espacios;
    }
}