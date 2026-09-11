# Manual técnico

## Arquitectura
- `modelo`: entidades del sistema.
- `datos`: arreglos estáticos y reglas de negocio.
- `persistencia`: lectura/escritura de archivos de texto.
- `interfaz`: ventanas Swing.
- `reportes`: generación de HTML.
- `util`: bitácora.
- `controlador`: autenticación.

## Estructuras
- Animales: `Animal[]` de capacidad 100.
- Adoptantes: `Adoptante[]` de capacidad 100.
- Solicitudes: `Solicitud[]` de capacidad 100.
- Rescates: `Rescate[]` de capacidad 100.
- Usuarios: `Usuario[]` de capacidad 100.
- Ubicaciones: `EspacioRefugio[4][5]`.

## Validaciones principales
Se controlan campos vacíos, códigos/DPI duplicados, estados válidos, capacidad máxima, adoptantes activos, animales inexistentes, animales adoptados/eliminados, espacios ocupados y transiciones inválidas de solicitudes/rescates.

## Persistencia
Los módulos principales escriben archivos `.txt` con campos separados por `;`. Al iniciar cada ventana, sus datos se cargan desde los archivos correspondientes.

## Reportes
`ReporteHTML` genera cuatro archivos HTML con fecha y hora: animales, adopciones, ocupación y bitácora.

## Bitácora
`BitacoraUtil` registra fecha, usuario, acción y detalle en `bitacora.txt`.

## Diagrama de flujo general
Inicio -> Login -> Menú principal -> módulo seleccionado -> validar -> ejecutar operación -> guardar datos -> registrar bitácora -> actualizar interfaz -> volver al menú.

## Matriz de ubicaciones
Filas: Recepción, Cuarentena, Recuperación, Adopción.
Columnas: espacios 1, 2, 3, 4 y 5.
Cada celda guarda el código del animal o una cadena vacía si está disponible.
