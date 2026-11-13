# Pruebas

Pruebas automatizadas con **JUnit 4**.

| Archivo | Qué verifica |
|---|---|
| `Pagos/FabricaEstrategiaPagoTest.java` | La fábrica devuelve la estrategia correcta, ignora tildes/mayúsculas, falla con tipos desconocidos y permite registrar nuevas formas de pago |
| `Pagos/EstrategiasPagoTest.java` | Cálculo y validaciones de contado, crédito y leasing |
| `Servicio/CompraServiceTest.java` | Integración: compra, factura, descuento de inventario y manejo de errores |
| `Servicio/ConcesionarioServiceTest.java` | Búsquedas (cliente, asesor, vehículo, compra), asociación cliente-vehículo, inventario e ids de compra únicos |

## Cómo ejecutarlas
En NetBeans: clic derecho en *Test Libraries* > *Add Library* > **JUnit 4.x**, luego clic derecho en el proyecto > **Test**.
Los comandos por consola están en el `README.md` de la raíz.
