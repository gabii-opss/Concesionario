package Pagos;

import Servicio.DatosAdicionales;
import Servicio.DatosFactura;

/**
 * Estrategia concreta: leasing. Exige entidad financiera, usa una cuota inicial
 * (10 % del valor si no se indica otra) y financia el resto en cuotas.
 */
public class EstrategiaLeasing implements EstrategiaPago {

    static final double PORCENTAJE_INICIAL = 0.10;
    static final int CUOTAS_POR_DEFECTO = 36;

    @Override
    public String getNombre() {
        return "LEASING";
    }

    @Override
    public void aplicar(DatosFactura factura, DatosAdicionales datos) {
        if (datos == null || datos.getEntidadFinanciera() == null
                || datos.getEntidadFinanciera().isBlank()) {
            throw new PagoInvalidoException("El leasing requiere una entidad financiera.");
        }

        double monto = factura.getMontoTotal();

        int cuotas = datos.getNumeroCuotas() != null ? datos.getNumeroCuotas() : CUOTAS_POR_DEFECTO;
        if (cuotas <= 0) {
            throw new PagoInvalidoException("El número de cuotas debe ser mayor que cero.");
        }

        double cuotaInicial = datos.getCuotaInicial() != null
                ? datos.getCuotaInicial()
                : monto * PORCENTAJE_INICIAL;
        if (cuotaInicial < 0 || cuotaInicial >= monto) {
            throw new PagoInvalidoException("La cuota inicial debe ser mayor o igual a 0 y menor al valor del vehículo.");
        }

        factura.setEntidadFinanciera(datos.getEntidadFinanciera().trim());
        factura.setCuotaInicial(cuotaInicial);
        factura.setNumeroCuotas(cuotas);
        factura.setValorCuota(
                CalculoFinanciero.cuotaMensual(monto - cuotaInicial, cuotas, CalculoFinanciero.TASA_MENSUAL));
    }
}
