package Pagos;

import Servicio.DatosAdicionales;
import Servicio.DatosFactura;

/**
 * PATRÓN STRATEGY - Interfaz común de todas las formas de pago.
 *
 * Cada forma de pago (contado, crédito, leasing) encapsula su propia regla de
 * cálculo y de validación. Quien la usa (CalculadoraPago) no necesita saber
 * cuál es: solo invoca {@link #aplicar}.
 *
 * Para agregar una nueva forma de pago basta con crear una clase que
 * implemente esta interfaz y registrarla en {@link FabricaEstrategiaPago}.
 */
public interface EstrategiaPago {

    /** Nombre normalizado de la forma de pago (MAYÚSCULAS, sin tildes). Ej: "CREDITO". */
    String getNombre();

    /**
     * Valida los datos adicionales y completa la factura con los cálculos
     * propios de esta forma de pago.
     *
     * @throws PagoInvalidoException si los datos adicionales no son válidos
     */
    void aplicar(DatosFactura factura, DatosAdicionales datos);
}
