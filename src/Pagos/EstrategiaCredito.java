package Pagos;

import Servicio.DatosAdicionales;
import Servicio.DatosFactura;

/** Estrategia concreta: crédito directo con cuota fija mensual. */
public class EstrategiaCredito implements EstrategiaPago {

    static final int CUOTAS_POR_DEFECTO = 12;

    @Override
    public String getNombre() {
        return "CREDITO";
    }

    @Override
    public void aplicar(DatosFactura factura, DatosAdicionales datos) {
        int cuotas = CUOTAS_POR_DEFECTO;
        if (datos != null && datos.getNumeroCuotas() != null) {
            cuotas = datos.getNumeroCuotas();
        }
        if (cuotas <= 0) {
            throw new PagoInvalidoException("El número de cuotas debe ser mayor que cero.");
        }

        factura.setNumeroCuotas(cuotas);
        factura.setValorCuota(
                CalculoFinanciero.cuotaMensual(factura.getMontoTotal(), cuotas, CalculoFinanciero.TASA_MENSUAL));
    }
}
