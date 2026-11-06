package Servicio;

import concesionarioo.Compra;

/**
 * Calculadora de pagos completa y corregida
 */
public class CalculadoraPago implements iCalculadoraPago {
    private static final double PORCENTAJE_INICIAL_LEASING = 0.10;
    private static final double TASA_INTERES_CREDITO = 0.02; // 2% mensual

    @Override
    public DatosFactura calcularFactura(Compra compra, DatosAdicionales datos) {
        String tipoPago = compra.getTipoPago().toUpperCase();
        DatosFactura factura = crearFacturaBase(compra);

        switch (tipoPago) {
            case "CONTADO":
                calcularContado(factura);
                break;
            case "CREDITO":
            case "CRÉDITO":
                calcularCredito(factura, compra.getVehiculo().getCosto(), datos);
                break;
            case "LEASING":
                calcularLeasing(factura, compra.getVehiculo().getCosto(), datos);
                break;
            default:
                calcularContado(factura);
        }

        return factura;
    }

    private DatosFactura crearFacturaBase(Compra compra) {
        String nombreCompleto = compra.getCliente().getNombre() + " " +
                compra.getCliente().getApellido();
        String infoVehiculo = compra.getVehiculo().getMarca() + " " +
                compra.getVehiculo().getModelo();

        return new DatosFactura(
                compra.getTipoPago(),
                nombreCompleto,
                compra.getAsesor().getNombre(),
                infoVehiculo,
                compra.getFecha(),
                compra.getVehiculo().getCosto()
        );
    }

    private void calcularContado(DatosFactura factura) {
        // Para pago de contado no hay cálculos adicionales
    }

    private void calcularCredito(DatosFactura factura, double monto, DatosAdicionales datos) {
        if (datos == null || datos.getNumeroCuotas() == null || datos.getNumeroCuotas() <= 0) {
            // Valores por defecto
            factura.setNumeroCuotas(12);
            factura.setValorCuota(calcularCuotaConInteres(monto, 12, TASA_INTERES_CREDITO));
        } else {
            int cuotas = datos.getNumeroCuotas();
            factura.setNumeroCuotas(cuotas);
            factura.setValorCuota(calcularCuotaConInteres(monto, cuotas, TASA_INTERES_CREDITO));
        }
    }

    private void calcularLeasing(DatosFactura factura, double monto, DatosAdicionales datos) {
        // Entidad financiera
        if (datos != null && datos.getEntidadFinanciera() != null) {
            factura.setEntidadFinanciera(datos.getEntidadFinanciera());
        } else {
            factura.setEntidadFinanciera("Por definir");
        }

        // Cuota inicial (10%)
        double cuotaInicial = monto * PORCENTAJE_INICIAL_LEASING;
        factura.setCuotaInicial(cuotaInicial);

        // Saldo a financiar
        double saldoFinanciar = monto - cuotaInicial;

        // Calcular cuotas - CORRECCIÓN DEL BUG
        if (datos != null && datos.getNumeroCuotas() != null && datos.getNumeroCuotas() > 0) {
            int cuotas = datos.getNumeroCuotas();
            factura.setNumeroCuotas(cuotas); // CORREGIDO: era Integer.BYTES
            factura.setValorCuota(calcularCuotaConInteres(saldoFinanciar, cuotas, TASA_INTERES_CREDITO));
        } else {
            // Valores por defecto: 36 cuotas
            factura.setNumeroCuotas(36);
            factura.setValorCuota(calcularCuotaConInteres(saldoFinanciar, 36, TASA_INTERES_CREDITO));
        }
    }

    /**
     * Calcula la cuota mensual con interés compuesto
     * Fórmula: C = M * [i(1+i)^n] / [(1+i)^n - 1]
     */
    private double calcularCuotaConInteres(double monto, int numeroCuotas, double tasaMensual) {
        if (numeroCuotas == 0) return 0;
        if (tasaMensual == 0) return monto / numeroCuotas;

        double factorInteres = Math.pow(1 + tasaMensual, numeroCuotas);
        return monto * (tasaMensual * factorInteres) / (factorInteres - 1);
    }
}