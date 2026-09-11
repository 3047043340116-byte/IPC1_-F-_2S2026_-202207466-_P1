# Diagramas para documentación

## Módulos
```text
                         CENTRO DE RESCATE ANIMAL
                                  |
        -----------------------------------------------------------
        |          |           |          |          |            |
    Animales   Adoptantes  Solicitudes  Rescates  Ubicaciones  Reportes
                                                               |
                                                         Bitácora HTML
```

## Matriz
```text
                 ESPACIOS
             1       2       3       4       5
Recepción    [ ]     [ ]     [ ]     [ ]     [ ]
Cuarentena   [ ]     [ ]     [ ]     [ ]     [ ]
Recuperación [ ]     [ ]     [ ]     [ ]     [ ]
Adopción     [ ]     [ ]     [ ]     [ ]     [ ]
```

## Flujo
```text
INICIO
  |
 LOGIN
  |
 ¿Credenciales válidas? -- NO --> Mensaje de error --+
  |                                                   |
 SI                                                  LOGIN
  |
 MENÚ PRINCIPAL
  |
 Seleccionar módulo
  |
 Validar datos
  |
 Ejecutar operación
  |
 Guardar archivo + bitácora
  |
 Actualizar tabla
  |
 Regresar al menú
```
