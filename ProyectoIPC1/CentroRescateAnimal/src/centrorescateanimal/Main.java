package centrorescateanimal;
import datos.AnimalDatos;
import datos.AdoptanteDatos;
import datos.SolicitudDatos;
import datos.RescateDatos;

import modelo.Animal;
import modelo.Adoptante;
import modelo.Solicitud;
import modelo.Rescate;

import persistencia.AnimalPersistencia;
import persistencia.AdoptantePersistencia;
import persistencia.SolicitudPersistencia;

import persistencia.RescatePersistencia;
public class Main {

    public static void main(String[] args) {

        // =====================================================
        // MÓDULO DE ANIMALES
        // =====================================================

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


        // =====================================================
        // BUSCAR ANIMAL
        // =====================================================

        System.out.println("\n=== BUSCAR ANIMAL ===");

        Animal encontrado =
                datosAnimales.buscarAnimal("A001");

        if (encontrado != null) {

            System.out.println("Animal encontrado:");
            System.out.println(encontrado);

        } else {

            System.out.println("Animal no encontrado.");
        }


        // =====================================================
        // EDITAR ESTADO DEL ANIMAL
        // =====================================================

        System.out.println("\n=== EDITAR ESTADO ===");

        boolean actualizado =
                datosAnimales.editarEstado(
                        "A001",
                        "En tratamiento"
                );

        if (actualizado) {

            System.out.println(
                    "Estado actualizado correctamente."
            );

        } else {

            System.out.println(
                    "No se encontró el animal."
            );
        }

        System.out.println(
                "\n=== ANIMALES DESPUÉS DE EDITAR ==="
        );

        datosAnimales.listarAnimales();


        // =====================================================
        // ELIMINAR ANIMAL
        // =====================================================

        System.out.println("\n=== ELIMINAR ANIMAL ===");

        boolean eliminado =
                datosAnimales.eliminarAnimal("A002");

        if (eliminado) {

            System.out.println(
                    "Animal eliminado correctamente."
            );

        } else {

            System.out.println(
                    "No se encontró el animal."
            );
        }

        System.out.println(
                "\n=== ANIMALES DESPUÉS DE ELIMINAR ==="
        );

        datosAnimales.listarAnimales();


        // =====================================================
        // PERSISTENCIA DE ANIMALES
        // =====================================================

        System.out.println("\n=== GUARDAR ANIMALES ===");

        AnimalPersistencia persistenciaAnimales =
                new AnimalPersistencia("animales.txt");

        boolean guardadoAnimales =
                persistenciaAnimales.guardar(datosAnimales);

        if (guardadoAnimales) {

            System.out.println(
                    "Animales guardados correctamente."
            );

        } else {

            System.out.println(
                    "No se pudieron guardar los animales."
            );
        }


        // =====================================================
        // CARGAR ANIMALES
        // =====================================================

        System.out.println("\n=== SIMULAR CIERRE DEL PROGRAMA ===");

        AnimalDatos datosAnimalesNuevos =
                new AnimalDatos(100);

        System.out.println(
                "Se creó un arreglo nuevo."
        );

        System.out.println("\n=== CARGAR ANIMALES ===");

        boolean cargadoAnimales =
                persistenciaAnimales.cargar(
                        datosAnimalesNuevos
                );

        if (cargadoAnimales) {

            System.out.println(
                    "Animales cargados correctamente."
            );

            System.out.println(
                    "\n=== ANIMALES CARGADOS ==="
            );

            datosAnimalesNuevos.listarAnimales();

        } else {

            System.out.println(
                    "No se pudieron cargar los animales."
            );
        }


        // =====================================================
        // MÓDULO DE ADOPTANTES
        // =====================================================

        System.out.println(
                "\n=== MÓDULO DE ADOPTANTES ==="
        );

        AdoptanteDatos datosAdoptantes =
                new AdoptanteDatos(100);

        Adoptante adoptante1 =
                new Adoptante(
                        "1234567890101",
                        "Juan Pérez",
                        "55555555",
                        "Ciudad de Guatemala",
                        "Activo"
                );

        Adoptante adoptante2 =
                new Adoptante(
                        "9876543210101",
                        "María López",
                        "44444444",
                        "Mixco",
                        "Activo"
                );

        datosAdoptantes.registrarAdoptante(adoptante1);
        datosAdoptantes.registrarAdoptante(adoptante2);

        System.out.println(
                "\n=== ADOPTANTES REGISTRADOS ==="
        );

        datosAdoptantes.listarAdoptantes();


        // =====================================================
        // BUSCAR ADOPTANTE
        // =====================================================

        System.out.println(
                "\n=== BUSCAR ADOPTANTE ==="
        );

        Adoptante encontradoAdoptante =
                datosAdoptantes.buscarAdoptante(
                        "1234567890101"
                );

        if (encontradoAdoptante != null) {

            System.out.println(
                    "Adoptante encontrado:"
            );

            System.out.println(
                    encontradoAdoptante
            );

        } else {

            System.out.println(
                    "Adoptante no encontrado."
            );
        }


        // =====================================================
        // EDITAR ADOPTANTE
        // =====================================================

        System.out.println(
                "\n=== EDITAR ADOPTANTE ==="
        );

        boolean actualizadoAdoptante =
                datosAdoptantes.editarAdoptante(
                        "1234567890101",
                        "Juan Pérez López",
                        "55556666",
                        "Villa Nueva"
                );

        if (actualizadoAdoptante) {

            System.out.println(
                    "Adoptante actualizado correctamente."
            );

        } else {

            System.out.println(
                    "No se encontró el adoptante."
            );
        }

        datosAdoptantes.listarAdoptantes();


        // =====================================================
        // ELIMINAR ADOPTANTE
        // =====================================================

        System.out.println(
                "\n=== ELIMINAR ADOPTANTE ==="
        );

        boolean eliminadoAdoptante =
                datosAdoptantes.eliminarAdoptante(
                        "9876543210101"
                );

        if (eliminadoAdoptante) {

            System.out.println(
                    "Adoptante eliminado correctamente."
            );

        } else {

            System.out.println(
                    "No se encontró el adoptante."
            );
        }

        System.out.println(
                "\n=== ADOPTANTES DESPUÉS DE ELIMINAR ==="
        );

        datosAdoptantes.listarAdoptantes();


        // =====================================================
        // VALIDAR ADOPTANTE REPETIDO
        // =====================================================

        System.out.println(
                "\n=== VALIDAR DPI REPETIDO ==="
        );

        Adoptante adoptanteRepetido =
                new Adoptante(
                        "1234567890101",
                        "Pedro López",
                        "33333333",
                        "Guatemala",
                        "Activo"
                );

        boolean registradoRepetido =
                datosAdoptantes.registrarAdoptante(
                        adoptanteRepetido
                );

        if (registradoRepetido) {

            System.out.println(
                    "El adoptante fue registrado."
            );

        } else {

            System.out.println(
                    "No se pudo registrar: el DPI ya existe."
            );
        }


        // =====================================================
        // VALIDAR DPI INCORRECTO
        // =====================================================

        System.out.println(
                "\n=== VALIDAR DPI INCORRECTO ==="
        );

        Adoptante dpiIncorrecto =
                new Adoptante(
                        "123",
                        "Carlos López",
                        "33333333",
                        "Guatemala",
                        "Activo"
                );

        boolean registradoIncorrecto =
                datosAdoptantes.registrarAdoptante(
                        dpiIncorrecto
                );

        if (registradoIncorrecto) {

            System.out.println(
                    "El adoptante fue registrado."
            );

        } else {

            System.out.println(
                    "No se pudo registrar: DPI inválido."
            );
        }


        // =====================================================
        // VALIDAR DATOS VACÍOS
        // =====================================================

        System.out.println(
                "\n=== VALIDAR DATOS VACÍOS ==="
        );

        Adoptante datosVacios =
                new Adoptante(
                        "",
                        "",
                        "",
                        "",
                        "Activo"
                );

        boolean registradoVacio =
                datosAdoptantes.registrarAdoptante(
                        datosVacios
                );

        if (registradoVacio) {

            System.out.println(
                    "El adoptante fue registrado."
            );

        } else {

            System.out.println(
                    "No se pudo registrar: existen datos vacíos."
            );
        }


        // =====================================================
        // PERSISTENCIA DE ADOPTANTES
        // =====================================================

        System.out.println(
                "\n=== GUARDAR ADOPTANTES ==="
        );

        AdoptantePersistencia persistenciaAdoptantes =
                new AdoptantePersistencia(
                        "adoptantes.txt"
                );

        boolean guardadoAdoptantes =
                persistenciaAdoptantes.guardar(
                        datosAdoptantes
                );

        if (guardadoAdoptantes) {

            System.out.println(
                    "Adoptantes guardados correctamente."
            );

        } else {

            System.out.println(
                    "No se pudieron guardar los adoptantes."
            );
        }


        // =====================================================
        // CARGAR ADOPTANTES
        // =====================================================

        System.out.println(
                "\n=== CARGAR ADOPTANTES ==="
        );

        AdoptanteDatos datosAdoptantesNuevos =
                new AdoptanteDatos(100);

        boolean cargadosAdoptantes =
                persistenciaAdoptantes.cargar(
                        datosAdoptantesNuevos
                );

        if (cargadosAdoptantes) {

            System.out.println(
                    "Adoptantes cargados correctamente."
            );

            System.out.println(
                    "\n=== ADOPTANTES CARGADOS ==="
            );

            datosAdoptantesNuevos.listarAdoptantes();

        } else {

            System.out.println(
                    "No se pudieron cargar los adoptantes."
            );
        }


        // =====================================================
        // MÓDULO DE SOLICITUDES
        // =====================================================

        System.out.println(
                "\n=== MÓDULO DE SOLICITUDES ==="
        );

        SolicitudDatos datosSolicitudes =
                new SolicitudDatos(
                        100,
                        datosAnimales,
                        datosAdoptantes
                );


        // =====================================================
        // CREAR SOLICITUD
        // =====================================================

        Solicitud solicitud1 =
                new Solicitud(
                        "S001",
                        "1234567890101",
                        "A001",
                        "05/09/2026",
                        "Pendiente"
                );

        boolean registrada =
                datosSolicitudes.registrarSolicitud(
                        solicitud1
                );

        if (registrada) {

            System.out.println(
                    "Solicitud S001 registrada correctamente."
            );

        } else {

            System.out.println(
                    "No se pudo registrar la solicitud S001."
            );
        }


        // =====================================================
        // REGLAS DE NEGOCIO
        // =====================================================

        System.out.println(
                "\n===== PRUEBAS DEL DÍA 4 - PASO 2 ====="
        );


        // -----------------------------------------------------
        // Segunda solicitud para el mismo animal
        // -----------------------------------------------------

        Solicitud solicitud2 =
                new Solicitud(
                        "S002",
                        "1234567890101",
                        "A001",
                        "05/09/2026",
                        "Pendiente"
                );

        boolean registradaSolicitud2 =
                datosSolicitudes.registrarSolicitud(
                        solicitud2
                );

        System.out.println(
                "Segunda solicitud para el mismo animal: "
                + registradaSolicitud2
        );


        // -----------------------------------------------------
        // Aprobar solicitud
        // -----------------------------------------------------

        boolean aprobada =
                datosSolicitudes.cambiarEstado(
                        "S001",
                        "Aprobada"
                );

        System.out.println(
                "Solicitud S001 aprobada: "
                + aprobada
        );

        System.out.println(
                "Estado actual del animal A001: "
                + datosAnimales
                        .buscarAnimal("A001")
                        .getEstado()
        );


        // -----------------------------------------------------
        // Estado incorrecto
        // -----------------------------------------------------

        boolean estadoIncorrecto =
                datosSolicitudes.cambiarEstado(
                        "S001",
                        "CualquierEstado"
                );

        System.out.println(
                "Cambiar a estado incorrecto: "
                + estadoIncorrecto
        );


        // -----------------------------------------------------
        // Animal inexistente
        // -----------------------------------------------------

        Solicitud solicitud3 =
                new Solicitud(
                        "S003",
                        "1234567890101",
                        "A999",
                        "05/09/2026",
                        "Pendiente"
                );

        boolean animalInexistente =
                datosSolicitudes.registrarSolicitud(
                        solicitud3
                );

        System.out.println(
                "Solicitud con animal inexistente: "
                + animalInexistente
        );


        // -----------------------------------------------------
        // Adoptante inexistente
        // -----------------------------------------------------

        Solicitud solicitud4 =
                new Solicitud(
                        "S004",
                        "1111111111111",
                        "A001",
                        "05/09/2026",
                        "Pendiente"
                );

        boolean adoptanteInexistente =
                datosSolicitudes.registrarSolicitud(
                        solicitud4
                );

        System.out.println(
                "Solicitud con adoptante inexistente: "
                + adoptanteInexistente
        );


        // =====================================================
        // LISTAR SOLICITUDES
        // =====================================================

        System.out.println(
                "\n=== SOLICITUDES REGISTRADAS ==="
        );

        datosSolicitudes.listarSolicitudes();


        // =====================================================
        // BUSCAR SOLICITUD
        // =====================================================

        System.out.println(
                "\n=== BUSCAR SOLICITUD ==="
        );

        Solicitud solicitudEncontrada =
                datosSolicitudes.buscarSolicitud(
                        "S001"
                );

        if (solicitudEncontrada != null) {

            System.out.println(
                    "Solicitud encontrada:"
            );

            System.out.println(
                    solicitudEncontrada
            );

        } else {

            System.out.println(
                    "Solicitud no encontrada."
            );
        }


        // =====================================================
        // PERSISTENCIA DE SOLICITUDES
        // =====================================================

        System.out.println(
                "\n=== GUARDAR SOLICITUDES ==="
        );

        SolicitudPersistencia persistenciaSolicitudes =
                new SolicitudPersistencia(
                        "solicitudes.txt"
                );

        boolean guardadoSolicitudes =
                persistenciaSolicitudes.guardar(
                        datosSolicitudes
                );

        if (guardadoSolicitudes) {

            System.out.println(
                    "Solicitudes guardadas correctamente."
            );

        } else {

            System.out.println(
                    "No se pudieron guardar las solicitudes."
            );
        }


        // =====================================================
        // CARGAR SOLICITUDES
        // =====================================================

        System.out.println(
                "\n=== CARGAR SOLICITUDES ==="
        );

        SolicitudDatos datosSolicitudesCargadas =
                new SolicitudDatos(
                        100,
                        datosAnimales,
                        datosAdoptantes
                );

        boolean cargadoSolicitudes =
                persistenciaSolicitudes.cargar(
                        datosSolicitudesCargadas
                );

        if (cargadoSolicitudes) {

            System.out.println(
                    "Solicitudes cargadas correctamente."
            );

            System.out.println(
                    "\n=== SOLICITUDES CARGADAS ==="
            );

            datosSolicitudesCargadas.listarSolicitudes();

        } else {

            System.out.println(
                    "No se pudieron cargar las solicitudes."
            );
        }

        
        // ==================================================
// PRUEBAS DEL MODULO DE RESCATES
// ==================================================

System.out.println("\n=================================");
System.out.println("PRUEBAS DEL MODULO DE RESCATES");
System.out.println("=================================");

RescateDatos datosRescates = new RescateDatos(100);

// Registrar rescates

Rescate rescate1 = new Rescate(
        "R001",
        "06/09/2026",
        "Zona 1",
        "Perro",
        "Perro encontrado en la calle.",
        "Registrado"
);

Rescate rescate2 = new Rescate(
        "R002",
        "06/09/2026",
        "Zona 12",
        "Gato",
        "Gato encontrado en una vivienda abandonada.",
        "Registrado"
);

System.out.println(
        "Registrar R001: "
        + datosRescates.registrarRescate(rescate1)
);

System.out.println(
        "Registrar R002: "
        + datosRescates.registrarRescate(rescate2)
);

// Listar rescates

System.out.println("\nRescates registrados:");

datosRescates.listarRescates();

// Buscar rescate

System.out.println("\nBuscar R001:");

Rescate rescateEncontrado =
        datosRescates.buscarRescate("R001");

if (rescateEncontrado != null) {
    System.out.println(rescateEncontrado);
} else {
    System.out.println("Rescate no encontrado.");
}

// Cambiar estado

System.out.println("\nCambiar estado de R001:");

boolean cambioEstado =
        datosRescates.cambiarEstado(
                "R001",
                "En atención"
        );

System.out.println(
        "Cambio realizado: " + cambioEstado
);

System.out.println(
        datosRescates.buscarRescate("R001")
);

// Probar código duplicado

System.out.println("\nProbar rescate duplicado:");

Rescate rescateDuplicado = new Rescate(
        "R001",
        "07/09/2026",
        "Zona 3",
        "Perro",
        "Otro caso.",
        "Registrado"
);

System.out.println(
        "Registrar duplicado: "
        + datosRescates.registrarRescate(rescateDuplicado)
);

// Probar estado inválido

System.out.println("\nProbar estado inválido:");

Rescate rescateInvalido = new Rescate(
        "R003",
        "07/09/2026",
        "Zona 5",
        "Gato",
        "Caso de prueba.",
        "EstadoInventado"
);

System.out.println(
        "Registrar estado inválido: "
        + datosRescates.registrarRescate(rescateInvalido)
);

// Eliminar lógicamente

System.out.println("\nEliminar R002:");



System.out.println(
        "Eliminación realizada: " + eliminado
);

// Listar nuevamente

System.out.println("\nRescates después de las pruebas:");

datosRescates.listarRescates();

// ==================================================
// PRUEBA DE PERSISTENCIA DE RESCATES
// ==================================================

System.out.println("\n=================================");
System.out.println("PERSISTENCIA DE RESCATES");
System.out.println("=================================");

RescatePersistencia persistenciaRescates =
        new RescatePersistencia("rescates.txt");

// Guardar

boolean guardadoRescates =
        persistenciaRescates.guardar(datosRescates);

System.out.println(
        "Rescates guardados: "
        + guardadoRescates
);

// Crear una estructura nueva

RescateDatos rescatesCargados =
        new RescateDatos(100);

// Cargar

boolean cargadoRescates =
        persistenciaRescates.cargar(
                rescatesCargados
        );

System.out.println(
        "Rescates cargados: "
        + cargadoRescates
);

// Mostrar datos cargados

System.out.println("\nRescates recuperados del archivo:");

rescatesCargados.listarRescates();

        // =====================================================
        // FIN
        // =====================================================

        System.out.println(
                "\n=== FIN DE LAS PRUEBAS ==="
        );
    }
}