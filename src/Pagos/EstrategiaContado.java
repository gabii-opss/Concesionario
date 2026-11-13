package Pagos;

import Servicio.DatosAdicionales;
import Servicio.DatosFactura;

/** Estrategia concreta: pago de contado. No genera financiación. */
public class EstrategiaContado implements EstrategiaPago {

    @Override
    public String getNombre() {
        return "CONTADO";
    }

    @Override
    public void aplicar(DatosFactura factura, DatosAdicionales datos) {
        // Al contado se paga el monto total: no hay cuotas, intereses ni entidad.
    }
}
