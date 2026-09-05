package centrorescateanimal;

import datos.AnimalDatos;
import modelo.Animal;
import persistencia.AnimalPersistencia;
import datos.AdoptanteDatos;
import modelo.Adoptante;
import persistencia.AdoptantePersistencia;
import datos.SolicitudDatos;
import modelo.Solicitud;




public class Main {

    public static void main(String[] args) {

        AnimalDatos datosAnimales = new AnimalDatos(100);

        Animal animal1 = new Animal(
                "A001",
                "Firulais",
                "Perro",
                "Rescatado"
        );

        Animal animal2 = new Animal(
                "A002",
                "Michi",
                "Gato",
                "En tratamiento"
        );

        datosAnimales.registrarAnimal(animal1);
        datosAnimales.registrarAnimal(animal2);

        System.out.println("=== ANIMALES REGISTRADOS ===");

        datosAnimales.listarAnimales();
    
System.out.println("\n=== BUSCAR ANIMAL ===");

Animal encontrado = datosAnimales.buscarAnimal("A001");

if (encontrado != null) {
    System.out.println("Animal encontrado:");
    System.out.println(encontrado);
} else {
    System.out.println("Animal no encontrado.");
}    

System.out.println("\n=== EDITAR ESTADO ===");

boolean actualizado = datosAnimales.editarEstado(
        "A001",
        "En tratamiento"
);

if (actualizado) {
    System.out.println("Estado actualizado correctamente.");
} else {
    System.out.println("No se encontró el animal.");
}

System.out.println("\n=== ANIMALES DESPUÉS DE EDITAR ===");

datosAnimales.listarAnimales();


System.out.println("\n=== ELIMINAR ANIMAL ===");

boolean eliminado = datosAnimales.eliminarAnimal("A002");

if (eliminado) {
    System.out.println("Animal eliminado correctamente.");
} else {
    System.out.println("No se encontró el animal.");
}

System.out.println("\n=== ANIMALES DESPUÉS DE ELIMINAR ===");

datosAnimales.listarAnimales();

System.out.println("\n=== GUARDAR ANIMALES ===");

AnimalPersistencia persistencia =
        new AnimalPersistencia("animales.txt");

boolean guardado = persistencia.guardar(datosAnimales);

if (guardado) {
    System.out.println("Animales guardados correctamente.");
} else {
    System.out.println("No se pudieron guardar los animales.");
}

System.out.println("\n=== SIMULAR CIERRE DEL PROGRAMA ===");

AnimalDatos datosNuevos = new AnimalDatos(100);

System.out.println("Se creó un arreglo nuevo.");

System.out.println("\n=== CARGAR ANIMALES ===");

boolean cargado = persistencia.cargar(datosNuevos);

if (cargado) {

    System.out.println("Animales cargados correctamente.");

    System.out.println("\n=== ANIMALES CARGADOS ===");

    datosNuevos.listarAnimales();

} else {

    System.out.println("No se pudieron cargar los animales.");
}
System.out.println("\n=== CARGAR ANIMALES ===");

AnimalDatos nuevosDatos = new AnimalDatos(100);



if (cargado) {
    System.out.println("Animales cargados correctamente.");

    System.out.println("\n=== ANIMALES CARGADOS ===");

    nuevosDatos.listarAnimales();

} else {
    System.out.println("No se pudieron cargar los animales.");
}

System.out.println("\n=== MÓDULO DE ADOPTANTES ===");

AdoptanteDatos datosAdoptantes = new AdoptanteDatos(100);

Adoptante adoptante1 = new Adoptante(
        "1234567890101",
        "Juan Pérez",
        "55555555",
        "Ciudad de Guatemala",
        "Activo"
);

Adoptante adoptante2 = new Adoptante(
        "9876543210101",
        "María López",
        "44444444",
        "Mixco",
        "Activo"
);

datosAdoptantes.registrarAdoptante(adoptante1);
datosAdoptantes.registrarAdoptante(adoptante2);

System.out.println("\n=== ADOPTANTES REGISTRADOS ===");

datosAdoptantes.listarAdoptantes();

System.out.println("\n=== BUSCAR ADOPTANTE ===");

Adoptante encontradoAdoptante =
        datosAdoptantes.buscarAdoptante("1234567890101");

if (encontradoAdoptante != null) {

    System.out.println("Adoptante encontrado:");
    System.out.println(encontradoAdoptante);

} else {

    System.out.println("Adoptante no encontrado.");
}

System.out.println("\n=== EDITAR ADOPTANTE ===");

boolean actualizadoAdoptante =
        datosAdoptantes.editarAdoptante(
                "1234567890101",
                "Juan Pérez López",
                "55556666",
                "Villa Nueva"
        );

if (actualizadoAdoptante) {
    System.out.println("Adoptante actualizado correctamente.");
} else {
    System.out.println("No se encontró el adoptante.");
}

datosAdoptantes.listarAdoptantes();

System.out.println("\n=== ELIMINAR ADOPTANTE ===");

boolean eliminadoAdoptante =
        datosAdoptantes.eliminarAdoptante("9876543210101");

if (eliminadoAdoptante) {
    System.out.println("Adoptante eliminado correctamente.");
} else {
    System.out.println("No se encontró el adoptante.");
}

System.out.println("\n=== ADOPTANTES DESPUÉS DE ELIMINAR ===");

datosAdoptantes.listarAdoptantes();

System.out.println("\n=== GUARDAR ADOPTANTES ===");

AdoptantePersistencia persistenciaAdoptantes =
        new AdoptantePersistencia("adoptantes.txt");

boolean guardadoAdoptantes =
        persistenciaAdoptantes.guardar(datosAdoptantes);

if (guardadoAdoptantes) {
    System.out.println("Adoptantes guardados correctamente.");
} else {
    System.out.println("No se pudieron guardar los adoptantes.");
}

System.out.println("\n=== CARGAR ADOPTANTES ===");

AdoptanteDatos datosAdoptantesNuevos =
        new AdoptanteDatos(100);

boolean cargadosAdoptantes =
        persistenciaAdoptantes.cargar(datosAdoptantesNuevos);

if (cargadosAdoptantes) {

    System.out.println("Adoptantes cargados correctamente.");

    System.out.println("\n=== ADOPTANTES CARGADOS ===");

    datosAdoptantesNuevos.listarAdoptantes();

} else {
Adoptante adoptanteRepetido = new Adoptante(
        "1234567890101",
        "Pedro López",
        "33333333",
        "Guatemala",
        "Activo"
);

boolean registradoRepetido =
        datosAdoptantes.registrarAdoptante(adoptanteRepetido);

if (registradoRepetido) {
    System.out.println("El adoptante fue registrado.");
} else {
    System.out.println("No se pudo registrar: el DPI ya existe.");
}
    System.out.println("No se pudieron cargar los adoptantes.");
}

Adoptante dpiIncorrecto = new Adoptante(
        "123",
        "Carlos López",
        "33333333",
        "Guatemala",
        "Activo"
);

boolean registradoIncorrecto =
        datosAdoptantes.registrarAdoptante(dpiIncorrecto);

if (registradoIncorrecto) {
    System.out.println("El adoptante fue registrado.");
} else {
    System.out.println("No se pudo registrar: DPI inválido.");
}

Adoptante datosVacios = new Adoptante(
        "",
        "",
        "",
        "",
        "Activo"
);

boolean registradoVacio =
        datosAdoptantes.registrarAdoptante(datosVacios);

if (registradoVacio) {
    System.out.println("El adoptante fue registrado.");
} else {
    System.out.println("No se pudo registrar: existen datos vacíos.");
}


System.out.println("\n=== MÓDULO DE SOLICITUDES ===");

SolicitudDatos datosSolicitudes =
        new SolicitudDatos(
                100,
                datosAnimales,
                datosAdoptantes
        );

Solicitud solicitud1 = new Solicitud(
        "S001",
        "1234567890101",
        "A001",
        "05/09/2026",
        "Pendiente"
);

boolean registrada = datosSolicitudes.registrarSolicitud(solicitud1);

System.out.println();
System.out.println("===== PRUEBAS DEL DIA 4 - PASO 2 =====");

Solicitud solicitud2 = new Solicitud(
        "S002",
        "1234567890101",
        "A001",
        "05/09/2026",
        "Pendiente"
);

boolean registradaSolicitud2 =
        datosSolicitudes.registrarSolicitud(solicitud2);

System.out.println("Segunda solicitud para el mismo animal: "
        + registradaSolicitud2);

boolean estadoIncorrecto =
        datosSolicitudes.cambiarEstado(
                "S001",
                "CualquierEstado"
        );

System.out.println("Cambiar a estado incorrecto: "
        + estadoIncorrecto);

Solicitud solicitud3 = new Solicitud(
        "S003",
        "1234567890101",
        "A999",
        "05/09/2026",
        "Pendiente"
);

boolean animalInexistente =
        datosSolicitudes.registrarSolicitud(solicitud3);

System.out.println("Solicitud con animal inexistente: "
        + animalInexistente);

Solicitud solicitud4 = new Solicitud(
        "S004",
        "1111111111111",
        "A001",
        "05/09/2026",
        "Pendiente"
);

boolean adoptanteInexistente =
        datosSolicitudes.registrarSolicitud(solicitud4);

System.out.println("Solicitud con adoptante inexistente: "
        + adoptanteInexistente);



if (registrada) {
    System.out.println("Solicitud registrada correctamente.");
} else {
    System.out.println("No se pudo registrar la solicitud.");
}

System.out.println("\n=== SOLICITUDES REGISTRADAS ===");

datosSolicitudes.listarSolicitudes();

System.out.println("\n=== BUSCAR SOLICITUD ===");

Solicitud solicitudEncontrada =
        datosSolicitudes.buscarSolicitud("S001");

if (solicitudEncontrada != null) {

    System.out.println("Solicitud encontrada:");
    System.out.println(solicitudEncontrada);

} else {

    System.out.println("Solicitud no encontrada.");
}

System.out.println("\n=== CAMBIAR ESTADO ===");

boolean estadoActualizado =
        datosSolicitudes.cambiarEstado(
                "S001",
                "Aprobada"
        );

if (estadoActualizado) {
    System.out.println("Estado actualizado correctamente.");
} else {
    System.out.println("No se encontró la solicitud.");
}

datosSolicitudes.listarSolicitudes();



    }
}