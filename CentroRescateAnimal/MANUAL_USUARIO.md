# Manual de usuario

## 1. Inicio de sesión
Ejecute el proyecto. Ingrese usuario y contraseña. Los usuarios de demostración son `admin/admin123` y `auxiliar/auxiliar123`.

## 2. Animales
Registre código, nombre, especie y estado. Puede buscar por código, nombre, especie o estado. Seleccione un registro para actualizar su estado o eliminarlo lógicamente.

## 3. Adoptantes
Ingrese DPI de 13 dígitos, nombre, teléfono, dirección y estado. El DPI no puede repetirse. Seleccione un registro para editarlo o eliminarlo lógicamente.

## 4. Solicitudes
Una solicitud necesita un código único, un adoptante activo y un animal existente que no esté eliminado ni adoptado. Una solicitud pendiente puede ser aprobada o rechazada. Al aprobarla, el animal pasa a `Adoptado`.

## 5. Rescates
Registre código, fecha, ubicación, especie, descripción y prioridad. Los estados siguen el flujo `Registrado -> En atención -> Completado`.

## 6. Ubicaciones
La matriz tiene 4 áreas y 5 espacios por área. Seleccione área y espacio para consultar. Para asignar un animal, escriba su código. No se permite ocupar un espacio ocupado ni ubicar dos veces al mismo animal. La liberación solicita confirmación.

## 7. Reportes
Cada botón genera un archivo HTML con fecha y hora en el nombre. Se generan reportes de animales, adopciones, ocupación y bitácora.

## 8. Datos del estudiante
Escriba su nombre, carné y sección y pulse Guardar. Estos datos quedan en `estudiante.txt`.
