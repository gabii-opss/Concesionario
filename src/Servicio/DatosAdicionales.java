package Servicio;

/**
 * Datos opcionales que dependen de la forma de pago (cuotas, entidad financiera
 * y cuota inicial). Cada {@link Pagos.EstrategiaPago} toma solo los que necesita.
 */
public class DatosAdicionales {

    private Integer numeroCuotas;
    private String entidadFinanciera;
    private Double cuotaInicial;

    public DatosAdicionales() {
    }

    public DatosAdicionales(Integer numeroCuotas, String entidadFinanciera) {
        this(numeroCuotas, entidadFinanciera, null);
    }

    public DatosAdicionales(Integer numeroCuotas, String entidadFinanciera, Double cuotaInicial) {
        this.numeroCuotas = numeroCuotas;
        this.entidadFinanciera = entidadFinanciera;
        this.cuotaInicial = cuotaInicial;
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public String getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public Double getCuotaInicial() {
        return cuotaInicial;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public void setEntidadFinanciera(String entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public void setCuotaInicial(Double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }
}
