package Pagos;

/**
 * Utilidad con las fórmulas financieras compartidas por las estrategias de
 * crédito y leasing (evita duplicar el cálculo en cada una).
 */
public final class CalculoFinanciero {

    /** Interés mensual aplicado a crédito y leasing (2 %). */
    public static final double TASA_MENSUAL = 0.02;

    private CalculoFinanciero() {
    }

    /**
     * Cuota fija mensual (sistema francés): C = M * [i(1+i)^n] / [(1+i)^n - 1]
     *
     * @param monto        valor financiado
     * @param numeroCuotas número de cuotas (debe ser mayor que 0)
     * @param tasaMensual  tasa de interés mensual (0.02 = 2 %)
     */
    public static double cuotaMensual(double monto, int numeroCuotas, double tasaMensual) {
        if (numeroCuotas <= 0) {
            throw new PagoInvalidoException("El número de cuotas debe ser mayor que cero.");
        }
        if (tasaMensual == 0) {
            return monto / numeroCuotas;
        }
        double factor = Math.pow(1 + tasaMensual, numeroCuotas);
        return monto * (tasaMensual * factor) / (factor - 1);
    }
}
