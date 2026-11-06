# Concesionario

## Descripción y objetivo

El proyecto **Concesionario** es un sistema desarrollado en Java diseñado para centralizar y administrar de manera eficiente las operaciones principales de un concesionario de vehículos[span_0](start_span)[span_0](end_span). La aplicación permite gestionar la información de clientes, asesores, catálogo de vehículos con existencias, así como realizar los procesos de preventa, venta y generación de facturas[span_1](start_span)[span_1](end_span). 

El objetivo principal es construir una plataforma robusta y mantenible aplicando buenas prácticas de desarrollo, arquitectura por capas y patrones de diseño de software a lo largo de la asignatura[span_2](start_span)[span_2](end_span).

## Alcance

El alcance actual abarca la gestión de datos maestros y transacciones comerciales de un concesionario vehicular[span_3](start_span)[span_3](end_span). Permite la administración en memoria / base de datos de usuarios (clientes y asesores), inventario de autos, y el flujo comercial completo desde la asignación de preventa hasta la emisión final de la factura de venta[span_4](start_span)[span_4](end_span).

## Funcionalidades implementadas

- **Gestión de Clientes y Asesores:** Registro, consulta y actualización de la información básica de usuarios[span_5](start_span)[span_5](end_span).
- **Catálogo de Vehículos:** Control de inventario de vehículos disponibles con detalles de existencias[span_6](start_span)[span_6](end_span).
- **Gestión de Preventas y Ventas:** Registro de intenciones de compra y formalización de la transacción de venta[span_7](start_span)[span_7](end_span).
- **Facturación:** Generación de soporte de facturación derivado de las ventas de vehículos[span_8](start_span)[span_8](end_span).

## Tecnologías utilizadas

- **Lenguaje de Programación:** Java
- **IDE:** Apache NetBeans / Visual Studio Code[span_9](start_span)[span_9](end_span)
- **Gestor de Construcción:** Maven
- **Control de Versiones:** Git y GitHub[span_10](start_span)[span_10](end_span)[span_11](start_span)[span_11](end_span)

## Estructura actual del proyecto

Concesionarioo/
├── README.md
├── .gitignore
├── build.xml, manifest.mf, nbproject/     # configuración NetBeans / Ant
├── src/
│   ├── concesionarioo/    # Modelos: Persona, Cliente, Asesor, Vehiculo, Compra
│   ├── Repositorio/       # Interfaces y repositorios en memoria
│   ├── Servicio/          # Lógica de negocio: CompraService, CalculadoraPago, notificaciones...
│   ├── Pagos/             # Patrón Strategy + Factory (formas de pago)
│   └── Swing/             # Interfaz gráfica (formularios y menú)
├── tests/
│   ├── Pagos/             # Pruebas de estrategias y fábrica
│   └── Servicio/          # Prueba de integración de CompraService
└── docs/
    ├── descripcion_proyecto.md
