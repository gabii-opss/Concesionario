# Descripción del proyecto

## 1. Descripción general de la solución
Aplicación en Java que administra las operaciones de un concesionario de vehículos: clientes, asesores, catálogo con existencias, procesos de preventa y venta, y facturación. El sistema centraliza la información para evitar errores del manejo manual del inventario.

Al ejecutarse abre un menú desde el cual se puede registrar clientes, ver los listados de clientes y asesores, ver el catálogo con sus existencias y realizar preventas y ventas. En una venta se asigna un asesor al cliente; si el cliente compra, se muestra la factura y las existencias del vehículo disminuyen. El sistema trae 3 asesores preregistrados y permite registrar más.

## 2. Módulos o componentes y sus responsabilidades
> Completar o ajustar según el código real del proyecto.

| Componente | Responsabilidad |
| --- | --- |
| `Main` | Punto de entrada: abre el menú principal. |
| `Persona`, `Cliente`, `Asesor` | Representan a las personas del sistema y guardan sus datos. |
| `Vehiculo` | Representa un vehículo del catálogo y sus existencias. |
| `Compra` | Representa una compra: relaciona cliente, vehículo y asesor. |
| `Pagos` | Manejo de los pagos asociados a una compra. |
| `Repositorio` | Almacenamiento y consulta de los datos (clientes, asesores, vehículos). |
| `Servicio` | Lógica del negocio: registro, preventa, venta y actualización de existencias. |
| `Swing` | Interfaz gráfica del sistema. |

## 3. Tecnologías y herramientas
- Java como lenguaje de programación.
- NetBeans (proyecto Ant) como entorno de desarrollo.
- Swing para la interfaz gráfica.
- Git y GitHub para el control de versiones.

## 4. Organización del desarrollo
- El código fuente está en `src/`, organizado por paquetes según su responsabilidad.
- Cada integrante trabaja en su propia rama y la integra a `main` con revisión del equipo.
- Los commits son pequeños y con mensajes claros.
- La documentación está en `docs/` y las pruebas en `tests/`.
- El proyecto avanza por entregas: primero la base, luego los patrones de diseño y las pruebas.
