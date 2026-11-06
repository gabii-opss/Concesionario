package Servicio;

public class DatosAdicionales {
    private Integer numeroCuotas;
    private String entidadFinanciera;

    public DatosAdicionales() {}

    public DatosAdicionales(Integer numeroCuotas, String entidadFinanciera) {
        this.numeroCuotas = numeroCuotas;
        this.entidadFinanciera = entidadFinanciera;
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public String getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public void setEntidadFinanciera(String entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }
}