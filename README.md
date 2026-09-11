# Centro de Rescate Animal - IPC1

Proyecto Java Swing para gestionar animales, adoptantes, solicitudes de adopción, rescates, ubicaciones, reportes HTML y bitácora.

## Requisitos
- Java 17 o superior.
- NetBeans u otro IDE compatible con Ant.
- El proyecto usa arreglos estáticos y matrices; no usa ArrayList, HashMap, List, Queue, Stack ni Vector.

## Ejecución
1. Abrir la carpeta `CentroRescateAnimal` en NetBeans.
2. Verificar Java 17+ en las propiedades del proyecto.
3. Ejecutar `centrorescateanimal.Main`.
4. Credenciales de demostración:
   - Administrador: `admin` / `admin123`
   - Auxiliar: `auxiliar` / `auxiliar123`

## Módulos
- Animales: registrar, buscar por código/nombre/especie/estado, actualizar estado y eliminar lógicamente.
- Adoptantes: registrar, buscar, editar y eliminar lógicamente.
- Solicitudes: registrar, buscar, cambiar/atender estados y validar animal/adoptante.
- Rescates: registrar, buscar, cambiar prioridad/estado y atender.
- Ubicaciones: matriz 4x5, consulta, asignación, liberación y control de ocupación.
- Reportes: animales, adopciones, ocupación y bitácora en HTML.
- Datos del estudiante: información editable y persistente.

## Archivos de datos
`animales.txt`, `adoptantes.txt`, `solicitudes.txt`, `rescates.txt`, `espacios.txt`, `usuarios.txt`, `bitacora.txt` y `estudiante.txt`.

## Nota sobre datos personales
El archivo `estudiante.txt` contiene valores de reemplazo. Antes de entregar, el estudiante debe escribir su propio nombre, carné y sección.

## Nota sobre la interfaz
La versión entregada conserva las ventanas que ya estaban creadas en NetBeans GUI Builder y se completó su lógica. Si la cátedra exige estrictamente construcción manual por código, debe verificarse esta excepción con el docente antes de entregar.
