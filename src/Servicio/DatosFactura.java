package Servicio;

import java.time.LocalDate;

public class DatosFactura {
    private final String tipoFactura;
    private final String nombreCliente;
    private final String nombreAsesor;
    private final String infoVehiculo;
    private final LocalDate fecha;
    private final double montoTotal;
    private Integer numeroCuotas;
    private Double valorCuota;
    private Double cuotaInicial;
    private String entidadFinanciera;

    public DatosFactura(String tipoFactura, String nombreCliente, String nombreAsesor,
                        String infoVehiculo, LocalDate fecha, double montoTotal) {
        this.tipoFactura = tipoFactura;
        this.nombreCliente = nombreCliente;
        this.nombreAsesor = nombreAsesor;
        this.infoVehiculo = infoVehiculo;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
    }

    public String getTipoFactura() { return tipoFactura; }
    public String getNombreCliente() { return nombreCliente; }
    public String getNombreAsesor() { return nombreAsesor; }
    public String getInfoVehiculo() { return infoVehiculo; }
    public LocalDate getFecha() { return fecha; }
    public double getMontoTotal() { return montoTotal; }
    public Integer getNumeroCuotas() { return numeroCuotas; }
    public Double getValorCuota() { return valorCuota; }
    public Double getCuotaInicial() { return cuotaInicial; }
    public String getEntidadFinanciera() { return entidadFinanciera; }

    public void setNumeroCuotas(Integer numeroCuotas) { this.numeroCuotas = numeroCuotas; }
    public void setValorCuota(Double valorCuota) { this.valorCuota = valorCuota; }
    public void setCuotaInicial(Double cuotaInicial) { this.cuotaInicial = cuotaInicial; }
    public void setEntidadFinanciera(String entidadFinanciera) { this.entidadFinanciera = entidadFinanciera; }
}