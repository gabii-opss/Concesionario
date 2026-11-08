# Concesionario

## Descripción y objetivo

El proyecto **Concesionario** es un sistema desarrollado en Java, diseñado para centralizar y administrar de manera eficiente las operaciones principales de un concesionario de vehículos. La aplicación permite gestionar la información de clientes, asesores y catálogo de vehículos con existencias, así como realizar los procesos de preventa, venta y generación de facturas.

El objetivo principal es construir una plataforma robusta y mantenible aplicando buenas prácticas de desarrollo, arquitectura por capas y patrones de diseño de software a lo largo de la asignatura.

## Problema que busca resolver

Un concesionario que lleva el registro de clientes, ventas y preventas de forma manual tiene dificultades para saber qué vehículos hay disponibles, qué asesor atiende a cada cliente y cuánto debe pagar el cliente según la modalidad de pago. El sistema centraliza esa información y automatiza el cálculo de la factura.

## Alcance

El alcance actual abarca la gestión de datos maestros y transacciones comerciales de un concesionario vehicular. Permite la administración en memoria de usuarios (clientes y asesores), el inventario de autos y el flujo comercial completo, desde la asignación de preventa hasta la emisión final de la factura de venta.

No incluye todavía persistencia en base de datos, autenticación de usuarios ni reportes exportables.

## Funcionalidades implementadas

- **Gestión de clientes y asesores:** registro y consulta de la información básica de los usuarios, con asignación automática de asesor.
- **Catálogo de vehículos:** control de inventario de vehículos disponibles, con detalle de existencias.
- **Gestión de preventas y ventas:** registro de intenciones de compra (estado PREVENTA) y formalización de la transacción de venta (estado COMPRA).
- **Compras con tres formas de pago:** contado, crédito y leasing, con cálculo de cuotas con interés.
- **Facturación:** generación del soporte de facturación derivado de las ventas, con el plan de financiación cuando aplica.
- **Validaciones y manejo de errores:** cuotas mayores que cero, entidad financiera obligatoria en leasing, cuota inicial válida, inventario disponible y mensajes claros para el usuario mediante `PagoInvalidoException`.
- **Control de inventario y desempeño:** al concretar la compra se descuenta la existencia del vehículo y se registra la venta del asesor.
- **Pruebas automatizadas:** 23 casos con JUnit 4 sobre las estrategias de pago, la fábrica y el servicio de compras.

## Tecnologías utilizadas

- **Lenguaje de programación:** Java (JDK 21 o superior)
- **Interfaz gráfica:** Swing
- **IDE:** Apache NetBeans / Visual Studio Code
- **Gestor de construcción:** Apache Ant (`build.xml`, proyecto de NetBeans)
- **Pruebas:** JUnit 4
- **Control de versiones:** Git y GitHub

## Estructura actual del proyecto

```
Concesionario/
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
│   ├── README.md      
└── docs/
    ├── descripcion_proyecto.md
```

## Patrones y decisiones de construcción

El proyecto aplica un patrón principal, implementado en esta entrega, y varios patrones de arquitectura presentes en la base del código.

### Patrones de diseño implementados

#### 1. Strategy (patrón principal)
- **Problema:** el cálculo del pago dependía de `switch` por tipo (contado, crédito, leasing) repetidos en la calculadora y en la interfaz, y existían dos jerarquías duplicadas con reglas distintas.
- **Justificación:** las formas de pago comparten un objetivo (completar la factura) pero con reglas diferentes, y pueden aparecer nuevas.
- **Aplicación:** cada forma de pago es una estrategia intercambiable que valida sus datos y calcula su parte de la factura.
- **Componentes:** `EstrategiaPago` (interfaz), `EstrategiaContado`, `EstrategiaCredito`, `EstrategiaLeasing`, `CalculadoraPago` (contexto) y `CalculoFinanciero` (fórmula compartida).
- **Beneficios:** sin `switch`, código duplicado eliminado, nuevas formas de pago sin modificar las existentes y pruebas por estrategia.

#### 2. Factory (fábrica simple con registro)
- **Problema:** la interfaz entrega un texto ("CRÉDITO", "Leasing") y alguien debe traducirlo a la estrategia correcta.
- **Aplicación:** `FabricaEstrategiaPago` normaliza el texto (tildes y mayúsculas), devuelve la estrategia y permite registrar nuevas con `registrar(...)`. Un tipo desconocido lanza `PagoInvalidoException`.
- **Beneficio:** la decisión de qué estrategia usar queda en un solo lugar.

### Patrones de arquitectura presentes en el código

| Patrón / principio | Dónde se ve | Para qué sirve |
|---|---|---|
| **Repository** | `Repositorio/` (`iRepoCliente`, `iRepoVehiculo`, `iRepoCompra`, `iRepoAsesor` y sus implementaciones en memoria) | Separa el acceso a los datos de la lógica de negocio; permite cambiar la memoria por una base de datos sin tocar los servicios |
| **Service Layer** | `Servicio/` (`ClientService`, `VehiculoService`, `CompraService`) | Concentra las reglas de negocio fuera de la interfaz gráfica |
| **Facade** | `ConcesionarioService` | Ofrece a la interfaz Swing un único punto de acceso a clientes, vehículos, asesores, compras y reportes |
| **Inyección de dependencias** | Constructores de `CompraService`, `ClientService` y `ConcesionarioService`; se ensamblan en `Registro_cliente.main` | Las clases dependen de interfaces, no de implementaciones concretas, lo que facilita las pruebas |
| **Abstracción por interfaz** | `iServicioNotificacion` / `ServicioNotificacionSwing` | La lógica de negocio no depende de Swing; se puede cambiar la forma de notificar |
| **Segregación de interfaces** | `iBuscablePorId`, `iEliminable`, `iActualizable`, `iRepositorioBase<T>` | Interfaces pequeñas y genéricas que cada repositorio combina según lo que necesita |
| **DTO (objeto de transferencia)** | `DatosFactura`, `DatosAdicionales` | Transportan los datos de la factura y los datos opcionales del pago entre capas |
| **Herencia y polimorfismo** | `Persona` → `Cliente`, `Asesor` | Reutiliza atributos comunes de las personas |


## Integrantes del equipo

| Gabriela Puentes |
| Manuel Cifuentes |
| Cesar Lozano     |
| Sebastian Medina |

## Estado actual del proyecto

Segunda entrega: núcleo funcional completo (clientes, asesores, catálogo, preventas y compras con tres formas de pago), patrón Strategy + Factory implementado, validaciones, manejo de errores y pruebas automatizadas.

